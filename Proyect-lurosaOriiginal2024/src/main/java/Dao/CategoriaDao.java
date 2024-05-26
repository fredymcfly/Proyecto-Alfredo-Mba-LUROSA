package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Categoria;


public class CategoriaDao {

    private static Connection con = null;
    private static CategoriaDao instance = null;

    private CategoriaDao() throws SQLException {
        this.con = DBConexion.getConexion();
    }

    public static synchronized CategoriaDao getinstance() throws SQLException {
        if (instance == null) {
            instance = new CategoriaDao();
        }
        return instance;
    }

    //-----------------INSERTAR CATALOGO----------------------------------------------
	public Categoria insertar(Categoria cat) {
		
		//ponemos bloque try-catch para controlar cualquier excepcion de sql 
		try {
			String query = "INSERT INTO categorias (nombre, descripcion) VALUES (?,?)";
			PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			
			  System.out.println("Preparando la consulta SQL: " + query);
	            System.out.println("Datos del producto: " + cat);
			// ponemos los parametros que desamos que guarden en la bd que el usuario introduzca 
			// que salen de nuestra clase creada 
			ps.setString(1, cat.getNombre());
			ps.setString(2, cat.getDescripcion());
				
			 System.out.println("Ejecutando la consulta...");
			// utilizamos execute para realizar el envio que solo devuelve el numero de tuplas afectadas
			int filas = ps.executeUpdate();
			 System.out.println("Filas afectadas: " + filas);

			ps.close();
			 System.out.println("Producto insertado con éxito: " + cat);
			//si finaliza correctamente la insercion, devolvemos cliente
			return cat;
		
		} catch (SQLException e) {
			//si se provoca algun error en la insercion , devolvemos false
			e.printStackTrace();
			return null;
		}
		
	}

	

    //------------------EDITAR CATALOGO----------------------------------------
    public void update(Categoria c) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
      //      ps.setInt(3, c.getId());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Se han actualizado " + filas + " fila(s) correctamente.");
            } else {
                System.out.println("No se actualizaron filas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    // -----------------ELIMINAR CATALOGO---------------------------------------
    public static void eliminarCategoria(Categoria c) throws SQLException {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM categorias WHERE id = ?";
            ps = con.prepareStatement(sql);
       //     ps.setInt(1, c.getId());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Se han eliminado " + filas + " fila(s) correctamente.");
            } else {
                System.out.println("No se eliminaron filas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
