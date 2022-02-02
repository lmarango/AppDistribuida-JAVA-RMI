package s_seguimiento_usuarios.dto;

import java.io.Serializable;

public class EjercicioDTO implements Serializable {
    private String nomEjercicio;
    private int repeticiones;
    private int peso;
    //Constructor
    public EjercicioDTO(String nomEjercicio, int repeticiones, int peso) {
        this.nomEjercicio = nomEjercicio;
        this.repeticiones = repeticiones;
        this.peso = peso;
    }
    //Getters and Setters
    public String getNomEjercicio() {
        return nomEjercicio;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public int getRepeticiones() {
        return repeticiones;
    }
    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }
    public void setNomEjercicio(String nomEjercicio) {
        this.nomEjercicio = nomEjercicio;
    }
}
