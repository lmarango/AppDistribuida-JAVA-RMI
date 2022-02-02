package s_gestion_usuarios.dto;

import java.io.Serializable;

public class CredencialDTO implements Serializable{
    private String usaurio;
    private String clave;
    private int id;

    //Constructor
    public CredencialDTO(String usaurio, String clave, int id) {
        this.usaurio = usaurio;
        this.clave = clave;
        this.id = id;
    }
    //Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsaurio() {
        return usaurio;
    }
    public void setUsaurio(String usaurio) {
        this.usaurio = usaurio;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }    
}
