package Dao;
import modelo.Usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LogeoDao {
	/*
	 * public static Connection con = null;: Esta línea declara una variable estática llamada con de tipo Connection (presumiblemente importada de algún paquete como java.sql). Esta variable se inicializa como null, lo que significa que aún no se ha establecido una conexión a la base de datos.
private static DaoLogin instance = null;: Aquí se declara una variable estática llamada instance de tipo DaoLogin. Esta variable también se inicializa como null. Es común utilizar esta técnica para implementar el patrón Singleton, asegurándose de que solo haya una instancia de DaoLogin en la aplicación.
public DaoLogin() throws SQLException {: Este es el constructor de la clase DaoLogin. El constructor puede lanzar una excepción del tipo SQLException, lo que sugiere que este constructor puede estar involucrado en la inicialización de la conexión a la base de datos, y puede fallar si hay algún problema de conexión.
this.con = BDConexion.getConexion();: En esta línea, se llama al método getConexion() de una clase llamada BDConexion (probablemente una clase personalizada que encapsula la lógica de conexión a la base de datos). El valor devuelto por este método se asigna a la variable con, que es la conexión a la base de datos que se utilizará para realizar operaciones de base de datos en esta clase.
En resumen, este código define una clase DaoLogin que tiene una conexión a la base de datos (con) y probablemente implementa algún tipo de lógica relacionada con la autenticación o inicio de sesión de usuarios.

	 */
	 public static Connection con = null; // !! preguntar que pasa si lo privatizas 
	private static LogeoDao instance = null;
	
	// aqui se crea una instancia de la clase Logeodao que es una clase que maneja el acceso a la 
	// base de datos para operaciones relacionadas con el login-registro de ususario 
	public  LogeoDao () throws SQLException {
		this.con = DBConexion.getConexion();
	}
///-------------------------------------------//
	//metodo estatico que me devuelbe una misma clase en la que estoy tipo ola 
	/*public static LogeoDao getinstance()  {
		// siesto sige siendo nulo 
		if (instance==null) {
		// quiero que sea igual a logeo da a osea asi mismo	
			instance= new LogeoDao();
	}
	return instance;
	*/
//-----------------------------------------------------------------------
	
	// en este bloque de codigo se hace el metodo silgenton en el cual  cuando llame al metodo 
	//estatico de esta clase me devuelva propia clase para disponer despues todos sus metodos 
	// sin tener que instanciar de nuevo 
	
	 /**
     * Devuelve la instancia única de LogeoDao, implementando el patrón Singleton.
     * 
     * @return la instancia de LogeoDao.
     * @throws SQLException si ocurre un error al crear la instancia.
     */
	public static LogeoDao getinstance() throws SQLException {
	 if (instance==null) {
		 instance= new LogeoDao();
		 
	 }
	 return instance;
	}
	 /**
     * Genera el hash MD5 de una cadena de texto.
     * 
     * @param input la cadena de texto a hashear.
     * @return el hash MD5 de la cadena de texto.
     */
	private String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * Realiza el inicio de sesión del usuario.
     * 
     * @param user el usuario que intenta iniciar sesión.
     * @return el usuario con los detalles completos si el inicio de sesión es exitoso, null en caso contrario.
     */
	public Usuario login(Usuario user) {
		try {
			
			//en el caso de login, usamos una sentencia sql para consultar el usuario
			//OBTENEMOS TODOS LOS CAMPOS 
			PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE nombreUsuario = ? and contrasena = ?");
		
			String passMD5 = getMD5(user.getContrasena());
			
			ps.setString(1, user.getNombreUsuario());			//
			ps.setString(2, passMD5);
			// utilizamos execute para realizar el envio que solo devuelve el numero de tuplas afectadas
			ResultSet filas= ps.executeQuery();
			
			//inicializamos dos variables que tomaran despues el valor del usuario leido si es correcto
			String nombre_Usuario = "";
			boolean isAdmin = false;
			
			while(filas.next())
			{
				//si se han encontrado registros, establecemos los datos para el objeto UsuarioRegistrado
				nombre_Usuario = filas.getString("nombreUsuario");	
				user.setNombreUsuario(nombre_Usuario);				
				isAdmin = filas.getBoolean("EsAdmin");		
				user.setEsadmin(isAdmin);
				user.setId(filas.getInt("id"));
			}
			
			ps.close();
			
			if(nombre_Usuario == "")
			{
				//si la variable nombreUsuario sigue vacia en este punto, significa que no se ha encontrado el usuario
				user = null;				
			}
			
			return user;
		
		} catch (SQLException e) {
			user = null;
			//si se provoca algun error en la insercion , devolvemos false
			return user;
		}
	}
	 /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * @param u el usuario a insertar.
     * @return el usuario insertado con su ID asignado.
     */
	public Usuario insertar(Usuario u) {
	
		//ponemos bloque try-catch para controlar cualquier excepcion de sql 
		try {
		
			PreparedStatement ps = con.prepareStatement("INSERT usuarios (nombreUsuario,email,contrasena, esAdmin) VALUES (?,?,?,false)", PreparedStatement.RETURN_GENERATED_KEYS);
			// ponemos los parametros que desamos que guarden en la bd que el usuario introduzca 
			// que salen de nuestra clase creada 
			ps.setString(1, u.getNombreUsuario());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getContrasena());
			// utilizamos execute para realizar el envio que solo devuelve el numero de tuplas afectadas
			int filas = ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			
            if (generatedKeys.next()) {
                u.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Error al crear el usuario.");
            }
	        
			ps.close();
			
			//si finaliza correctamente la insercion, devolvemos true
			return u;
		
		} catch (SQLException e) {
			//si se provoca algun error en la insercion , devolvemos false
			return u;
		}
	}
		
	public void update() {
		
	}
	/**
     * Elimina un usuario de la base de datos.
     * 
     * @param s el usuario a eliminar.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
	public static void elimLogin(Usuario s) throws SQLException {
		String sql = "DELETE FROM usuarios WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, s.getId());
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
	
}
