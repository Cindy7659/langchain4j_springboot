package com.yeyao.Config;

import com.yeyao.AiServices.ConsultantService;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CommonConfig {

    //    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Bean
    public ConsultantService consultantService() {
        return AiServices.builder(ConsultantService.class)
                .chatModel(openAiChatModel)
                .build();
    }
}
