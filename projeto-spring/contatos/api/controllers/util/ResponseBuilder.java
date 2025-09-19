package com.example.contatos.api.controllers.util;

import com.example.contatos.domain.models.ResponseModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseBuilder {

    // Guardar constantes para o status do ResponseModel
    private static class ResponseModelStatus {
        public static final String SUCCESS = "success";
        public static final String FAILURE = "failure";
    }

    // Response bem sucedida listando objetos de response DTO
    public static ResponseModel buildSuccessResponse(List<?> data) {
        return new ResponseModel(ResponseModelStatus.SUCCESS,data, LocalDateTime.now(), new ArrayList<>());
    }

    // Response falha, com mensagens de erro (exemplo: mensagens vindas do Jakarta Validation)
    public static ResponseModel buildFailureResponse(List<String> messages){
        return new ResponseModel(ResponseModelStatus.FAILURE,new ArrayList<>(), LocalDateTime.now(), messages);
    }
}
