package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.Usuario;

import java.sql.SQLException;
import java.io.IOException;
import Dao.ProductoDao;
/**
 * Servlet implementation class ServletCrearProducto
 */
public class ServletCrearProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCrearProducto() {
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
		String nombreproducto=request.getParameter("nombreProducto");
		String descripcion=request.getParameter("descripcion");
		Integer precio = Integer.parseInt(request.getParameter("precio"));
		Integer stock = Integer.parseInt(request.getParameter("stock"));
		
		Producto misproductos = new  Producto (nombreproducto,descripcion,precio,stock);

		System.out.println(misproductos.toString());
		try {
			
			
			
		Producto pro = new Producto (nombreproducto,descripcion,precio,stock);			
		Producto respuestaModelo =pro.insertar();	
		if(pro != null)
		{
			response.sendRedirect("adminArea.html");
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
	}

}
