/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n3dshop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class MenuUtama extends javax.swing.JFrame {

    int a, hasil;
    private String hrg_ecer, hrg_grosir, Id, No_faktur, totalmembayar1, totalmembayar2, tglpenjualan, nama_barangpnj, jml_gr, jml_ecr, jumlah_ecerpnj, jumlah_grosirpnj, eceranpnj, grosirpnj, hrg_ecerpnj, hrg_grosirpnj;
    private String Id_barang, SATUAN, SATUANpmb, tgl, kode, stokBarangEcer, stokBarangGrosir, id_trpembelian, id_trpenjualan, id_return, suppid, ecr_supp, grs_supp;
    private Date tglsekarang;
    public PreparedStatement pst;

    /**
     * Creates new form jam_digital
     */
    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        initComponents();
        load_table();
        Banyak();
        tampil_data();
        tampil_barang();
        Tampil_Jam();
        PilihSatuan();
        Tampil_Tanggal();
        tampil_combo();
        transaksi_pembelian_id();
        transaksi_penjualan_id();
        return_barang_id();
        Supplier_id();
        HargaTot();
        tabel_return_barang();
        id_barangi1.requestFocus();
    }

    public void Tampil_Jam() {
        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "", nol_detik = "";

                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();

                if (nilai_jam <= 9) {
                    nol_jam = "0";
                }
                if (nilai_menit <= 9) {
                    nol_menit = "0";
                }
                if (nilai_detik <= 9) {
                    nol_detik = "0";
                }

                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);

                tanggalreal.setText(jam + ":" + menit + ":" + detik + "");
            }
        };
        new Timer(1000, taskPerformer).start();
    }

    private void transaksi_pembelian_id() {
        try {
            String sql = "SELECT COUNT(Id_pembelian) AS ttl_id from transaksi_pembelian";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            rs.next();
            id_trpembelian = rs.getString("ttl_id");
            if (Integer.valueOf(id_trpembelian) == 0) {
                txt_IDT.setText("T-1");
                String sql12 = "Insert into transaksi_pembelian (Id_pembelian) values ('" + txt_IDT.getText() + "')";
                java.sql.PreparedStatement pst1 = cn.prepareStatement(sql12);
                pst1.execute();
            } else {
                String sql1 = "SELECT COUNT(Id_pembelian) AS ttl_id from transaksi_pembelian";
                java.sql.Connection cn1 = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st1 = cn1.createStatement();
                java.sql.ResultSet rs1 = st1.executeQuery(sql1);
                rs1.next();
                txt_IDT.setText("T-"+rs1.getString("ttl_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void transaksi_penjualan_id() {
        try {
            String sql = "SELECT COUNT(no_faktur) AS ttl_id from transaksi_penjualan";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            rs.next();
            id_trpenjualan = rs.getString("ttl_id");
            if (Integer.valueOf(id_trpenjualan) == 0) {
                IDT_Penjualan.setText("TRN-1");
                String sql12 = "Insert into transaksi_penjualan (no_faktur) values ('" + IDT_Penjualan.getText() + "')";
                java.sql.PreparedStatement pst1 = cn.prepareStatement(sql12);
                pst1.execute();
            } else {
                String sql1 = "SELECT COUNT(no_faktur)  AS ttl_id from transaksi_penjualan";
                java.sql.Connection cn1 = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st1 = cn1.createStatement();
                java.sql.ResultSet rs1 = st1.executeQuery(sql1);
                rs1.next();
                IDT_Penjualan.setText("TRN-" + rs1.getString("ttl_id"));
                id_transaksi.setText("TRN-"+ rs1.getString("ttl_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void return_barang_id() {
        try {
            String sql = "SELECT COUNT(id_return) AS ttl_id from return_barang";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            rs.next();
            id_return = rs.getString("ttl_id");
            if (Integer.valueOf(id_return) == 0) {
                ReturnID.setText("RTN-1");
                String sql12 = "Insert into return_barang (id_return) values ('" + ReturnID.getText() + "')";
                java.sql.PreparedStatement pst1 = cn.prepareStatement(sql12);
                pst1.execute();
            } else {
                String sql1 = "SELECT COUNT(id_return) AS ttl_id from return_barang";
                java.sql.Connection cn1 = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st1 = cn1.createStatement();
                java.sql.ResultSet rs1 = st1.executeQuery(sql1);
                rs1.next();
                ReturnID.setText("RTN-"+rs1.getString("ttl_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Supplier_id() {
        try {
            String sql = "SELECT COUNT(id_supplier) AS ttl_id from supplier";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            rs.next();
            id_trpembelian = rs.getString("ttl_id");
            if (Integer.valueOf(id_trpembelian) == 0) {
                IDTSupplier.setText("SP-1");
                String sql12 = "Insert into supplier (id_supplier) values ('" + namaSupplier.getText() + "')";
                java.sql.PreparedStatement pst1 = cn.prepareStatement(sql12);
                pst1.execute();
            } else {
                String sql1 = "SELECT COUNT(id_supplier) AS ttl_id from supplier";
                java.sql.Connection cn1 = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st1 = cn1.createStatement();
                java.sql.ResultSet rs1 = st1.executeQuery(sql1);
                rs1.next();
                IDTSupplier.setText("SP-"+rs1.getString("ttl_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Tampil_Tanggal() {
        tglsekarang = new Date();
        SimpleDateFormat smpdtfmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        tgl = smpdtfmt.format(tglsekarang);

        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        tglpenjualan = String.valueOf(fm.format(tglsekarang));

    }

    private void load_table() {
        //membuat tampilan tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id barang");
        model.addColumn("Nama barang");
        model.addColumn("Stok ecer");
        model.addColumn("Stok grosir");
        model.addColumn("Eceran");
        model.addColumn("Grosir");
        model.addColumn("Harga Ecer");
        model.addColumn("Harga Grosir");
        //menampilkan database dalam tabel
        try {
            String sql = "SELECT id_barang, nama_barang, stok_ecer, stok_grosir, eceran, grosir, harga_eceran, harga_grosir FROM barang;";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_barang"),
                    res.getString("nama_barang"), res.getString("stok_ecer"), res.getString("stok_grosir"), res.getString("eceran"), res.getString("grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
            }
            barang_barang.setModel(model);
        } catch (Exception e) {
        }
    }

    public void tabel_return_barang() {
        //membuat tampilan tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID BARANG");
        model.addColumn("ECERAN");
        model.addColumn("GROSIR");
        model.addColumn("JUMLAH ECER");
        model.addColumn("JUMLAH GROSIR");

        //menampilkan database dalam tabel
        try {
            String sql = "SELECT Id_barang, eceran, grosir, jumlah_ecer, jumlah_grosir FROM `keranjang` WHERE keterangan = 'RETURN';";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("Id_barang"), res.getString("eceran"), res.getString("grosir"), res.getString("jumlah_grosir"), res.getString("jumlah_ecer")});
            }
            returnnnn.setModel(model);
        } catch (Exception e) {
        }
    }

    private void hapus() {
        namaprd.setText("");
        jumlahprd.setText("");
        hargaprduk.setText("");
        cari.setText("");
        satuan.setSelectedItem("SATUAN");
    }

    private void Banyak() {
        try {
            String sql = "SELECT COUNT(Id_barang) AS total FROM barang";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            res.next();
            total.setText(res.getString("total"));
        } catch (Exception e) {
        }
    }

    public void tampil_barang() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id barang");
        model.addColumn("Nama barang");
        model.addColumn("Satuan Ecer");
        model.addColumn("Satuan Grosir");
        model.addColumn("Jumlah Eceran");
        model.addColumn("Jumlah Grosir");
        model.addColumn("Harga Eceran");
        model.addColumn("Harga Grosir");

        try {
            String sql = "select * from keranjang where keterangan = 'PEMBELIAN'";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = cn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_barang"),
                    res.getString("nama_barang"), res.getString("eceran"), res.getString("grosir"), res.getString("jumlah_ecer"), res.getString("jumlah_grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
            }
            table_barang.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak muncul");
        }
    }

    public void tampil_data() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id barang");
        model.addColumn("Nama barang");
        model.addColumn("Satuan Ecer");
        model.addColumn("Satuan Grosir");
        model.addColumn("Jumlah Eceran");
        model.addColumn("Jumlah Grosir");
        model.addColumn("Harga Eceran");
        model.addColumn("Harga Grosir");

        try {
            String sql = "select * from keranjang where keterangan = 'PENJUALAN'";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = cn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_barang"),
                    res.getString("nama_barang"), res.getString("eceran"), res.getString("grosir"), res.getString("jumlah_ecer"), res.getString("jumlah_grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
            }

            list_barang.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak muncul");
        }
    }


    private void HargaTot() {
        try {
            String sql = "SELECT SUM(harga_eceran) AS total, SUM(harga_grosir) AS ttl, jumlah_grosir, jumlah_ecer FROM keranjang WHERE keterangan = 'PENJUALAN'";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            res.next();
            totalmembayar1 = res.getString("total");
            totalmembayar2 = res.getString("ttl");
            jml_gr = res.getString("jumlah_grosir");
            jml_ecr = res.getString("jumlah_ecer");
            hasil = (Integer.valueOf(totalmembayar1) * Integer.valueOf(jml_ecr)) + (Integer.valueOf(totalmembayar2) * Integer.valueOf(jml_gr));
            if (hasil == 0) {
                HargaBayar.setText("Rp. ");
            } else {
                HargaBayar.setText("Rp. " + String.valueOf(hasil));
            }

        } catch (Exception e) {
        }
    }

    private void TotalPembelian() {

        try {
            String sql = "SELECT SUM(harga_eceran) AS totalEceran, SUM(harga_grosir) AS totalGrosir from keranjang where keterangan = 'PEMBELIAN'";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            rs.next();
            String totalecr = rs.getString("totalEceran");
            String totalgsr = rs.getString("totalGrosir");

            if (totalecr == null && totalgsr == null) {
                hargaTotal.setText("Rp. ");
            } else if (totalecr == null) {
                int totalkeseluruhan = 0 + Integer.valueOf(totalgsr);
                hargaTotal.setText("Rp. " + String.valueOf(totalkeseluruhan));
            } else if (totalgsr == null) {
                int totalkeseluruhan = Integer.valueOf(totalecr) + 0;
                hargaTotal.setText("Rp. " + String.valueOf(totalkeseluruhan));
            } else {
                int totalkeseluruhan = Integer.valueOf(totalecr) + Integer.valueOf(totalgsr);
                hargaTotal.setText("Rp. " + String.valueOf(totalkeseluruhan));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PilihSatuan() {
        try {
            Object pilihan = satuan.getSelectedItem();
            String sql1 = "SELECT eceran, grosir FROM barang WHERE  Id_barang = '" + Id_barang + "';";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql1);
            res.next();
            if (pilihan == "ECERAN") {
                SATUAN = res.getString("eceran");
            } else if (pilihan == "GROSIR") {
                SATUAN = res.getString("grosir");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PilihSatuan2() {
        try {
            Object pilihan = satuan4.getSelectedItem();
            String sql1 = "SELECT eceran, grosir FROM barang WHERE  Id_barang = '" + id_barangpnj.getText() + "';";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql1);
            res.next();
            if (pilihan == "ECERAN") {
                SATUAN = res.getString("eceran");
            } else if (pilihan == "GROSIR") {
                SATUAN = res.getString("grosir");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        strukpnj = new javax.swing.JScrollPane();
        strukpeminjaman = new javax.swing.JTextArea();
        strukpemb = new javax.swing.JScrollPane();
        strukpembelian = new javax.swing.JTextArea();
        txt_IDTOther = new javax.swing.JTextField();
        NamaKasir = new javax.swing.JTextField();
        UsernameKasir = new javax.swing.JTextField();
        IDTSupplier = new javax.swing.JLabel();
        txt_IDT = new javax.swing.JLabel();
        hargaprduk1 = new javax.swing.JTextField();
        ReturnID = new javax.swing.JLabel();
        IDT_Penjualan = new javax.swing.JLabel();
        Kasir = new javax.swing.JPanel();
        Cari = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_tpembelian = new javax.swing.JButton();
        btn_tjual1 = new javax.swing.JButton();
        btn_tjual2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        barang_barang = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        namaprd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tambah = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        batal = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        satuan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jumlahprd = new javax.swing.JTextField();
        hargaprduk = new javax.swing.JTextField();
        TransaksiPenjualan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        HargaBayar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_barang = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        id_transaksi = new javax.swing.JTextField();
        jumlah_barang = new javax.swing.JTextField();
        bayar1 = new javax.swing.JTextField();
        kembali = new javax.swing.JTextField();
        CETAK = new javax.swing.JButton();
        bataltrn = new javax.swing.JButton();
        tanggalreal = new javax.swing.JLabel();
        kembalikecari = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        mencariPenjualan = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        id_barangpnj = new javax.swing.JTextField();
        satuan4 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        clear3 = new javax.swing.JButton();
        TambahBrg = new javax.swing.JButton();
        TransaksiPembelian = new javax.swing.JPanel();
        clear1 = new javax.swing.JButton();
        namaSupplier = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_barang = new javax.swing.JTable();
        hargaTotal = new javax.swing.JTextField();
        mencari1 = new javax.swing.JTextField();
        jumlah_pembelian = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        id_barangi1 = new javax.swing.JTextField();
        namabarang1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        print = new javax.swing.JButton();
        tambahprd = new javax.swing.JButton();
        btlprd = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        satuan1 = new javax.swing.JComboBox<>();
        namasatuan1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        harga1 = new javax.swing.JTextField();
        kembalikecari1 = new javax.swing.JButton();
        logout1 = new javax.swing.JButton();
        Return = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        id_pembelian = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jumlahBarang1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        namaBarang1 = new javax.swing.JTextField();
        CETAK1 = new javax.swing.JButton();
        tanggalreal1 = new javax.swing.JLabel();
        kembalikecari2 = new javax.swing.JButton();
        logout2 = new javax.swing.JButton();
        mencariBarang1 = new javax.swing.JTextField();
        satuan2 = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        satuan3 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        keteranganrtn = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();
        tambahprd1 = new javax.swing.JButton();
        clear2 = new javax.swing.JButton();
        btlprd1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        returnnnn = new javax.swing.JTable();

        strukpeminjaman.setColumns(20);
        strukpeminjaman.setRows(5);
        strukpnj.setViewportView(strukpeminjaman);

        strukpembelian.setColumns(20);
        strukpembelian.setRows(5);
        strukpemb.setViewportView(strukpembelian);

        txt_IDTOther.setText("jTextField1");

        IDTSupplier.setText("jLabel32");

        txt_IDT.setForeground(new java.awt.Color(255, 255, 255));
        txt_IDT.setText("jLabel31");

        hargaprduk1.setEditable(false);
        hargaprduk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaprduk1ActionPerformed(evt);
            }
        });
        hargaprduk1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hargaprduk1KeyReleased(evt);
            }
        });

        ReturnID.setText("jLabel32");

        IDT_Penjualan.setText("jLabel32");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1280, 720));

        Kasir.setLayout(new java.awt.CardLayout());

        Cari.setBackground(new java.awt.Color(0, 9, 87));
        Cari.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel2.setBackground(new java.awt.Color(52, 76, 183));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Angkringan__1_-removebg-preview 1.png"))); // NOI18N

        btn_tpembelian.setBackground(new java.awt.Color(252, 242, 6));
        btn_tpembelian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_tpembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/icons8_buy.png"))); // NOI18N
        btn_tpembelian.setText("TRANSAKSI BELI");
        btn_tpembelian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_tpembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tpembelianActionPerformed(evt);
            }
        });
        btn_tpembelian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btn_tpembelianKeyReleased(evt);
            }
        });

        btn_tjual1.setBackground(new java.awt.Color(252, 242, 6));
        btn_tjual1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_tjual1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Vector-2.png"))); // NOI18N
        btn_tjual1.setText("TRANSAKSI JUAL");
        btn_tjual1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_tjual1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tjual1ActionPerformed(evt);
            }
        });

        btn_tjual2.setBackground(new java.awt.Color(252, 242, 6));
        btn_tjual2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_tjual2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/returning.png"))); // NOI18N
        btn_tjual2.setText("RETURN BARANG");
        btn_tjual2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_tjual2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tjual2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(516, 516, 516)
                .addComponent(btn_tjual2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_tjual1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btn_tpembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_tjual1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_tjual2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_tpembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DAFTAR PRODUK");

        jPanel3.setBackground(new java.awt.Color(116, 114, 203));

        jScrollPane1.setBorder(null);

        barang_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        barang_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barang_barangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                barang_barangMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(barang_barang);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cari Product");

        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("TOTAL :");

        total.setBackground(new java.awt.Color(255, 255, 255));
        total.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                totalPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total)
                .addGap(156, 156, 156))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(total)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("NAMA PRODUK");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("HARGA");

        tambah.setBackground(new java.awt.Color(252, 242, 6));
        tambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Group 10.png"))); // NOI18N
        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        batal.setBackground(new java.awt.Color(252, 242, 6));
        batal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Vector-1.png"))); // NOI18N
        batal.setText("BATAL");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SATUAN");

        satuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SATUAN", "ECERAN", "GROSIR" }));
        satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satuanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("JUMLAH BARANG");

        jumlahprd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahprdActionPerformed(evt);
            }
        });
        jumlahprd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahprdKeyReleased(evt);
            }
        });

        hargaprduk.setEditable(false);
        hargaprduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaprdukActionPerformed(evt);
            }
        });
        hargaprduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hargaprdukKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout CariLayout = new javax.swing.GroupLayout(Cari);
        Cari.setLayout(CariLayout);
        CariLayout.setHorizontalGroup(
            CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(CariLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CariLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CariLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(satuan, javax.swing.GroupLayout.Alignment.LEADING, 0, 319, Short.MAX_VALUE)
                                .addComponent(namaprd, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CariLayout.createSequentialGroup()
                                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(hargaprduk, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addComponent(jumlahprd, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        CariLayout.setVerticalGroup(
            CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CariLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CariLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namaprd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(7, 7, 7)
                        .addComponent(jumlahprd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hargaprduk, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(CariLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        Kasir.add(Cari, "card3");

        TransaksiPenjualan.setBackground(new java.awt.Color(0, 9, 87));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TRANSAKSI PENJUALAN");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        HargaBayar.setBackground(new java.awt.Color(255, 232, 22));
        HargaBayar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        HargaBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HargaBayarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CARI");

        list_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(list_barang);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("KODE TRANSAKSI");

        jumlah_barang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlah_barangKeyReleased(evt);
            }
        });

        bayar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayar1ActionPerformed(evt);
            }
        });
        bayar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayar1KeyReleased(evt);
            }
        });

        CETAK.setBackground(new java.awt.Color(255, 232, 22));
        CETAK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/SCANBESAR.png"))); // NOI18N
        CETAK.setText("CETAK");
        CETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CETAKActionPerformed(evt);
            }
        });

        bataltrn.setBackground(new java.awt.Color(255, 232, 22));
        bataltrn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/flat-color-icons_cancel.png"))); // NOI18N
        bataltrn.setText("BATAL");
        bataltrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bataltrnActionPerformed(evt);
            }
        });

        tanggalreal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tanggalreal.setForeground(new java.awt.Color(255, 255, 255));
        tanggalreal.setText("tanggal");

        kembalikecari.setBackground(new java.awt.Color(255, 232, 22));
        kembalikecari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/KEMBALI.png"))); // NOI18N
        kembalikecari.setText("KEMBALI");
        kembalikecari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalikecariActionPerformed(evt);
            }
        });

        logout.setBackground(new java.awt.Color(255, 232, 22));
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/logout.png"))); // NOI18N
        logout.setText(" KELUAR");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        mencariPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mencariPenjualanActionPerformed(evt);
            }
        });
        mencariPenjualan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mencariPenjualanKeyReleased(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("ID BARANG");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("JUMLAH BARANG");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("BAYAR");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("KEMBALI");

        id_barangpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_barangpnjKeyReleased(evt);
            }
        });

        satuan4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SATUAN", "ECERAN", "GROSIR" }));
        satuan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satuan4ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("SATUAN");

        clear3.setBackground(new java.awt.Color(255, 232, 22));
        clear3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/clear.png"))); // NOI18N
        clear3.setText(" HAPUS");
        clear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear3ActionPerformed(evt);
            }
        });

        TambahBrg.setBackground(new java.awt.Color(255, 232, 22));
        TambahBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Group 10.png"))); // NOI18N
        TambahBrg.setText("TAMBAH");
        TambahBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahBrgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransaksiPenjualanLayout = new javax.swing.GroupLayout(TransaksiPenjualan);
        TransaksiPenjualan.setLayout(TransaksiPenjualanLayout);
        TransaksiPenjualanLayout.setHorizontalGroup(
            TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiPenjualanLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(HargaBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiPenjualanLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiPenjualanLayout.createSequentialGroup()
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                                .addComponent(tanggalreal)
                                .addGap(861, 861, 861)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(mencariPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                                .addComponent(kembalikecari, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CETAK, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TransaksiPenjualanLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(31, 31, 31)
                                        .addComponent(id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel33))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(satuan4, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(id_barangpnj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)))
                                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel14))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bayar1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jumlah_barang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bataltrn, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TambahBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clear3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiPenjualanLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        TransaksiPenjualanLayout.setVerticalGroup(
            TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiPenjualanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(HargaBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(130, Short.MAX_VALUE))
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mencariPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tanggalreal)))
                        .addGap(35, 35, 35)
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(id_barangpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32))
                                .addGap(18, 18, 18)
                                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(satuan4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33))
                                .addGap(18, 18, 18)
                                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jumlah_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bayar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))
                            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(TambahBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bataltrn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clear3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kembalikecari, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CETAK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))))
        );

        Kasir.add(TransaksiPenjualan, "card3");

        TransaksiPembelian.setBackground(new java.awt.Color(0, 9, 87));

        clear1.setBackground(new java.awt.Color(255, 232, 22));
        clear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/clear.png"))); // NOI18N
        clear1.setText(" HAPUS");
        clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear1ActionPerformed(evt);
            }
        });

        namaSupplier.setDropMode(javax.swing.DropMode.INSERT);
        namaSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaSupplierActionPerformed(evt);
            }
        });
        namaSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaSupplierKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("TRANSAKSI PEMBELIAN");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CARI");

        table_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_barangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_barang);

        hargaTotal.setBackground(new java.awt.Color(255, 232, 22));
        hargaTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        mencari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mencari1ActionPerformed(evt);
            }
        });
        mencari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mencari1KeyReleased(evt);
            }
        });

        jumlah_pembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_pembelianActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("SUPPLIER");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("JUMLAH");

        id_barangi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_barangi1ActionPerformed(evt);
            }
        });
        id_barangi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                id_barangi1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                id_barangi1KeyTyped(evt);
            }
        });

        namabarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabarang1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("NAMA BARANG");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ID BARANG");

        print.setBackground(new java.awt.Color(255, 232, 22));
        print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/flat-color-icons_print (1).png"))); // NOI18N
        print.setText("PRINT");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        tambahprd.setBackground(new java.awt.Color(255, 232, 22));
        tambahprd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/ant-design_plus-circle-outlined.png"))); // NOI18N
        tambahprd.setText("TAMBAH");
        tambahprd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahprdActionPerformed(evt);
            }
        });

        btlprd.setBackground(new java.awt.Color(255, 232, 22));
        btlprd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/flat-color-icons_cancel.png"))); // NOI18N
        btlprd.setText("BATAL");
        btlprd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlprdActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("SATUAN");

        satuan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH SATUAN", "ECERAN", "GROSIR" }));
        satuan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satuan1ActionPerformed(evt);
            }
        });

        namasatuan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namasatuan1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("NAMA SATUAN");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("HARGA JUAL");

        harga1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                harga1ActionPerformed(evt);
            }
        });

        kembalikecari1.setBackground(new java.awt.Color(255, 232, 22));
        kembalikecari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/KEMBALI.png"))); // NOI18N
        kembalikecari1.setText("KEMBALI");
        kembalikecari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalikecari1ActionPerformed(evt);
            }
        });

        logout1.setBackground(new java.awt.Color(255, 232, 22));
        logout1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/logout.png"))); // NOI18N
        logout1.setText(" KELUAR");
        logout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransaksiPembelianLayout = new javax.swing.GroupLayout(TransaksiPembelian);
        TransaksiPembelian.setLayout(TransaksiPembelianLayout);
        TransaksiPembelianLayout.setHorizontalGroup(
            TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel15)
                .addGap(533, 533, 533)
                .addComponent(hargaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                .addGap(939, 939, 939)
                .addComponent(jLabel16)
                .addGap(12, 12, 12)
                .addComponent(mencari1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(kembalikecari1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logout1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel22))
                    .addComponent(jLabel23)
                    .addComponent(jLabel25)
                    .addComponent(jLabel13)
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel26))
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel27))
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel24)))
                .addGap(18, 18, 18)
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_barangi1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namabarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(satuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlah_pembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namasatuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(harga1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tambahprd, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btlprd, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TransaksiPembelianLayout.setVerticalGroup(
            TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel15))
                    .addComponent(hargaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel16))
                    .addComponent(mencari1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel22)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel24)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel23)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel25)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel13)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel26)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel27))
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(namaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(id_barangi1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(namabarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(satuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jumlah_pembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(namasatuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(harga1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(tambahprd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(clear1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btlprd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kembalikecari1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(logout1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        Kasir.add(TransaksiPembelian, "card3");

        Return.setBackground(new java.awt.Color(0, 9, 87));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("RETURN BARANG ");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("CARI");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("ID PEMBELIAN");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("ID BARANG");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("JUMLAH");

        jumlahBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahBarang1ActionPerformed(evt);
            }
        });
        jumlahBarang1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahBarang1KeyReleased(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("NAMA SUPLIER");

        CETAK1.setBackground(new java.awt.Color(255, 232, 22));
        CETAK1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/flat-color-icons_print (1).png"))); // NOI18N
        CETAK1.setText("CETAK");
        CETAK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CETAK1ActionPerformed(evt);
            }
        });

        tanggalreal1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tanggalreal1.setForeground(new java.awt.Color(255, 255, 255));
        tanggalreal1.setText("tanggal");

        kembalikecari2.setBackground(new java.awt.Color(255, 232, 22));
        kembalikecari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/KEMBALI.png"))); // NOI18N
        kembalikecari2.setText("KEMBALI");
        kembalikecari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalikecari2ActionPerformed(evt);
            }
        });

        logout2.setBackground(new java.awt.Color(255, 232, 22));
        logout2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/logout.png"))); // NOI18N
        logout2.setText(" KELUAR");
        logout2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout2ActionPerformed(evt);
            }
        });

        mencariBarang1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mencariBarang1KeyReleased(evt);
            }
        });

        satuan2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH SATUAN", "ECERAN", "GROSIR" }));
        satuan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satuan2ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("SATUAN");

        satuan3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH SUPPLIER" }));
        satuan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satuan3ActionPerformed(evt);
            }
        });

        keteranganrtn.setColumns(20);
        keteranganrtn.setRows(5);
        jScrollPane5.setViewportView(keteranganrtn);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("KETERANGAN");

        tambahprd1.setBackground(new java.awt.Color(255, 232, 22));
        tambahprd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/ant-design_plus-circle-outlined.png"))); // NOI18N
        tambahprd1.setText("TAMBAH");
        tambahprd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahprd1ActionPerformed(evt);
            }
        });

        clear2.setBackground(new java.awt.Color(255, 232, 22));
        clear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/clear.png"))); // NOI18N
        clear2.setText(" HAPUS");
        clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear2ActionPerformed(evt);
            }
        });

        btlprd1.setBackground(new java.awt.Color(255, 232, 22));
        btlprd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/flat-color-icons_cancel.png"))); // NOI18N
        btlprd1.setText("BATAL");
        btlprd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlprd1ActionPerformed(evt);
            }
        });

        returnnnn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(returnnnn);

        javax.swing.GroupLayout ReturnLayout = new javax.swing.GroupLayout(Return);
        Return.setLayout(ReturnLayout);
        ReturnLayout.setHorizontalGroup(
            ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ReturnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReturnLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(mencariBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(ReturnLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tanggalreal1)
                        .addGap(108, 108, 108))))
            .addGroup(ReturnLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReturnLayout.createSequentialGroup()
                        .addComponent(kembalikecari2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CETAK1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(logout2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ReturnLayout.createSequentialGroup()
                        .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(id_pembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReturnLayout.createSequentialGroup()
                                        .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel29)
                                            .addComponent(jLabel30)
                                            .addComponent(jLabel31))
                                        .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(ReturnLayout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(satuan3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 3, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReturnLayout.createSequentialGroup()
                                                .addGap(18, 23, Short.MAX_VALUE)
                                                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane5)
                                                    .addComponent(jumlahBarang1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                                    .addComponent(satuan2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReturnLayout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(namaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(27, 27, 27)
                        .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tambahprd1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clear2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btlprd1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        ReturnLayout.setVerticalGroup(
            ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReturnLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tanggalreal1))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mencariBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(21, 21, 21)
                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReturnLayout.createSequentialGroup()
                        .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ReturnLayout.createSequentialGroup()
                                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(id_pembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(namaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(satuan3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jumlahBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(satuan2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30)))
                            .addGroup(ReturnLayout.createSequentialGroup()
                                .addComponent(tambahprd1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(clear2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btlprd1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(ReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembalikecari2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CETAK1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        Kasir.add(Return, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Kasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Kasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public void tampil_combo() {
        try {
            String sql = "SELECT nama_supplier, id_supplier FROM supplier GROUP BY nama_supplier;";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                satuan3.addItem(rs.getString("nama_supplier"));
                suppid = rs.getString("id_supplier");
            }

            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();

        } catch (Exception e) {

        }
    }

    private void btn_returnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.repaint();
        Kasir.revalidate();

        Kasir.add(TransaksiPenjualan);
        Kasir.repaint();
        Kasir.revalidate();
        tampil_data();
        HargaTot();

    }

    private void cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id barang");
        model.addColumn("Nama barang");
        model.addColumn("Stok");
        model.addColumn("Eceran");
        model.addColumn("Grosir");
        model.addColumn("Harga Ecer");
        model.addColumn("Harga Grosir");
        //menampilkan database dalam tabel
        try {
            String sql = "SELECT id_barang, nama_barang, stok, eceran, grosir, harga_eceran, harga_grosir FROM barang where nama_barang LIKE '%" + cari.getText() + "%';";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_barang"),
                    res.getString("nama_barang"), res.getString("stok"), res.getString("eceran"), res.getString("grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
            }
            barang_barang.setModel(model);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cariKeyReleased

    private void totalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_totalPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_totalPropertyChange

    private void barang_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barang_barangMouseClicked
        // TODO add your handling code here:
        a = barang_barang.rowAtPoint(evt.getPoint());
        String NamaBr = (String) barang_barang.getValueAt(a, 1);
        namaprd.setText(NamaBr);

        hrg_ecer = (String) barang_barang.getValueAt(a, 6);
        hrg_grosir = (String) barang_barang.getValueAt(a, 7);

        Id_barang = (String) barang_barang.getValueAt(a, 0);

        stokBarangEcer = (String) barang_barang.getValueAt(a, 2);
        stokBarangGrosir = (String) barang_barang.getValueAt(a, 3);


    }//GEN-LAST:event_barang_barangMouseClicked

    private void hargaprdActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void jumlahprdKeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

    }

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        if (satuan.getSelectedItem() == "ECERAN") {
            try {
                PilihSatuan();
                String sql = "INSERT INTO keranjang (Id_barang, nama_barang, eceran, jumlah_ecer, harga_eceran, keterangan) Values ('" + Id_barang + "', '" + namaprd.getText() + "', '" + SATUAN + "', '" + jumlahprd.getText() + "', '" + hargaprduk.getText() + "', 'PENJUALAN')";
                String sql1 = "INSERT INTO detail_transaksi_penjualan (no_faktur, Id_barang, jumlah_ecer, satuan, harga, tanggal) Values ('" + IDT_Penjualan.getText() + "','" + Id_barang + "', '" + jumlahprd.getText() + "', '" + SATUAN + "', '" + hargaprduk.getText() + "', '" + tgl + "')";
                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst.execute();
                pst1.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                hapus();
                load_table();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        } else if (satuan.getSelectedItem() == "GROSIR") {

            try {
                PilihSatuan();
                String sql = "INSERT INTO keranjang (Id_barang, nama_barang, grosir, jumlah_grosir, harga_grosir, keterangan) Values ('" + Id_barang + "', '" + namaprd.getText() + "', '" + SATUAN + "', '" + jumlahprd.getText() + "', '" + hargaprduk.getText() + "', 'PENJUALAN')";
                String sql1 = "INSERT INTO detail_transaksi_penjualan (no_faktur, Id_barang, jumlah_grosir, satuan, harga, tanggal) Values ('" + IDT_Penjualan.getText()+ "','" + Id_barang + "', '" + jumlahprd.getText() + "', '" + SATUAN + "', '" + hargaprduk.getText() + "', '" + tgl + "')";
                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst.execute();
                pst1.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                hapus();
                load_table();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Satuan");
        }

    }//GEN-LAST:event_tambahActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
       hapus();
    }//GEN-LAST:event_batalActionPerformed

    private void btn_tpembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tpembelianActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.add(TransaksiPembelian);
        Kasir.repaint();
        Kasir.validate();
        tampil_barang();
        TotalPembelian();
        transaksi_pembelian_id();
        namaSupplier.requestFocus();
    }//GEN-LAST:event_btn_tpembelianActionPerformed

    private void satuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuanActionPerformed
        // TODO add your handling code here:
        jumlahprd.setText("");
    }//GEN-LAST:event_satuanActionPerformed

    private void mencariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mencariActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_mencariActionPerformed

    private void bataltrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bataltrnActionPerformed
        // TODO add your handling code here:
        
        int ok=JOptionPane.showConfirmDialog(null,"Apakah Anda Yakin Ingin Menghapus Data Transaksi?","Confirmation",JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            try {
            String sql = "DELETE FROM keranjang;";
            String sql1 = "DELETE From detail_transaksi_penjualan where no_faktur = '"+id_transaksi.getText()+"'";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            st.executeUpdate(sql);
            st.executeUpdate(sql1);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            tampil_data();
            HargaTot();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Dihapus");
        }
        } 
        
    }//GEN-LAST:event_bataltrnActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void mencariKeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("SATUAN");
        model.addColumn("JUMLAH");
        model.addColumn("HARGA");
        model.addColumn("ACTION");

        try {
            String sql = "Select * From keranjang where nama_barang LIKE '%" + mencariPenjualan.getText() + "%';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = cn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("Id_barang"), res.getString("nama_barang"), res.getString("satuan"), res.getString("jumlah"), res.getString("harga")});
            }

            list_barang.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak muncul");

        }
    }

    private void bayar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayar1ActionPerformed

    private void bayarKeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        kembali.setText("");
        if (bayar1.getText() == "") {
            kembali.setText("");
        } else {

            int membayar = Integer.parseInt(bayar1.getText());
            int Hargatt = hasil;

            int totalkembali = membayar - Hargatt;
            kembali.setText("Rp. " + String.valueOf(totalkembali));
        }
    }

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        int ok=JOptionPane.showConfirmDialog(null,"Apakah Anda Yakin Ingin Keluar?","Confirmation",JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            this.setVisible(false);
            new Login().setVisible(true);
        } 
        
    }//GEN-LAST:event_logoutActionPerformed

    private void kembalikecariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalikecariActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.repaint();
        Kasir.revalidate();

        Kasir.add(Cari);
        Kasir.repaint();
        Kasir.revalidate();
        load_table();
    }//GEN-LAST:event_kembalikecariActionPerformed

    private void HargaBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HargaBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HargaBayarActionPerformed

    private void CETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CETAKActionPerformed
        try {
            String sql = "UPDATE transaksi_penjualan SET username = '" + UsernameKasir.getText() + "', tanggal = '" + tglpenjualan + "', Harga_Total = '" + HargaBayar.getText() + "', bayar = '" + bayar1.getText() + "', kembali = '" + kembali.getText() + "' where transaksi_penjualan.no_faktur = '" + id_transaksi.getText() + "'";
            java.sql.Connection cnn = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = cnn.prepareStatement(sql);
            pst.execute();

            try {
                JasperDesign jasdi = JRXmlLoader.load("E:\\Tugas Proposal\\mmmmmmm\\codingN3d\\src\\report\\report3.jrxml");
                java.sql.Connection con = (Connection) Koneksi.getkoneksi();

                String sql1 = "SELECT transaksi_penjualan.no_faktur, transaksi_penjualan.username, transaksi_penjualan.Harga_Total, transaksi_penjualan.bayar, transaksi_penjualan.kembali, detail_transaksi_penjualan.Id_barang, detail_transaksi_penjualan.jumlah_ecer, detail_transaksi_penjualan.jumlah_grosir, detail_transaksi_penjualan.satuan, detail_transaksi_penjualan.Harga, barang.nama_barang, petugas.nama, NOW()\n" +
                "FROM transaksi_penjualan \n" +
                "JOIN detail_transaksi_penjualan \n" +
                "ON transaksi_penjualan.no_faktur = detail_transaksi_penjualan.no_faktur \n" +
                "JOIN barang \n" +
                "ON barang.id_barang = detail_transaksi_penjualan.Id_barang\n" +
                "JOIN petugas\n" +
                "ON petugas.username = transaksi_penjualan.username\n" +
                "where transaksi_penjualan.no_faktur ='" + IDT_Penjualan.getText() + "'";
                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql1);
                jasdi.setQuery(newQuery);
                JasperReport js = JasperCompileManager.compileReport(jasdi);
                JasperPrint jp = JasperFillManager.fillReport(js, null, con);
                // JasperExportManager.exportReportToHtmlFile(jp ,ore);
                JasperViewer.viewReport(jp);

                try {
                    java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                    java.sql.Statement stt = conn.createStatement();
                    java.sql.ResultSet rss = stt.executeQuery("select COUNT(no_faktur) as no_terakhir from transaksi_penjualan;");

                    if (rss.next()) {
                        String kode = rss.getString("no_terakhir");
                        String AN = "" + (Integer.parseInt(kode) + 1);
                        String strip = "-";

                        IDT_Penjualan.setText("TRN" + strip + AN);

                    } else {
                        IDT_Penjualan.setText("TRN-1");
                    }

                    String sql12 = "Insert into transaksi_penjualan (no_faktur) values ('" + IDT_Penjualan.getText() + "')";
                    String sql3 = "DELETE From keranjang where keterangan = 'PENJUALAN'";
                    java.sql.Connection cnnc = (Connection) Koneksi.getkoneksi();
                    pst = cnnc.prepareStatement(sql12);
                    java.sql.PreparedStatement pst1 = cnnc.prepareStatement(sql3);
                    pst.execute();
                    pst1.execute();
                    tampil_data();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Silahkan Login Terlebih Dahulu");
        }
//                

    }//GEN-LAST:event_CETAKActionPerformed


    private void bayar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayar1KeyReleased
        // TODO add your handling code here:

        kembali.setText("");
        String byr = bayar1.getText();
        double byr1 = Double.parseDouble(byr);
        double hbyr = Double.parseDouble(String.valueOf(hasil));

        double tt = byr1 - hbyr;
        String hsl = String.format("%.2f", tt);
        kembali.setText("Rp. " + hsl);


    }//GEN-LAST:event_bayar1KeyReleased

    private void jumlahprdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahprdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahprdActionPerformed

    private void jumlahprdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahprdKeyReleased
        // TODO add your handling code here:
        hargaprduk.setText("");
        String pilihan = satuan.getSelectedItem().toString();

        if (pilihan == "ECERAN") {
            int brgecr = Integer.valueOf(stokBarangEcer);
            String jl = jumlahprd.getText();
            int jmlprdecr = Integer.valueOf(jl);
            double jm = Double.parseDouble(jl);
            double st = Double.parseDouble(hrg_ecer);

            if (jmlprdecr > brgecr) {
                JOptionPane.showMessageDialog(null, "Maaf Barang tidak mencukupi");
            } else {
                double tt = jm * st;
                String hsl = String.format("%.2f", tt);
                hargaprduk.setText(hsl);
            }

        } else if (pilihan == "GROSIR") {
            int brggsr = Integer.valueOf(stokBarangGrosir);
            String jl = jumlahprd.getText();
            int jmlprdgsr = Integer.valueOf(jl);
            double jm = Double.parseDouble(jl);
            double st = Double.parseDouble(hrg_grosir);

            if (jmlprdgsr > brggsr) {
                JOptionPane.showMessageDialog(null, "Maaf Barang tidak mencukupi");
            } else {
                double tt = jm * st;
                String hsl = String.format("%.2f", tt);
                hargaprduk.setText(hsl);
            }

        } else {
            hargaprduk.setText("");
        }
    }//GEN-LAST:event_jumlahprdKeyReleased

    private void hargaprdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaprdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaprdukActionPerformed

    private void hargaprdukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaprdukKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaprdukKeyReleased

    private void mencariPenjualanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mencariPenjualanKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan");
        model.addColumn("Jumlah");
        model.addColumn("Harga");

        try {
            String sql = "Select * From keranjang where nama_barang LIKE '%" + mencariPenjualan.getText() + "%';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = cn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("Id_barang"), res.getString("nama_barang"), res.getString("satuan"), res.getString("jumlah"), res.getString("harga")});
            }

            list_barang.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak muncul");
        }
    }//GEN-LAST:event_mencariPenjualanKeyReleased

    private void namaSupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaSupplierKeyReleased
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String sql1 = "SELECT * FROM supplier where nama_supplier LIKE '%" + namaSupplier.getText() + "%';";
                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st = conn.createStatement();
                java.sql.ResultSet res = st.executeQuery(sql1);
                if (res.next()) {
                    String nmsp = res.getString("nama_supplier");
                    suppid = res.getString("id_supplier");
                    namaSupplier.setText(nmsp);
                    id_barangi1.requestFocus();

                } else {
                    namaSupplier.setText(namaSupplier.getText());

                    try {
                        java.sql.Connection conn1 = (Connection) Koneksi.getkoneksi();
                        java.sql.Statement stt = conn1.createStatement();
                        java.sql.ResultSet rss = stt.executeQuery("select COUNT(id_supplier) as no_terakhir from supplier;");

                        if (rss.next()) {
                            String kode = rss.getString("no_terakhir");
                            String AN = "" + (Integer.parseInt(kode) + 1);
                            String strip = "-";

                            IDTSupplier.setText("SP" + strip + AN);

                        } else {
                            IDTSupplier.setText("SP-1");
                        }

                        String sql12 = "Insert into supplier (id_supplier, nama_supplier) values ('" + IDTSupplier.getText() + "', '" + namaSupplier.getText() + "')";
                        java.sql.Connection cnnc = (Connection) Koneksi.getkoneksi();
                        pst = cnnc.prepareStatement(sql12);
                        pst.execute();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    id_barangi1.requestFocus();

                }

            } catch (SQLException ex) {
                Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_namaSupplierKeyReleased

    private void logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout1ActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin Ingin Keluar???", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
        this.setVisible(false);
        new Login().setVisible(true);
        }
    }//GEN-LAST:event_logout1ActionPerformed

    private void kembalikecari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalikecari1ActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.repaint();
        Kasir.revalidate();

        Kasir.add(Cari);
        Kasir.repaint();
        Kasir.revalidate();
        load_table();
    }//GEN-LAST:event_kembalikecari1ActionPerformed

    private void satuan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuan1ActionPerformed
        jumlah_pembelian.requestFocus();
    }//GEN-LAST:event_satuan1ActionPerformed

    private void btlprdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlprdActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin Membatalkan Transaksi ini???", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM detail_transaksi_pembelian where Id_pembelian = '"+txt_IDT.getText()+"'";
                
                String sql1 = "DELETE FROM keranjang where keterangan = 'PEMBELIAN'";
                java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st = cn.createStatement();
                st.executeUpdate(sql);
                st.executeUpdate(sql1);
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                hapus();
                namaSupplier.setText("");
                id_barangi1.setText("");
                namabarang1.setText("");
                jumlah_pembelian.setText("");
                satuan1.setSelectedItem("PILIH SATUAN");
                namasatuan1.setText("");
                harga1.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            tampil_barang();
            TotalPembelian();
        }
    }//GEN-LAST:event_btlprdActionPerformed

    private void tambahprdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahprdActionPerformed

        if (satuan1.getSelectedItem() == "ECERAN") {
            try {
                String sql = "INSERT INTO barang (Id_barang, nama_barang, eceran, harga_eceran) Values (?,?,?,?)";
                String sql2 = "Insert Into detail_transaksi_pembelian (Id_pembelian, tanggal, Id_barang, jumlah_ecer, harga_eceran) values ('" + txt_IDT.getText() + "', '" + tgl + "', '" + id_barangi1.getText() + "', '" + jumlah_pembelian.getText() + "', '" + harga1.getText() + "')";
                String sql3 = "Insert Into keranjang (id_barang, nama_barang, eceran, jumlah_ecer, harga_eceran, keterangan) values ('" + id_barangi1.getText() + "', '" + namabarang1.getText() + "', '" + namasatuan1.getText() + "', '" + jumlah_pembelian.getText() + "', '" + harga1.getText() + "', 'PEMBELIAN')";
                java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
                java.sql.PreparedStatement pst2 = conntt.prepareStatement(sql2);
                java.sql.PreparedStatement pst3 = conntt.prepareStatement(sql3);
                pst.setString(1, id_barangi1.getText());
                pst.setString(2, namabarang1.getText());
                pst.setString(3, namasatuan1.getText());
                pst.setString(4, harga1.getText());
                pst.executeUpdate();
                pst2.executeUpdate();
                pst3.executeUpdate();
                tampil_barang();
                TotalPembelian();
                satuan1.setSelectedItem("PILIH SATUAN");
                namasatuan1.setText("");
                harga1.setText("");
                jumlah_pembelian.setText("");
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");

            } catch (Exception e) {
                try {
                    String sql = "UPDATE barang SET eceran = '" + namasatuan1.getText() + "', harga_eceran='" + harga1.getText() + "' where id_barang = '" + id_barangi1.getText() + "'";
                    String sql2 = "UPDATE detail_transaksi_pembelian SET jumlah_ecer = '" + jumlah_pembelian.getText() + "', harga_eceran='" + harga1.getText() + "' where id_barang = '" + id_barangi1.getText() + "'";
                    String sql3 = "UPDATE keranjang SET eceran = '" + namasatuan1.getText() + "', jumlah_ecer = '" + jumlah_pembelian.getText() + "', harga_eceran = '" + harga1.getText() + "' where id_barang = '" + id_barangi1.getText() + "'";
                    java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.PreparedStatement pst2 = conn.prepareStatement(sql2);
                    java.sql.PreparedStatement pst3 = conn.prepareStatement(sql3);
                    pst.execute();
                    pst2.execute();
                    pst3.execute();
                    tampil_barang();
                    TotalPembelian();
                    JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

        } else if (satuan1.getSelectedItem() == "GROSIR") {
            try {
                String sql = "INSERT INTO barang (Id_barang, nama_barang, grosir, harga_grosir) Values (?,?,?,?)";
                String sql2 = "Insert Into detail_transaksi_pembelian (Id_pembelian, tanggal, Id_barang, jumlah_grosir, harga_grosir) values ('" + txt_IDT.getText() + "', '" + tgl + "', '" + id_barangi1.getText() + "', '" + jumlah_pembelian.getText() + "', '" + harga1.getText() + "')";
                String sql3 = "Insert Into keranjang (id_barang, nama_barang, grosir, jumlah_grosir, harga_grosir, keterangan) values ('" + id_barangi1.getText() + "', '" + namabarang1.getText() + "', '" + namasatuan1.getText() + "', '" + jumlah_pembelian.getText() + "', '" + harga1.getText() + "', 'pembelian')";
                java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
                java.sql.PreparedStatement pst2 = conntt.prepareStatement(sql2);
                java.sql.PreparedStatement pst3 = conntt.prepareStatement(sql3);
                pst.setString(1, id_barangi1.getText());
                pst.setString(2, namabarang1.getText());
                pst.setString(3, namasatuan1.getText());
                pst.setString(4, harga1.getText());
                pst.executeUpdate();
                pst2.executeUpdate();
                pst3.executeUpdate();
                tampil_barang();
                TotalPembelian();
                satuan1.setSelectedItem("PILIH SATUAN");
                namasatuan1.setText("");
                harga1.setText("");
                jumlah_pembelian.setText("");
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            } catch (Exception e) {
                try {
                    String sql = "UPDATE barang SET grosir = '" + namasatuan1.getText() + "', harga_grosir='" + harga1.getText() + "' where id_barang = '" + id_barangi1.getText() + "'";
                    String sql2 = "UPDATE detail_transaksi_pembelian SET jumlah_grosir = '" + jumlah_pembelian.getText() + "', harga_grosir='" + harga1.getText() + "' where id_barang = '" + id_barangi1.getText() + "'";
                    String sql3 = "UPDATE keranjang SET grosir = '" + namasatuan1.getText() + "', jumlah_grosir = '" + jumlah_pembelian.getText() + "', harga_grosir= '" + harga1.getText() + "' where id_barang = '" + id_barangi1.getText() + "'";
                    java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.PreparedStatement pst2 = conn.prepareStatement(sql2);
                    java.sql.PreparedStatement pst3 = conn.prepareStatement(sql3);
                    pst.execute();
                    pst2.execute();
                    pst3.execute();
                    tampil_barang();
                    TotalPembelian();
                    JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                } catch (SQLException ex) {
                    Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tambahprdActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "UPDATE transaksi_pembelian SET id_supplier = '" + suppid + "', username = '" + UsernameKasir.getText() + "', Harga_Total = '" + hargaTotal.getText() + "' where id_pembelian = '" + txt_IDT.getText() + "'";
            java.sql.Connection cnn = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = cnn.prepareStatement(sql);
            pst.execute();

                   try {
                    JasperDesign jasdi = JRXmlLoader.load("E:\\Tugas Proposal\\mmmmmmm\\codingN3d\\src\\reportpembelian\\pembelianstr.jrxml");
                    java.sql.Connection con = (Connection) Koneksi.getkoneksi();

                    String sql1 = "SELECT transaksi_pembelian.Id_pembelian, supplier.nama_supplier, detail_transaksi_pembelian.tanggal, detail_transaksi_pembelian.Id_barang, "
                            + "detail_transaksi_pembelian.jumlah_ecer, detail_transaksi_pembelian.jumlah_grosir, detail_transaksi_pembelian.harga_eceran, "
                            + "detail_transaksi_pembelian.harga_grosir, barang.nama_barang, petugas.nama, barang.eceran, barang.grosir, NOW(), SUM(detail_transaksi_pembelian.harga_eceran) + SUM(detail_transaksi_pembelian.harga_grosir) AS total "
                            + "FROM detail_transaksi_pembelian JOIN transaksi_pembelian ON detail_transaksi_pembelian.Id_pembelian = transaksi_pembelian.Id_pembelian JOIN barang ON detail_transaksi_pembelian.Id_barang =  barang.id_barang  JOIN supplier ON transaksi_pembelian.id_supplier  = supplier.id_supplier JOIN "
                            + "petugas ON transaksi_pembelian.username = petugas.username where transaksi_pembelian.Id_pembelian = '"+txt_IDT.getText()+"';";
                    JRDesignQuery newQuery = new JRDesignQuery();
                    newQuery.setText(sql1);
                    jasdi.setQuery(newQuery);
                    JasperReport js = JasperCompileManager.compileReport(jasdi);
                    JasperPrint jp = JasperFillManager.fillReport(js, null, con);
                    // JasperExportManager.exportReportToHtmlFile(jp ,ore);
                    JasperViewer.viewReport(jp);
//                    
            try {
                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st = conn.createStatement();
                java.sql.ResultSet rss = st.executeQuery("select COUNT(Id_pembelian) as no_terakhir from transaksi_pembelian;");

                if (rss.next()) {
                    String kode = rss.getString("no_terakhir");
                    String AN = "" + (Integer.parseInt(kode) + 1);
                    String strip = "-";

                    txt_IDT.setText("T" + strip + AN);

                } else {
                    txt_IDT.setText("T-1");
                }

                String sql12 = "Insert into transaksi_pembelian (Id_pembelian) values ('" + txt_IDT.getText() + "')";
                String sql3 = "DELETE From keranjang where keterangan = 'PEMBELIAN'";
                java.sql.Connection cnnc = (Connection) Koneksi.getkoneksi();
                pst = cnn.prepareStatement(sql12);
                java.sql.PreparedStatement pst1 = cnnc.prepareStatement(sql3);
                pst.execute();
                pst1.execute();
                tampil_barang();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

                 } catch (Exception e) {
                     JOptionPane.showMessageDialog(rootPane, e);
                 } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_printActionPerformed

    private void mencari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mencari1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id barang");
        model.addColumn("Nama barang");
        model.addColumn("Satuan Ecer");
        model.addColumn("Satuan Grosir");
        model.addColumn("Jumlah Eceran");
        model.addColumn("Jumlah Grosir");
        model.addColumn("Harga Eceran");
        model.addColumn("Harga Grosir");

        try {
            String sql = "SELECT * where keranjang nama_barang LIKE '%" + mencari1.getText() + "%';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = cn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_barang"),
                    res.getString("nama_barang"), res.getString("eceran"), res.getString("grosir"), res.getString("jumlah_ecer"), res.getString("jumlah_grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
            }

            table_barang.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak muncul");
        }
    }//GEN-LAST:event_mencari1KeyReleased

    private void mencari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mencari1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_mencari1ActionPerformed

    private void clear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear1ActionPerformed
        // TODO add your handling code here:
        id_barangi1.setText("");
        namabarang1.setText("");
        jumlah_pembelian.setText("");
        satuan1.setSelectedItem("PILIH SATUAN");
        namasatuan1.setText("");
        harga1.setText("");
        id_barangi1.requestFocus();
    }//GEN-LAST:event_clear1ActionPerformed

    private void jumlahBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahBarang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahBarang1ActionPerformed

    private void jumlahBarang1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahBarang1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahBarang1KeyReleased

    private void CETAK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CETAK1ActionPerformed
        // TODO add your handling code here:
        
        try {
            String sql = "UPDATE return_barang SET id_pembelian = '" + id_pembelian.getText() + "', id_supplier = '" + suppid + "', username = '" + UsernameKasir.getText() + "', tanggal = '" + tgl + "' where id_return = '" +  ReturnID.getText() + "'";
            java.sql.Connection cnn = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = cnn.prepareStatement(sql);
            pst.execute();

                   try {
                    JasperDesign jasdi = JRXmlLoader.load("E:\\Tugas Proposal\\mmmmmmm\\codingN3d\\src\\reportreturn\\strukreturn.jrxml");
                    java.sql.Connection con = (Connection) Koneksi.getkoneksi();

                    String sql1 = "select return_barang.id_return, return_barang.id_pembelian, return_barang.tanggal, detail_return.jumlah_ecer, detail_return.jumlah_grosir, detail_return.eceran, detail_return.grosir, detail_return.keterangan, barang.nama_barang, supplier.nama_supplier, return_barang.username FROM return_barang JOIN detail_return ON detail_return.id_return = return_barang.id_return JOIN barang ON detail_return.id_barang = barang.id_barang JOIN supplier ON supplier.id_supplier = return_barang.id_supplier where return_barang.id_return = '"+ReturnID.getText()+"'";
                    JRDesignQuery newQuery = new JRDesignQuery();
                    newQuery.setText(sql1);
                    jasdi.setQuery(newQuery);
                    JasperReport js = JasperCompileManager.compileReport(jasdi);
                    JasperPrint jp = JasperFillManager.fillReport(js, null, con);
                    // JasperExportManager.exportReportToHtmlFile(jp ,ore);
                    JasperViewer.viewReport(jp);
//                    
                                try {
                                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                                java.sql.Statement st = conn.createStatement();
                                java.sql.ResultSet rss = st.executeQuery("select COUNT(id_return) as no_terakhir from return_barang;");

                                if (rss.next()) {
                                    String kode = rss.getString("no_terakhir");
                                    String AN = "" + (Integer.parseInt(kode) + 1);
                                    String strip = "-";

                                    ReturnID.setText("RTN" + strip + AN);

                                } else {
                                    ReturnID.setText("RTN-1");
                                }

                                String sql12 = "Insert into return_barang (id_return) values ('" + ReturnID.getText() + "')";
                                java.sql.Connection cnn1 = (Connection) Koneksi.getkoneksi();
                                pst = cnn1.prepareStatement(sql12);
                                pst.execute();

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }

                 } catch (Exception e) {
                     JOptionPane.showMessageDialog(rootPane, e);
                 } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }//GEN-LAST:event_CETAK1ActionPerformed

    private void kembalikecari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalikecari2ActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.repaint();
        Kasir.revalidate();

        Kasir.add(Cari);
        Kasir.repaint();
        Kasir.revalidate();
    }//GEN-LAST:event_kembalikecari2ActionPerformed

    private void logout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_logout2ActionPerformed

    private void mencariBarang1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mencariBarang1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_mencariBarang1KeyReleased

    private void btn_tjual1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tjual1ActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.add(TransaksiPenjualan);
        Kasir.repaint();
        Kasir.validate();
        tampil_data();
        HargaTot();
        transaksi_penjualan_id();
        id_barangpnj.requestFocus();
    }//GEN-LAST:event_btn_tjual1ActionPerformed

    private void table_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_barangMouseClicked
        // TODO add your handling code here:
        int b = table_barang.rowAtPoint(evt.getPoint());
        String IDtransaksi = (String) table_barang.getValueAt(b, 0);
        id_barangi1.setText(IDtransaksi);

        String NAMApembelian = (String) table_barang.getValueAt(b, 1);
        namabarang1.setText(NAMApembelian);
    }//GEN-LAST:event_table_barangMouseClicked

    private void mencariPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mencariPenjualanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mencariPenjualanActionPerformed

    private void btn_tjual2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tjual2ActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.add(Return);
        Kasir.repaint();
        Kasir.validate();
        tabel_return_barang();
        tanggalreal1.setText(tgl);
        id_pembelian.requestFocus();
        
    }//GEN-LAST:event_btn_tjual2ActionPerformed

    private void satuan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuan2ActionPerformed
        // TODO add your handling code here:
        if (satuan2.getSelectedItem() == "ECERAN") {
            try {
                String sql = "SELECT eceran FROM barang where id_barang = '" + namaBarang1.getText() + "'";
                java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st = cn.createStatement();
                java.sql.ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    ecr_supp = rs.getString("eceran");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (satuan2.getSelectedItem() == "GROSIR") {
            try {
                String sql = "SELECT grosir FROM barang where id_barang = '" + namaBarang1.getText() + "'";
                java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st = cn.createStatement();
                java.sql.ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    grs_supp = rs.getString("grosir");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_satuan2ActionPerformed

    private void satuan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuan3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_satuan3ActionPerformed

    private void barang_barangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barang_barangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_barang_barangMouseEntered

    private void id_barangi1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_barangi1KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_id_barangi1KeyTyped

    private void id_barangi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_barangi1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String IdpembelianBrg = id_barangi1.getText();
        }
    }//GEN-LAST:event_id_barangi1KeyPressed

    private void tambahprd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahprd1ActionPerformed
        // TODO add your handling code here:
        if (satuan2.getSelectedItem() == "ECERAN") {
            try {
                String sql = "INSERT INTO keranjang (Id_barang, jumlah_ecer, eceran, keterangan) Values ('" + namaBarang1.getText() + "', '" + jumlahBarang1.getText() + "', '" + ecr_supp + "', 'RETURN')";
                String sql1 = "INSERT INTO detail_return (id_return, id_barang, jumlah_ecer, eceran, keterangan) Values ('" + ReturnID.getText() + "','" + namaBarang1.getText() + "', '" + jumlahBarang1.getText() + "', '" + ecr_supp + "', '" + keteranganrtn.getText() + "')";
                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst.execute();
                pst1.execute();
                tabel_return_barang();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        } else if (satuan2.getSelectedItem() == "GROSIR") {
            try {
                String sql = "INSERT INTO keranjang (Id_barang, jumlah_grosir, grosir, keterangan) Values ('" + namaBarang1.getText() + "', '" + jumlahBarang1.getText() + "', '" + grs_supp + "', 'RETURN')";
                String sql1 = "INSERT INTO detail_return (id_return, id_barang, jumlah_grosir, grosir, keterangan) Values ('" + ReturnID.getText() + "','" + namaBarang1.getText() + "', '" + jumlahBarang1.getText() + "', '" + grs_supp + "', '" + keteranganrtn.getText() + "')";
                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst.execute();
                pst1.execute();
                tabel_return_barang();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_tambahprd1ActionPerformed

    private void clear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clear2ActionPerformed

    private void btlprd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlprd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btlprd1ActionPerformed

    private void namabarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namabarang1ActionPerformed
        // TODO add your handling code here:
        satuan1.requestFocus();
    }//GEN-LAST:event_namabarang1ActionPerformed

    private void id_barangi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_barangi1ActionPerformed
        // TODO add your handling code here:
        namabarang1.requestFocus();
    }//GEN-LAST:event_id_barangi1ActionPerformed

    private void jumlah_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_pembelianActionPerformed
        // TODO add your handling code here:
        namasatuan1.requestFocus();
    }//GEN-LAST:event_jumlah_pembelianActionPerformed

    private void namasatuan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namasatuan1ActionPerformed
        // TODO add your handling code here:
        harga1.requestFocus();
    }//GEN-LAST:event_namasatuan1ActionPerformed

    private void harga1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_harga1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_harga1ActionPerformed

    private void satuan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuan4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_satuan4ActionPerformed

    private void clear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clear3ActionPerformed

    private void TambahBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahBrgActionPerformed
        // TODO add your handling code here:
        if (satuan4.getSelectedItem() == "ECERAN") {
            try {
                PilihSatuan2();
                String sql = "INSERT INTO keranjang (Id_barang, nama_barang, eceran, jumlah_ecer, harga_eceran, keterangan) Values ('" + id_barangpnj.getText() + "', '" + nama_barangpnj + "', '" + SATUAN + "', '" + jumlah_barang.getText() + "', '" + hargaprduk1.getText() + "', 'PENJUALAN')";
                String sql1 = "INSERT INTO detail_transaksi_penjualan (no_faktur, Id_barang, jumlah_ecer, satuan, harga, tanggal) Values ('" + IDT_Penjualan.getText() + "','" + id_barangpnj.getText() + "', '" + jumlah_barang.getText() + "', '" + SATUAN + "', '" + hargaprduk1.getText() + "', '" + tgl + "')";
                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst.execute();
                pst1.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                id_barangpnj.setText("");
                satuan4.setSelectedItem("SATUAN");
                jumlah_barang.setText("");
                hargaprduk1.setText("");
                tampil_data();
                HargaTot();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        } else if (satuan4.getSelectedItem() == "GROSIR") {

            try {
                PilihSatuan2();
                String sql = "INSERT INTO keranjang (Id_barang, nama_barang, grosir, jumlah_grosir, harga_grosir, keterangan) Values ('" + id_barangpnj.getText() + "', '" + nama_barangpnj + "', '" + SATUAN + "', '" + jumlah_barang.getText() + "', '" + hargaprduk1.getText() + "', 'PENJUALAN')";
                String sql1 = "INSERT INTO detail_transaksi_penjualan (no_faktur, Id_barang, jumlah_grosir, satuan, harga, tanggal) Values ('" + IDT_Penjualan.getText() + "','" + id_barangpnj.getText() + "', '" + jumlah_barang.getText() + "', '" + SATUAN + "', '" + hargaprduk1.getText() + "', '" + tgl + "')";
                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst.execute();
                pst1.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                id_barangpnj.setText("");
                satuan4.setSelectedItem("SATUAN");
                jumlah_barang.setText("");
                hargaprduk1.setText("");
                tampil_data();
                HargaTot();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_TambahBrgActionPerformed

    private void id_barangpnjKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_barangpnjKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String sql = "SELECT * from barang where id_barang = '" + id_barangpnj.getText() + "'";
                java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st = cn.createStatement();
                java.sql.ResultSet rs = st.executeQuery(sql);

                rs.next();
                nama_barangpnj = rs.getString("nama_barang");
                jumlah_ecerpnj = rs.getString("stok_ecer");
                jumlah_grosirpnj = rs.getString("stok_grosir");
                eceranpnj = rs.getString("eceran");
                grosirpnj = rs.getString("grosir");
                hrg_ecerpnj = rs.getString("harga_eceran");
                hrg_grosirpnj = rs.getString("harga_grosir");

            } catch (SQLException ex) {
                Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_id_barangpnjKeyReleased

    private void hargaprduk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaprduk1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaprduk1ActionPerformed

    private void hargaprduk1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaprduk1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaprduk1KeyReleased

    private void jumlah_barangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah_barangKeyReleased
        // TODO add your handling code here:
        hargaprduk1.setText("");
        String pilihan = satuan4.getSelectedItem().toString();

        if (pilihan == "ECERAN") {
            int brgecr = Integer.valueOf(jumlah_ecerpnj);
            String jl = jumlah_barang.getText();
            int jmlprdecr = Integer.valueOf(jl);
            double jm = Double.parseDouble(jl);
            double st = Double.parseDouble(hrg_ecerpnj);

            if (jmlprdecr > brgecr) {
                JOptionPane.showMessageDialog(null, "Maaf Barang tidak mencukupi");
            } else {
                double tt = jm * st;
                String hsl = String.format("%.2f", tt);
                hargaprduk1.setText(hsl);
            }

        } else if (pilihan == "GROSIR") {
            int brggsr = Integer.valueOf(jumlah_grosirpnj);
            String jl = jumlah_barang.getText();
            int jmlprdgsr = Integer.valueOf(jl);
            double jm = Double.parseDouble(jl);
            double st = Double.parseDouble(hrg_grosirpnj);

            if (jmlprdgsr > brggsr) {
                JOptionPane.showMessageDialog(null, "Maaf Barang tidak mencukupi");
            } else {
                double tt = jm * st;
                String hsl = String.format("%.2f", tt);
                hargaprduk1.setText(hsl);
            }

        } else {
            hargaprduk1.setText("");
        }
    }//GEN-LAST:event_jumlah_barangKeyReleased

    private void btn_tpembelianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_tpembelianKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tpembelianKeyReleased

    private void namaSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaSupplierActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CETAK;
    private javax.swing.JButton CETAK1;
    public javax.swing.JPanel Cari;
    private javax.swing.JTextField HargaBayar;
    private javax.swing.JLabel IDTSupplier;
    private javax.swing.JLabel IDT_Penjualan;
    private javax.swing.JPanel Kasir;
    public static javax.swing.JTextField NamaKasir;
    private javax.swing.JPanel Return;
    private javax.swing.JLabel ReturnID;
    private javax.swing.JButton TambahBrg;
    private javax.swing.JPanel TransaksiPembelian;
    private javax.swing.JPanel TransaksiPenjualan;
    public static javax.swing.JTextField UsernameKasir;
    private javax.swing.JTable barang_barang;
    private javax.swing.JButton batal;
    private javax.swing.JButton bataltrn;
    private javax.swing.JTextField bayar1;
    private javax.swing.JButton btlprd;
    private javax.swing.JButton btlprd1;
    private javax.swing.JButton btn_tjual1;
    private javax.swing.JButton btn_tjual2;
    private javax.swing.JButton btn_tpembelian;
    private javax.swing.JTextField cari;
    private javax.swing.JButton clear1;
    private javax.swing.JButton clear2;
    private javax.swing.JButton clear3;
    private javax.swing.JTextField harga1;
    private javax.swing.JTextField hargaTotal;
    private javax.swing.JTextField hargaprduk;
    private javax.swing.JTextField hargaprduk1;
    private javax.swing.JTextField id_barangi1;
    private javax.swing.JTextField id_barangpnj;
    private javax.swing.JTextField id_pembelian;
    private javax.swing.JTextField id_transaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jumlahBarang1;
    private javax.swing.JTextField jumlah_barang;
    private javax.swing.JTextField jumlah_pembelian;
    private javax.swing.JTextField jumlahprd;
    private javax.swing.JTextField kembali;
    private javax.swing.JButton kembalikecari;
    private javax.swing.JButton kembalikecari1;
    private javax.swing.JButton kembalikecari2;
    private javax.swing.JTextArea keteranganrtn;
    private javax.swing.JTable list_barang;
    private javax.swing.JButton logout;
    private javax.swing.JButton logout1;
    private javax.swing.JButton logout2;
    private javax.swing.JTextField mencari1;
    private javax.swing.JTextField mencariBarang1;
    private javax.swing.JTextField mencariPenjualan;
    private javax.swing.JTextField namaBarang1;
    private javax.swing.JTextField namaSupplier;
    private javax.swing.JTextField namabarang1;
    private javax.swing.JTextField namaprd;
    private javax.swing.JTextField namasatuan1;
    private javax.swing.JButton print;
    private javax.swing.JTable returnnnn;
    private javax.swing.JComboBox<String> satuan;
    private javax.swing.JComboBox<String> satuan1;
    private javax.swing.JComboBox<String> satuan2;
    private javax.swing.JComboBox<String> satuan3;
    private javax.swing.JComboBox<String> satuan4;
    public javax.swing.JScrollPane strukpemb;
    public static javax.swing.JTextArea strukpembelian;
    public static javax.swing.JTextArea strukpeminjaman;
    public javax.swing.JScrollPane strukpnj;
    private javax.swing.JTable table_barang;
    private javax.swing.JButton tambah;
    private javax.swing.JButton tambahprd;
    private javax.swing.JButton tambahprd1;
    private javax.swing.JLabel tanggalreal;
    private javax.swing.JLabel tanggalreal1;
    private javax.swing.JLabel total;
    private javax.swing.JLabel txt_IDT;
    private javax.swing.JTextField txt_IDTOther;
    // End of variables declaration//GEN-END:variables
public void namaSup() {
        try {
            String sql1 = "SELECT * FROM supplier where nama_supplier LIKE '%" + namaSupplier.getText() + "%';";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql1);
            while (res.next()) {
                res.getString("nama_supplier");
            }
            if (namaSupplier.getText() == null) {
                namaSupplier.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
