package com.david.springboot.app.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		create();

	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nombre");
		String name = scanner.nextLine();
		System.out.println("Apellido");
		String lastname = scanner.nextLine();
		System.out.println("Lenguaje de programacion");
		String programingLanguage = scanner.nextLine();
		scanner.close();
		Person person = new Person(null, name, lastname, programingLanguage);

		Person personnew = repository.save(person);
		System.out.println(personnew);

		repository.findById(personnew.getId()).ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void findOne() {

		/*
		 * Person person = null;
		 * Optional<Person> optionalPerson = repository.findById(1L);
		 * if (optionalPerson.isPresent()) {
		 * person = optionalPerson.get();
		 * }
		 * System.err.println(person);
		 */
		repository.findOne(1L).ifPresent(System.out::println);
		repository.findOneName("Pepe").ifPresent(System.out::println);
		repository.findOneLikeName("Ma").ifPresent(System.out::println);
		repository.findByNameContaining("fa").ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
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
