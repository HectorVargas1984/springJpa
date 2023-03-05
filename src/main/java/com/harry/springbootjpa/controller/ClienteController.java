package com.harry.springbootjpa.controller;

import com.harry.springbootjpa.service.IClienteService;
import com.harry.springbootjpa.model.Cliente;
import com.harry.springbootjpa.response.ClienteResponseRest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(originPatterns = {"http://localhost:4200"})
@Controller
@RequestMapping("/api/v1")
public class ClienteController {

    private IClienteService clientesDao;

    ClienteController(IClienteService clienteDao){
        this.clientesDao = clienteDao;
    }


    @GetMapping("/clientes/{page}")
    public Page<Cliente> index(@PathVariable Integer page) {
        return clientesDao.listClietPage(PageRequest.of(page, 4));
    }

    @GetMapping("/cliente/page/{page}")
    public ResponseEntity<ClienteResponseRest> listadoCliente(@PathVariable Integer page){
        ResponseEntity<ClienteResponseRest> response = clientesDao.listClient(page);
        return response ;
    }

    @PostMapping("/cliente")
    public ResponseEntity<ClienteResponseRest> saveCliente(@Valid @RequestBody Cliente cliente, BindingResult result){

        ClienteResponseRest responseErr = new ClienteResponseRest();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            responseErr.getClienteResponse().setError(errors);
            responseErr.setMetadata("Error de datos", "-1", "Datos ingresados no validos", 0);

            return new ResponseEntity<>(responseErr, HttpStatus.BAD_REQUEST);
        }


        ResponseEntity<ClienteResponseRest> response = clientesDao.saveClient(cliente);
        return response;
    }

    @PutMapping("cliente/{id}")
    public ResponseEntity<ClienteResponseRest> updateClient(@PathVariable Long id, @Valid @RequestBody Cliente cliente, BindingResult result){

        ClienteResponseRest responseErr = new ClienteResponseRest();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            responseErr.getClienteResponse().setError(errors);
            responseErr.setMetadata("Error de datos", "-1", "Datos ingresados no validos", 0);

            return new ResponseEntity<>(responseErr, HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<ClienteResponseRest> response = clientesDao.updateClient(cliente, id);

        return response;
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<ClienteResponseRest> delete(@PathVariable Long id){

        ResponseEntity<ClienteResponseRest> response = clientesDao.delete(id);

        return response;
    }


}
