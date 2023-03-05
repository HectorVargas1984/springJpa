package com.harry.springbootjpa.dao;

import com.harry.springbootjpa.model.Cliente;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface IClientDao extends PagingAndSortingRepository<Cliente, Long> {
}
