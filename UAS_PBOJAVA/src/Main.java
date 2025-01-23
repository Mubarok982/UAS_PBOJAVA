package src;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Contoh data mahasiswa S1
        MahasiswaS1 mahasiswaS1 = new MahasiswaS1("123", "Rizqy", "Teknik Informatika", "2022", "Ilmu Komputer", 144);
        mahasiswaS1.displayInfo();

        // Contoh data mahasiswa S2
        MahasiswaS2 mahasiswaS2 = new MahasiswaS2("124", "Ahmad", "Teknik Informatika", "2021", "Kecerdasan Buatan", "Dr. Budi");
        mahasiswaS2.displayInfo();

        // Simpan ke database
        saveMahasiswaToDatabase(mahasiswaS1);
        saveMahasiswaToDatabase(mahasiswaS2);
    }

    // Method untuk menyimpan data mahasiswa ke database
    public static void saveMahasiswaToDatabase(Mahasiswa mahasiswa) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Koneksi.getConnection(); // Menyambung ke database
            // Modifikasi query sesuai dengan struktur tabel yang benar
            String sql = "INSERT INTO mahasiswa (id, nama, jurusan, angkatan, jenis_mahasiswa, program_studi, jumlah_sks, topik_tesis, nama_pembimbing) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, mahasiswa.getId());
            stmt.setString(2, mahasiswa.getNama());
            stmt.setString(3, mahasiswa.getJurusan());
            stmt.setString(4, mahasiswa.getAngkatan());

            // Menentukan jenis mahasiswa dan menyimpan data S1 atau S2
            if (mahasiswa instanceof MahasiswaS1) {
                MahasiswaS1 s1 = (MahasiswaS1) mahasiswa;
                stmt.setString(5, "S1"); // Menyimpan "S1" di kolom jenis_mahasiswa
                stmt.setString(6, s1.getProgramStudi());
                stmt.setInt(7, s1.getJumlahSKS());
                stmt.setNull(8, Types.VARCHAR); // Topik tesis kosong untuk S1
                stmt.setNull(9, Types.VARCHAR); // Nama pembimbing kosong untuk S1
            } else if (mahasiswa instanceof MahasiswaS2) {
                MahasiswaS2 s2 = (MahasiswaS2) mahasiswa;
                stmt.setString(5, "S2"); // Menyimpan "S2" di kolom jenis_mahasiswa
                stmt.setNull(6, Types.VARCHAR); // Program studi kosong untuk S2
                stmt.setNull(7, Types.INTEGER); // Jumlah SKS kosong untuk S2
                stmt.setString(8, s2.getTopikTesis());
                stmt.setString(9, s2.getNamaPembimbing());
            }

            stmt.executeUpdate();
            System.out.println("Data mahasiswa berhasil disimpan ke database!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menyimpan data ke database: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
