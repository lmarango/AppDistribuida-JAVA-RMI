package cliente.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdminCllbckImpl extends UnicastRemoteObject implements AdminCllbckInt{

    public AdminCllbckImpl() throws RemoteException{
        super();
    }
    @Override
    public void informarIngreso(String nombreC, int id) throws RemoteException {
        System.out.println("***En Informar Ingreso...");
        System.out.println("***El usuario " + nombreC + " identificado con id " + id + " ingreso a la aplicacion***");
    }
    
}
