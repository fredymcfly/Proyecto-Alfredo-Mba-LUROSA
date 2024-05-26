package modelo;

import java.sql.SQLException;

import Dao.LogeoDao;

public class Usuario {
	 
	private int id;
	private String nombreUsuario;
	private String email;
	private String contrasena;
	private boolean Esadmin;
	
	
	public  Usuario(){
		
	}
	// Creo el constructor que llama a los setString a los get que estan en el dao
		public Usuario( String nombreUsuario,String email,String contrasena) {
			
			this.nombreUsuario=nombreUsuario;
			this.email = email;
			this.contrasena = contrasena;
			;
		}
	
	
	
	
	
//--------------------------------------------------
	public Usuario(int id, String nombreUsuario, String contrasena, String email, boolean esadmin) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.email = email;
		Esadmin = esadmin;
	}
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}



	public boolean isEsadmin() {
		return Esadmin;
	}
	
	

	public void setEsadmin(boolean esadmin) {
		Esadmin = esadmin;
	}

	
	// login
	public Usuario login() throws SQLException 
	{
		Usuario respuestaDAO = LogeoDao.getinstance().login(this);
		
		return respuestaDAO;
	}
	
//-----------------Insertar--------------------//
	//metodo que llama a la insercion de bd
	
	public Usuario insertar() throws SQLException {
	// que llamara al metodo insertar del dao que tiene el patron singelton
	// codigo optimizado
		
		Usuario respuestaDAO = LogeoDao.getinstance().insertar(this);
		
		return respuestaDAO;
	/* Código valido:
	 * LogeoDao dao = new LogeoDao();
	 * dao.insertar(this);
	 */
	
	}
//---------------Actualizar-Modificar------------//	

	public void update() {
	// mreamos metodo llamamos al objeto y llamar al metodo update
		
		//LogeoDao.getinstance().update(this);
	}
	
	@Override
	public String toString() {
		return "UsuarioRegistrado [id=" + id + ", nombreUsuario=" + nombreUsuario +  ", email=" + email + ", contrasena=" + contrasena
				+ ", Esadmin=" + Esadmin + "]";
	}
	
	public void eliminarLogin() throws SQLException {
		LogeoDao borrar = new LogeoDao();
		borrar.elimLogin(this);
	}
}

