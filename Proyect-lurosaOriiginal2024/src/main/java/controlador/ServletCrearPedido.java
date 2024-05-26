package controlador;
//import modelo.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Producto;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class ServletCrearPedido
 */
public class ServletCrearPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession sesion;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCrearPedido() {
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
		
		HttpSession sesion = request.getSession();
    	Object idUsuario = sesion.getAttribute("idUsuario");
		
    	if(idUsuario != null)
    	{
    		try {
    			
    			//con el dato de idUsuario de la sesion, consultamos el cliente 
    			//que le corresponde para averiguar el id de cliente para el pedido
	    		Cliente modeloCliente = new Cliente();   	
	    		Cliente clienteLogado = modeloCliente.leerClientePorUsuario((int)idUsuario);
	    		
	    		//a continuacion generamos toda la informacion del pedido para grabarlo
	    		int idProducto = Integer.parseInt(request.getParameter("idProducto"));
	    		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
	    		
	    		//creamos objeto tipo fecha
	    		Date hoy = new Date();
	    		//creamos un formateador de fecha con el formato dia/mes/año
	    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    		//aplicamos la fecha formateada a cadena de texto fechaPedido
	    		String fechaPedido = formatter.format(hoy);
	    		
	    		String estado = "PENDIENTE ENVIO";
	    		
	    		int idCliente = clienteLogado.getIdCliente();
	    		
	    		
	    		//se crea el modelo de negocio de Pedido enviando los datos al constructor
	    		Pedido modeloPedido = new Pedido (idCliente, idProducto,cantidad, estado, fechaPedido); 
    		    		
    			//llamamos al metodo insertar para grabar el pedido
    			Pedido miPedidoNuevo = modeloPedido.insertar();
    			//controlamos si el pedido se ha creado correctamente
    			if(miPedidoNuevo == null)
    			{
    				response.sendRedirect("pedidoErroneo.html");
    			}
    			else
    			{
    				response.sendRedirect("pedidoRealizado.html");
    				System.out.println(miPedidoNuevo.toString());	
    			}	
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else
    	{
    		//si entra por aqui el cliente no se ha logeado y por tanto no puede comprar
    		//asi que le redirigimos a la pagina de login
    		response.sendRedirect("IniciarSesion.html");
    	}
		
	}

}
