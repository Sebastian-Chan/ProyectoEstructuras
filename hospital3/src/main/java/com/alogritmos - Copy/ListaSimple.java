package com.alogritmos;

// LISTA ENLAZADA SIMPLE PARA GESTION DE PACIENTES
public class ListaSimple<T> implements EstructuraDatos<T> {
    private Nodo<T> cabeza;
    private int tamaño;
    
    // CONSTRUCTOR - INICIALIZA LA LISTA VACIA
    public ListaSimple() {
        this.cabeza = null;
        this.tamaño = 0;
    }
    
    // AGREGA UN ELEMENTO AL FINAL DE LA LISTA
    @Override
    public boolean agregar(T elemento) {
        if (cabeza == null) {
            cabeza = new Nodo<>(elemento);
            tamaño++;
            return true;
        }
        
        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        
        actual.setSiguiente(new Nodo<>(elemento));
        tamaño++;
        return true;
    }
    
    // ELIMINA UN ELEMENTO DE LA LISTA
    @Override
    public boolean eliminar(T elemento) {
        if (cabeza == null) return false;
        
        // SI EL PRIMER ELEMENTO ES EL QUE BUSCAMOS
        if (cabeza.getDato().equals(elemento)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return true;
        }
        
        // BUSCAR EN EL RESTO DE LA LISTA
        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(elemento)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        
        return false;
    }
    
    // BUSCA UN ELEMENTO EN LA LISTA
    @Override
    public T buscar(T elemento) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    // VERIFICA SI LA LISTA CONTIENE UN ELEMENTO
    @Override
    public boolean contiene(T elemento) {
        return buscar(elemento) != null;
    }
    
    // RETORNA EL TAMAÑO DE LA LISTA
    @Override
    public int tamaño() {
        return tamaño;
    }
    
    // VERIFICA SI LA LISTA ESTA VACIA
    @Override
    public boolean estaVacia() {
        return cabeza == null;
    }
    
    // LIMPIA TODA LA LISTA
    @Override
    public void limpiar() {
        cabeza = null;
        tamaño = 0;
    }
    
    // MUESTRA TODOS LOS ELEMENTOS DE LA LISTA
    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("LA LISTA ESTA VACIA");
            return;
        }
        
        System.out.print("LISTA: ");
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getSiguiente() != null) {
                System.out.print(" -> ");
            }
            actual = actual.getSiguiente();
        }
        System.out.println(" -> NULL");
    }
}