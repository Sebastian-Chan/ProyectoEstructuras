package com.alogritmos;

public class Nodo<T>{
    // Para listas simples y circulares
    protected T dato;
    protected Nodo<T> siguiente;
    
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    // Getters y Setters
    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }
    public Nodo<T> getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo<T> siguiente) { this.siguiente = siguiente; }
}