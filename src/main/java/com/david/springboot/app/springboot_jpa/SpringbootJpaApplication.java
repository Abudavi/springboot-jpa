package com.david.springboot.app.springboot_jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.david.springboot.app.springboot_jpa.entities.Person;
import com.david.springboot.app.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Consulta:");
		findOne();

	}

	public void findOne() {

		/*
		 * Person person = null;
		 * Optional<Person> optionalPerson = repository.findById(1L);
		 * if (optionalPerson.isPresent()) {
		 * person = optionalPerson.get();
		 * }
		 * System.err.println(person);
		 */
		repository.findById(1L).ifPresent(System.out::println);

	}

	public void list() {
		// List<Person> persons = (List<Person>) repository.findAll();
		List<Person> persons = (List<Person>) repository.findByProgramingLanguageAndName("Java", "Andres");
		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsValue = repository.obtenerPersoData();

		personsValue.stream().forEach(person -> System.out.println(person[0] + " Es experto en " + person[1]));

		List<Object[]> lastname = repository.obtenerPersonaLastname("Guzman");
		lastname.stream().forEach(person -> System.out.println(person[0]));

	}
}
