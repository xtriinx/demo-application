package com.ttamma.demoapplication.services;

import com.ttamma.demoapplication.model.Country;
import com.ttamma.demoapplication.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServices {
    @Autowired
    private CountryRepository repo;

    public List<Country> listAllCountries(){
        return (List<Country>) repo.findAll();
    }
}
