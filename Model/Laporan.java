package Model;

public class Laporan {

    private String id_laporan;
    private String tanggal; // Date SQL bisa mapping ke String atau java.sql.Date
    private double total_penghasilan;
    private double total_pengeluaran;
    private double rata_rata_harian;

    public Laporan() {
    }

    public String getId_laporan() {
        return id_laporan;
    }

    public void setId_laporan(String id_laporan) {
        this.id_laporan = id_laporan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public double getTotal_penghasilan() {
        return total_penghasilan;
    }

    public void setTotal_penghasilan(double total_penghasilan) {
        this.total_penghasilan = total_penghasilan;
    }

    public double getTotal_pengeluaran() {
        return total_pengeluaran;
    }

    public void setTotal_pengeluaran(double total_pengeluaran) {
        this.total_pengeluaran = total_pengeluaran;
    }

    public double getRata_rata_harian() {
        return rata_rata_harian;
    }

    public void setRata_rata_harian(double rata_rata_harian) {
        this.rata_rata_harian = rata_rata_harian;
    }
}
