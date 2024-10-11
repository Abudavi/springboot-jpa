package com.david.springboot.app.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.david.springboot.app.springboot_jpa.entities.Person;
import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByProgramingLanguage(String programingLanguage);

    @Query("select p from Person p where p.programingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgramingLanguageAndname(String programingLanguage, String name);

    List<Person> findByProgramingLanguageAndName(String programingLanguage, String name);

    @Query("Select p.name, p.programingLanguage from Person p")
    List<Object[]> obtenerPersoData();

    @Query("select p from Person p where p.lastname=?1")
    List<Object[]> obtenerPersonaLastname(String lastname);

    @Query("Select p.name from Person p")
    List<Object[]> obtenerPersoName();
}
