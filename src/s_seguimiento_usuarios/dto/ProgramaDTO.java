package s_seguimiento_usuarios.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ProgramaDTO implements Serializable {
    private int dia;
    private ArrayList<EjercicioDTO> ejercicios;
    //Constructor
    public ProgramaDTO(int dia, ArrayList<EjercicioDTO> ejercicios) {
        this.dia = dia;
        this.ejercicios = ejercicios;
    }
    //Getters and Setters
    public int getDia() {
        return dia;
    }
    public ArrayList<EjercicioDTO> getEjercicios() {
        return ejercicios;
    }
    public void setEjercicios(ArrayList<EjercicioDTO> ejercicios) {
        this.ejercicios = ejercicios;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
}
