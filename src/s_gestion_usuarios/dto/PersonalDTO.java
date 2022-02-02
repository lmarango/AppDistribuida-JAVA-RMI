package s_gestion_usuarios.dto;

import java.io.Serializable;

public class PersonalDTO implements Serializable{
    private String tipo_id; //cc, ti, pp
    private int id;
    private String nombreCompleto;
    private String ocupacion; 
    private String usuario;
    private String clave;

    //Constructor
    public PersonalDTO(String tipo_id, int id, String nombreCompleto, 
                       String ocupacion, String usuario, String clave){
        this.setTipo_id(tipo_id);
        this.setId(id);
        this.setNombreCompleto(nombreCompleto);
        this.setOcupacion(ocupacion);
        this.setUsuario(usuario);
        this.setClave(clave);
    }
    //Getters and Setters
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
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
    public String getTipo_id() {
        return tipo_id;
    }
    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }
}
