package com.javasampleapproach.h2database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.javasampleapproach.h2database.model.Customer;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long>,CrudRepository<Customer, Long>{
    List<Customer> findByLastName(String lastName);
    
    @Override
    List<Customer> findAll();
} 