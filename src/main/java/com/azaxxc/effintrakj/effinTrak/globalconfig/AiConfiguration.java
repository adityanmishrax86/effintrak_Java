package com.azaxxc.effintrakj.effinTrak.globalconfig;

import org.springframework.context.annotation.Configuration;

/**
 * Spring AI Configuration
 * ChatClient is now created directly in ChatService with proper ChatModel injection
 */
@Configuration
public class AiConfiguration {
    // Configuration handled via application.properties
    // ChatClient is instantiated in ChatService with ChatModel dependency injection
}
