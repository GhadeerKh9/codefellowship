package com.example.codefellowship.repositories;

import com.example.codefellowship.models.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<Application,Integer> {
    public Application findByUsername(String username);
}