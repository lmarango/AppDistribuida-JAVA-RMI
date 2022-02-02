package s_gestion_usuarios.sop_rmi;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import cliente.sop_rmi.AdminCllbckInt;
import cliente.sop_rmi.FapCllbckint;
import java.rmi.RemoteException;
import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.UsuarioDTO;
import s_gestion_usuarios.utilidades.UtilidadesRegistroC;
import s_seguimiento_usuarios.dto.*;
import s_seguimiento_usuarios.sop_rmi.*;

public class GesUsuariosImpl extends UnicastRemoteObject implements GesUsuariosInt{
	//Atributos
	private ArrayList<PersonalDTO> personal;
	private ArrayList<UsuarioDTO> usuarios;
	private int contador=0;
	private int contUsuarios=0;
	private GestionNotificacionesInt objReferenciaRemota;
	private AdminCllbckInt objCllbckAdmin;
	private FapCllbckint objCllbckFAP;
	private String Admint_tID = "CC";
	private int idAdmin = 0;
	private String nombreAdmin = "Administrador del sistema";
	private String userAdmin = "admin";
	private String admOcupacion = "admin";
	private String passAdmin = "12345";
	
	//Constructor
	public GesUsuariosImpl() throws RemoteException{
		super();
		this.personal =  new ArrayList<PersonalDTO>();
		this.usuarios = new ArrayList<UsuarioDTO>();
		PersonalDTO admin = new PersonalDTO(Admint_tID, idAdmin, nombreAdmin, admOcupacion, userAdmin, passAdmin);
		personal.add(admin);
	}
	//Metodos remotos
	@Override
	public boolean registrarPersonal(PersonalDTO objUsuario) throws RemoteException {
		System.out.println("Entrando a registral Personal...");
		boolean bandera = false;

		if (contador < 3) {
			bandera = personal.add(objUsuario);
			if (bandera) {
				contador=contador+1;
				System.out.println("Personal Registrado! ");	
			}
		}
		return bandera;
	}
	@Override
	public PersonalDTO consultarPersonal(int id) throws RemoteException {
		PersonalDTO resultado = null;
		System.out.println("Entrando a consultar personal...");
		int bandera = buscarPersonal(id);
		if (bandera != -1)
			resultado = personal.get(bandera);
		return resultado;
	}
	@Override
	public boolean registrarUsuario(UsuarioDTO objUsuario) throws RemoteException{
		System.out.println("***Entrando a registrar usuario...");
		boolean bandera = false;

		if(contUsuarios < 4){
			bandera = usuarios.add(objUsuario);
			if (bandera) {
				contUsuarios=contUsuarios+1;
				System.out.println("Usuario " + objUsuario.getNombreCompleto() + " fue registrado!");	
				objCllbckFAP.informarIngreso(objUsuario.getNombreCompleto(), objUsuario.getId());
			}
		}
		return bandera;
	}
	@Override
	public UsuarioDTO consultarUsuario(int id) throws RemoteException{
		System.out.println("**Entrando a consultar usuario...");
		UsuarioDTO resultado = null;
		int bandera = buscarUsuario(id);
		if(bandera != -1){
			resultado = usuarios.get(bandera);
		}
		return resultado;
	}
	@Override
	public boolean abrirSesion(CredencialDTO objCredencial) throws RemoteException {
		boolean resultado = false;
		System.out.println("Invocando abrirSesion()...");
		if(objCredencial.getUsaurio().equals(this.userAdmin)){
			if (objCredencial.getClave().equals(this.passAdmin) && objCredencial.getId() == this.idAdmin) {
				System.out.println("Inicio de Sesión como administrador.");
				resultado = true;	
			}
		} else {			
			PersonalDTO persona = consultarPersonal(objCredencial.getId());
			if (persona != null) {
				if (objCredencial.getUsaurio().equals(persona.getUsuario()) && objCredencial.getClave().equals(persona.getClave()) && 
					objCredencial.getId() == persona.getId()) {
					resultado = true;
					if(persona.getOcupacion().equalsIgnoreCase("PAF")){
						objCllbckAdmin.informarIngreso(persona.getNombreCompleto(), persona.getId());
						NotificacionDTO objNotificacion = new NotificacionDTO(persona.getNombreCompleto(), persona.getOcupacion()); 
						objReferenciaRemota.enviarNotificacion(objNotificacion);
					}
				}
			}else{
				UsuarioDTO usuario = consultarUsuario(objCredencial.getId());
				if(usuario != null){
					resultado = true;
					System.out.println("Usuario " + usuario.getUsuario() + " ingresó al sistema.");
				}
			}
		}
		return resultado;
	}
	@Override
	public void registrarCallback(AdminCllbckInt objAdmin) throws RemoteException{
		System.out.println("**Invocando Registrar Callback para Admin");
		objCllbckAdmin=objAdmin;
	}
	@Override
	public void registrarCallback(FapCllbckint objFap) throws RemoteException{
		System.out.println("**Invocando Registrar Callback para FAP");
		objCllbckFAP = objFap;
	}
	/**
	 * Busca las credenciales en la lista del personal 
	 * @param objCredencial objeto de tipo credencial a comparar
	 * @return la posisción en la lista si se encuentra, de lo contrario -1
	 */
	public int buscarCredencial(CredencialDTO objCredencial){
		int resultado = -1;

		for (int i = 0; i < this.personal.size(); i++) {
			if((objCredencial.getUsaurio() == this.personal.get(i).getUsuario()) && 
				(objCredencial.getClave() == this.personal.get(i).getClave())){
				resultado = i;
				break;
			}
		}
		return resultado;
	}
	/**
	 * Busca el usuario personal en el vector por el id
	 * @param id ID a buscar en la lista
	 * @return La posición en la lista si se encontro, de lo contrario -1
	 */
	public int buscarPersonal(int id){
		int resultado = -1;
		for (int i = 0; i < this.personal.size(); i++) {
			if (this.personal.get(i).getId() == id) {
				resultado = i;
				break;
			}
		}
		return resultado;
	}
	/**
	 * Vusaca el usuario personal en el vector por el id
	 * @param id ID a buscar en la lista
	 * @return La posición en la lista si se encontro, de lo contrario -1
	 */
	public int buscarUsuario(int id){
		int resultado = -1;
		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getId() == id) {
				resultado = i;
				break;
			}
		}
		return resultado;
	}
	/**
	 * Consulta la referencia remota dad una IP y PUerto
	 * @param direccionIpRMIRegistry IP registrada a consultar
	 * @param numPuertoRMIRegistry Puerto registrado aconsultar
	 */
	public void consultarReferenciaRemota(String direccionIpRMIRegistry, int  numPuertoRMIRegistry){
		System.out.println(" ");
		System.out.println("Desde consultarReferenciaRemotaDeNotificacion()...");
		this.objReferenciaRemota= (GestionNotificacionesInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,
		numPuertoRMIRegistry, "ObjetoRemotoNotificacion");
	}
	@Override
	public int retContPersonal(){
		System.out.println("***Contar Personal");
		return this.contador;
	}
	@Override
	public int retContUsuarios(){
		System.out.println("***Contar usuarios");
		return this.contUsuarios;
	}
}
