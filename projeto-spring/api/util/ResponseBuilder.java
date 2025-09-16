package com.example.contatos.util;

import com.example.contatos.models.ResponseModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseBuilder {

    // Response listando um único objeto
    public static ResponseModel buildResponse(Object singleObject) {
        return new ResponseModel(List.of(singleObject), LocalDateTime.now(), new ArrayList<>());
    }

    // Response listando vários objetos (Sobrecarga)
    public static ResponseModel buildResponse(List<?> data) {
        return new ResponseModel(data, LocalDateTime.now(), new ArrayList<>());
    }

    // Response com lista de dados (data) vazia
    public static ResponseModel buildEmptyDataResponse(){
        return new ResponseModel(new ArrayList<>(), LocalDateTime.now(), new ArrayList<>());
    }

    // Response bad request sem mensagens de erro
    public static ResponseModel buildBadRequestResponse(){
        return new ResponseModel(new ArrayList<>(), LocalDateTime.now(), new ArrayList<>());
    }

    // Response bad request com mensagens de erro (exemplo: mensagens vindas do Jakarta Validation)
    public static ResponseModel buildBadRequestResponse(List<?> messages){
        return new ResponseModel(new ArrayList<>(), LocalDateTime.now(), messages);
    }
}
