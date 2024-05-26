package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Cliente;

public class ClienteDao {
	private static Connection con = null; // Si lo privatizas, solo se puede acceder dentro de esta clase.
	
	// llena el metodo get instance para el patroin singelton
	private static ClienteDao instance = null;
	
	public ClienteDao() throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	
	
	// PATRON SINGELTON 
	public static ClienteDao getInstance() throws SQLException {
		if (instance == null) {
			instance = new ClienteDao();
		}
		return instance;
	}
	
	public ArrayList<Cliente> listar() throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes c inner join usuarios u on u.id = c.idUsuario where u.esadmin = 0");
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Cliente> result = null;
		 
		while(rs.next()) {
			
			if(result== null) {
				
				result = new ArrayList<Cliente>();
			}
			result.add(new Cliente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9)));
	    }
		return result;
	}
	
	
	
	public Cliente insertar(Cliente client) {
		
		//ponemos bloque try-catch para controlar cualquier excepcion de sql 
		try {
		
			PreparedStatement ps = con.prepareStatement("INSERT INTO clientes (idUsuario,nombreUsuario, apellido, direccion, provincia, pais, poblacion, telefono) VALUES (?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

			// ponemos los parametros que desamos que guarden en la bd que el usuario introduzca 
			// que salen de nuestra clase creada 
			ps.setInt(1, client.getIdUsuario());
			ps.setString(2, client.getNombre());
			ps.setString(3, client.getApellido());
			ps.setString(4, client.getDireccion());
			ps.setString(5, client.getProvincia());
			ps.setString(6, client.getPais());
			ps.setString(7, client.getPoblacion());
			ps.setString(8, client.getTelefono());
			
			
			// utilizamos execute para realizar el envio que solo devuelve el numero de tuplas afectadas
			int filas = ps.executeUpdate();
			
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	client.setIdCliente(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Error al crear el cliente.");
	            }
	        }
			ps.close();
			
			//si finaliza correctamente la insercion, devolvemos cliente
			return client;
		
		} catch (SQLException e) {
			//si se provoca algun error en la insercion , devolvemos false
			return client;
		}
	}
		
     public Cliente editarCliente(Cliente client) {
		
		//ponemos bloque try-catch para controlar cualquier excepcion de sql 
		try {
		
			String query = "UPDATE clientes set nombre = ?, apellido = ?, direccion = ?" +
			",provincia = ?, pais = ?, poblacion = ?, telefono = ? WHERE idCliente = ?";
			
			PreparedStatement ps = con.prepareStatement(query);

			// ponemos los parametros que desamos que guarden en la bd que el usuario introduzca 
			// que salen de nuestra clase creada 
			ps.setString(1, client.getNombre());
			ps.setString(2, client.getApellido());
			ps.setString(3, client.getDireccion());
			ps.setString(4, client.getProvincia());
			ps.setString(5, client.getPais());
			ps.setString(6, client.getPoblacion());
			ps.setString(7, client.getTelefono());
			ps.setInt(8, client.getIdCliente());
			
			// utilizamos execute para realizar el envio que solo devuelve el numero de tuplas afectadas
			int filas = ps.executeUpdate();
			
			if(filas<=0)
			{
				client = null;
			}
			
			ps.close();
			
			//si finaliza correctamente la update, devolvemos cliente
			return client;
		
		} catch (SQLException e) {
			//si se provoca algun error en la update , devolvemos false
			return client;
		}
	}

	 public static void eliminarCliente(Cliente c) throws SQLException {
			String sql = "DELETE FROM clientes WHERE idCliente = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, c.getIdCliente());
			int filas = ps.executeUpdate();
			
			ps.close();
	}
	 
	 public static Cliente leerCliente(int idCliente) throws SQLException {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes where idCliente = ?");
			ps.setInt(1, idCliente);
			ResultSet rs = ps.executeQuery();
			
			Cliente result = null;
			 
			while(rs.next()) {
				
				if(result== null) {
					
					result = new Cliente();
				}
				result = new Cliente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9));
		    }
			return result;
			
		}
		
	 public static Cliente leerClientePorUsuario(int idUsuario) throws SQLException {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes where idUsuario = ?");
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			
			Cliente result = null;
			 
			while(rs.next()) {
				
				if(result== null) {
					
					result = new Cliente();
				}
				result = new Cliente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9));
		    }
			return result;
			
		}
	
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	/*
	 *private Connection con = null; // Si lo privatizas, solo se puede acceder dentro de esta clase.
	
	private static ClienteDao instance = null;
	
	public ClienteDao() throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	public static ClienteDao getInstance() throws SQLException {
		if (instance == null) {
			instance = new ClienteDao();
		}
		return instance;
	}
	
	public ArrayList<Clientes> listar() throws SQLException {
		String sql="SELECT * FROM usuarios";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Clientes> ls = null;
		 
		while(rs.next()) {
			
			if(ls == null) {
				
				ls = new ArrayList<Clientes>();
			}
			ls.add(new Clientes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
	    }
		return ls;
	 */
	
	
	
	
	
	// creo un nuevo metodo publico que me devuelve un string listar json 
	// quiero que me haga un Strin a con cadena vacia para mi atributo 
	/*
	public String ListarJson() throws SQLException {
		String json="";
		
		Gson gson =new Gson();
		
		json = gson.toJson(this.listar());
		
		return json;
		
	}
*/
}
