package com.harry.springbootjpa.response;

import com.harry.springbootjpa.model.Cliente;

import java.util.List;

public class ClienteResponse {
    List<Cliente> cliete;

    List<String> Error;

    public List<String> getError() {
        return Error;
    }

    public void setError(List<String> error) {
        Error = error;
    }

    public List<Cliente> getCliete() {
        return cliete;
    }

    public void setCliete(List<Cliente> cliete) {
        this.cliete = cliete;
    }
}
