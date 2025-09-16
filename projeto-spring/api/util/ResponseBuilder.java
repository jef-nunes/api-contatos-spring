package com.example.contatos.util;

import com.example.contatos.models.ResponseModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseBuilder {

    // Guardar constantes para o status do ResponseModel
    private static class ResponseModelStatus {
        public static final String SUCCESS = "success";
        public static final String FAILURE = "failure";
    }

    // Response bem sucedida listando um único objeto
    public static ResponseModel buildSuccessResponse(Object singleObject) {
        return new ResponseModel(ResponseModelStatus.SUCCESS,List.of(singleObject), LocalDateTime.now(), new ArrayList<>());
    }

    // Sobrecarga - Response bem sucedida listando vários objetos
    public static ResponseModel buildSuccessResponse(List<?> data) {
        return new ResponseModel(ResponseModelStatus.SUCCESS,data, LocalDateTime.now(), new ArrayList<>());
    }

    // Response bem sucedida com lista de dados (data) vazia
    public static ResponseModel buildSuccessEmptyDataResponse(){
        return new ResponseModel(ResponseModelStatus.SUCCESS,new ArrayList<>(), LocalDateTime.now(), new ArrayList<>());
    }

    // Response falha com lista de dados (data) vazia
    public static ResponseModel buildFailureEmptyDataResponse(){
        return new ResponseModel(ResponseModelStatus.FAILURE,new ArrayList<>(), LocalDateTime.now(), new ArrayList<>());
    }

    // Response falha, bad request, sem mensagens de erro
    public static ResponseModel buildFailureBadRequestResponse(){
        return new ResponseModel(ResponseModelStatus.FAILURE,new ArrayList<>(), LocalDateTime.now(), new ArrayList<>());
    }

    // Sobrecarga - Response falha, bad request, com mensagens de erro (exemplo: mensagens vindas do Jakarta Validation)
    public static ResponseModel buildFailureBadRequestResponse(List<?> messages){
        return new ResponseModel(ResponseModelStatus.FAILURE,new ArrayList<>(), LocalDateTime.now(), messages);
    }
}
