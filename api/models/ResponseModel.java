package com.example.contatos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel {

    private List<?> data;
    private List<?> messages;
    private LocalDateTime timestamp;

    public ResponseModel(List<?> data, LocalDateTime timestamp, List<?> messages) {
        this.data = data;
        this.timestamp = timestamp;
        this.messages = messages;
    }
}