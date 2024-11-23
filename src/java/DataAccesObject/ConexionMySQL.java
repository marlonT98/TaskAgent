package DataAccesObject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    // Variables de configuración
    private static final String URL = "jdbc:mysql://localhost:3306/botica"; 
    private static final String USERNAME = "admin"; 
    private static final String PASSWORD = "admin"; 
    private Connection connection;

    // Constructor que establece la conexión
    public ConexionMySQL() {
        try {
            // Registrar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Verificar si la conexión es exitosa
            if (connection != null) {
                DatabaseMetaData dm = connection.getMetaData();
                System.out.println("Conexión con MySQL establecida...");
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    // Método para obtener la conexión
    public Connection getConexion() {
        return this.connection;
    }

    // Método principal para probar la conexión
    public static void main(String[] args) {
        ConexionMySQL conexion = new ConexionMySQL();
        if (conexion.getConexion() != null) {
            System.out.println("Conexión exitosa. ¡Listo para usar la base de datos!");
        } else {
            System.out.println("Error al establecer la conexión.");
        }
    }
}
