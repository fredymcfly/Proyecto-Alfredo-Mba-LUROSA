package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Servlet implementation class ServletListarProductos
 */
public class ServletListarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarProductos() {
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
			
			Producto misProductos = new Producto();
					
			String resultado = misProductos.listarProductos();
				
			out.print(resultado);
			
			System.out.println(resultado.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

		
		// en esta parte queremos que el json nos devuelva los datos de la bd
		//PrintWriter out = response.getWriter();
		
		//esta va a ser la respuesta que obtenga el dao clientes
		
		// trabajo con el patron singelton que es capaz de trabajar un una clase con todas
		// sun funcionavilidades
		/*
		try {
			ArrayList<Productos>listaEnObjeto= ProductoDao.getInstance().listar();
			
			// prueba para ver si llega al servler 
			
			for ( Productos a : listaEnObjeto) {
				System.out.println(a.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		*/
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

}
}