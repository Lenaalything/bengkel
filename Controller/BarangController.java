package Controller;

import Model.Barang;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class BarangController {

    public Statement stm;
    public ResultSet res;
    public String sql;

    //model tabel/bentuk tabel/tabel virtual sebelum di apply di view
    DefaultTableModel dtm = new DefaultTableModel();// import dulu 

    //Konstruktor
    public BarangController() {
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }

    //Method 1: Membuat model atau desain tabel virtual
    public DefaultTableModel createTable() {
        this.dtm.addColumn("ID Barang");
        this.dtm.addColumn("Nama Barang");
        this.dtm.addColumn("Harga");
        this.dtm.addColumn("Stok");
        return this.dtm;
    }

    public void TampilkanBarang() {
        try {
            //persiapkan tabel virtual
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();

            //query
            this.sql = "SELECT * FROM tbuser";

            //jalankan
            this.res = this.stm.executeQuery(sql);

            //masukkan hasil querynya ke tabel virtual
            while (res.next()) {
                Object[] obj = new Object[4];
                //nama harus sama dg yg di database
                obj[0] = res.getString("id_barang");
                obj[1] = res.getString("nama_barang");
                obj[2] = res.getString("harga_barang");
                obj[3] = res.getString("stok");
                this.dtm.addRow(obj);
            }

        } catch (Exception e) {
            System.out.println("Gagal query " + e);
        }
    }
    //TambahBarang

    public boolean tambahBarang(String a, String b, double c, int d) {
        //ubungkan dengan model
        Barang bar = new Barang();
        //set nilai ke model
        bar.setId_barang(a);
        bar.setNama_barang(b);

        try {
            //query
            this.sql = "INSERT INTO tb_barang(id_barang, nama_barang, harga_barang, stok) VALUES "
                    + "('" + bar.getId_barang() + "','" + bar.getNama_barang() + "'," + bar.getHarga_barang() + "," + bar.getStok() + ")";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal tambah: " + e);
            return false;
        }
    }

    public boolean ubahBarang(String a, String b, double c, int d) {
        Barang bar = new Barang();
        bar.setId_barang(a);
        bar.setNama_barang(b);
        bar.setHarga_barang(c);
        bar.setStok(d);

        try {
            //query
            //Perhatikan tanda petik satu (') untuk tipe data String
         this.sql = "UPDATE tb_barang SET nama_barang='" + bar.getNama_barang() + "', harga_barang=" + bar.getHarga_barang()+ ", stok=" + bar.getStok() 
                     + " WHERE id_barang='" + bar.getId_barang() + "'";

            //untuk insert,update,delete gunakan .executeUpdate(sql)
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal update: " + e);
            return false;
        }
    }
        //Hapus Anggota
        public boolean hapusBarang(int a) {
         Barang bar = new Barang();
         bar.getId_barang();

        try {
            this.sql = "DELETE FROM tb_barang WHERE id="+bar.getId_barang()+" ";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
