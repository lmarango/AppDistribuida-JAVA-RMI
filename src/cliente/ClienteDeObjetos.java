package cliente;

import cliente.utilidades.UtilidadesRegistroC;
import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.UsuarioDTO;
import s_gestion_usuarios.sop_rmi.GesUsuariosInt;
import s_seguimiento_usuarios.dto.*;
import s_seguimiento_usuarios.sop_rmi.GestionPacientesInt;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import cliente.sop_rmi.*;
import cliente.utilidades.UtilidadesConsola;

public class ClienteDeObjetos
{
	private static GesUsuariosInt objRemoto;
	private static GestionPacientesInt objRemoto2;

	public static void main(String[] args)
	{
            int numPuertoNS= 0;
            String direccionIpNS = "";

            System.out.println("Cual es el la dirección ip donde se encuentra  el N_S");
            direccionIpNS = UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el N_S");
            numPuertoNS = UtilidadesConsola.leerEntero();

            objRemoto = (GesUsuariosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpNS,numPuertoNS, "ObjetoRemotoPersonal");
			objRemoto2 = (GestionPacientesInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpNS,numPuertoNS, "ObjetoRemotoUsuarios");
            MenuPrincipal();

	}

	private static void MenuPrincipal() {
		int op = 0;
		do
        {
            System.out.println("===Menu Principal===");
            System.out.println("|1. Abrir Sesion	   |");			
            System.out.println("|2. Salir			   |");
			System.out.println("========================");
			System.out.print("Ingrese su opcion: ");

            op = UtilidadesConsola.leerEntero();

			switch(op)
			{
			case 1:
				login();
				break;
			case 2:
				System.out.println("Saliendo del programa...");
				break;

			default:
				System.out.println("Opcion invalida. Intente nuevamente.");
			}    
		}while(op != 2);
	}
	//Método de logueo
	private static void login(){
		String varUser = "";
		String varPass = "";
		int varID = -1;
		boolean login = false;
		System.out.println("=== INICIAR SESION ===");
		System.out.println("Usuario: ");
		varUser = UtilidadesConsola.leerCadena();
		System.out.println("Clave: ");
		varPass = UtilidadesConsola.leerCadena();
		do{
			System.out.println("ID: ");
			varID = UtilidadesConsola.leerEntero();
		}while(varID < 0);

		CredencialDTO varObjCredencial = new CredencialDTO(varUser, varPass, varID);
		try {
			login = objRemoto.abrirSesion(varObjCredencial);
		} catch (RemoteException e) {
			System.out.println("Error al abrir Sesion. " + e.getMessage());
		}
		if (login) {
			System.out.println("Iniciando Sesion...");
			if(varUser.equals("admin")){
				try {
					System.out.println("***Registrando CallBack...");
					AdminCllbckImpl objAdmin = new AdminCllbckImpl();
					objRemoto.registrarCallback(objAdmin);
				} catch (RemoteException e) {
					System.out.println("Error: Callback no se pudo completar " + e.getMessage());
				}
				MenuAdm();
			}else{
				try {
					PersonalDTO objPersonal = objRemoto.consultarPersonal(varID);
					if(objPersonal != null){
						if (objPersonal.getOcupacion().equals("Secretaria")){
							MenuSec();
						}
						if (objPersonal.getOcupacion().equals("PAF")){
							System.out.println("*** Registrando Callback Fap");
							FapCllbckImpl objFap =  new FapCllbckImpl();
							objRemoto.registrarCallback(objFap);
							MenuPAF();               
						}
					}else{
						UsuarioDTO objUsuario = objRemoto.consultarUsuario(varID);
						if(objUsuario != null){
							MenuUser(objUsuario.getId());
						}
					}						
				} catch (RemoteException e) {
					System.out.println("Error al consutar usuario...");
				}
			}
		} else{
			System.out.println("Datos erroneos, verifiquelos e intente nuevamente");
		}
	}
	//Menú de la secretaria
	private static void MenuSec(){
		int opcion = 0;
		do
		{
			System.out.println("===Menu Secretaria===");
			System.out.println("|1. Registrar usuario  |");			
			System.out.println("|2. Consultar usuario  |");
			System.out.println("|3. Salir			   |");
			System.out.println("========================");
			System.out.println("Ingrese su opcion: ");

			opcion = UtilidadesConsola.leerEntero();

			switch(opcion)
			{
				case 1:
					RegistrarUsuario();
					break;
				case 2:
				    ConsultarUsuario();
					break;	
				case 3:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
			}
		}while(opcion != 3);
	} 
	//Métodos de la secretaria
	private static void RegistrarUsuario(){
		try{
			System.out.println("==== Registrar Usuario ====");
			if (objRemoto.retContUsuarios() < 5) {
				System.out.println("Ingrese el nombre del paciente");
				String nombreUsuario = UtilidadesConsola.leerCadena();
				int id = 0;
				do{
					System.out.println("Ingrese la identificacion");
					id = UtilidadesConsola.leerEntero();
				}while(id < 1);
				String facultad="";
				int opcion = 0;
				do{
					System.out.println("===Facultad a la que pertenece===");
					System.out.println("|1. FACARTES			 	    |");			
					System.out.println("|2. FACAGRO						|");
					System.out.println("|3. FSALUD						|");
					System.out.println("|4. FHUMANAS					|");
					System.out.println("|5. FCCEA						|");
					System.out.println("|6. FACNED						|");
					System.out.println("|7. FDERECHO					|");
					System.out.println("|8. FCIVIL						|");
					System.out.println("|9. FIET						|");
					System.out.println("============================");
					System.out.print("Ingrese su opcion: ");

					opcion = UtilidadesConsola.leerEntero();
				}while(opcion < 1 || opcion > 9);
				switch (opcion) {
					case 1:
						facultad = "FACARTES";
						break;
					case 2:
						facultad = "FACAGRO";
						break;
					case 3:
						facultad = "FSALUD";
						break;
					case 4:
						facultad = "FHUMANAS";
						break;
					case 5:
						facultad = "FCCEA";
						break;
					case 6:
						facultad = "FACNED";
						break;
					case 7:
						facultad = "FDERECHO";
						break;
					case 8:
						facultad = "FCIVIL";
						break;
					case 9:
						facultad = "FIET";
						break;
					default:
						System.out.println("ERROR. La fulctad no está registrada");
						break;
				}
				do{
					System.out.println("=== Tipo de usuario ===");
					System.out.println("|1. Docente		      |");			
					System.out.println("|2. Administrativo    |");
					System.out.println("=======================");
					System.out.print("Ingrese su opcion: ");
					opcion = UtilidadesConsola.leerEntero();
				}while(opcion < 1 || opcion > 2);
				String tipoUsuario="";
				switch (opcion) {
					case 1:
						tipoUsuario = "Docente";
						break;
					case 2:
						tipoUsuario = "Administrativo";
						break;
				}
				/*
				String fechaIngreso = "";
				do{
					System.out.println("=== Fecha de Ingreso ===");
					System.out.println("formato:dd/mm/aa");
					fechaIngreso = UtilidadesConsola.leerCadena();
				}while(fechaIngreso.length() != 8);
				*/
				Calendar fecha = new GregorianCalendar();
				int dia = fecha.get(Calendar.DAY_OF_MONTH);
				int mes = fecha.get(Calendar.MONTH) + 1;
				int anio = fecha.get(Calendar.YEAR);
				String fechaIngreso = dia+"/"+mes+"/"+anio;
				do{
					System.out.println("=== Patologia usuario ===");
					System.out.println("|1. Ingresar Patologia  |");			
					System.out.println("|2. Ninguna 	        |");
					System.out.println("=========================");
					System.out.print("Ingrese su opcion: ");
					opcion = UtilidadesConsola.leerEntero();
				}while(opcion < 1 || opcion > 2);
				String patologia="";
				switch (opcion) {
					case 1:
						do{
							patologia = UtilidadesConsola.leerCadena();
						}while(patologia.length()<1);
						break;
					case 2:
						patologia = "Ninguna";
						break;
				}
				String usuario="";
				do{
					System.out.println("Ingrese el usuario: ");
					usuario = UtilidadesConsola.leerCadena();
				}while(usuario.length() < 8);
				String clave="";
				do{
					System.out.println("Ingrese la contraseña: ");
					clave = UtilidadesConsola.leerCadena();
				}while(clave.length() < 8);

				UsuarioDTO nuevoUsuario = new UsuarioDTO(nombreUsuario, id, facultad, tipoUsuario, fechaIngreso, patologia, clave, usuario);
				boolean valor = objRemoto.registrarUsuario(nuevoUsuario);//invocación al método remoto
				if(valor)
					System.out.println("Registro realizado satisfactoriamente...");
				else
					System.out.println("no se pudo realizar el registro...");
			}else{
				System.out.println("Se ha alcanzado el máximo de regitros de pacientes");
			}
		}
		catch (RemoteException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	private static void ConsultarUsuario(){
		System.out.println("==== Consultar Usuario ====");
		System.out.println("Ingrese la identificacion: ");
		int id = UtilidadesConsola.leerEntero();

		try {
			UsuarioDTO objUsuario = objRemoto.consultarUsuario(id);
			if (objUsuario != null) {
				System.out.println("Usario recuperado exitosamente!");
				System.out.println("=== INFO DEL USUARIO ===!");
				System.out.println("Nombre: " + objUsuario.getNombreCompleto());
				System.out.println("ID: " + objUsuario.getId());
				System.out.println("Tipo: " + objUsuario.getTipoUsuario());
				System.out.println("Facultad: " + objUsuario.getFacultad());
				System.out.println("Usuario: " + objUsuario.getUsuario());
				System.out.println("Patologia: " + objUsuario.getPatologia());
				System.out.println("Fecha de Ingreso: " + objUsuario.getFechaIngreso());
				System.out.println("==== FIN CONSULTA ====");
			} else {
				System.out.println("Usuario no encontrado...");
			}
		} catch (RemoteException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	//Menú PAF
	private static void MenuPAF(){
		int opcion = 0;
		do
		{
			System.out.println("========Menu PAF=========");
			System.out.println("|1. Valorar             |");			
			System.out.println("|2. Programa Físico     |");
			System.out.println("|3. Registrar asistencia|");
			System.out.println("|4. Salir			    |");
			System.out.println("=========================");
			System.out.println("Ingrese su opcion: ");

			opcion = UtilidadesConsola.leerEntero();

			switch(opcion)
			{
				case 1:
					Valorar();
					break;
				case 2:
					ProgramaFisico();
					break;	
				case 3:
					RegistrarAsistencia();
					break;
				case 4:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
			}

		}while(opcion != 4);

	} 
	//Métodos PAF
	private static void Valorar(){
		try {
			System.out.println("==== Valorar Paciente ====");
			int id=0;
			do{
				System.out.println("Ingrese la identificacion");
				id = UtilidadesConsola.leerEntero();
			}while(id < 1);
			if(objRemoto.consultarUsuario(id) != null){
				if (objRemoto2.consultarValoracion(id) == null) {
					Calendar fecha = new GregorianCalendar();
					int dia = fecha.get(Calendar.DAY_OF_MONTH);
					int mes = fecha.get(Calendar.MONTH) + 1;
					int anio = fecha.get(Calendar.YEAR);
					String fechaValoracion = dia+"/"+mes+"/"+anio;
					int fecCarReposo = 0;
					do{
						System.out.println("Frecuencia Cardiaca en reposo: ");
						fecCarReposo = UtilidadesConsola.leerEntero();
					}while(fecCarReposo < 1);
					int fecCarActiva = 0;
					do{
						System.out.println("Frecuencia Cardiaca Activa: ");
						fecCarActiva = UtilidadesConsola.leerEntero();
					}while(fecCarActiva < 1);
					int estatura = 0;
					do{
						System.out.println("Estatura(cm): ");
						estatura = UtilidadesConsola.leerEntero();
					}while(estatura < 1);
					int brazo = 0;
					do{
						System.out.println("Brazo(cm): ");
						brazo = UtilidadesConsola.leerEntero();
					}while(brazo < 1);
					int pierna = 0;
					do{
						System.out.println("Pierna(cm): ");
						pierna = UtilidadesConsola.leerEntero();
					}while(pierna < 1);
					int pecho = 0;
					do{
						System.out.println("Pecho(cm): ");
						pecho = UtilidadesConsola.leerEntero();
					}while(pecho < 1);
					int cintura = 0;
					do{
						System.out.println("Cintura(cm): ");
						cintura = UtilidadesConsola.leerEntero();
					}while(cintura < 1);
					String estado ="";
					int opcion=0;
					do
					{
						System.out.println("===Estado del Paciente===");
						System.out.println("|1. Saludable			   |");			
						System.out.println("|2. Sobrepeso 			   |");
						System.out.println("|3. Bajo de Peso		   |");
						System.out.println("============================");
						System.out.print("Ingrese su opcion: ");

						opcion = UtilidadesConsola.leerEntero();
					}while(opcion < 1 || opcion > 3);
					switch (opcion) {
						case 1:
							estado = "Saludable";
							break;
						case 2:
							estado = "Sobrepeso";
							break;
						case 3:
							estado = "Bajo de peso";
							break;
						default:
							break;
					}
					ValoracionDTO objValoracion= new ValoracionDTO(id, fechaValoracion, fecCarReposo, fecCarActiva, estatura, brazo, pierna, pecho, cintura, estado);
					boolean valor = objRemoto2.registrarValoracion(objValoracion);//invocación al método remoto
					if(valor)
						System.out.println("Registro realizado satisfactoriamente...");
					else
						System.out.println("no se pudo realizar el registro...");	
				}else{
					System.out.println("El paciente con id " + id + " ya tiene una valoración.");	
				}
			}else{
			System.out.println("El paciente con id " + id + " no se encuentra registrado.");
			}
		} catch (RemoteException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	private static void ProgramaFisico(){
		try {
			System.out.println("==== Programa Físico ====");
			int id=0;
			do{
				System.out.println("Ingrese la identificacion: ");
				id = UtilidadesConsola.leerEntero();
			}while(id < 1);
			if(objRemoto.consultarUsuario(id) != null){
				if (objRemoto2.consultarPrograma(id) == null) {
					if(objRemoto2.consultarValoracion(id) != null){
						String fechaIni = "";
						do {
							System.out.println("==== Fecha de Inicio del programa ====");
							System.out.println("Formato (dd/mm/aa): ");
							fechaIni = UtilidadesConsola.leerCadena();
						} while (fechaIni.length() != 8);
						ProgramaFisicoDTO objProgSemana = null;
						ArrayList<ProgramaDTO> objProgramaSemanal = new ArrayList<ProgramaDTO>();
						for (int i = 0; i < 3; i++) {
							String nomEjercicio = "";
							int repeticiones = 0;
							int peso = 0;
							ArrayList<EjercicioDTO> tmplstEjercicio = new ArrayList<EjercicioDTO>();
							ProgramaDTO tmpPrograma = null;
							EjercicioDTO tmpEjecercicio = null;
							System.out.println("dia " + (i+1) + "de la semana.");
							for (int j = 0; j < 3; j++) {
								do {
									System.out.println("Nombre del ejercicio " + (j+1) + ":");
									nomEjercicio = UtilidadesConsola.leerCadena();
								} while (nomEjercicio.length() < 3);
								do {
									System.out.println("Repeticiones: ");
									repeticiones = UtilidadesConsola.leerEntero();
								} while (repeticiones < 1);
								do {
									System.out.println("Peso: ");
									peso = UtilidadesConsola.leerEntero();
								} while (peso < 3);
								tmpEjecercicio = new EjercicioDTO(nomEjercicio, repeticiones, peso);
								tmplstEjercicio.add(tmpEjecercicio);
							}
							tmpPrograma = new ProgramaDTO((i+1), tmplstEjercicio);
							objProgramaSemanal.add(tmpPrograma);
						}
						objProgSemana = new ProgramaFisicoDTO(id, fechaIni, objProgramaSemanal);
	
						boolean resultado = objRemoto2.regProgramaFisico(objProgSemana);
						if (resultado) {
							System.out.println("Se registró el programa con éxito");
						} else {
							System.out.println("ERROR: No se pudo realizar el registro");
						}
					}else{
						System.out.println("El paciente con id " + id + " no ha sido valorado.");	
					}	
				}else{
					System.out.println("El paciente con id " + id + " Ya tiene un programa registrado");	
				}
			}else{
				System.out.println("El paciente con id " + id + " no se encuentra registrado.");
			}
			objRemoto2.regProgramaFisico(null);
		} catch (RemoteException e) {
			System.out.println("ERROR: La opreción no se pudo completar. " + e.getMessage());
		}
	}
	private static void RegistrarAsistencia(){
		try {
			System.out.println("==== Registrar Asistencia ====");
			int id=0;
			do{
				System.out.println("Ingrese la identificacion: ");
				id = UtilidadesConsola.leerEntero();
			}while(id < 1);
			if(objRemoto.consultarUsuario(id) != null){
				if(objRemoto2.consultarValoracion(id) != null){
					if (objRemoto2.consultarPrograma(id) != null) {
						String fechaAsistencia = "";
						do {
							System.out.println("==== Fecha de asistencia ====");
							System.out.println("Formato (dd/mm/aa): ");
							fechaAsistencia = UtilidadesConsola.leerCadena();
						} while (fechaAsistencia.length() != 8);
						String observacion ="";
						int opcion=0;
						do
						{
							System.out.println("=== Observacion ===");
							System.out.println("|1. Ingresar excusa		   |");			
							System.out.println("|2. No asiste 			   |");
							System.out.println("|3. Ninguna 			   |");
							System.out.println("============================");
							System.out.print("Ingrese su opcion: ");
							opcion = UtilidadesConsola.leerEntero();
						}while(opcion < 1 || opcion > 3);
						switch (opcion) {
							case 1:
								System.out.print("Ingrese la excusa: ");
								observacion = UtilidadesConsola.leerCadena();
								break;
							case 2:
								observacion = "No asiste";
								break;
							case 3:
								observacion = "Ninguna";
								break;
						}
						AsistenciaDTO objAsistencia = new AsistenciaDTO(id, fechaAsistencia, observacion);
						boolean resultado = objRemoto2.RegistrarAsistencia(objAsistencia);
						int contador = objRemoto2.contarFaltas(id);
						if (resultado) {
							System.out.println("Se registró la asistencia con éxito!");
							if (contador >= 3) {
								System.out.println("Se han exedido el número de faltas.");
								System.out.println("El usuario con id " + id + " tendra que volver a rezalizar valoracion,\ny elaborar el programa físico.");
							}
						} else {
							System.out.println("ERROR :No se pudo registrar la asistencia.");
						}
					}else{
						System.out.println("El paciente con id " + id + " aun no tiene registrado el programa físico!");
					}
				}else{
					System.out.println("El paciente con id " + id + " aun no ha sido valorado!");
				}
			}
		} catch (RemoteException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	// Menú del administrador
	private static void MenuAdm(){
		int opcion = 0;
		do
		{
			System.out.println("===Menu Administrador===");
			System.out.println("|1. Registrar personal |");			
			System.out.println("|2. Consultar personal |");
			System.out.println("|3. Salir			   |");
			System.out.println("========================");
			System.out.print("Ingrese su opcion: ");

			opcion = UtilidadesConsola.leerEntero();

			switch(opcion)
			{
				case 1:
					Opcion1();
					break;
				case 2:
					Opcion2();
					break;	
				case 3:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción incorrecta");
			}
		}while(opcion != 3);

	} 
	//Métodos del administrador
	private static void Opcion1() 
    {
		try
        {
			System.out.println("==Registro de Personal==");
			if(objRemoto.retContPersonal() < 2){
				int opcion = 0;
				do
				{
					System.out.println("===Tido de Identificación===");
					System.out.println("|1. CC 					   |");			
					System.out.println("|2. Pasaporte 			   |");
					System.out.println("|3. Tarjeta identidad	   |");
					System.out.println("============================");
					System.out.print("Ingrese su opcion: ");

					opcion = UtilidadesConsola.leerEntero();
				}while(opcion < 1 || opcion > 3);

				String tipo_id = "";
				switch (opcion) {
					case 1:
						tipo_id = "CC";
						break;
					case 2:
						tipo_id = "PP";
						break;
					case 3:
						tipo_id = "TI";
						break;
					default:
						break;
				}
				int id=0;
				do
				{
					System.out.println("Ingrese la identificacion");
					id = UtilidadesConsola.leerEntero();
				}while(id < 1);
				
				String nombreCompleto="";
				do
				{
					System.out.println("Ingrese el nombre completo ");
					nombreCompleto = UtilidadesConsola.leerCadena();
				}while(nombreCompleto.length() < 2);

				do
				{
					System.out.println("===Ocupacion del personal===");
					System.out.println("|1. Secretaria			   |");			
					System.out.println("|2. PAF	 			       |");
					System.out.println("============================");
					System.out.print("Ingrese su opcion: ");

					opcion = UtilidadesConsola.leerEntero();
				}while(opcion < 1 || opcion > 2);
				String ocupacion="";
				switch (opcion) {
					case 1:
						ocupacion = "Secretaria";
						break;
					case 2:
						ocupacion = "PAF";
						break;
					default:
						break;
				}

				String usuario="";
				do
				{
					System.out.println("Ingrese el usuario: ");
					usuario = UtilidadesConsola.leerCadena();
				}while(usuario.length() < 8);

				String clave="";
				do
				{
					System.out.println("Ingrese la contraseña: ");
					clave = UtilidadesConsola.leerCadena();
				}while(clave.length() < 8);

				PersonalDTO objUsuario= new PersonalDTO(tipo_id, id, nombreCompleto, ocupacion, usuario, clave);

				boolean valor = objRemoto.registrarPersonal(objUsuario);//invocación al método remoto
				if(valor)
					System.out.println("Registro realizado satisfactoriamente...");
				else
					System.out.println("no se pudo realizar el registro...");

			} else{
				System.out.println("Cantidad máxima de registros alcanzada.");
			}
        }
        catch(RemoteException e)
        {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }
	private static void Opcion2(){
		try
        {
            System.out.println("===Consultar Personal===");
            System.out.println("Ingrese la identificacion");
            int id = UtilidadesConsola.leerEntero();			

            PersonalDTO objUsuario= objRemoto.consultarPersonal(id);
            if(objUsuario!=null)
            {
				System.out.println("Tipo ID: " + objUsuario.getTipo_id());
				System.out.println("No. ID: " + objUsuario.getId());
				System.out.println("Nombre Completo: " + objUsuario.getNombreCompleto());
				System.out.println("Ocupación: " + objUsuario.getOcupacion());
				System.out.println("Usuario: " + objUsuario.getUsuario());
            }
            else
				System.out.println("Usuario no encontrado");
        }
        catch(RemoteException e)
        {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
	}
	//Menú de usuario
	private static void MenuUser(int id){
		int opcion = 0;
		do{
			System.out.println("===== Menú Usuario =====");
			System.out.println("|1. Consultar Valoracion|");			
			System.out.println("|2. Consultar asistencia|");
			System.out.println("|3. Salir			    |");
			System.out.println("========================");
			System.out.println("Ingrese su opcion: ");
			opcion = UtilidadesConsola.leerEntero();
			switch(opcion)
			{
				case 1:
						ConsultarValoracion(id);
						break;
				case 2:
						ConsultarAsistencia(id);
						break;	
				case 3:
						System.out.println("Saliendo...");
						break;
				default:
						System.out.println("Opción incorrecta");
			}
		}while(opcion != 3);
	}
	//Metodos usuario
	public static void ConsultarValoracion(int id){
		try {
			System.out.println("==== Consultar Valoracion ====");
			System.out.println("==== Valoración del paciente " +  id + " ====");
			System.out.println("==== Resultados de consulta ====");
			ValoracionDTO objVAloracion = objRemoto2.consultarValoracion(id);
			if (objVAloracion != null) {
				System.out.println("Fecha de valoración: " + objVAloracion.getFechaValoracion());
				System.out.println("Frecuencia cardiaca en reposo: " + objVAloracion.getFecCardiacaReposo());
				System.out.println("Frecuencia cardiaca activa: " + objVAloracion.getFecCardiacaActiva());
				System.out.println("Estatura: " + objVAloracion.getEstatura() + " cm.");
				System.out.println("Medida del brazo: " + objVAloracion.getBrazo() + " cm.");
				System.out.println("Medida de la pierna: " + objVAloracion.getPierna() + " cm.");
				System.out.println("Medida del pecho: " + objVAloracion.getPecho() + " cm.");
				System.out.println("Medida de la cintura: " + objVAloracion.getCintura() + " cm.");
				System.out.println("Estado: " + objVAloracion.getEstado());
			}else{
				System.out.println("No hay datos para su consulta!");
			}
		} catch (RemoteException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	public static void ConsultarAsistencia(int id){
		try {	
			System.out.println("==== Consultar Asistencia ====");
			System.out.println("=== Asistencia del paciente con id " + id + " ===");
			System.out.println("==== Resultados de consulta ====");
			if (objRemoto2.consultarValoracion(id) != null) {
				if (objRemoto2.consultarPrograma(id) != null) {
					ArrayList<AsistenciaDTO> listaAsistencia = new ArrayList<AsistenciaDTO>();
					listaAsistencia = objRemoto2.consultarAsistencia(id);
					if (listaAsistencia.size() > 0) {
						for (int i = 0; i < listaAsistencia.size(); i++) {
							System.out.println("Registro numero " + (i+1));
							System.out.println("Fecha de Asistencia " + listaAsistencia.get(i).getFechaDeAsistencia());
							System.out.println("Observacion " + listaAsistencia.get(i).getObservacion());
							System.out.println("===================================");
							System.out.println("");
						}
					}else{
						System.out.println("No posee registro de asistencia!");
					}		
				}else{
					System.out.println("Para acceder al registro el PAF debe primero elaborar su programa!");
				}
			}else{
				System.out.println("Para acceder al registro debe ser valorado primero!");
			}
		} catch (RemoteException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}
