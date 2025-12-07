package Model;

public class Servis {

    private String id_servis;
    private String nama_pelanggan;
    private String nama_barangservis;
    private String servis;
    private String status;

    public Servis() {
    }

    public String getId_servis() {
        return id_servis;
    }

    public void setId_servis(String id_servis) {
        this.id_servis = id_servis;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getNama_barangservis() {
        return nama_barangservis;
    }

    public void setNama_barangservis(String nama_barangservis) {
        this.nama_barangservis = nama_barangservis;
    }

    public String getServis() {
        return servis;
    }

    public void setServis(String servis) {
        this.servis = servis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
