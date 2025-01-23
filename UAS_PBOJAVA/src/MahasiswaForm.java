package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MahasiswaForm extends JFrame {
    // Komponen UI
    private JTextField namaField, jurusanField, angkatanField, programStudiField, jumlahSKSField, topikTesisField, namaPembimbingField;
    private JComboBox<String> jenisComboBox;
    private JButton saveButton;

    // Panel untuk komponen dinamis
    private JPanel s1Panel, s2Panel;

    public MahasiswaForm() {
        // Set up frame
        setTitle("Form Input Data Mahasiswa");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Spacing between components

        // Inisialisasi komponen
        JLabel namaLabel = new JLabel("Nama:");
        namaField = new JTextField(20);
        JLabel jurusanLabel = new JLabel("Jurusan:");
        jurusanField = new JTextField(20);
        JLabel angkatanLabel = new JLabel("Angkatan:");
        angkatanField = new JTextField(20);
        JLabel jenisLabel = new JLabel("Jenis Mahasiswa:");
        jenisComboBox = new JComboBox<>(new String[]{"S1", "S2"});

        // Panel untuk mahasiswa S1
        s1Panel = new JPanel(new GridBagLayout());
        JLabel programStudiLabel = new JLabel("Program Studi:");
        programStudiField = new JTextField(20);
        JLabel jumlahSKSLabel = new JLabel("Jumlah SKS:");
        jumlahSKSField = new JTextField(20);

        GridBagConstraints s1Gbc = new GridBagConstraints();
        s1Gbc.insets = new Insets(5, 5, 5, 5);
        s1Gbc.gridx = 0;
        s1Gbc.gridy = 0;
        s1Panel.add(programStudiLabel, s1Gbc);
        s1Gbc.gridx = 1;
        s1Panel.add(programStudiField, s1Gbc);
        s1Gbc.gridx = 0;
        s1Gbc.gridy = 1;
        s1Panel.add(jumlahSKSLabel, s1Gbc);
        s1Gbc.gridx = 1;
        s1Panel.add(jumlahSKSField, s1Gbc);

        // Panel untuk mahasiswa S2
        s2Panel = new JPanel(new GridBagLayout());
        JLabel topikTesisLabel = new JLabel("Topik Tesis:");
        topikTesisField = new JTextField(20);
        JLabel namaPembimbingLabel = new JLabel("Nama Pembimbing:");
        namaPembimbingField = new JTextField(20);

        GridBagConstraints s2Gbc = new GridBagConstraints();
        s2Gbc.insets = new Insets(5, 5, 5, 5);
        s2Gbc.gridx = 0;
        s2Gbc.gridy = 0;
        s2Panel.add(topikTesisLabel, s2Gbc);
        s2Gbc.gridx = 1;
        s2Panel.add(topikTesisField, s2Gbc);
        s2Gbc.gridx = 0;
        s2Gbc.gridy = 1;
        s2Panel.add(namaPembimbingLabel, s2Gbc);
        s2Gbc.gridx = 1;
        s2Panel.add(namaPembimbingField, s2Gbc);

        // Tombol Simpan
        saveButton = new JButton("Simpan Data");
        saveButton.setBackground(new Color(0, 123, 255)); // Blue button
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Menambahkan komponen ke frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(namaLabel, gbc);

        gbc.gridx = 1;
        add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(jurusanLabel, gbc);

        gbc.gridx = 1;
        add(jurusanField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(angkatanLabel, gbc);

        gbc.gridx = 1;
        add(angkatanField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(jenisLabel, gbc);

        gbc.gridx = 1;
        add(jenisComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(s1Panel, gbc);

        gbc.gridy = 5;
        add(s2Panel, gbc);

        gbc.gridy = 6;
        add(saveButton, gbc);

        // Action listener untuk jenis mahasiswa
        jenisComboBox.addActionListener(e -> updateForm());

        // Default: tampilkan S1
        updateForm();

        // Action listener untuk tombol Simpan
        saveButton.addActionListener(e -> saveMahasiswa());
    }

    // Method untuk menyembunyikan/menampilkan panel berdasarkan jenis mahasiswa
    private void updateForm() {
        String selectedJenis = (String) jenisComboBox.getSelectedItem();
        if ("S1".equals(selectedJenis)) {
            s1Panel.setVisible(true);
            s2Panel.setVisible(false);
        } else {
            s1Panel.setVisible(false);
            s2Panel.setVisible(true);
        }
        // Refresh frame untuk memastikan perubahan terlihat
        revalidate();
        repaint();
    }

    // Method untuk menyimpan data mahasiswa ke database
    public void saveMahasiswa() {
        String nama = namaField.getText();
        String jurusan = jurusanField.getText();
        String angkatan = angkatanField.getText();
        String jenis = (String) jenisComboBox.getSelectedItem();
        String programStudi = programStudiField.getText();
        String jumlahSKS = jumlahSKSField.getText();
        String topikTesis = topikTesisField.getText();
        String namaPembimbing = namaPembimbingField.getText();

        // Validasi input (Nama, Jurusan, dan Angkatan wajib diisi)
        if (nama.isEmpty() || jurusan.isEmpty() || angkatan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kolom Nama, Jurusan, dan Angkatan wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validasi tambahan berdasarkan jenis mahasiswa
        if ("S1".equals(jenis) && (programStudi.isEmpty() || jumlahSKS.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Kolom Program Studi dan Jumlah SKS wajib diisi untuk mahasiswa S1!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if ("S2".equals(jenis) && (topikTesis.isEmpty() || namaPembimbing.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Kolom Topik Tesis dan Nama Pembimbing wajib diisi untuk mahasiswa S2!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Konversi jumlah SKS menjadi integer (hanya untuk S1)
        int sks = 0;
        if ("S1".equals(jenis)) {
            try {
                sks = Integer.parseInt(jumlahSKS);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Jumlah SKS harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Simpan ke database
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Koneksi.getConnection(); // Menyambung ke database
            String sql = "INSERT INTO mahasiswa (nama, jurusan, angkatan, jenis_mahasiswa, program_studi, jumlah_sks, topik_tesis, nama_pembimbing) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, nama);
            stmt.setString(2, jurusan);
            stmt.setString(3, angkatan);
            stmt.setString(4, jenis);
            stmt.setString(5, programStudi.isEmpty() ? null : programStudi);
            stmt.setInt(6, sks);
            stmt.setString(7, topikTesis.isEmpty() ? null : topikTesis);
            stmt.setString(8, namaPembimbing.isEmpty() ? null : namaPembimbing);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil disimpan ke database!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data ke database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method untuk menampilkan form
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MahasiswaForm().setVisible(true));
    }
}
