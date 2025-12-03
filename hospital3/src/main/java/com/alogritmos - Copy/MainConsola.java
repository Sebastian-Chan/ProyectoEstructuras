package com.alogritmos;

import java.util.Scanner;

public class MainConsola {
    static SistemaHospitalario hospital;
    static Scanner sc = new Scanner(System.in);

    // LIMPIAR CONSOLA
    public static void cleanConsole() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    // MENU PRINCIPAL
    public static int mostrarMenu() throws InterruptedException {
        int opcion;
        boolean repetir;
        do {
            repetir = false;
            cleanConsole();
            
            System.out.println("==========================================");
            System.out.println("   SISTEMA HOSPITALARIO SIMPLIFICADO");
            System.out.println("==========================================");
            System.out.println();
            System.out.println("=== GESTION DE PACIENTES (LISTA) ===");
            System.out.println("1.  Registrar paciente");
            System.out.println("2.  Dar de alta paciente");
            System.out.println("3.  Consultar paciente");
            System.out.println("4.  Ver todos los pacientes");
            System.out.println();
            System.out.println("=== EMERGENCIAS (COLA FIFO) ===");
            System.out.println("5.  Agregar paciente a emergencias");
            System.out.println("6.  Atender siguiente emergencia");
            System.out.println("7.  Ver cola de emergencias");
            System.out.println();
            System.out.println("=== ALTAS (PILA LIFO) ===");
            System.out.println("8.  Ver ultimas altas");
            System.out.println("9.  Deshacer ultima alta");
            System.out.println();
            System.out.println("=== INFORMACION (MATRIZ) ===");
            System.out.println("10. Ver mapa del hospital");
            System.out.println("11. Ver estadisticas");
            System.out.println("12. Simulacion automatica");
            System.out.println();
            System.out.println("0.  Salir");
            System.out.println("=========================================");
            System.out.print("SELECCIONA UNA OPCION: ");
            
            opcion = sc.nextInt();
            sc.nextLine();
            
            if (opcion > 12 || opcion < 0) {
                System.out.println("ERROR: INGRESA UN VALOR ENTRE 0 Y 12");
                Thread.sleep(1500);
                repetir = true;
            }
        } while (repetir);
        return opcion;
    }

    // OPCION 1: REGISTRAR PACIENTE
    public static void registrarPaciente() throws InterruptedException {
        cleanConsole();
        System.out.println("=== REGISTRAR NUEVO PACIENTE ===");
        
        System.out.print("ID DEL PACIENTE: ");
        String id = sc.nextLine();
        
        System.out.print("NOMBRE DEL PACIENTE: ");
        String nombre = sc.nextLine();
        
        System.out.println("\nPROCESANDO REGISTRO...");
        Thread.sleep(1000);
        
        hospital.registrarPaciente(id, nombre);
        
        System.out.println("\nPRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 2: DAR DE ALTA
    public static void darDeAltaPaciente() throws InterruptedException {
        cleanConsole();
        System.out.println("=== DAR DE ALTA PACIENTE ===");
        
        if (hospital.getTotalPacientes() == 0) {
            System.out.println("NO HAY PACIENTES REGISTRADOS");
            Thread.sleep(2000);
            return;
        }
        
        System.out.print("ID DEL PACIENTE: ");
        String id = sc.nextLine();
        
        System.out.println("\nPROCESANDO ALTA...");
        Thread.sleep(1000);
        
        hospital.darDeAltaPaciente(id);
        
        System.out.println("\nPRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 3: CONSULTAR PACIENTE
    public static void consultarPaciente() throws InterruptedException {
        cleanConsole();
        System.out.println("=== CONSULTAR PACIENTE ===");
        
        if (hospital.getTotalPacientes() == 0) {
            System.out.println("NO HAY PACIENTES REGISTRADOS");
            Thread.sleep(2000);
            return;
        }
        
        System.out.print("ID DEL PACIENTE: ");
        String id = sc.nextLine();
        
        System.out.println("\nBUSCANDO PACIENTE...");
        Thread.sleep(800);
        
        hospital.consultarPaciente(id);
        
        System.out.println("\nPRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 4: VER TODOS LOS PACIENTES
    public static void verTodosPacientes() {
        cleanConsole();
        hospital.mostrarTodosLosPacientes();
        System.out.println("PRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 5: AGREGAR EMERGENCIA
    public static void agregarEmergencia() throws InterruptedException {
        cleanConsole();
        System.out.println("=== AGREGAR EMERGENCIA ===");
        
        System.out.print("ID DEL PACIENTE: ");
        String id = sc.nextLine();
        
        System.out.print("NOMBRE DEL PACIENTE: ");
        String nombre = sc.nextLine();
        
        System.out.println("\nAGREGANDO A EMERGENCIAS...");
        Thread.sleep(1000);
        
        hospital.agregarEmergencia(id, nombre);
        
        System.out.println("\nPRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 6: ATENDER EMERGENCIA
    public static void atenderEmergencia() throws InterruptedException {
        cleanConsole();
        System.out.println("=== ATENDER EMERGENCIA ===");
        
        System.out.println("ATENDIENDO SIGUIENTE EMERGENCIA...");
        Thread.sleep(1500);
        
        hospital.atenderEmergencia();
        
        System.out.println("\nPRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 7: VER COLA DE EMERGENCIAS
    public static void verColaEmergencias() {
        cleanConsole();
        hospital.mostrarColaEmergencias();
        System.out.println("PRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 8: VER ULTIMAS ALTAS
    public static void verUltimasAltas() {
        cleanConsole();
        hospital.mostrarUltimasAltas();
        System.out.println("PRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 9: DESHACER ULTIMA ALTA
    public static void deshacerUltimaAlta() throws InterruptedException {
        cleanConsole();
        System.out.println("=== DESHACER ULTIMA ALTA ===");
        
        System.out.println("PROCESANDO...");
        Thread.sleep(1000);
        
        hospital.deshacerUltimaAlta();
        
        System.out.println("\nPRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 10: VER MAPA DEL HOSPITAL
    public static void verMapaHospital() {
        cleanConsole();
        hospital.mostrarMapaHospital();
        System.out.println("PRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 11: VER ESTADISTICAS
    public static void verEstadisticas() throws InterruptedException {
        cleanConsole();
        hospital.mostrarEstadisticas();
        
        if (hospital.estaLleno()) {
            System.out.println("ATENCION: EL HOSPITAL ESTA LLENO");
        }
        
        System.out.println("PRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // OPCION 12: SIMULACION AUTOMATICA
    public static void simulacionAutomatica() throws InterruptedException {
        cleanConsole();
        System.out.println("=== SIMULACION AUTOMATICA ===");
        System.out.println("\n");
        
        // AGREGAR EMERGENCIAS (COLA)
        System.out.println("AGREGANDO EMERGENCIAS (COLA FIFO)...");
        hospital.agregarEmergencia("EMG001", "Juan Urgente");
        Thread.sleep(1000);
        hospital.agregarEmergencia("EMG002", "Maria Crisis");
        Thread.sleep(1000);
        hospital.agregarEmergencia("EMG003", "Pedro Emergencia");
        Thread.sleep(1000);
        
        hospital.mostrarColaEmergencias();
        Thread.sleep(2000);
        
        // ATENDER EMERGENCIAS
        System.out.println("ATENDIENDO EMERGENCIAS...");
        hospital.atenderEmergencia();
        Thread.sleep(1500);
        hospital.atenderEmergencia();
        Thread.sleep(1500);
        
        // REGISTRAR PACIENTES (LISTA)
        System.out.println("REGISTRANDO PACIENTES (LISTA SIMPLE)...");
        hospital.registrarPaciente("PAC001", "Ana Gonzalez");
        Thread.sleep(1000);
        hospital.registrarPaciente("PAC002", "Carlos Lopez");
        Thread.sleep(1000);
        hospital.registrarPaciente("PAC003", "Laura Martinez");
        Thread.sleep(1000);
        
        // MOSTRAR ESTADO
        hospital.mostrarTodosLosPacientes();
        Thread.sleep(2000);
        
        // DAR ALTAS (PILA)
        System.out.println("DANDO ALTAS (PILA LIFO)...");
        hospital.darDeAltaPaciente("EMG001");
        Thread.sleep(1500);
        hospital.darDeAltaPaciente("PAC001");
        Thread.sleep(1500);
        
        hospital.mostrarUltimasAltas();
        Thread.sleep(2000);
        
        // DESHACER ALTA
        System.out.println("DEMOSTRANDO DESHACER ALTA...");
        hospital.deshacerUltimaAlta();
        Thread.sleep(2000);
        
        // ESTADO FINAL
        System.out.println("ESTADO FINAL (MATRIZ)...");
        hospital.mostrarMapaHospital();
        Thread.sleep(2000);
        
        hospital.mostrarEstadisticas();
        
        System.out.println("\nSIMULACION COMPLETADA");
        System.out.println("\nESTRUCTURAS DEMOSTRADAS:");
        System.out.println("LISTA SIMPLE (PACIENTES)");
        System.out.println("COLA FIFO (EMERGENCIAS)");
        System.out.println("PILA LIFO (ALTAS)");
        System.out.println("MATRIZ (HABITACIONES)");
        
        System.out.println("\nPRESIONA ENTER PARA CONTINUAR...");
        sc.nextLine();
    }

    // METODO PRINCIPAL
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==========================================");
        System.out.println("  BIENVENIDO AL SISTEMA HOSPITALARIO");
        System.out.println("==========================================");
        System.out.println("\nINICIALIZANDO SISTEMA...");
        Thread.sleep(2000);
        
        // CREAR HOSPITAL CON 3 PISOS Y 5 HABITACIONES POR PISO
        hospital = new SistemaHospitalario(3, 5);
        
        System.out.println("\nSISTEMA LISTO PARA USAR");
        Thread.sleep(2000);
        
        int opcion;
        boolean continuar = true;
        
        while (continuar) {
            opcion = mostrarMenu();
            
            try {
                switch (opcion) {
                    case 1: registrarPaciente(); break;
                    case 2: darDeAltaPaciente(); break;
                    case 3: consultarPaciente(); break;
                    case 4: verTodosPacientes(); break;
                    case 5: agregarEmergencia(); break;
                    case 6: atenderEmergencia(); break;
                    case 7: verColaEmergencias(); break;
                    case 8: verUltimasAltas(); break;
                    case 9: deshacerUltimaAlta(); break;
                    case 10: verMapaHospital(); break;
                    case 11: verEstadisticas(); break;
                    case 12: simulacionAutomatica(); break;
                    case 0:
                        cleanConsole();
                        System.out.println("==========================================");
                        System.out.println("      CERRANDO SISTEMA HOSPITALARIO");
                        System.out.println("==========================================");
                        System.out.println("\nGUARDANDO DATOS...");
                        Thread.sleep(1000);
                        
                        System.out.println("\nESTADISTICAS FINALES:");
                        hospital.mostrarEstadisticas();
                        
                        System.out.println("DATOS GUARDADOS CORRECTAMENTE");
                        System.out.println("\nESTRUCTURAS DE DATOS UTILIZADAS:");
                        System.out.println("   LISTA SIMPLE (PACIENTES)");
                        System.out.println("   COLA FIFO (EMERGENCIAS)");
                        System.out.println("   PILA LIFO (ALTAS)");
                        System.out.println("   MATRIZ (HABITACIONES)");
                        System.out.println("\nGRACIAS POR USAR EL SISTEMA HOSPITALARIO");
                        continuar = false;
                        break;
                    default:
                        System.out.println("OPCION NO VALIDA");
                        Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("PRESIONA ENTER PARA CONTINUAR...");
                sc.nextLine();
            }
        }
        
        sc.close();
    }
}