package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {

	// En la conecion va el enlace de la base de datos a la que enlazaremos 
		public static final String JDBC_URL = "jdbc:mysql://localhost:3306/proyectolurosa";
		//Creamos el atributo estático tipo Connection, que es un tipo de conexión que me da el driver sql.
		 public static Connection instance = null;
		
		//Creamos un método público estático para que desde cualquier punto del programa cuando lo instanciemos nos devuelva
		//la conexión a la ruta.
		public static Connection getConexion() throws SQLException {
			//Debemos meterlo en un if por si llamamos a la clase por segunda vez y ya está conectada.
			if(instance == null) {
				
			//Queremos que este método se conecte a "jdbc:mysql://localhost:3360/flota" y me lo devuelva generando una instancia.
			instance = DriverManager.getConnection(JDBC_URL, "root", "");//Añadimos la excepción.
			}
			return instance;
}
}