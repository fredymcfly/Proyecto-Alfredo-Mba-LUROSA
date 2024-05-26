package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import Dao.ClienteDao;
import Dao.ProductoDao;

public class Producto {
	
	 private int idProducto;
	 private int idAdmin;
	 private String nombreproducto;
	 private String descripcion;
	 private int precio;
	 private int stock;
	
	 public Producto() {
		 
	 }
	 

//---------------------- CONTRUCTRO PARA INSERTAR  --------------------------------------

    public Producto(String nombre, String descripcion, int precio, int stock) {
        this.nombreproducto = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

//----------------------CONTRUCTOR PARA LISTAR --------------------------------------
		    
		    
	public Producto(int idProducto, int idAdmin, String nombreproducto, String descripcion, int precio, int stock) {
		super();
		this.idProducto = idProducto;
		this.idAdmin = idAdmin;
		this.nombreproducto = nombreproducto;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}  
		 

	public int getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}


	public int getIdAdmin() {
		return idAdmin;
	}


	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}


	public String getNombreproducto() {
		return nombreproducto;
	}


	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getPrecio() {
		return precio;
	}


	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		stock = stock;
	}


	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", idAdmin=" + idAdmin + ", nombreproducto=" + nombreproducto
				+ ", descripcion=" + descripcion + ", precio=" + precio + ", Stock=" + stock + "]";
	}


	public  Producto insertar() throws SQLException {
			// que llamara al metodo insertar del dao que tiene el patron singelton
			// codigo optimizado
		 Producto respuestaDAO = ProductoDao.getInstance().insertar(this);
				
		return respuestaDAO;
	 }
	 
	 public Producto editarProducto() throws SQLException {
			
		 ProductoDao editar = new ProductoDao();
			return editar.editarProducto(this);
		}
	 
	 public String listarProductos() throws SQLException {
			String json = "";
			Gson objetoGson = new Gson();
			ProductoDao resultado = new ProductoDao();
			json = objetoGson.toJson(resultado.listar());
			return json;
		}
	 

	 
		public String leerProducto(int idProducto) throws SQLException {
			String json = "";
			Gson objetoGson = new Gson();
			ProductoDao resultado = new ProductoDao();
			json = objetoGson.toJson(resultado.leerProducto(idProducto));
			return json;
		} 
	

		public void borrarProducto() throws SQLException {
			ProductoDao borrar = new ProductoDao();
			borrar.eliminarProducto(this);
			
		}

}