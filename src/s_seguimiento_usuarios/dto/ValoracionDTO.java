package s_seguimiento_usuarios.dto;

import java.io.Serializable;

public class ValoracionDTO implements Serializable {
    private int idPaciente;
    private String fechaValoracion;
    private int fecCardiacaReposo;
    private int fecCardiacaActiva;
    private int estatura;
    private int brazo; 
    private int pierna;
    private int pecho;
    private int cintura;
    private String estado;
    //Constructor
    public ValoracionDTO(int idPaciente, String fechaValoracion, int fecCardiacaReposo, int fecCardiacaActiva, int estatura, int brazo, int pierna, int pecho, int cintura, String estado) {
        this.idPaciente = idPaciente;
        this.fechaValoracion = fechaValoracion;
        this.fecCardiacaReposo = fecCardiacaReposo;
        this.fecCardiacaActiva = fecCardiacaActiva;
        this.estatura = estatura;
        this.brazo = brazo;
        this.pierna = pierna;
        this.pecho = pecho;
        this.cintura = cintura;
        this.estado = estado;
    }
    //Getters and Setters
    public String getEstado() {
        return estado;
    }
    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getFechaValoracion() {
        return fechaValoracion;
    }
    public void setFechaValoracion(String fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }
    public int getFecCardiacaReposo() {
        return fecCardiacaReposo;
    }
    public void setFecCardiacaReposo(int fecCardiacaReposo) {
        this.fecCardiacaReposo = fecCardiacaReposo;
    }
    public int getFecCardiacaActiva() {
        return fecCardiacaActiva;
    }
    public void setFecCardiacaActiva(int fecCardiacaActiva) {
        this.fecCardiacaActiva = fecCardiacaActiva;
    }
    public double getEstatura() {
        return estatura;
    }
    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }
    public double getBrazo() {
        return brazo;
    }
    public void setBrazo(int brazo) {
        this.brazo = brazo;
    }
    public double getPierna() {
        return pierna;
    }
    public void setPierna(int pierna) {
        this.pierna = pierna;
    }
    public double getPecho() {
        return pecho;
    }
    public void setPecho(int pecho) {
        this.pecho = pecho;
    }
    public double getCintura() {
        return cintura;
    }
    public void setCintura(int cintura) {
        this.cintura = cintura;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
