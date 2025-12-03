package com.alogritmos;

// INTERFAZ QUE DEFINE LOS MÉTODOS BÁSICOS PARA ESTRUCTURAS DE DATOS
public interface EstructuraDatos<T> {
    boolean agregar(T elemento);
    boolean eliminar(T elemento);
    T buscar(T elemento);
    boolean contiene(T elemento);
    int tamaño();
    boolean estaVacia();
    void limpiar();
    void mostrar();
}

