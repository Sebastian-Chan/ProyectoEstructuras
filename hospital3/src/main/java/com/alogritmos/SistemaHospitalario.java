package com.alogritmos;

// SISTEMA HOSPITALARIO SIMPLIFICADO CON 4 ESTRUCTURAS DE DATOS
public class SistemaHospitalario {
    // === LISTA SIMPLE PARA PACIENTES ACTIVOS ===
    private ListaSimple<Paciente> pacientesActivos;
    
    // === MATRIZ PARA GESTION DE HABITACIONES ===
    private GestorHabitaciones gestorHabitaciones;
    
    // === COLA FIFO PARA EMERGENCIAS ===
    private Cola<Paciente> colaEmergencias;
    
    // === PILA LIFO PARA ALTAS RECIENTES ===
    private Pila<Paciente> pilaAltasRecientes;
    
    // CONSTRUCTOR - INICIALIZA TODO EL SISTEMA
    public SistemaHospitalario(int pisos, int habitacionesPorPiso) {
        this.pacientesActivos = new ListaSimple<>();
        this.gestorHabitaciones = new GestorHabitaciones(pisos, habitacionesPorPiso);
        this.colaEmergencias = new Cola<>();
        this.pilaAltasRecientes = new Pila<>();
        
        System.out.println("\n=== SISTEMA HOSPITALARIO INICIADO ===");
        System.out.println("LISTA SIMPLE: PACIENTES ACTIVOS");
        System.out.println("MATRIZ: GESTION DE HABITACIONES");
        System.out.println("COLA FIFO: EMERGENCIAS");
        System.out.println("PILA LIFO: ALTAS RECIENTES");
        System.out.println("======================================\n");
    }
    
    // === METODOS PARA PACIENTES (LISTA SIMPLE) ===
    
    // REGISTRAR PACIENTE NORMAL
    public boolean registrarPaciente(String id, String nombre) {
        if (buscarPacientePorId(id) != null) {
            System.out.println("ERROR: YA EXISTE UN PACIENTE CON ESE ID");
            return false;
        }
        
        String habitacionAsignada = gestorHabitaciones.asignarHabitacion();
        if (habitacionAsignada == null) {
            System.out.println("ERROR: NO HAY HABITACIONES DISPONIBLES");
            return false;
        }
        
        Paciente nuevoPaciente = new Paciente(id, nombre, habitacionAsignada);
        pacientesActivos.agregar(nuevoPaciente);
        
        System.out.println("PACIENTE REGISTRADO EXITOSAMENTE:");
        nuevoPaciente.mostrarInfo();
        
        return true;
    }
    
    // BUSCAR PACIENTE POR ID
    public Paciente buscarPacientePorId(String id) {
        Paciente temporal = new Paciente();
        temporal.setId(id);
        return pacientesActivos.buscar(temporal);
    }
    
    // CONSULTAR PACIENTE
    public void consultarPaciente(String id) {
        Paciente paciente = buscarPacientePorId(id);
        if (paciente != null) {
            paciente.mostrarInfo();
        } else {
            System.out.println("NO SE ENCONTRO PACIENTE CON ID: " + id);
        }
    }
    
    // MOSTRAR TODOS LOS PACIENTES
    public void mostrarTodosLosPacientes() {
        System.out.println("\n=== PACIENTES ACTIVOS ===");
        if (pacientesActivos.estaVacia()) {
            System.out.println("NO HAY PACIENTES REGISTRADOS");
        } else {
            pacientesActivos.mostrar();
            System.out.println("TOTAL: " + pacientesActivos.tamaño());
        }
        System.out.println("==========================\n");
    }
    
    // === METODOS PARA EMERGENCIAS (COLA FIFO) ===
    
    // AGREGAR PACIENTE A EMERGENCIAS
    public boolean agregarEmergencia(String id, String nombre) {
        Paciente temporal = new Paciente(id, nombre);
        if (colaEmergencias.contiene(temporal)) {
            System.out.println("ERROR: EL PACIENTE YA ESTA EN LA COLA DE EMERGENCIAS");
            return false;
        }
        
        colaEmergencias.encolar(temporal);
        System.out.println("PACIENTE AGREGADO A EMERGENCIAS: " + nombre);
        System.out.println("POSICION EN COLA: " + colaEmergencias.tamaño());
        
        return true;
    }
    
    // ATENDER SIGUIENTE EMERGENCIA
    public boolean atenderEmergencia() {
        Paciente pacienteEmergencia = colaEmergencias.desencolar();
        
        if (pacienteEmergencia == null) {
            System.out.println("NO HAY PACIENTES EN LA COLA DE EMERGENCIAS");
            return false;
        }
        
        boolean registrado = registrarPaciente(pacienteEmergencia.getId(), 
                                             pacienteEmergencia.getNombre());
        
        if (registrado) {
            System.out.println("EMERGENCIA ATENDIDA Y PACIENTE HOSPITALIZADO:");
            System.out.println(pacienteEmergencia.getNombre());
        } else {
            System.out.println("EMERGENCIA ATENDIDA PERO NO SE PUDO HOSPITALIZAR");
            System.out.println("PACIENTE ENVIADO A CASA: " + pacienteEmergencia.getNombre());
        }
        
        return true;
    }
    
    // MOSTRAR COLA DE EMERGENCIAS
    public void mostrarColaEmergencias() {
        System.out.println("\n=== COLA DE EMERGENCIAS ===");
        if (colaEmergencias.estaVacia()) {
            System.out.println("NO HAY EMERGENCIAS PENDIENTES");
        } else {
            colaEmergencias.mostrar();
        }
        System.out.println("============================\n");
    }
    
    // === METODOS PARA ALTAS (PILA LIFO) ===
    
    // DAR DE ALTA A UN PACIENTE
    public boolean darDeAltaPaciente(String id) {
        Paciente paciente = buscarPacientePorId(id);
        
        if (paciente == null) {
            System.out.println("ERROR: NO SE ENCONTRO EL PACIENTE CON ID: " + id);
            return false;
        }
        
        String habitacion = paciente.getHabitacion();
        gestorHabitaciones.liberarHabitacion(habitacion);
        
        pacientesActivos.eliminar(paciente);
        pilaAltasRecientes.apilar(paciente);
        
        System.out.println("PACIENTE DADO DE ALTA:");
        System.out.println(paciente.getNombre() + " - HABITACION " + habitacion + " LIBERADA");
        
        return true;
    }
    
    // MOSTRAR ULTIMAS ALTAS
    public void mostrarUltimasAltas() {
        System.out.println("\n=== ULTIMAS ALTAS ===");
        if (pilaAltasRecientes.estaVacia()) {
            System.out.println("NO HAY ALTAS RECIENTES");
        } else {
            pilaAltasRecientes.mostrar();
        }
        System.out.println("======================\n");
    }
    
    // DESHACER ULTIMA ALTA
    public boolean deshacerUltimaAlta() {
        Paciente ultimaAlta = pilaAltasRecientes.desapilar();
        
        if (ultimaAlta == null) {
            System.out.println("NO HAY ALTAS PARA DESHACER");
            return false;
        }
        
        boolean reRegistrado = registrarPaciente(ultimaAlta.getId(), ultimaAlta.getNombre());
        
        if (reRegistrado) {
            System.out.println("ALTA DESHECHA - PACIENTE RE-INGRESADO:");
            System.out.println(ultimaAlta.getNombre());
        } else {
            pilaAltasRecientes.apilar(ultimaAlta);
            System.out.println("NO SE PUDO DESHACER LA ALTA (SIN HABITACIONES)");
        }
        
        return reRegistrado;
    }
    
    // === METODOS DE INFORMACION ===
    
    // MOSTRAR MAPA DEL HOSPITAL
    public void mostrarMapaHospital() {
        gestorHabitaciones.mostrarMapaHospital();
    }
    
    // MOSTRAR ESTADISTICAS
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADISTICAS DEL HOSPITAL ===");
        System.out.println("PACIENTES ACTIVOS: " + pacientesActivos.tamaño());
        System.out.println("EMERGENCIAS PENDIENTES: " + colaEmergencias.tamaño());
        System.out.println("ALTAS RECIENTES: " + pilaAltasRecientes.tamaño());
        System.out.println("HABITACIONES LIBRES: " + gestorHabitaciones.contarHabitacionesLibres());
        System.out.println("==================================\n");
    }
    
    // OBTENER TOTAL DE PACIENTES
    public int getTotalPacientes() {
        return pacientesActivos.tamaño();
    }
    
    // VERIFICAR SI EL HOSPITAL ESTA LLENO
    public boolean estaLleno() {
        return gestorHabitaciones.contarHabitacionesLibres() == 0;
    }
}