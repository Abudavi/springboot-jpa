package com.david.springboot.app.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.david.springboot.app.springboot_jpa.entities.Person;
import java.util.List;
import java.util.Optional;

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

    Optional<Person> findById(Long id);

    @Query("Select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("Select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("Select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

}
