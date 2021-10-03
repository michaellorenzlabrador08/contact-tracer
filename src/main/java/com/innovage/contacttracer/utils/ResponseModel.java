package com.innovage.contacttracer.utils;

public class ResponseModel {
    private Boolean success;
    private Object response;
    private String message;


    public ResponseModel (){

    }
    public ResponseModel(Boolean success, Object response, String message) {
        this.success = success;
        this.response = response;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
