package com.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanagement.model.client.Client;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long>{

}
