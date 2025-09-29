package com.example.contatos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class DataFolderConfig {

    @Bean
    public boolean createDataFolder(){
        File folder = new File("./data");
        if(!folder.exists()){
            folder.mkdirs();
        }
        return true;
    }

}
