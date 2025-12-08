package Controller;

import Model.Servis;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class ServisController {

    public Statement stm;
    public ResultSet res;
    public String sql;

    // Wadah tabel virtual
    DefaultTableModel dtm = new DefaultTableModel();

    // Konstruktor
    public ServisController() {
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }

      //Method 1: Membuat model atau desain tabel virtual
    public DefaultTableModel createTable() {
        this.dtm.addColumn("ID Servis");
        this.dtm.addColumn("Nama Pelanggan");
        this.dtm.addColumn("Kendaraan/Barang");
        this.dtm.addColumn("Keluhan/Servis");
        this.dtm.addColumn("Status");
        return this.dtm;
    }

    // Method 2: TAMPILKAN SEMUA DATA (Untuk Admin/History)
    public void tampilkanServis() {
        try {
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();

            this.sql = "SELECT * FROM tb_servis";
            this.res = this.stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_servis");
                obj[1] = res.getString("nama_pelanggan");
                obj[2] = res.getString("nama_barangservis");
                obj[3] = res.getString("servis");
                obj[4] = res.getString("status");
                this.dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Gagal query servis: " + e);
        }
    }

    // Method 2b: TAMPILKAN KHUSUS ANTRIAN (Untuk Dashboard Mekanik)
    public void tampilkanAntrianMekanik() {
        try {
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();

            // Filter hanya yang belum selesai
            this.sql = "SELECT * FROM tb_servis WHERE status != 'Selesai"; 
            this.res = this.stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_servis");
                obj[1] = res.getString("nama_pelanggan");
                obj[2] = res.getString("nama_barangservis");
                obj[3] = res.getString("servis");
                obj[4] = res.getString("status");
                this.dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Gagal query antrian: " + e);
        }
    }

    // Method 3: TAMBAH SERVIS BARU (Fitur Kasir)
    // Status default otomatis "Menunggu"
    public boolean tambahServis(String id, String nama, String barang, String keluhan) {
        Servis srv = new Servis();
        srv.setId_servis(id);
        srv.setNama_pelanggan(nama);
        srv.setNama_barangservis(barang);
        srv.setServis(keluhan);
        srv.setStatus("Menunggu"); 

        try {
            this.sql = "INSERT INTO tb_servis (id_servis, nama_pelanggan, nama_barangservis, servis, status) VALUES " + "('" + srv.getId_servis() + "', '" + srv.getNama_pelanggan() + "', '" 
                     + srv.getNama_barangservis() + "', '" + srv.getServis() + "', '" + srv.getStatus() + "')";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal tambah servis: " + e);
            return false;
        }
    }

    // Method 4: UPDATE STATUS (Fitur Mekanik)
    // Mekanik cuma perlu update status (misal jadi 'Proses' atau 'Selesai')
    public boolean updateStatus(String id, String statusBaru) {
        try {
            this.sql = "UPDATE tb_servis SET status='" + statusBaru + "' WHERE id_servis='" + id + "'";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal update status: " + e);
            return false;
        }
    }
    
    // Method 5: UPDATE LENGKAP (Fitur Admin/Kasir jika ada salah ketik)
    public boolean ubahServis(String id, String nama, String barang, String keluhan, String status) {
        try {
            this.sql = "UPDATE tb_servis SET nama_pelanggan='" + nama + "', " + "nama_barangservis='" + barang + "', "+ "servis='" + keluhan + "', "+ "status='" + status + "' "
                     + "WHERE id_servis='" + id + "'";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal ubah servis: " + e);
            return false;
        }
    }

    // Method 6: HAPUS SERVIS (Jika batal)
    public boolean hapusServis(String id) {
        try {
            this.sql = "DELETE FROM tb_servis WHERE id_servis='" + id + "'";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal hapus servis: " + e);
            return false;
        }
    }
}
