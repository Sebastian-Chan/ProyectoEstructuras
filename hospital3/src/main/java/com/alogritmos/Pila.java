package com.alogritmos;

public class Pila<T> implements EstructuraDatos<T> {
    // NODO QUE REPRESENTA LA CIMA DE LA PILA
    private Nodo<T> cima;
    // CONTADOR DE ELEMENTOS EN LA PILA
    private int tamaño;
    
    // CONSTRUCTOR - INICIALIZA LA PILA VACÍA
    public Pila() {
        this.cima = null;
        this.tamaño = 0;
    }
    
    // AGREGA UN ELEMENTO A LA CIMA DE LA PILA (PUSH)
    @Override
    public boolean agregar(T elemento) {
        return apilar(elemento);
    }
    
    // MÉTODO ESPECÍFICO PARA APILAR (PUSH)
    public boolean apilar(T elemento) {
        // CREAR NUEVO NODO
        Nodo<T> nuevo = new Nodo<>(elemento);
        // EL NUEVO NODO APUNTA AL NODO QUE ESTABA EN LA CIMA
        nuevo.setSiguiente(cima);
        // EL NUEVO NODO SE CONVIERTE EN LA NUEVA CIMA
        cima = nuevo;
        tamaño++;
        return true;
    }
    
    // REMUEVE Y RETORNA EL ELEMENTO DE LA CIMA (POP)
    public T desapilar() {
        // SI LA PILA ESTÁ VACÍA
        if (cima == null) {
            return null;
        }
        
        // OBTENER EL DATO DE LA CIMA
        T dato = cima.getDato();
        // LA CIMA AHORA ES EL SIGUIENTE NODO
        cima = cima.getSiguiente();
        tamaño--;
        return dato;
    }
    
    // RETORNA EL ELEMENTO DE LA CIMA SIN REMOVERLO (PEEK)
    public T verCima() {
        if (cima == null) {
            return null;
        }
        return cima.getDato();
    }
    
    // ELIMINA UN ELEMENTO ESPECÍFICO (NO ES TÍPICO EN PILAS)
    @Override
    public boolean eliminar(T elemento) {
        // CREAR PILA TEMPORAL PARA GUARDAR ELEMENTOS
        Pila<T> temporal = new Pila<>();
        boolean encontrado = false;
        
        // SACAR ELEMENTOS HASTA ENCONTRAR EL DESEADO
        while (cima != null) {
            T actual = desapilar();
            if (actual.equals(elemento) && !encontrado) {
                encontrado = true; // NO LO AGREGAMOS A LA PILA TEMPORAL
            } else {
                temporal.apilar(actual);
            }
        }
        
        // REGRESAR LOS ELEMENTOS A LA PILA ORIGINAL
        while (temporal.cima != null) {
            apilar(temporal.desapilar());
        }
        
        return encontrado;
    }
    
    // BUSCA UN ELEMENTO EN LA PILA
    @Override
    public T buscar(T elemento) {
        Nodo<T> actual = cima;
        // RECORRER LA PILA DESDE LA CIMA
        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    // VERIFICA SI LA PILA CONTIENE UN ELEMENTO
    @Override
    public boolean contiene(T elemento) {
        return buscar(elemento) != null;
    }
    
    // RETORNA EL TAMAÑO DE LA PILA
    @Override
    public int tamaño() {
        return tamaño;
    }
    
    // VERIFICA SI LA PILA ESTÁ VACÍA
    @Override
    public boolean estaVacia() {
        return cima == null;
    }
    
    // LIMPIA TODA LA PILA
    @Override
    public void limpiar() {
        cima = null;
        tamaño = 0;
    }
    
    // MUESTRA TODOS LOS ELEMENTOS DE LA PILA
    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La pila está vacía");
            return;
        }
        
        System.out.println("Pila (de cima a base):");
        Nodo<T> actual = cima;
        int posicion = 1;
        while (actual != null) {
            System.out.println("  [" + posicion + "] " + actual.getDato() + 
                             (posicion == 1 ? " <- CIMA" : ""));
            actual = actual.getSiguiente();
            posicion++;
        }
        System.out.println("  [BASE]");
    }
}