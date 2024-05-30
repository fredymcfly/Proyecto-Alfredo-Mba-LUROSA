package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Pedido;
import modelo.Producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletActualizarPedido
 */
public class ServletActualizarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActualizarPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try {
			PrintWriter out = response.getWriter();
			// recibo todos los parrametros que me llegan del formulario editar
			int idPedido = Integer.parseInt(request.getParameter("idProducto"));	
			int idCliente = Integer.parseInt(request.getParameter("precio"));
			int cantidad = Integer.parseInt(request.getParameter("precio"));
			String estado = request.getParameter("nombreProducto");
			String fecha = request.getParameter("descripcion");
			int idProducto = Integer.parseInt(request.getParameter("stock"));			
			
			
			// CREA CONSTRUCTOR 
			Pedido miPedido = new Pedido(idProducto,idCliente,cantidad,estado,fecha,idProducto);
			Pedido productoActualizado = miPedido.editarProducto();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
