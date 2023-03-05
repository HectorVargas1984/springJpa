package com.harry.springbootjpa.service;

import com.harry.springbootjpa.dao.IClientDao;
import com.harry.springbootjpa.model.Cliente;

import com.harry.springbootjpa.response.ClienteResponseRest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteSericeImpl implements IClienteService {

   /* para trabajar con entity manager de jpa
   @PersistenceContext
   private EntityManager em;
   */

    private IClientDao iClientDao;

    public ClienteSericeImpl(IClientDao iclienteDao) {
        this.iClientDao = iclienteDao;
    }


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ClienteResponseRest> listClient(Integer page) {
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {
            Pageable pageRequest = PageRequest.of(page, 4);
            Page<Cliente> clientList = iClientDao.findAll(pageRequest);

            var totalPaginas = clientList.getTotalPages();
            clientList.forEach(list::add);


            response.getClienteResponse().setCliete(list);
            response.setMetadata("Respuesta Ok", "00", "Listado de clientes ok", totalPaginas);

        } catch (Exception e) {
            response.setMetadata("Respuesta error", "-1", "Error al listar los clientes ", 0);
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> saveClient(Cliente cliente) {

        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {
            var clienteSave = iClientDao.save(cliente);

            list.add(clienteSave);
            response.getClienteResponse().setCliete(list);
            response.setMetadata("Respuesta Ok", "00", "Listado de clientes ok", 0);

        } catch (Exception e) {
            response.setMetadata("Respuesta error", "-1", "Error al listar los clientes ", 0);
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> updateClient(Cliente cliente, Long id) {

        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {

            Optional<Cliente> clientSearch = iClientDao.findById(id);

            if (clientSearch.isPresent()) {


                clientSearch.get().setNombre(cliente.getNombre());
                clientSearch.get().setApellido(cliente.getApellido());
                clientSearch.get().setEmail(cliente.getEmail());
                clientSearch.get().setCreateAt(cliente.getCreateAt());

                Cliente clienteUpdate = iClientDao.save(clientSearch.get());

                list.add(clienteUpdate);
                response.getClienteResponse().setCliete(list);
                response.setMetadata("Respuesta ok", "00", "Cliente actualizado correctamente", 0);

            } else {
                response.setMetadata("Respuesta fallida", "-1", "Cliente no encontrado", 0);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            }

        } catch (Exception e) {
            response.setMetadata("Respuesta error", "-1", "Error al buscar en la base de datos", 0);
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> delete(Long id) {

        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {

            Optional<Cliente> clientSearch = iClientDao.findById(id);

            if (clientSearch.isPresent()) {
                iClientDao.delete(clientSearch.get());

                list.add(clientSearch.get());
                response.getClienteResponse().setCliete(list);
                response.setMetadata("Respuesta ok", "00", "Cliente Eliminado con exito", 0);
            } else {
                response.setMetadata("Respuesta fallida", "-1", "Cliente no encontrado", 0);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta error", "-1", "Error al buscar en la base de datos", 0);
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> listClietPage(Pageable pageable) {
        return iClientDao.findAll(pageable);
    }


}
