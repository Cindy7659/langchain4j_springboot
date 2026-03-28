package com.yeyao.Config;

import com.yeyao.AiServices.ConsultantService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.IngestionResult;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommonConfig {

    // 引入自定义的会话记录存储对象
    @Autowired
    private ChatMemoryStore redisChatMemoryStore;

    // 引入向量模型
    @Autowired
    private EmbeddingModel embeddingModel;

/*    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Bean
    public ConsultantService consultantService() {
        return AiServices.builder(ConsultantService.class)
                .chatModel(openAiChatModel)
                .build();
    }*/

    // 构建会话记忆对象
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
    }

    // 创建会话记忆对象提供者
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(20)
                        .chatMemoryStore(redisChatMemoryStore)
                        .build();
            }
        };
    }

    // 向量数据库存储
    // 会自动注入一个embeddingStore对象，不能重复，所以这里我们改成store
    @Bean
    public EmbeddingStore<TextSegment> store() {
        // 加载文档进内存
        //List<Document> contents = ClassPathDocumentLoader.loadDocuments("content");
        List<Document> contents = ClassPathDocumentLoader.loadDocuments("pdf", new ApachePdfBoxDocumentParser());
        // 构建向量数据库操作对象
        InMemoryEmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();
        // 构建文档分割器对象
        DocumentSplitter recursive = DocumentSplitters.recursive(500, 100);
        // 构建对象，完成文本数据切割，向量化，存储
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .documentSplitter(recursive)
                .embeddingModel(embeddingModel)
                .build();
        ingestor.ingest(contents);
        return store;
    }

    // 向量数据库检索
    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore<TextSegment> embeddingStore) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .minScore(0.5)
                .maxResults(3)
                .embeddingModel(embeddingModel)
                .build();
    }
}
