package com.example.challengeBuscadorDeLibros.service;

public interface IConvierteDatos
{
    <T> T obtenerDatos(String json, Class<T> tClass);
}