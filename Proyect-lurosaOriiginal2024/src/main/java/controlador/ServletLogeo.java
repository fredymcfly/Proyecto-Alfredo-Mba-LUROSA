package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class GLogeo
 */
public class ServletLogeo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession sesion;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogeo() {
        super();
        // TODO Auto-generated constructor stub
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
		
		HttpSession sesion = request.getSession();
		//PARA CERRAR 
		//sesion.invalidate();
		//response.sendRedirect("CatalogoPrincipal.html");
		String nombreUsuario =request.getParameter("nombreUsuario");
		//String email = request.getParameter("email");
		String contrasena =request.getParameter("contrasena");
		
		Usuario user = new Usuario (nombreUsuario ,"",contrasena);
		try {
			
			Usuario respuestaModelo = user.login();
			
			//recibimos la respuesta del login
			//si el usuarioregistrado recibido es nulo, el login no es valido
			//si el usuario registrado no es nulo, entonces comprobaremos si el usuario es un admin o no
			if(respuestaModelo == null)
			{
				response.sendRedirect("IniciarSesion.html");
			}
			else
			{
				sesion = request.getSession();
				
				//Meto los datos que necesito dentro de la sesion, en este caso el id_Login y el is_Admin
				sesion.setAttribute("id", respuestaModelo.getId());
				
				sesion.setAttribute("idUsuario",respuestaModelo.getId());
				//PARA PREGUNTAR POR UNA SESION VALIDA
				//sesion.getAttribute("idUsuario");
				
				//si el 
				if(respuestaModelo.isEsadmin())
				{ // es administrador
					response.sendRedirect("adminArea.html");
				}
				else
				{
					//es cliente
					response.sendRedirect("CatalogoPrincipal.html");
				}
				
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
