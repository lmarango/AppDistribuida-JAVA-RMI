
package s_gestion_usuarios;

import java.rmi.RemoteException;

import s_gestion_usuarios.sop_rmi.GesUsuariosImpl;
import s_gestion_usuarios.utilidades.UtilidadesConsola;
import s_gestion_usuarios.utilidades.UtilidadesRegistroS;

public class ServidorDeObjetos1{
    public static void main(String args[]) throws RemoteException{
        int numPuertoNS;
        String direccionIpNS;
        System.out.println("Cual es el la dirección ip donde se encuentra  el N_S");
        direccionIpNS = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el N_S");
        numPuertoNS = UtilidadesConsola.leerEntero();
        GesUsuariosImpl objRemoto = new GesUsuariosImpl();
        objRemoto.consultarReferenciaRemota(direccionIpNS, numPuertoNS);
        try
        {
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpNS, numPuertoNS, "ObjetoRemotoPersonal");
        }catch (Exception e){
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
    }
}
