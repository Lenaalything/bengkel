package Model;

public class Mekanik {

    private String id_pegawai;
    private String nama;
    private String password;

    // Constructor Kosong
    public Mekanik() {
    }

    // Getter dan Setter
    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
