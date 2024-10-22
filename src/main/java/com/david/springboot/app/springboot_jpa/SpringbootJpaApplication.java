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
		personalizeQueries();

	}

	@Transactional(readOnly = true)
	public void personalizeQueries() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("==============Consulta nombre por id ==============");
		System.out.println("Ingrese el id que desea para optener el nombre");
		Long id = scanner.nextLong();
		scanner.close();
		String name = repository.getNameById(id);
		System.out.println(name);

	}

	@Transactional
	public void delete() {
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar");
		Long id = scanner.nextLong();

		repository.deleteById(id);
		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void delete2() {
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar");
		Long id = scanner.nextLong();

		Optional<Person> perOptional = repository.findById(id);
		perOptional.ifPresentOrElse(person -> repository.delete(person), () -> System.out.println("El id no existe"));
		repository.findAll().forEach(System.out::print);
		/*
		 * esto es lo equivalente de lo de arriba pero con un if
		 * if (perOptional.isPresent()) {
		 * Person p = perOptional.orElseThrow();
		 * repository.deleteById(p.getId());
		 * repository.findAll().forEach(System.out::println);
		 * } else {
		 * System.out.println("El id no existe");
		 * }
		 */

		scanner.close();
	}

	@Transactional
	public void update() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a la persona a editar");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);

		/* optionalPerson.ifPresent(person -> { */
		if (optionalPerson.isPresent()) {
			Person person = optionalPerson.orElseThrow();
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programacion");
			String programigLanguage = scanner.next();
			person.setProgramingLanguage(programigLanguage);
			Person personDb = repository.save(person);
			System.out.println(personDb);

		} else {
			System.out.println("El usuario no existe !");
		}

		// });

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
