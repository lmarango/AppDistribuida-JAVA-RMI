package s_seguimiento_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import s_seguimiento_usuarios.dto.NotificacionDTO;

public interface GestionNotificacionesInt extends Remote{
    void enviarNotificacion(NotificacionDTO objDTO2) throws RemoteException;
};