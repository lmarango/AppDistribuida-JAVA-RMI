/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s_seguimiento_usuarios.dto;

import java.io.Serializable;

/**
 *
 * @author Lenovo!
 */
public class NotificacionDTO implements Serializable{
    private String nombreCompleto;
    private String ocupacion;
    //Constructor
    public NotificacionDTO(String nombreCompleto, String ocupacion) {
        this.setNombreCompleto(nombreCompleto);
        this.setOcupacion(ocupacion);
    }
    //Getters and Setters
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
}
