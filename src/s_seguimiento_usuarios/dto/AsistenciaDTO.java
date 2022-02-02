package s_seguimiento_usuarios.dto;

import java.io.Serializable;

public class AsistenciaDTO implements Serializable {
    private int idPaciente;
    private String fechaDeAsistencia;
    private String Observacion;
    public AsistenciaDTO(int idPaciente, String fechaDeAsistencia, String observacion) {
        this.idPaciente = idPaciente;
        this.fechaDeAsistencia = fechaDeAsistencia;
        Observacion = observacion;
    }
    public String getObservacion() {
        return Observacion;
    }
    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getFechaDeAsistencia() {
        return fechaDeAsistencia;
    }
    public void setFechaDeAsistencia(String fechaDeAsistencia) {
        this.fechaDeAsistencia = fechaDeAsistencia;
    }
    public void setObservacion(String observacion) {
        this.Observacion = observacion;
    }
}
