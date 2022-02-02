package s_seguimiento_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import s_seguimiento_usuarios.dto.*;

public interface GestionPacientesInt extends Remote {
    public boolean registrarValoracion(ValoracionDTO objValoracion) throws RemoteException;
    public ValoracionDTO consultarValoracion(int id) throws RemoteException;
    public boolean regProgramaFisico(ProgramaFisicoDTO objProgramaFisico) throws RemoteException;
    public ProgramaFisicoDTO consultarPrograma(int id) throws RemoteException;
    public boolean RegistrarAsistencia(AsistenciaDTO objAsistencia) throws RemoteException;
    public ArrayList<AsistenciaDTO> consultarAsistencia(int id) throws RemoteException;
    public int contarFaltas(int id) throws RemoteException;    
}
