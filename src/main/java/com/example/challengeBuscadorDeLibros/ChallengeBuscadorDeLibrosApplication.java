package com.example.challengeBuscadorDeLibros;

import com.example.challengeBuscadorDeLibros.Main.Main;
import com.example.challengeBuscadorDeLibros.repository.AuthorRepository;
import  com.example.challengeBuscadorDeLibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeBuscadorDeLibrosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(
				ChallengeBuscadorDeLibrosApplication.class, args);


	}

//	@Autowired
//	private LibroRepository repository;
//	@Autowired
//	private AuthorRepository authorRepository;
//	@Override
//	public void run(String... args) throws Exception {
//		// Crear una instancia de la clase main y llamar al método showMenu
//		main principal = new main(repository, authorRepository);
//		principal.showMenu();
//	}

	@Autowired
	private LibroRepository repository;
	@Autowired
	private AuthorRepository authorRepository;
	public void run(String[] args) {
		Main principal = new Main(repository,authorRepository);
		principal.showMenu();
	}


}
