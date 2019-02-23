package com.example.spring_mvc_demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This provides POJO <==> JSON support and some custom configuration for proper support of java.time data types
 * and certain serialization/deserialization features
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {

        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // pretty-print data
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        /*
         * This has to be explicitly disabled, otherwise Jackson will lose time zone offsets
         * when deserializing OffsetDateTime instances
         */
        objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        return objectMapper;
    }
}
