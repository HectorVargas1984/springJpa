package com.harry.springbootjpa.service;



import com.harry.springbootjpa.model.Cliente;
import com.harry.springbootjpa.response.ClienteResponseRest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface IClienteService {
    ResponseEntity<ClienteResponseRest> listClient(Integer page);

    Page<Cliente> listClietPage(Pageable pageable);

    ResponseEntity<ClienteResponseRest> saveClient(Cliente cliente);

    ResponseEntity<ClienteResponseRest> updateClient(Cliente cliente, Long id);

    ResponseEntity<ClienteResponseRest> delete(Long id);


}
