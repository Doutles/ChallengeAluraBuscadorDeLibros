package com.example.challengeBuscadorDeLibros.Main;

import com.example.challengeBuscadorDeLibros.Model.Author;
import com.example.challengeBuscadorDeLibros.Model.DatosLibro;
import com.example.challengeBuscadorDeLibros.Model.Libro;
import com.example.challengeBuscadorDeLibros.repository.LibroRepository;
import com.example.challengeBuscadorDeLibros.repository.AuthorRepository;
import com.example.challengeBuscadorDeLibros.service.ConvierteDatos;
import com.example.challengeBuscadorDeLibros.service.RequestAPI;

import java.util.List;
import java.util.Scanner;


public class Main {
    private final RequestAPI requestAPi = new RequestAPI();
    private final Scanner scanner = new Scanner(System.in);
    private final ConvierteDatos convierteDatos = new ConvierteDatos();
    private final LibroRepository libroRepository;
    private final AuthorRepository authorRepository;
    private List<Libro> libros;
    private List<Author> autores;

    public Main(LibroRepository libroRepository, AuthorRepository authorRepository){
        this.libroRepository = libroRepository;
        this.authorRepository = authorRepository;
    }


    // Mostrar el menu en consola
    public void showMenu()
    {
        var opcion = -1;
        while (opcion != 0){
            var menu ="""
                    **************************************************
                               Busqueda de Libros y Autores
                    **************************************************
                    
                    Selecciona una opcion acontinuacion: 
                    
                    1 - Buscar un libro
                    2 - Consultar libros buscados
                    3 - Consultar autores
                    4 - Consultar autores de un año especifico
                    5 - Consultar libros por lenguaje
                     
                    0 - Salir               
                    """;

            try {
                System.out.println(menu);
                opcion = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){

                System.out.println("Ingrese una opción valida");
            }

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    consultarLibros();
                    break;
                case 3:
                    consultarAutores();
                    break;
                case 4:
                    consultarAutoresPorAno();
                    break;
                case 5:
                    consultarLibrosLenguaje();
                    break;
                case 0:
                    System.out.println("Gracias por consultar");
                    break;
                default:
                    System.out.println("Ingresa una valor valida");
            }
        }
    }

    // Extrae los datos de un libro
    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro");
        var busqueda = scanner.nextLine().toLowerCase().replace(" ","%20");
        String urlBase = "https://gutendex.com/books/";
        var json = requestAPi.getData(urlBase +
                "?search=" +
                busqueda);

        return convierteDatos.obtenerDatos(json, DatosLibro.class);
    }

    // Busca un libro y guarda infromacion en la BD en sus tablas correspondientes
    private void buscarLibro()
    {
        DatosLibro datosLibro = getDatosLibro();

        try {
            Libro libro = new Libro(datosLibro.resultados().get(0));
            Author author = new Author(datosLibro.resultados().get(0).autorList().get(0));

            System.out.printf("""
                            libro[
                                titulo: %s
                                author: %s
                                lenguaje: %s
                                descargas: %s
                            ]
                            %n""", libro.getTitulo(),
            libro.getAutor(),
            libro.getLenguaje(),
            libro.getDescargas().toString());

            libroRepository.save(libro);
            authorRepository.save(author);

        }catch (Exception e){
            System.out.println("no se encontro ese libro");
        }

    }

    // Trae los libros guardados en la BD
    private void consultarLibros() {
        libros = libroRepository.findAll();
        libros.forEach(l -> {
            System.out.printf("""
                                Titulo: %s
                                Author: %s
                                Lenguaje: %s
                                Descargas: %s
                            %n""", l.getTitulo(),
            l.getAutor(),
            l.getLenguaje(),
            l.getDescargas().toString());
        });
    }

    // Trae todos los autores de los libros consultados en la BD
    private void consultarAutores() {
        autores = authorRepository.findAll();
        autores.forEach(a -> {
            System.out.printf("""
                                Autor: %s
                                Año de nacimiento: %s
                                Año de muerte: %s
                            %n""", a.getAutor(),
            a.getNacimiento().toString(),
            a.getDefuncion().toString());
        });
    }

    // Trae a los autores apartir de cierto año
    public void consultarAutoresPorAno()
    {
        System.out.println("Ingresa el año a partir del cual buscar:");
        var anoBusqueda = scanner.nextInt();
        scanner.nextLine();

        List<Author> authors = authorRepository.autorPorFecha(anoBusqueda);
        authors.forEach( a -> {
            System.out.printf("""
                    Nombre: %s
                    Fecha de nacimiento: %s
                    Fecha de defuncion: %s
                    %n""", a.getAutor(),a.getNacimiento().toString(),a.getDefuncion().toString());
        });
    }


    private void consultarLibrosLenguaje()
    {
        System.out.println("""
                ****************************************************************    
                    Selcciona el lenguaje de los libros que deseas consultar
                ****************************************************************
                1 - en (Ingles)
                2 - es (Español)
                """);

        try {

            var opcion2 = scanner.nextInt();
            scanner.nextLine();

            switch (opcion2) {
                case 1:
                    libros = libroRepository.findByLenguaje("en");
                    break;
                case 2:
                    libros = libroRepository.findByLenguaje("es");
                    break;

                default:
                    System.out.println("Ingresa una opcion valida");
            }

            libros.forEach(l -> {
                System.out.printf("""
                                    Titulo: %s
                                    Author: %s
                                    Lenguaje: %s
                                    Descargas: %s
                                %n""", l.getTitulo(),
                    l.getAutor(),
                    l.getLenguaje(),
                    l.getDescargas().toString());
            });

        } catch (Exception e){
            System.out.println("Ingresa un valor valido");
        }
    }


}
