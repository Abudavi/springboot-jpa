package com.david.springboot.app.springboot_jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.david.springboot.app.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
