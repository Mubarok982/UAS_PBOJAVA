package src;

public class MahasiswaS2 extends Mahasiswa {
        private String topikTesis;
        private String namaPembimbing;
    
        // Constructor
        public MahasiswaS2(String id, String nama, String jurusan, String angkatan, String topikTesis, String namaPembimbing) {
            super(id, nama, jurusan, angkatan); // Memanggil constructor parent
            this.topikTesis = topikTesis;
            this.namaPembimbing = namaPembimbing;
        }
    
        // Getter dan Setter
        public String getTopikTesis() {
            return topikTesis;
        }
    
        public void setTopikTesis(String topikTesis) {
            this.topikTesis = topikTesis;
        }
    
        public String getNamaPembimbing() {
            return namaPembimbing;
        }
    
        public void setNamaPembimbing(String namaPembimbing) {
            this.namaPembimbing = namaPembimbing;
        }
    
        // Override displayInfo untuk Polymorphism
        @Override
        public void displayInfo() {
            super.displayInfo();  // Memanggil displayInfo dari parent
            System.out.println("Topik Tesis: " + topikTesis);
            System.out.println("Nama Pembimbing: " + namaPembimbing);
        }
}
    

