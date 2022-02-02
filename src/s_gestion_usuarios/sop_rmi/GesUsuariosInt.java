package s_gestion_usuarios.sop_rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import cliente.sop_rmi.AdminCllbckInt;
import cliente.sop_rmi.FapCllbckint;
import s_gestion_usuarios.dto.*;

public interface GesUsuariosInt extends Remote{
	
	public boolean registrarPersonal(PersonalDTO objUsuario) throws RemoteException;
	public PersonalDTO consultarPersonal(int id) throws RemoteException;
	public boolean registrarUsuario(UsuarioDTO objUsuario) throws RemoteException;
	public UsuarioDTO consultarUsuario(int id) throws RemoteException;
	public boolean abrirSesion(CredencialDTO objCredencial) throws RemoteException;
	public void registrarCallback(AdminCllbckInt objAdmin) throws RemoteException;
	public void registrarCallback(FapCllbckint objAdmin) throws RemoteException;
	public int retContPersonal() throws RemoteException;
	public int retContUsuarios() throws RemoteException;
}
