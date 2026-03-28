package com.yeyao.AiServices;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

//@AiService(
//        wiringMode = AiServiceWiringMode.EXPLICIT, // 手动装配
//        chatModel = "openAiChatModel" // 指定模型
//)
@AiService
public interface ConsultantService {
    // 聊天
    public abstract String chat(String message);
}
