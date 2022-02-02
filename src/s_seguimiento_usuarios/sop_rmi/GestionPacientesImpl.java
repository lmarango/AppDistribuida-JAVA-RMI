package s_seguimiento_usuarios.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import s_seguimiento_usuarios.dto.AsistenciaDTO;
import s_seguimiento_usuarios.dto.ProgramaFisicoDTO;
import s_seguimiento_usuarios.dto.ValoracionDTO;

public class GestionPacientesImpl extends UnicastRemoteObject implements GestionPacientesInt {

    private ArrayList<ValoracionDTO> lstValoraciones;
    private ArrayList<ProgramaFisicoDTO> lstProgramas;
    private ArrayList<AsistenciaDTO> lstAsistencia;
    //Constructor
    public GestionPacientesImpl() throws RemoteException {
        super();
        this.lstValoraciones = new ArrayList<ValoracionDTO>();
        this.lstProgramas = new ArrayList<ProgramaFisicoDTO>();
        this.lstAsistencia = new ArrayList<AsistenciaDTO>();
    }
    //Metodos
    @Override
    public boolean registrarValoracion(ValoracionDTO objValoracion) throws RemoteException {
        System.out.println("***Entrando a registrarValoracion()...");
        boolean resultado = false;
        if(objValoracion != null){
            resultado = lstValoraciones.add(objValoracion);
            if (resultado) {
                System.out.println("Valoración física Registrada! ");
            }else{
                System.out.println("ERROR: No se pudo registrar la valoración física.");
            }
        }
        return resultado;
    }
    @Override
    public ValoracionDTO consultarValoracion(int id) throws RemoteException{
        System.out.println("***Entrando a consultarValoracion()...");
        ValoracionDTO objValoracion = null;
        int bandera =  buscarValoracion(id);
        if (bandera != -1) {
           objValoracion = lstValoraciones.get(bandera);
        }else{                
            System.out.println("ERROR: No se pudo se encontró la valoración.");
        }
        return objValoracion;
    }
    @Override
    public boolean regProgramaFisico(ProgramaFisicoDTO objProgramaFisico) throws RemoteException{
        System.out.println("***Entrando a regProgramaFisico()...");
        boolean resultado = false;
        if (objProgramaFisico != null) {
            resultado = lstProgramas.add(objProgramaFisico);
            if (resultado) {
                System.out.println("Programa física Agregado! ");
            } else {
                System.out.println("ERROR: No se pudo registrar el programa físico.");
            }
        }
        return resultado;
    }
    @Override
    public ProgramaFisicoDTO consultarPrograma(int id) throws RemoteException{
        System.out.println("***Entrando a consultarValoracion()...");
        ProgramaFisicoDTO resultado = null;
        int bandera = buscarPrograma(id);
        if (bandera != -1) {
            resultado = lstProgramas.get(bandera);
        }else{
            System.out.println("ERROR: No se pudo se encontró el Programa.");
        }
        return resultado;
    }
    @Override
    public boolean RegistrarAsistencia(AsistenciaDTO objAsistencia) throws RemoteException{     
        System.out.println("***En registrarAsistencia()...");
        boolean resultado = false;
        if (objAsistencia != null) {
            resultado = this.lstAsistencia.add(objAsistencia);
            int faltas = contarFaltas(objAsistencia.getIdPaciente());
            if (resultado) {
                System.out.println("Registro exitoso de asistencia.");
            }else{
                System.out.println("Registro de asistencia falló.");
            }
            if (faltas >= 3) {
                System.out.println("Alcanzó el numero maximo de faltas");
                System.out.println("Se borrarán valoracion y programa físico");
                eliminarValoracion(objAsistencia.getIdPaciente());
                eliminarPrograma(objAsistencia.getIdPaciente());
            }
        }
        return resultado;
    }
    @Override
    public ArrayList<AsistenciaDTO> consultarAsistencia(int id) throws RemoteException{
        System.out.println("***En consultarAsistencia()...");
        ArrayList<AsistenciaDTO> lstAsistencias = new ArrayList<AsistenciaDTO>();
        for (int i = 0; i < this.lstAsistencia.size(); i++) {
            if (this.lstAsistencia.get(i).getIdPaciente() == id) {
                lstAsistencias.add(this.lstAsistencia.get(i));
            }
        }
        return lstAsistencias;
    }
    @Override
    //métodos auxiliares
    public int contarFaltas(int id) throws RemoteException{
        System.out.println("***En contarFaltas()...");
        int contador = 0;
        for (int i = 0; i < this.lstAsistencia.size(); i++) {
            if (this.lstAsistencia.get(i).getIdPaciente() == id) {
                if(this.lstAsistencia.get(i).getObservacion().equals("No asiste")){
                    contador = contador + 1;
                } 
            }
        }
        return contador;
    }
    public int buscarValoracion(int id){
        int resultado =  -1;
        for (int i = 0; i < lstValoraciones.size(); i++) {
            if (this.lstValoraciones.get(i).getIdPaciente() == id) {
                resultado = i;
                break;
            }
        }
        return resultado;
    }
    public int buscarPrograma(int id){
        int resultado = -1;
        for (int i = 0; i < lstProgramas.size(); i++) {
            if (this.lstProgramas.get(i).getIdPaciente() == id) {
                resultado = i;
                break;
            }
        }
        return resultado;
    }  
    public boolean eliminarValoracion(int id){
        boolean resultado = false;
        for (int i = 0; i < this.lstValoraciones.size(); i++) {
            if(this.lstValoraciones.get(i).getIdPaciente() ==  id){
                this.lstValoraciones.remove(i);
                resultado = true;
                break;
            }
        }
        return resultado;
    }
    public boolean eliminarPrograma(int id){
        boolean resultado = false;
        for (int i = 0; i < this.lstProgramas.size(); i++) {
            if (this.lstProgramas.get(i).getIdPaciente() == id) {
                this.lstProgramas.remove(i);
                resultado = true;
                break;
            }
        }
        return resultado;
    }
}
