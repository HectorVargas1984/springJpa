package com.harry.springbootjpa.response;

public class ClienteResponseRest extends ResposeRest{

    private ClienteResponse clienteResponse = new ClienteResponse();

    public ClienteResponse getClienteResponse() {
        return clienteResponse;
    }

    public void setClienteResponse(ClienteResponse clienteResponse) {
        this.clienteResponse = clienteResponse;
    }
}
