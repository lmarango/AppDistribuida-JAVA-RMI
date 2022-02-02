package s_gestion_usuarios.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
    private String nombreCompleto;
    private int id;
    private String facultad;
    private String tipoUsuario;
    private String fechaIngreso;
    private String patologia;
    private String clave;
    private String usuario;

    //Constructor
    public UsuarioDTO(String nombreCompleto, int id, String facultad, String tipoUsuario, 
                      String fechaIngreso, String patologia, String clave, String usuario) {
        this.nombreCompleto = nombreCompleto;
        this.id = id;
        this.facultad = facultad;
        this.tipoUsuario = tipoUsuario;
        this.fechaIngreso = fechaIngreso;
        this.patologia = patologia;
        this.clave = clave;
        this.usuario = usuario;
    }
    //Getters and Setters
    public String getUsuario() {
        return usuario;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFacultad() {
        return facultad;
    }
    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public String getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public String getPatologia() {
        return patologia;
    }
    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


}
