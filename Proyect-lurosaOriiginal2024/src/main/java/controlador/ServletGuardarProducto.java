package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServetLeerProducto
 */
public class ServletGuardarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGuardarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
			
	}
		/*
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			PrintWriter out = response.getWriter();
			// recibo todos los parrametros que me llegan del formulario editar
			int idProducto = Integer.parseInt(request.getParameter("idProducto"));			
			String nombreProducto = request.getParameter("nombreProducto");
			String descripcion = request.getParameter("descripcion");
			int precio = Integer.parseInt(request.getParameter("precio"));
			int stock = Integer.parseInt(request.getParameter("stock"));			
			
			Producto miProducto = new Producto(idProducto,1, nombreProducto,descripcion, precio,stock);
			Producto productoActualizado = miProducto.editarProducto();
			if(productoActualizado == null)
			{
				//out.print("KO");
				response.sendRedirect("editarProducto.html");
			}
			else
			{
				response.sendRedirect("adminArea.html");			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
