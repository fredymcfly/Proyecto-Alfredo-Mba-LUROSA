package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import Dao.PedidoDao;
import Dao.ProductoDao;
/**
 * Esta clase representa un Pedido con detalles sobre el pedido, 
 * como el cliente, el producto, la cantidad, el estado y la fecha.
 */

public class Pedido {
 private int idPedido;
 private int idCliente;
 private int idProducto;
 private int cantidad;
 private String Estado;
 private String fecha;
 
 
 
 public Pedido() {
	 
 }
 
 /**
  * Constructor con parámetros.
  * 
  * @param idPedidoParam ID del pedido.
  * @param idClienteParam ID del cliente.
  * @param idProductoParam ID del producto.
  * @param cantidadParam Cantidad del producto.
  * @param estadoParam Estado del pedido.
  * @param fechaParam Fecha del pedido.
  * 
  *Creao dos constructores parecidos  en una necesito el ide pedido y en otra el id cliente 
  */

 public Pedido(int idPedidoParam, int idClienteParam,int idProductoParam, int cantidadParam, String estadoParam, String fechaParam)
 {
	 idPedido = idPedidoParam;
	 idCliente = idClienteParam;
	 idProducto = idProductoParam;
	cantidad = 	cantidadParam;
	Estado = estadoParam;
	fecha = fechaParam;	 
 }
 public Pedido(int idClienteParam,int idProductoParam, int cantidadParam, String estadoParam, String fechaParam)
 {
	 idCliente = idClienteParam;
	 idProducto = idProductoParam;
	cantidad = 	cantidadParam;
	Estado = estadoParam;
	fecha = fechaParam;	 
 }
 
 /**
  * Constructor con parámetros.
  * 
  * @param idProducto ID del producto.
  * @param cantidad Cantidad del producto.
  */
public Pedido(int idProducto, int cantidad) {
	super();
	this.idProducto = idProducto;
	this.cantidad = cantidad;
}


public int getidPedido() {
	return idPedido;
}
/**
 * Obtiene el ID del pedido.
 * 
 * @return ID del pedido.
 */
public void setIdPedido(int valor)
{
	idPedido = valor;
}
/**
 * Obtiene el ID del producto.
 * 
 * @return ID del producto.
 */
public int getidProducto() {
	return idProducto;
}
/**
 * Establece el ID del producto.
 * 
 * @param valor ID del producto.
 */
public void setidProducto(int valor)
{
	idProducto = valor;
}
/**
 * Obtiene el ID del cliente.
 * 
 * @return ID del cliente.
 */

public int getidCliente() {
	return idCliente;
}

/**
* Establece el ID del cliente.
* 
* @param valor ID del cliente.
*/

public void setidCliente(int valor)
{
	idCliente = valor;
}

/**
 * Obtiene la cantidad del producto.
 * 
 * @return Cantidad del producto.
 */

public int getCantidad() {
	return cantidad;
}
/**
 * Establece la cantidad del producto.
 * 
 * @param valor Cantidad del producto.
 */
public void setCantidad(int valor)
{
	cantidad = valor;
}
/**
 * Obtiene el estado del pedido.
 * 
 * @return Estado del pedido.
 */
public String getEstado() {
	return Estado;
}
/**
 * Establece el estado del pedido.
 * 
 * @param valor Estado del pedido.
 */
public void Estado(String valor)
{
	Estado = valor;
}
/**
 * Obtiene la fecha del pedido.
 * 
 * @return Fecha del pedido.
 */
public String getFecha() {
	return fecha;
}
/**
 * Establece la fecha del pedido.
 * 
 * @param valor Fecha del pedido.
 */
public void setFecha(String valor)
{
	fecha = valor;
}


//crear metodos para insertar, leer, listar

/**
 * Inserta un nuevo pedido en la base de datos.
 * 
 * @return El pedido insertado.
 * @throws SQLException Si ocurre un error SQL.
 */
public  Pedido insertar() throws SQLException {
		
    Pedido respuestaDAO = PedidoDao.getInstance().insertar(this);
	return respuestaDAO;
 }
 
/**
 * Lista todos los pedidos en formato JSON.
 * 
 * @return Representación JSON de todos los pedidos.
 * @throws SQLException Si ocurre un error SQL.
 */
public String listarPedidos() throws SQLException {
	String json = "";
	Gson objetoGson = new Gson();
	PedidoDao resultado = new PedidoDao();
	json = objetoGson.toJson(resultado.listar());
	return json;
}
/**
 * Lee los detalles de un pedido por ID de producto en formato JSON.
 * 
 * @param idProducto ID del producto.
 * @return Representación JSON del pedido.
 * @throws SQLException Si ocurre un error SQL.
 */
public String leerPedido(int idProducto) throws SQLException {
	String json = "";
	Gson objetoGson = new Gson();
	PedidoDao resultado = new PedidoDao();
	json = objetoGson.toJson(resultado.listar());
	return json;
} 

/**
 * Edita un pedido existente en la base de datos.
 * 
 * @return El pedido editado.
 * @throws SQLException Si ocurre un error SQL.
 */
public Pedido editarProducto() throws SQLException {
	
	 PedidoDao editar = new PedidoDao();
		return editar.editarPedido(this);
	}
}


