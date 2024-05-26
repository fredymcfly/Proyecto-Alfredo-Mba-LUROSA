package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServetLeerProducto
 */
public class ServletLeerProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession sesion;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLeerProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		
		HttpSession sesion = request.getSession();
    	Object idUsuario = sesion.getAttribute("idUsuario");
		
    	if(idUsuario != null) {
		try {	
			
			PrintWriter out = response.getWriter();
			
			String idProducto = request.getParameter("idProducto");
					
			Producto miProducto = new Producto();
					
			//llamamos al metodo leer del objeto de negocio Producto
			String resultado = miProducto.leerProducto(Integer.parseInt(idProducto));
				
			out.print(resultado);
			System.out.println(resultado);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		} else {
			response.sendRedirect("InicioSesion.html");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
