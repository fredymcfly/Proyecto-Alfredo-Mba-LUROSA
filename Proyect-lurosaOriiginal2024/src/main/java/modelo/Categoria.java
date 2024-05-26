package modelo;

import java.sql.SQLException;
import Dao.CategoriaDao;


public class Categoria {
	
	private int idCategoria;
	private int idAdmin;
	private String nombre;
	private String descripcion;
	
public Categoria() {
	
}

public Categoria(int idCategoria, int idAdmin, String nombre, String descripcion) {
	super();
	this.idCategoria = idCategoria;
	this.idAdmin = idAdmin;
	this.nombre = nombre;
	this.descripcion = descripcion;
}




public Categoria(String nombre, String descripcion) {
	super();
	this.nombre = nombre;
	this.descripcion=descripcion;
}

public int getIdCategoria() {
	return idCategoria;
}

public void setIdCategoria(int idCategoria) {
	this.idCategoria = idCategoria;
}

public int getIdAdmin() {
	return idAdmin;
}

public void setIdAdmin(int idAdmin) {
	this.idAdmin = idAdmin;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

@Override
public String toString() {
	return "Categoria [idCategoria=" + idCategoria + ", idAdmin=" + idAdmin + ", nombre=" + nombre + ", descripcion="
			+ descripcion + "]";
}

public Categoria insertar() throws SQLException {
	// que llamara al metodo insertar del dao que tiene el patron singelton
	// codigo optimizado
		
		Categoria respuestaDAO = CategoriaDao.getinstance().insertar(this);
		
		return respuestaDAO;
}
}
