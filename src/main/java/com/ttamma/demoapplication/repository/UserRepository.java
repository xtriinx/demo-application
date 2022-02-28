package com.ttamma.demoapplication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ttamma.demoapplication.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	 
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);
}
