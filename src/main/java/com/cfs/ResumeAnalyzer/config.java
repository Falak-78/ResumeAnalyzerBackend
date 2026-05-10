package com.cfs.ResumeAnalyzer;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    public ChatClient chatClient(OpenAiChatModel chatModel) {
        return ChatClient.create(chatModel);
    }
    /*
    @Bean
    public ChatClient chatClient(OllamaChatModel chatModel) {
        return ChatClient.create(chatModel);
    }
    */

}