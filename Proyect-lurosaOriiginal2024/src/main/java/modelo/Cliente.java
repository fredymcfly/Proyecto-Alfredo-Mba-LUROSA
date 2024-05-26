package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import Dao.ClienteDao;
import Dao.LogeoDao;

public class Cliente {
	private int idCliente;
	private int idUsuario;
	private String nombre;
	private String apellido;
	private String direccion;
	private String provincia;
	private String pais;
	private String poblacion;
	private String telefono;
	private boolean EsAdmin;
	
	
 public Cliente()  {
	 
 }
// creo constructor
 public Cliente( int idCliente,int idUsuario, String nombre, String apellido,String direccion,String provincia, String pais, String poblacion, String telefono) {
	 	
	 	this.idCliente=idCliente;
		this.idUsuario=idUsuario;		
		this.nombre=nombre;
		this.apellido= apellido;
		this.direccion = direccion;
		this.provincia=provincia;
		this.pais=pais;
		this.poblacion=poblacion;
		this.telefono=telefono;
	}

public Cliente(int idCliente, int idUsuario, String nombre, String apellido, String direccion, String provincia,
		String pais, String poblacion, String telefono, boolean esAdmin) {
	super();
	this.idCliente = idCliente;
	this.idUsuario = idUsuario;
	this.nombre = nombre;
	this.apellido = apellido;
	this.direccion = direccion;
	this.provincia = provincia;
	this.pais = pais;
	this.poblacion = poblacion;
	this.telefono = telefono;
	EsAdmin = esAdmin;
}



public Cliente(int idUsuario, String nombre, String apellido, String direccion, String provincia, String pais,
		String poblacion, String telefono) {
	super();
	this.idUsuario = idUsuario;
	this.nombre = nombre;
	this.apellido = apellido;
	this.direccion = direccion;
	this.provincia = provincia;
	this.pais = pais;
	this.poblacion = poblacion;
	this.telefono = telefono;
}
public int getIdCliente() {
	return idCliente;
}


public void setIdCliente(int id) {
	this.idCliente = id;
}



public int getIdUsuario() {
	return idUsuario;
}


public void setIdUsuario(int id) {
	this.idUsuario = id;
}





public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public String getApellido() {
	return apellido;
}


public void setApellido(String apellido) {
	this.apellido = apellido;
}


public String getDireccion() {
	return direccion;
}


public void setDireccion(String direccion) {
	this.direccion = direccion;
}


public String getProvincia() {
	return provincia;
}


public void setProvincia(String provincia) {
	this.provincia = provincia;
}


public String getPais() {
	return pais;
}


public void setPais(String pais) {
	this.pais = pais;
}


public String getPoblacion() {
	return poblacion;
}


public void setPoblacion(String poblacion) {
	this.poblacion = poblacion;
}


public String getTelefono() {
	return telefono;
}


public void setTelefono(String telefono) {
	this.telefono = telefono;
}


public boolean isEsAdmin() {
	return EsAdmin;
}


public void setEsAdmin(boolean esAdmin) {
	EsAdmin = esAdmin;
}
 


 public  Cliente insertar() throws SQLException {
	// que llamara al metodo insertar del dao que tiene el patron singelton
	// codigo optimizado
	 Cliente respuestaDAO =  ClienteDao.getInstance().insertar(this);
		
	return respuestaDAO;
 
}
@Override
public String toString() {
	return "Cliente [idCliente=" + idCliente + ", idUsuario=" + idUsuario + ", nombre=" + nombre
			+ ", apellido=" + apellido + ", direccion=" + direccion + ", provincia=" + provincia
			+ ", pais=" + pais + ", poblacion=" + poblacion + ", telefono=" + telefono + ", EsAdmin=" + EsAdmin + "]";
}

public String listarClientes() throws SQLException {
	String json = "";
	Gson objetoGson = new Gson();
	ClienteDao resultado = new ClienteDao();
	json = objetoGson.toJson(resultado.listar());
	return json;
}

public Cliente leerClientePorUsuario(int idUsuario) throws SQLException {
	ClienteDao miClienteDAO = new ClienteDao();	
	Cliente resultado = miClienteDAO.leerClientePorUsuario(idUsuario);
	return resultado;
}

public String leerCliente(int idCliente) throws SQLException {
	String json = "";
	Gson objetoGson = new Gson();
	ClienteDao resultado = new ClienteDao();
	json = objetoGson.toJson(resultado.leerCliente(idCliente));
	return json;
}

//Eliminar cliente
public void borrarCliente() throws SQLException {
	ClienteDao borrar = new ClienteDao();
	borrar.eliminarCliente(this);
}


public Cliente editarCliente() throws SQLException {
	
	ClienteDao editar = new ClienteDao();
	return editar.editarCliente(this);
}

}
 