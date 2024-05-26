package controlador;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;

/**
 * Servlet implementation class ServletEliminarProducto
 */
public class ServletEliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarProducto() {
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
		// Se obtiene el ID del usuario que se va a borrar desde los parámetros de la solicitud
        int idBorrar = Integer.parseInt(request.getParameter("idProducto"));
        
        // Eliminar cliente de la tabla 'productos'
        try {
        	Producto producto = new Producto();
        	producto.setIdProducto(idBorrar);
        	producto.borrarProducto();
        	           
            System.out.println("Usuario borrado correctamente de la tabla productos"); // Se imprime un mensaje de éxito en la consola
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto de la tabla productos: " + e.getMessage()); // Se imprime un mensaje de error en la consola si ocurre una excepción SQL
        }
        
        response.sendRedirect("adminArea.html");
    }
}
	


