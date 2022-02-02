
package s_seguimiento_usuarios;

import java.rmi.RemoteException;

import s_seguimiento_usuarios.sop_rmi.GestionNotificacionesImpl;
import s_seguimiento_usuarios.sop_rmi.GestionPacientesImpl;
import s_seguimiento_usuarios.utilidades.UtilidadesConsola;
import s_seguimiento_usuarios.utilidades.UtilidadesRegistroS;

public class ServidorDeObjetos2{
    public static void main(String args[]) throws RemoteException{
        int numPuertoNS = 0;
        String direccionNS = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el n_s");
        direccionNS = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el n_s");
        numPuertoNS = UtilidadesConsola.leerEntero();

        GestionNotificacionesImpl objRemoto = new GestionNotificacionesImpl();
        GestionPacientesImpl objRemoto2 = new GestionPacientesImpl();
        try
        {
           UtilidadesRegistroS.arrancarNS(direccionNS,numPuertoNS);
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionNS, numPuertoNS, "ObjetoRemotoNotificacion");
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto2, direccionNS, numPuertoNS, "ObjetoRemotoUsuarios");
        } catch (Exception e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
    }
}
