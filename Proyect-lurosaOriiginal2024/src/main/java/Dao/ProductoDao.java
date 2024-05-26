package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Producto;

public class ProductoDao {
private static Connection con = null; // Si lo privatizas, solo se puede acceder dentro de esta clase.
	
	// llena el metodo get instance para el patroin singelton
	private static ProductoDao instance = null;
	
	public ProductoDao() throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	
	
	// PATRON SINGELTON 
	public static ProductoDao getInstance() throws SQLException {
		if (instance == null) {
			instance = new ProductoDao();
		}
		return instance;
	}

//----------------------LISTAR PRODUCTOS --------------------------------------
	public ArrayList<Producto> listar() throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM productos");
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Producto> result = null;
		 
		while(rs.next()) {
			
			if(result== null) {
				
				result = new ArrayList<Producto>();
			}
			//int idProducto, int idAdmin, String descripcion, String precio, String stock,
			//String nombreproducto
			result.add(new Producto(rs.getInt("idProducto"), rs.getInt("idAdmin"), rs.getString("NombreProducto"), rs.getString("descripcion"),rs.getInt("Stock"),rs.getInt("Precio")));
	    }
		return result;
	}

	//---------------------- INSERTAR PRODUCTOS  --------------------------------------
	
	public Producto insertar(Producto product) {
		
		//ponemos bloque try-catch para controlar cualquier excepcion de sql 
		try {
			String query = "INSERT INTO productos (nombreProducto, descripcion, precio, stock,idadmin) VALUES (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			
			  System.out.println("Preparando la consulta SQL: " + query);
	            System.out.println("Datos del producto: " + product);
			// ponemos los parametros que desamos que guarden en la bd que el usuario introduzca 
			// que salen de nuestra clase creada 
			ps.setString(1, product.getNombreproducto());
			ps.setString(2, product.getDescripcion());
			ps.setInt(3, product.getPrecio());
			ps.setInt(4, product.getStock());
			ps.setInt(5, 1);			
			 System.out.println("Ejecutando la consulta...");
			// utilizamos execute para realizar el envio que solo devuelve el numero de tuplas afectadas
			int filas = ps.executeUpdate();
			 System.out.println("Filas afectadas: " + filas);
			if (filas > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setIdProducto(generatedKeys.getInt(1));
                }
            }

			ps.close();
			 System.out.println("Producto insertado con éxito: " + product);
			//si finaliza correctamente la insercion, devolvemos cliente
			return product;
		
		} catch (SQLException e) {
			//si se provoca algun error en la insercion , devolvemos false
			e.printStackTrace();
			return null;
		}
		
	}

	

     public Producto editarProducto(Producto product) {
		
		//ponemos bloque try-catch para controlar cualquier excepcion de sql 
		try {
		
			String query = "UPDATE productos set nombreProducto = ?, descripcion = ?, precio = ?" +
			",stock = ? WHERE idProducto = ?";
			
			PreparedStatement ps = con.prepareStatement(query);

			// ponemos los parametros que desamos que guarden en la bd que el usuario introduzca 
			// que salen de nuestra clase creada 
			ps.setString(1, product.getNombreproducto());
			ps.setString(2, product.getDescripcion());
			ps.setInt(3, product.getPrecio());
			ps.setInt(4, product.getStock());
			ps.setInt(5, product.getIdProducto());
			
			// utilizamos execute para realizar el envio que solo devuelve el numero de tuplas afectadas
			int filas = ps.executeUpdate();
			
			if(filas<=0)
			{
				product = null;
			}
			
			ps.close();
			
			//si finaliza correctamente la update, devolvemos producto
			return product;
		
		} catch (SQLException e) {
			//si se provoca algun error en la update , devolvemos false
			return product;
		}
	}

	 public void eliminarProducto(Producto c) throws SQLException {
			String sql = "DELETE FROM productos WHERE idProducto = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, c.getIdProducto());
			int filas = ps.executeUpdate();
			
			ps.close();
	}
	 
	 public Producto leerProducto(int idProducto) throws SQLException {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM productos where idProducto = ?");
			ps.setInt(1, idProducto);
			ResultSet rs = ps.executeQuery();
			
			Producto result = null;
			 
			while(rs.next()) {
				
				result = new Producto(rs.getInt("idProducto"), 1,rs.getString("nombreProducto"),rs.getString("descripcion"), rs.getInt("precio"),rs.getInt("stock"));
		    }
			return result;
		
		}
		
}
