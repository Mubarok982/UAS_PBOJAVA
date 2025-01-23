package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mahasiswa_db";  
        String user = "root";  // Ganti dengan username MySQL Anda
        String pass = "";  // Ganti dengan password MySQL Anda

        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new SQLException("Koneksi gagal: " + e.getMessage());
        }
    }
}
