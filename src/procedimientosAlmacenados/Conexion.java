package procedimientosAlmacenados;
import java.sql.*;
import java.sql.DriverManager;

public class Conexion {
	private Conexion(){
		
	}
	
	//guardar el estado de la conexion
	private static Connection conexion;
	
	// variable para crear una sola instancia
	private static Conexion instancia;
	
	//variables para poder conectarnos
	public static final String URL = "jdbc:mysql://localhost:3306/matricula";
	public static final String USERNAME= "root";
	public static final String PASSWORD = "";
	
	//metodo para conectarse a la base de datos
	public Connection conectar() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(URL,USERNAME , PASSWORD);
			return conexion;
		}
		catch (Exception e) { 
			System.out.println(e);
		}
		return conexion;
	}
	// creamos el metodo para cerrar la conexion
	
	public void cerrarConexion()throws SQLException{
		try {
			conexion.close();
		}catch(SQLException e) {
			System.out.println(e);
			conexion.close();
		}finally {
			conexion.close();
		}
	}
	
	//patron singleton
	
	public static Conexion getInstance() {
		if(instancia == null) {
			instancia = new Conexion();
			
		}
		return instancia;
	}
}