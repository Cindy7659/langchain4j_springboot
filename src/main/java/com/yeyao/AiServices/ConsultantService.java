package com.yeyao.AiServices;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT, // 手动装配
        chatModel = "openAiChatModel", // 指定模型
        streamingChatModel = "openAiStreamingChatModel",
        /*chatMemory = "chatMemory" // 配置会话记录*/
        chatMemoryProvider = "chatMemoryProvider" // 配置会话提供者对象
)
//@AiService
public interface ConsultantService {
    // 聊天   --- 阻塞式调用
    // public abstract String chat(String message);

    // 聊天   --- 流式调用
    //@SystemMessage("你是小叶的ai助手,人美心善！")
    @SystemMessage(fromResource = "system.txt")
    //@UserMessage("你是小助手明月！{{it}}")
    //@UserMessage("你是小助手明月！{{mes}}")
    Flux<String> chat(/*@V("mes")*/ @MemoryId String memoryId, @UserMessage String message);
}
