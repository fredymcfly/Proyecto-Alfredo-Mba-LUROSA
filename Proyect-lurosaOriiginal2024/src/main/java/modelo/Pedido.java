package modelo;

import java.sql.SQLException;

import Dao.PedidoDao;
import Dao.ProductoDao;

public class Pedido {
 private int idPedido;
 private int idCliente;
 private int idProducto;
 private int cantidad;
 private String Estado;
 private String fecha;

 public Pedido(int idClienteParam,int idProductoParam, int cantidadParam, String estadoParam, String fechaParam)
 {
	 idCliente = idClienteParam;
	 idProducto = idProductoParam;
	cantidad = 	cantidadParam;
	Estado = estadoParam;
	fecha = fechaParam;	 
 }
 
 
public Pedido(int idProducto, int cantidad) {
	super();
	this.idProducto = idProducto;
	this.cantidad = cantidad;
}


public int getidPedido() {
	return idPedido;
}

public void setIdPedido(int valor)
{
	idPedido = valor;
}

public int getidProducto() {
	return idProducto;
}

public void setidProducto(int valor)
{
	idProducto = valor;
}

public int getidCliente() {
	return idCliente;
}

public void setidCliente(int valor)
{
	idCliente = valor;
}

public int getCantidad() {
	return cantidad;
}

public void setCantidad(int valor)
{
	cantidad = valor;
}

public String getEstado() {
	return Estado;
}

public void Estado(String valor)
{
	Estado = valor;
}

public String getFecha() {
	return fecha;
}

public void setFecha(String valor)
{
	fecha = valor;
}


//crear metodos para insertar, leer, listar


public  Pedido insertar() throws SQLException {
		
    Pedido respuestaDAO = PedidoDao.getInstance().insertar(this);
	return respuestaDAO;
 }
 


}


