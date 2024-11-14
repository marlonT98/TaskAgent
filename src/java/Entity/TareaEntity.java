package Entity;

import java.time.LocalDate;

/**
 * Entidad que representa una tarea.
 */
public class TareaEntity {

    private String id_tarea;
    private String fk_usuario;
    private String titulo;
    private String descripcion;
    private LocalDate fecha_creacion;
    private LocalDate fecha_vencimiento;
    private Estado estado;
    private Prioridad prioridad;

    // Enum para el estado de la tarea
public enum Estado {
    POR_HACER,   // Correspondiente a 'Por hacer'
    EN_PROGRESO, // Correspondiente a 'En progreso'
    COMPLETADO   // Correspondiente a 'Completado'
}
    // Enum para la prioridad de la tarea
  public enum Prioridad {
    Alta, Media, Baja
}

    // Métodos getters y setters
    public String getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(String id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(String fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

 
}
