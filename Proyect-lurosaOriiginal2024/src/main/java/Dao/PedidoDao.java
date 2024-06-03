package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Pedido;
import modelo.Producto;

public class PedidoDao {

private static Connection con = null; // Si lo privatizas, solo se puede acceder dentro de esta clase.
	
	// llena el metodo get instance para el patroin singelton
	private static PedidoDao instance = null;
	
	public PedidoDao() throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
    /**
     * Método para obtener la instancia única de PedidoDao (Singleton).
     * 
     * @return instancia única de PedidoDao.
     * @throws SQLException si ocurre un error al obtener la conexión.
     */

	// PATRON SINGELTON 
	public static PedidoDao getInstance() throws SQLException {
		if (instance == null) {
			instance = new PedidoDao();
		}
		return instance;
	}

	/**
     * Inserta un nuevo pedido en la base de datos.
     * 
     * @param pedi el objeto Pedido a insertar.
     * @return el objeto Pedido insertado con el ID generado, o null si ocurre un error.
     */
	
	
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


// LISTAR PEDIDO 
/**
 * Lista todos los pedidos en la base de datos.
 * 
 * @return una lista de objetos Pedido.
 * @throws SQLException si ocurre un error al realizar la consulta.
 */


	public ArrayList<Pedido>listar() throws SQLException{
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM pedidos");
		ResultSet rs =ps.executeQuery();
		
		ArrayList<Pedido>result=null;
		
		while(rs.next()) {
			
			// preguntamos si el arraylis este nulo no este inicializado 
			
			if (result==null) {
				result= new ArrayList<>();
				
			}
		result.add(new Pedido (rs.getInt("idPedido"),rs.getInt("idCliente"),rs.getInt("idProducto"),rs.getInt("cantidad"),rs.getString("estado"),rs.getString("fecha")));
		}
		
		
		return result;
	}
	
	

	/**
     * Actualiza un pedido en la base de datos.
     * 
     * @param pedi el objeto Pedido con los datos actualizados.
     * @return el objeto Pedido actualizado, o null si ocurre un error.
     */
    public Pedido editarPedido(Pedido pedi) {
		
		//ponemos bloque try-catch para controlar cualquier excepcion de sql 
		try {
		
			String query = "UPDATE pedidos SET idCliente = ?, idProducto = ?, cantidad = ?, estado = ?, fecha = ? WHERE idPedido = ?";

			
			PreparedStatement ps = con.prepareStatement(query);

			// ponemos los parametros que desamos que guarden en la bd que el usuario introduzca 
			// que salen de nuestra clase creada 
			ps.setInt(1, pedi.getidPedido());
			ps.setInt(2, pedi.getidCliente());
			ps.setInt(3, pedi.getidProducto());
			ps.setInt(4, pedi.getCantidad());
			ps.setString(5, pedi.getEstado());
			ps.setString(6,pedi.getFecha());
			
			// utilizamos execute para realizar el envio que solo devuelve el numero de tuplas afectadas
			int filas = ps.executeUpdate();
			
			if(filas<=0)
			{
				pedi = null;
			}
			
			ps.close();
			
			//si finaliza correctamente la update, devolvemos producto
			return pedi;
		
		} catch (SQLException e) {
			//si se provoca algun error en la update , devolvemos false
			return pedi;
		}
	}
}


