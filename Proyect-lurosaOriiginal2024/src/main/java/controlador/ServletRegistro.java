package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Usuario;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Servlet implementation class GLogeo
 */
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }
// Metodo para cifrar contraseña en el cual jasea la contraseña y la cifra
	public static String getMD5(String input) {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//datos para registro de usuario
		//1 hacer registro de usuario
		
		
		String nombreUsuario =request.getParameter("usuario");
		String email =request.getParameter("email");
		String contrasena = getMD5(request.getParameter("contrasena"));
		
		
		//2 hacer registro de cliente
		//datos para registro de cliente
		String nombre =request.getParameter("nombre"); 
	    String apellido =request.getParameter("apellido"); 
	    String direccion =request.getParameter("direccion");
	    String provincia =request.getParameter("provincia");
	    String pais =request.getParameter("pais");
	    String poblacion =request.getParameter("poblacion");
	    String telefono =request.getParameter("telefono");
		
	 
	
		
		try {
			
			//1 registramos el usuario, y obtenemos otro objeto usuario con el id ya establecido
			Usuario user = new Usuario (nombreUsuario ,email,contrasena);			
			Usuario respuestaModelo = user.insertar();		
//------------------------------------------------------			
			
			//2 registramos el cliente con el id de usuario recien creado
			Cliente miCliente = new Cliente(respuestaModelo.getId(), nombre,apellido,direccion,provincia,pais,poblacion,telefono);
			Cliente resultadoRegistro = miCliente.insertar();
			
			
			//recibimos la respuesta final del resultado de insertar un usuario
			//si es true ha sido exito y si no ha fallado el registro
			
			if(resultadoRegistro != null)
			{
				response.sendRedirect("CatalogoPrincipal.html");
			}
			else
			{
				response.sendRedirect("errorRegistro.html");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de conexión");
		}
		
		
		
		/*
		String nombre1 = request.getParameter("nombre");
		String email1 = request.getParameter("email");
		String contrasena1 = request.getParameter("contrasena");
		
		System.out.println(nombre1+email1+contrasena1);
		*/
	}

}
