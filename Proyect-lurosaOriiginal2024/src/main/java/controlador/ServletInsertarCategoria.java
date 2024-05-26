package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Categoria;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletInsertarCategoria
 */
@WebServlet("/ServletInsertarCatalogo")
public class ServletInsertarCategoria extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertarCategoria() {
        super();
        // Constructor por defecto
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion= request.getParameter("descripcion");

        Categoria miCategoria = new Categoria(nombre,descripcion);
      
	
       System.out.println(miCategoria.toString());

        
    }
}
