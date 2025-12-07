package Model;

public class Absen {

    private int id_absen;
    private String id_karyawan;
    private String nama;
    private String tanggal_absen; 

    // Constructor Kosong
    public Absen() {
    }

    // Getter Setter
    public int getId_absen() {
        return id_absen;
    }

    public void setId_absen(int id_absen) {
        this.id_absen = id_absen;
    }

    public String getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_absen() {
        return tanggal_absen;
    }

    public void setTanggal_absen(String tanggal_absen) {
        this.tanggal_absen = tanggal_absen;
    }
}
