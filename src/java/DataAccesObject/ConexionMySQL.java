package DataAccesObject;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    //Variables
    String sConxMySQL="jdbc:mysql://localhost/taskagent";
    String sUserName="root";
    String sUserPwd="";
    Connection oConnection;

    public ConexionMySQL() {
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
           oConnection = DriverManager.getConnection(sConxMySQL, sUserName,sUserPwd);
           if(oConnection != null){
               DatabaseMetaData dm = oConnection.getMetaData();
               
               System.out.println(this.getClass().getName()+ ": Conexion con MySQL Establecida..");
               System.out.println("Driver name: " + dm.getDriverName());
               System.out.println("Driver version: " + dm.getDriverVersion());
               System.out.println("Product name: " + dm.getDatabaseProductName());
               System.out.println("Product version: " + dm.getDatabaseProductVersion());

           }        
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
   public Connection getConexion(){
       return this.oConnection;
   }   
   public static void main(String[] args) {
        ConexionMySQL Cn=new ConexionMySQL();
   }
}