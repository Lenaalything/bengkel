package Model;

public class Gaji {

    private int id_gaji;
    private String id_karyawan;
    private double gaji;
    private boolean status_pengambilan_gaji;

    public Gaji() {
    }

    public int getId_gaji() {
        return id_gaji;
    }

    public void setId_gaji(int id_gaji) {
        this.id_gaji = id_gaji;
    }

    public String getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public double getGaji() {
        return gaji;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    public boolean isStatus_pengambilan_gaji() {
        return status_pengambilan_gaji;
    }

    public void setStatus_pengambilan_gaji(boolean status_pengambilan_gaji) {
        this.status_pengambilan_gaji = status_pengambilan_gaji;
    }
}
