package com.yeyao.AiServices;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT, // 手动装配
        chatModel = "openAiChatModel", // 指定模型
        streamingChatModel = "openAiStreamingChatModel"
)
//@AiService
public interface ConsultantService {
    // 聊天   --- 阻塞式调用
    // public abstract String chat(String message);

    // 聊天   --- 流式调用
    Flux<String> chat(String message);
}
