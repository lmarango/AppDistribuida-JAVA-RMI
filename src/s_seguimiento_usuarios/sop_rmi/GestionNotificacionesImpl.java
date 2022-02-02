package s_seguimiento_usuarios.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import s_seguimiento_usuarios.dto.NotificacionDTO;

public class GestionNotificacionesImpl extends UnicastRemoteObject implements GestionNotificacionesInt {

    public GestionNotificacionesImpl() throws RemoteException {
        super();
    }

    @Override
    public void enviarNotificacion(NotificacionDTO objNotificacion) throws RemoteException {
        System.out.println("El usuario " + objNotificacion.getNombreCompleto() + 
        " y ocupacion " + objNotificacion.getOcupacion() + " ingreso al sistema.");
    }
    
}
