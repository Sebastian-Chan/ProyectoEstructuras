package com.alogritmos;

public class Cola<T> implements EstructuraDatos<T> {
    // NODO QUE REPRESENTA EL FRENTE DE LA COLA
    private Nodo<T> frente;
    // NODO QUE REPRESENTA EL FINAL DE LA COLA
    private Nodo<T> finalCola;
    // CONTADOR DE ELEMENTOS EN LA COLA
    private int tamaño;
    
    // CONSTRUCTOR - INICIALIZA LA COLA VACÍA
    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamaño = 0;
    }
    
    // AGREGA UN ELEMENTO AL FINAL DE LA COLA (ENQUEUE)
    @Override
    public boolean agregar(T elemento) {
        return encolar(elemento);
    }
    
    // MÉTODO ESPECÍFICO PARA ENCOLAR
    public boolean encolar(T elemento) {
        // CREAR NUEVO NODO
        Nodo<T> nuevo = new Nodo<>(elemento);
        
        // SI LA COLA ESTÁ VACÍA
        if (finalCola == null) {
            frente = finalCola = nuevo;
        } else {
            // AGREGAR EL NUEVO NODO AL FINAL
            finalCola.setSiguiente(nuevo);
            finalCola = nuevo;
        }
        
        tamaño++;
        return true;
    }
    
    // REMUEVE Y RETORNA EL ELEMENTO DEL FRENTE (DEQUEUE)
    public T desencolar() {
        // SI LA COLA ESTÁ VACÍA
        if (frente == null) {
            return null;
        }
        
        // OBTENER EL DATO DEL FRENTE
        T dato = frente.getDato();
        // EL FRENTE AHORA ES EL SIGUIENTE NODO
        frente = frente.getSiguiente();
        
        // SI LA COLA QUEDÓ VACÍA
        if (frente == null) {
            finalCola = null;
        }
        
        tamaño--;
        return dato;
    }
    
    // RETORNA EL ELEMENTO DEL FRENTE SIN REMOVERLO
    public T verFrente() {
        if (frente == null) {
            return null;
        }
        return frente.getDato();
    }
    
    // ELIMINA UN ELEMENTO ESPECÍFICO DE LA COLA
    @Override
    public boolean eliminar(T elemento) {
        if (frente == null) return false;
        
        // SI EL PRIMER ELEMENTO ES EL QUE BUSCAMOS
        if (frente.getDato().equals(elemento)) {
            desencolar();
            return true;
        }
        
        // BUSCAR EN EL RESTO DE LA COLA
        Nodo<T> actual = frente;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(elemento)) {
                // ENCONTRADO - CONECTAR EL NODO ACTUAL CON EL SIGUIENTE AL ENCONTRADO
                Nodo<T> nodoAEliminar = actual.getSiguiente();
                actual.setSiguiente(nodoAEliminar.getSiguiente());
                
                // SI ELIMINAMOS EL ÚLTIMO NODO
                if (nodoAEliminar == finalCola) {
                    finalCola = actual;
                }
                
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        
        return false;
    }
    
    // BUSCA UN ELEMENTO EN LA COLA
    @Override
    public T buscar(T elemento) {
        Nodo<T> actual = frente;
        // RECORRER LA COLA DESDE EL FRENTE
        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    // VERIFICA SI LA COLA CONTIENE UN ELEMENTO
    @Override
    public boolean contiene(T elemento) {
        return buscar(elemento) != null;
    }
    
    // RETORNA EL TAMAÑO DE LA COLA
    @Override
    public int tamaño() {
        return tamaño;
    }
    
    // VERIFICA SI LA COLA ESTÁ VACÍA
    @Override
    public boolean estaVacia() {
        return frente == null;
    }
    
    // LIMPIA TODA LA COLA
    @Override
    public void limpiar() {
        frente = null;
        finalCola = null;
        tamaño = 0;
    }
    
    // MUESTRA TODOS LOS ELEMENTOS DE LA COLA
    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía");
            return;
        }
        
        System.out.print("Cola: FRENTE -> ");
        Nodo<T> actual = frente;
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getSiguiente() != null) {
                System.out.print(" -> ");
            } else {
                System.out.print(" <- FINAL");
            }
            actual = actual.getSiguiente();
        }
        System.out.println();
    }
}