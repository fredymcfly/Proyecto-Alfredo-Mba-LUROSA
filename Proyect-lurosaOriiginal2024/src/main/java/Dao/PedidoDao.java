package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Pedido;
import modelo.Producto;

public class PedidoDao {

private static Connection con = null; // Si lo privatizas, solo se puede acceder dentro de esta clase.
	
	// llena el metodo get instance para el patroin singelton
	private static PedidoDao instance = null;
	
	public PedidoDao() throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	
	
	// PATRON SINGELTON 
	public static PedidoDao getInstance() throws SQLException {
		if (instance == null) {
			instance = new PedidoDao();
		}
		return instance;
	}

	
	
	
public Pedido insertar(Pedido pedi) {
		
		//ponemos bloque try-catch para controlar cualquier excepcion de sql 
		try {
			String query = "INSERT INTO pedidos (idProducto,idCliente,cantidad,estado, fecha) VALUES (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			
			  System.out.println("Preparando la consulta SQL: " + query);
	            System.out.println("Datos del producto: " + pedi);
			// ponemos los parametros que desamos que guarden en la bd que el usuario introduzca 
			// que salen de nuestra clase creada 
			
			ps.setInt(1, pedi.getidProducto());
			ps.setInt(2, pedi.getidCliente());
			ps.setInt(3, pedi.getCantidad());
			ps.setString(4, pedi.getEstado());
			ps.setString(5, pedi.getFecha());
						
			 System.out.println("Ejecutando la consulta...");
			// utilizamos execute para realizar el envio que solo devuelve el numero de tuplas afectadas
			int filas = ps.executeUpdate();
			 System.out.println("Filas afectadas: " + filas);
			if (filas > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pedi.setIdPedido(generatedKeys.getInt(1));
                }
            }

			ps.close();
			 System.out.println("Pedido insertado con éxito: " + pedi);
			//si finaliza correctamente la insercion, devolvemos pedido
			return pedi;
		
		} catch (SQLException e) {
			//si se provoca algun error en la insercion , devolvemos false
			e.printStackTrace();
			return null;
		}
		
	}

}


