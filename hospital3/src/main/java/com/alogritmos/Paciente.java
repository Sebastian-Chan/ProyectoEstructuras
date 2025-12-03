package com.alogritmos;

public class Paciente {
   // ATRIBUTOS DEL PACIENTE - ESTOS SON LOS DATOS QUE VAMOS A GUARDAR
   private String id;          // CEDULA O NUMERO UNICO DEL PACIENTE
   private String nombre;      // NOMBRE COMPLETO DEL PACIENTE  
   private String habitacion;  // NUMERO DE HABITACION ASIGNADA (EJ: "101", "A-205")
   
   // CONSTRUCTOR VACIO - POR SI QUEREMOS CREAR UN PACIENTE SIN DATOS PRIMERO
   public Paciente() {
       this.id = "";
       this.nombre = "";
       this.habitacion = "";
   }
   public Paciente(String id, String nombre) { // CONSTRUCTOR SIN HABITACION (POR SI AUN NO SE ASIGNA)
       this.id = id;
       this.nombre = nombre;
       this.habitacion = "";
   }
    public Paciente(String id, String nombre, String habitacion) { // CONSTRUCTOR COMPLETO
         this.id = id;
         this.nombre = nombre;
         this.habitacion = habitacion;
    }
   
   
   
   // GETTERS - PARA OBTENER LA INFO DEL PACIENTE
   public String getId() {
       return id;
   }
   
   public String getNombre() {
       return nombre;
   }
   
   public String getHabitacion() {
       return habitacion;
   }
   
   // SETTERS - PARA CAMBIAR LA INFO DEL PACIENTE
   public void setId(String id) {
       this.id = id;
   }
   
   public void setNombre(String nombre) {
       this.nombre = nombre;
   }
   
   public void setHabitacion(String habitacion) {
       this.habitacion = habitacion;
   }
   
   // EQUALS - PARA COMPARAR SI DOS PACIENTES SON IGUALES (USAMOS EL ID)
   // ESTO ES SUPER IMPORTANTE PARA QUE FUNCIONE LA BUSQUEDA EN LA LISTA
   @Override
   public boolean equals(Object obj) {
       if (this == obj) return true;  // SI ES EL MISMO OBJETO
       if (obj == null) return false; // SI EL OBJETO ES NULO
       if (getClass() != obj.getClass()) return false; // SI NO SON DE LA MISMA CLASE
       
       Paciente otro = (Paciente) obj;  // HACEMOS CASTING PARA COMPARAR
       return this.id.equals(otro.id);  // DOS PACIENTES SON IGUALES SI TIENEN EL MISMO ID
   }
   
   // HASHCODE - NECESARIO CUANDO USAMOS EQUALS, JAVA LO PIDE
   @Override
   public int hashCode() {
       return id.hashCode();  // USAMOS EL HASH DEL ID
   }
   
   // TOSTRING - PARA MOSTRAR LA INFO DEL PACIENTE DE MANERA BONITA
   @Override
   public String toString() {
       return String.format("Paciente{ID: %s, Nombre: %s, Habitación: %s}", 
                          id, nombre, habitacion);
   }
   
   // METODO PARA VERIFICAR SI EL PACIENTE TIENE HABITACION ASIGNADA
   public boolean tieneHabitacion() {
       return habitacion != null && !habitacion.isEmpty();
   }
   
   // METODO PARA MOSTRAR INFO RESUMIDA DEL PACIENTE
   public void mostrarInfo() {
       System.out.println("=== INFORMACIÓN DEL PACIENTE ===");
       System.out.println("ID: " + id);
       System.out.println("Nombre: " + nombre);
       if (tieneHabitacion()) {
           System.out.println("Habitación: " + habitacion);
       } else {
           System.out.println("Habitación: SIN ASIGNAR");
       }
       System.out.println("================================");
   }
   
   // METODO PARA VALIDAR SI LOS DATOS DEL PACIENTE ESTAN COMPLETOS
   public boolean datosCompletos() {
       return !id.isEmpty() && !nombre.isEmpty() && tieneHabitacion();
   }
}