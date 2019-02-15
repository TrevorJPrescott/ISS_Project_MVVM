package com.trevorpc.newstart.model.object;



import java.util.List;


public class FullResponse {


    private String message;

    private Request request;

    private List<Response> response = null;

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public List<Response> getResponse() {
        return response;
    }
}