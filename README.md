📚 Buscador de Libros y Autores
Este proyecto implementa un programa de consola en Java para buscar información sobre libros y autores utilizando la API pública de Gutendex. Permite realizar búsquedas, consultar libros almacenados en la base de datos y filtrar autores por diversos criterios.

🚀 Características
El programa presenta el siguiente menú interactivo:

Buscar un libro: Busca un libro por nombre y guarda su información en la base de datos.
Consultar libros buscados: Muestra todos los libros previamente almacenados.
Consultar autores: Lista los autores de los libros consultados.
Consultar autores de un año específico: Muestra autores nacidos a partir de un año ingresado.
Consultar libros por lenguaje: Filtra los libros por idioma (Inglés o Español).
Salir: Finaliza el programa.
🛠️ Requisitos
Java Development Kit (JDK) 11+
Maven (para manejar dependencias)
Base de datos compatible con JPA (Ej. MySQL o H2 para desarrollo local).
⚙️ Configuración e Instalación
Clonar el repositorio:

bash
Copiar código
git clone https://github.com/tu_usuario/buscador-libros.git
cd buscador-libros
Configurar la base de datos:
Ajusta el archivo application.properties para configurar tu base de datos (JDBC URL, usuario y contraseña).


💻 Uso del Programa
Al iniciar el programa, aparecerá el siguiente menú en la consola:

text
Copiar código
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
Buscar un libro:
Escribe el título del libro a buscar. La información del libro y su autor se almacenarán en la base de datos.
Consultar libros buscados:
Lista todos los libros guardados con sus detalles: título, autor, lenguaje y descargas.
Consultar autores:
Muestra información sobre autores de libros almacenados.
Consultar autores de un año específico:
Escribe un año y filtra los autores nacidos a partir de esa fecha.
Consultar libros por lenguaje:
Filtra libros por idioma:
1: Inglés
2: Español
🌐 API Utilizada
Gutendex API:
Documentación oficial: Gutendex API
🧩 Dependencias Principales
Spring Boot: Manejo de aplicaciones y acceso a datos.
JPA: Persistencia de datos.
Jackson: Conversión de JSON a Java Objects.
📄 Clases Principales
Main: Controla el flujo del programa y muestra el menú.
RequestAPI: Realiza solicitudes a la API de Gutendex.
ConvierteDatos: Convierte la respuesta JSON en objetos Java.
LibroRepository y AuthorRepository: Repositorios para interactuar con la base de datos.
DatosLibro, Libro, y Author: Representan los datos del libro y autor
