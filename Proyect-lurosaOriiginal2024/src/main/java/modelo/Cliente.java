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
	
    /**
     * Constructor por defecto.
     */
    public Cliente() {
    }

    /**
     * Constructor con parámetros para inicializar un cliente.
     *
     * @param idCliente Identificador del cliente.
     * @param idUsuario Identificador del usuario.
     * @param nombre Nombre del cliente.
     * @param apellido Apellido del cliente.
     * @param direccion Dirección del cliente.
     * @param provincia Provincia del cliente.
     * @param pais País del cliente.
     * @param poblacion Población del cliente.
     * @param telefono Teléfono del cliente.
     */
    public Cliente(int idCliente, int idUsuario, String nombre, String apellido,
                   String direccion, String provincia, String pais,
                   String poblacion, String telefono) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.provincia = provincia;
        this.pais = pais;
        this.poblacion = poblacion;
        this.telefono = telefono;
    }

    /**
     * Constructor con parámetros incluyendo el estado de administrador.
     *
     * @param idCliente Identificador del cliente.
     * @param idUsuario Identificador del usuario.
     * @param nombre Nombre del cliente.
     * @param apellido Apellido del cliente.
     * @param direccion Dirección del cliente.
     * @param provincia Provincia del cliente.
     * @param pais País del cliente.
     * @param poblacion Población del cliente.
     * @param telefono Teléfono del cliente.
     * @param esAdmin Estado de administrador del cliente.
     */
    public Cliente(int idCliente, int idUsuario, String nombre, String apellido,
                   String direccion, String provincia, String pais,
                   String poblacion, String telefono, boolean esAdmin) {
        this(idCliente, idUsuario, nombre, apellido, direccion, provincia, pais, poblacion, telefono);
        this.EsAdmin = esAdmin;
    }

    /**
     * Constructor con parámetros sin el identificador de cliente.
     *
     * @param idUsuario Identificador del usuario.
     * @param nombre Nombre del cliente.
     * @param apellido Apellido del cliente.
     * @param direccion Dirección del cliente.
     * @param provincia Provincia del cliente.
     * @param pais País del cliente.
     * @param poblacion Población del cliente.
     * @param telefono Teléfono del cliente.
     */
    public Cliente(int idUsuario, String nombre, String apellido,
                   String direccion, String provincia, String pais,
                   String poblacion, String telefono) {
        this(0, idUsuario, nombre, apellido, direccion, provincia, pais, poblacion, telefono);
    }

    // Getters y Setters

    /**
     * Obtiene el identificador del cliente.
     * @return el identificador del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el identificador del cliente.
     * @param id el nuevo identificador del cliente.
     */
    public void setIdCliente(int id) {
        this.idCliente = id;
    }

    /**
     * Obtiene el identificador del usuario.
     * @return el identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario.
     * @param id el nuevo identificador del usuario.
     */
    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return el nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombre el nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del cliente.
     * @return el apellido del cliente.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del cliente.
     * @param apellido el nuevo apellido del cliente.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene la dirección del cliente.
     * @return la dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del cliente.
     * @param direccion la nueva dirección del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la provincia del cliente.
     * @return la provincia del cliente.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Establece la provincia del cliente.
     * @param provincia la nueva provincia del cliente.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Obtiene el país del cliente.
     * @return el país del cliente.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el país del cliente.
     * @param pais el nuevo país del cliente.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Obtiene la población del cliente.
     * @return la población del cliente.
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * Establece la población del cliente.
     * @param poblacion la nueva población del cliente.
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * Obtiene el teléfono del cliente.
     * @return el teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     * @param telefono el nuevo teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Verifica si el cliente es administrador.
     * @return true si el cliente es administrador, false en caso contrario.
     */
    public boolean isEsAdmin() {
        return EsAdmin;
    }

    /**
     * Establece el estado de administrador del cliente.
     * @param esAdmin el nuevo estado de administrador del cliente.
     */
    public void setEsAdmin(boolean esAdmin) {
        EsAdmin = esAdmin;
    }

    /**
     * Inserta el cliente en la base de datos.
     * @return el cliente insertado.
     * @throws SQLException si ocurre un error durante la operación.
     */
    public Cliente insertar() throws SQLException {
        Cliente respuestaDAO = ClienteDao.getInstance().insertar(this);
        return respuestaDAO;
    }

    /**
     * Convierte los detalles del cliente a una cadena de texto.
     * @return una cadena con los detalles del cliente.
     */
    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", idUsuario=" + idUsuario + ", nombre=" + nombre
                + ", apellido=" + apellido + ", direccion=" + direccion + ", provincia=" + provincia
                + ", pais=" + pais + ", poblacion=" + poblacion + ", telefono=" + telefono + ", EsAdmin=" + EsAdmin + "]";
    }

    /**
     * Lista todos los clientes y los devuelve en formato JSON.
     * @return una cadena JSON con la lista de clientes.
     * @throws SQLException si ocurre un error durante la operación.
     */
    public String listarClientes() throws SQLException {
        String json = "";
        Gson objetoGson = new Gson();
        ClienteDao resultado = new ClienteDao();
        json = objetoGson.toJson(resultado.listar());
        return json;
    }

    /**
     * Lee un cliente por su identificador de usuario.
     * @param idUsuario el identificador del usuario.
     * @return el cliente correspondiente al identificador de usuario.
     * @throws SQLException si ocurre un error durante la operación.
     */
    public Cliente leerClientePorUsuario(int idUsuario) throws SQLException {
        ClienteDao miClienteDAO = new ClienteDao();
        Cliente resultado = miClienteDAO.leerClientePorUsuario(idUsuario);
        return resultado;
    }

    /**
     * Lee un cliente por su identificador y devuelve la información en formato JSON.
     * @param idCliente el identificador del cliente.
     * @return una cadena JSON con la información del cliente.
     * @throws SQLException si ocurre un error durante la operación.
     */
    public String leerCliente(int idCliente) throws SQLException {
        String json = "";
        Gson objetoGson = new Gson();
        ClienteDao resultado = new ClienteDao();
        json = objetoGson.toJson(resultado.leerCliente(idCliente));
        return json;
    }

    /**
     * Lee un Elimina cliente  por su identificador y devuelve la información en formato JSON.
     * @return Cliente eliminado.
     * @throws SQLException si ocurre un error durante la operación.
     */
    

//Eliminar cliente
public void borrarCliente() throws SQLException {
	ClienteDao borrar = new ClienteDao();
	borrar.eliminarCliente(this);
}

/**
 * Edita un cliente  por su identificador y devuelve la información en formato JSON.
 * @return Cliente editado.
 * @throws SQLException si ocurre un error durante la operación.
 */

public Cliente editarCliente() throws SQLException {
	
	ClienteDao editar = new ClienteDao();
	return editar.editarCliente(this);
}

}
 