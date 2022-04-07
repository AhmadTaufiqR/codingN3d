package n3dshop;
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    private static Connection cn;
    
    public static Connection getkoneksi(){
        if(cn == null){
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                cn=DriverManager.getConnection("jdbc:mysql://localhost/n3d_shop","root","");
                System.out.println("BERHASIL");
            } catch (Exception e) {
                e.printStackTrace();
            }
           
        }
        return cn;
    }

}
