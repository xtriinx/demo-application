package com.ttamma.demoapplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttamma.demoapplication.model.Client;
import com.ttamma.demoapplication.repository.ClientRepository;

@Service
public class ClientServices {
	
	@Autowired
	private ClientRepository repo;

	public List<Client> listAll(long userId){
		return repo.findAllByUserId(userId);
	}

	public void save(Client client) {
		repo.save(client);
	}

	public Client get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
