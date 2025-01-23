package src;


public class Mahasiswa {
    private String id;
    private String nama;
    private String jurusan;
    private String angkatan;

    // Constructor
    public Mahasiswa(String id, String nama, String jurusan, String angkatan) {
        this.id = id;
        this.nama = nama;
        this.jurusan = jurusan;
        this.angkatan = angkatan;
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    // Method displayInfo untuk Polymorphism
    public void displayInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Jurusan: " + jurusan);
        System.out.println("Angkatan: " + angkatan);
    }
}


