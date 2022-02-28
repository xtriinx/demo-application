package com.ttamma.demoapplication.repository;

import com.ttamma.demoapplication.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> { }
