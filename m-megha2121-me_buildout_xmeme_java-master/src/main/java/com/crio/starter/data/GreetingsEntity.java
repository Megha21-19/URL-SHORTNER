package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "memes")
@NoArgsConstructor
public class GreetingsEntity {
    
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private int id;
    private String name;
    private String caption;
    private String url;
    
    
}