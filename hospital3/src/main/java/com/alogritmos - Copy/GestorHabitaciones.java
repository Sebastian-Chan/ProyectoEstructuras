package com.alogritmos;

// CLASE QUE MANEJA LAS HABITACIONES DEL HOSPITAL
class GestorHabitaciones {
    // MATRIZ QUE REPRESENTA TODAS LAS HABITACIONES DEL HOSPITAL
    // TRUE = OCUPADA, FALSE = LIBRE
    private boolean[][] habitaciones;
    private int pisos;      // CUANTOS PISOS TIENE EL HOSPITAL
    private int habitacionesPorPiso;  // CUANTAS HABITACIONES HAY EN CADA PISO
    
    // CONSTRUCTOR - AQUI DECIDIMOS EL TAMAÑO DEL HOSPITAL
    public GestorHabitaciones(int pisos, int habitacionesPorPiso) {
        this.pisos = pisos;
        this.habitacionesPorPiso = habitacionesPorPiso;
        this.habitaciones = new boolean[pisos][habitacionesPorPiso];
        
        // AL INICIO TODAS LAS HABITACIONES ESTAN LIBRES (FALSE)
        for (int i = 0; i < pisos; i++) {
            for (int j = 0; j < habitacionesPorPiso; j++) {
                habitaciones[i][j] = false;  // LIBRE
            }
        }
        
        System.out.println("HOSPITAL CREADO CON " + pisos + " PISOS Y " + 
                          habitacionesPorPiso + " HABITACIONES POR PISO");
    }
    
    // BUSCAR LA PRIMERA HABITACION LIBRE Y ASIGNARLA
    public String asignarHabitacion() {
        for (int piso = 0; piso < pisos; piso++) {
            for (int habitacion = 0; habitacion < habitacionesPorPiso; habitacion++) {
                // SI LA HABITACION ESTA LIBRE
                if (!habitaciones[piso][habitacion]) {
                    habitaciones[piso][habitacion] = true;  // LA MARCAMOS COMO OCUPADA
                    
                    // CREAMOS EL NUMERO DE HABITACION (PISO+1 PORQUE EMPEZAMOS EN 1, NO EN 0)
                    String numeroHabitacion = (piso + 1) + String.format("%02d", habitacion + 1);
                    return numeroHabitacion;  // EJEMPLO: "101", "205", "312"
                }
            }
        }
        
        // SI NO HAY HABITACIONES LIBRES
        return null;
    }
    
    // LIBERAR UNA HABITACION CUANDO EL PACIENTE SE VA
    public boolean liberarHabitacion(String numeroHabitacion) {
        try {
            // CONVERTIR EL NUMERO DE HABITACION A COORDENADAS
            int piso = Integer.parseInt(numeroHabitacion.substring(0, 1)) - 1;
            int habitacion = Integer.parseInt(numeroHabitacion.substring(1)) - 1;
            
            // VERIFICAR QUE LAS COORDENADAS SEAN VALIDAS
            if (piso >= 0 && piso < pisos && habitacion >= 0 && habitacion < habitacionesPorPiso) {
                habitaciones[piso][habitacion] = false;  // MARCAR COMO LIBRE
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR: NUMERO DE HABITACION INVALIDO");
        }
        
        return false;
    }
    
    // VERIFICAR SI UNA HABITACION ESTA OCUPADA
    public boolean estaOcupada(String numeroHabitacion) {
        try {
            int piso = Integer.parseInt(numeroHabitacion.substring(0, 1)) - 1;
            int habitacion = Integer.parseInt(numeroHabitacion.substring(1)) - 1;
            
            if (piso >= 0 && piso < pisos && habitacion >= 0 && habitacion < habitacionesPorPiso) {
                return habitaciones[piso][habitacion];
            }
        } catch (Exception e) {
            System.out.println("ERROR: NUMERO DE HABITACION INVALIDO");
        }
        
        return false;
    }
    
    // MOSTRAR EL MAPA COMPLETO DEL HOSPITAL
    public void mostrarMapaHospital() {
        System.out.println("\n=== MAPA DEL HOSPITAL ===");
        System.out.println("O = LIBRE, X = OCUPADA\n");
        
        for (int piso = 0; piso < pisos; piso++) {
            System.out.println("PISO " + (piso + 1) + ":");
            System.out.print("  ");
            
            for (int habitacion = 0; habitacion < habitacionesPorPiso; habitacion++) {
                String numeroHab = (piso + 1) + String.format("%02d", habitacion + 1);
                char estado = habitaciones[piso][habitacion] ? 'X' : 'O';
                System.out.print("[" + numeroHab + ":" + estado + "] ");
            }
            System.out.println();
        }
        System.out.println("========================\n");
    }
    
    // CONTAR CUANTAS HABITACIONES LIBRES HAY
    public int contarHabitacionesLibres() {
        int libres = 0;
        for (int i = 0; i < pisos; i++) {
            for (int j = 0; j < habitacionesPorPiso; j++) {
                if (!habitaciones[i][j]) {
                    libres++;
                }
            }
        }
        return libres;
    }
}