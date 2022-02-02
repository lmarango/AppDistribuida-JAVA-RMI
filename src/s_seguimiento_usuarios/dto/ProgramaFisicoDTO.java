package s_seguimiento_usuarios.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ProgramaFisicoDTO implements Serializable {
    private int idPaciente;
    private String fechaInicio;
    private ArrayList<ProgramaDTO> programaSemana;
    //Constructor
    public ProgramaFisicoDTO(int idPaciente, String fechaInicio, ArrayList<ProgramaDTO> programaSemana) {
        this.idPaciente = idPaciente;
        this.fechaInicio = fechaInicio;
        this.programaSemana = programaSemana;
    }
    //Getters and Setters
    public int getIdPaciente() {
        return idPaciente;
    }
    public ArrayList<ProgramaDTO> getProgramaSemana() {
        return programaSemana;
    }
    public void setProgramaSemana(ArrayList<ProgramaDTO> programaSemana) {
        this.programaSemana = programaSemana;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
