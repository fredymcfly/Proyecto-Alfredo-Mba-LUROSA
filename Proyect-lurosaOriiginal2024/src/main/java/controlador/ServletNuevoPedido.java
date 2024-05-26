package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletNuevoPedido
 */
public class ServletNuevoPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNuevoPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
        try {
        	
        	PrintWriter out = response.getWriter();	
        	
        	HttpSession sesion = request.getSession();
        	Object idUsuario = sesion.getAttribute("idUsuario");
        	
        	if(idUsuario != null)
        	{						
				String idProducto = request.getParameter("idProducto");						
				Producto miProducto = new Producto();						
				//llamamos al metodo leer del objeto de negocio Producto
				String resultado = miProducto.leerProducto(Integer.parseInt(idProducto));
					
				out.print(resultado);
				System.out.println(resultado);
        	}
        	else
        	{
        		out.print("");
        	}
            
        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e.getMessage()); // Se imprime un mensaje de error en la consola si ocurre una excepción SQL
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
