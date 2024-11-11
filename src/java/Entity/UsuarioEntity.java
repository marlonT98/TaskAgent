
package Entity;

import java.time.LocalDate;



public class UsuarioEntity {
    
    private String id_usuario;
    private String nombre;
    private String email;
    private String password;
    private LocalDate  fecha_creacion;
    private LocalDate  fecha_ultimo_acceso;

    public UsuarioEntity() {
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public LocalDate getFecha_ultimo_acceso() {
        return fecha_ultimo_acceso;
    }

    public void setFecha_ultimo_acceso(LocalDate fecha_ultimo_acceso) {
        this.fecha_ultimo_acceso = fecha_ultimo_acceso;
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
