package src;

public class MahasiswaS1 extends Mahasiswa {
        private String programStudi;
        private int jumlahSKS;
    
        // Constructor
        public MahasiswaS1(String id, String nama, String jurusan, String angkatan, String programStudi, int jumlahSKS) {
            super(id, nama, jurusan, angkatan); // Memanggil constructor parent
            this.programStudi = programStudi;
            this.jumlahSKS = jumlahSKS;
        }
    
        // Getter dan Setter
        public String getProgramStudi() {
            return programStudi;
        }
    
        public void setProgramStudi(String programStudi) {
            this.programStudi = programStudi;
        }
    
        public int getJumlahSKS() {
            return jumlahSKS;
        }
    
        public void setJumlahSKS(int jumlahSKS) {
            this.jumlahSKS = jumlahSKS;
        }
    
        // Override displayInfo untuk Polymorphism
        @Override
        public void displayInfo() {
            super.displayInfo();  // Memanggil displayInfo dari parent
            System.out.println("Program Studi: " + programStudi);
            System.out.println("Jumlah SKS: " + jumlahSKS);
        }
}
    

