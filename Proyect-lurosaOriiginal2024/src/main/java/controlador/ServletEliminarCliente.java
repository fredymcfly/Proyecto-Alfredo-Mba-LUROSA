package controlador;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Usuario;

/**
 * Servlet implementation class ServletEliminarCliente
 */
public class ServletEliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarCliente() {
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
        int idBorrar = Integer.parseInt(request.getParameter("idCliente"));
        
        // Eliminar cliente de la tabla 'clientes'
        try {
        	Cliente cliente = new Cliente();
        	cliente.setIdCliente(idBorrar);
        	cliente.borrarCliente();
        	           
            System.out.println("Usuario borrado correctamente de la tabla clientes"); // Se imprime un mensaje de éxito en la consola
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario de la tabla usuarios: " + e.getMessage()); // Se imprime un mensaje de error en la consola si ocurre una excepción SQL
        }
        
     // Eliminar usuario de la tabla 'usuario'
        try {
            //LogeoDao elimLog = new LogeoDao(); // Se instancia un objeto DaoLogin para interactuar con la tabla 'login'
            Usuario login = new Usuario(); // Se crea un objeto Login para representar al usuario
            login.setId(idBorrar); // Se establece el ID del usuario a borrar en el objeto Login
            login.eliminarLogin();
            
            //LogeoDao.elimLogin(login); // Se llama al método eliminarLog del objeto DaoLogin para eliminar el usuario de la tabla 'login'
            System.out.println("Usuario borrado correctamente de la tabla usuarios"); // Se imprime un mensaje de éxito en la consola
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario de la tabla usuarios: " + e.getMessage()); // Se imprime un mensaje de error en la consola si ocurre una excepción SQL
        }
        response.sendRedirect("adminArea.html");
    }
}
	


