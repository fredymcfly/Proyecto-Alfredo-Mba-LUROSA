package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServetLeerCliente
 */
public class ServletGuardarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGuardarCliente() {
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
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String direccion = request.getParameter("direccion");
			String provincia = request.getParameter("provincia");
			String pais = request.getParameter("pais");
			String poblacion= request.getParameter("poblacion");
			String telefono = request.getParameter("telefono");
			
			Cliente miCliente = new Cliente(idCliente,idUsuario, nombre, apellido,direccion,provincia,pais, poblacion, telefono);
			Cliente clienteActualizado = miCliente.editarCliente();
			if(clienteActualizado == null)
			{
				//out.print("KO");
				response.sendRedirect("adminArea.html");
			}
			else
			{
				response.sendRedirect("adminArea.html");			
			}
			//out.print(resultado);
			//System.out.println(resultado);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
