/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n3dshop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import n3dshop.Koneksi;
import n3dshop.Koneksi;
import n3dshop.Login;
import n3dshop.Login;

/**
 *
 * @author HP
 */
public class MenuUtama extends javax.swing.JFrame {

    int a;
    String hrg_ecer, hrg_grosir, Id, No_faktur, totalmembayar;
    String Id_barang, SATUAN, SATUANpmb, tgl;
    PreparedStatement pst;

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
        notransaksi();
        Tampil_Tanggal();
    }
public void Tampil_Jam(){
        ActionListener taskPerformer = new ActionListener() {
 
            public void actionPerformed(ActionEvent evt) {
            String nol_jam = "", nol_menit = "",nol_detik = "";
 
            java.util.Date dateTime = new java.util.Date();
            int nilai_jam = dateTime.getHours();
            int nilai_menit = dateTime.getMinutes();
            int nilai_detik = dateTime.getSeconds();
 
            if(nilai_jam <= 9) nol_jam= "0";
            if(nilai_menit <= 9) nol_menit= "0";
            if(nilai_detik <= 9) nol_detik= "0";
 
            String jam = nol_jam + Integer.toString(nilai_jam);
            String menit = nol_menit + Integer.toString(nilai_menit);
            String detik = nol_detik + Integer.toString(nilai_detik);
 
            tanggalreal.setText(jam+":"+menit+":"+detik+"");
            }
        };
    new Timer(1000, taskPerformer).start();
    }

public void Tampil_Tanggal() {
    java.util.Date tglsekarang = new java.util.Date();
    SimpleDateFormat smpdtfmt = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    tgl = smpdtfmt.format(tglsekarang);
    
}
private void load_table (){
    //membuat tampilan tabel
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
            String sql = "SELECT id_barang, nama_barang, stok, eceran, grosir, harga_eceran, harga_grosir FROM barang;";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()){
                model.addRow(new Object[]{res.getString("id_barang"),
                res.getString("nama_barang"),res.getString("stok"),res.getString("eceran"), res.getString("grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
            }
            barang_barang.setModel(model);
        } catch (Exception e) {
        }
    }

private void hapus(){
    namaprd.setText("");
    jumlahprd.setText("");
    hargaprd.setText("");
    cari.setText("");
    satuan.setSelectedItem("SATUAN");
}

private void Banyak(){
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


public void tampil_barang(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id barang");
        model.addColumn("Nama barang");
        model.addColumn("Stok");
        model.addColumn("Eceran");
        model.addColumn("Grosir");
        model.addColumn("Harga Ecer");
        model.addColumn("Harga Grosir");

        try {
            String sql = "Select * From barang where tanggal = '"+tgl+"';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = cn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()){
                model.addRow(new Object[]{res.getString("id_barang"),
                res.getString("nama_barang"),res.getString("stok"),res.getString("eceran"), res.getString("grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
            }

            table_barang.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak muncul");
        }
    }
     public void tampil_data() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan");
        model.addColumn("Jumlah");
        model.addColumn("Harga");

        try {
            String sql = "Select * From keranjang";
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

    public void jumlahbarang() {
        try {
            int total = 0;
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            java.sql.ResultSet rs = st.executeQuery("select sum(jumlah) AS d from keranjang;");
            while (rs.next()) {
                Long a = rs.getLong(1);
                if (a == 0) {
                    jumlah_barang.setText("");
                } else {
                    total = total + Integer.parseInt(rs.getString("d"));
                    jumlah_barang.setText("" + total);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void notransaksi() {
        try {
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            java.sql.ResultSet rs = st.executeQuery("select no_faktur from transaksi_penjualan");
            rs.next();
            No_faktur = rs.getString("no_faktur");
            id_transaksi.setText(No_faktur);
        } catch (Exception e) {
        }
    }
    
    private void HargaTot(){
        try {
            String sql = "SELECT SUM(harga) AS total FROM keranjang";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            res.next();
            totalmembayar = res.getString("total");
            if (totalmembayar == null) {
                HargaBayar.setText("Rp. ");
            } else {
                HargaBayar.setText("Rp. "+ totalmembayar);
            }
            
        } catch (Exception e) {
        }
    }

    public void PilihSatuan(){
        try {
            Object pilihan = satuan.getSelectedItem();
            String sql1 = "SELECT eceran, grosir, harga_eceran, harga_grosir FROM barang WHERE  Id_barang = '"+Id_barang+"';";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql1);
            res.next();
            if (pilihan == "ECERAN") {
                SATUAN = res.getString("eceran");
            } else if (pilihan == "GROSIR"){
                SATUAN = res.getString("grosir");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void print() {
        String IDT = id_transaksi.getText();
        String hrgt = HargaBayar.getText();
        String byr = bayar.getText();
        String kmbl = kembali.getText();
        
        DefaultTableModel tabmodel = (DefaultTableModel) list_barang.getModel();
        
        strukpeminjaman.setText(strukpeminjaman.getText() + "-----------------------------------------------------------------------------------------------------------------------" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "        ANGKRINGAN N3D-SHOP" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "Jl. KHOIRIL ANWAR, BADEAN, BONDOWOSO" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "***********************************************************************************************************************" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "ID TRANSAKSI : " + IDT + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "Tanggal       : " + tgl + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "Kasir         : " + NamaKasir.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "***********************************************************************************************************************" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "Id Barang" + "\t" + "Nama" + "\t" + "Satuan" + "\t" + "Jumlah" + "\t" + "Harga"+"\n");
        
        for (int i = 0; i < tabmodel.getRowCount(); i++) {
            String bk = (String) tabmodel.getValueAt(i, 0);
            String nm = (String) tabmodel.getValueAt(i, 1);
            String st = (String) tabmodel.getValueAt(i, 2);
            String jm = (String) tabmodel.getValueAt(i, 3);
            String hgt = (String) tabmodel.getValueAt(i, 4);
            strukpeminjaman.setText(strukpeminjaman.getText() + bk + "\t" + nm + "\t" + st + "\t" + jm + "\t" + hgt + "\n");
        }
        strukpeminjaman.setText(strukpeminjaman.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "\t" + "Bayar : " + byr + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "-----------------------------------------------------------------------------------------------------------------------" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "\t" + "HARGA TOTAL :" + hrgt + "\n" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "\t" + "KEMBALI :" + kmbl + "\n" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "-----------------------------------------------------------------------------------------------------------------------" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "Terima Kasih Telah berbelanja di " + "\n" + "\t" + "\t" + "\t" + "MY BOOK");
    }

public void print1() {
        String IDT = id_transaksi.getText();
        String hrgt = HargaBayar.getText();
        
        DefaultTableModel tabmodel = (DefaultTableModel) table_barang.getModel();
        
        strukpeminjaman.setText(strukpeminjaman.getText() + "-----------------------------------------------------------------------------------------------------------------------" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "              N3D-SHOP" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "        ANGKRINGAN N3D-SHOP" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "Jl. KHOIRIL ANWAR, BADEAN, BONDOWOSO" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "***********************************************************************************************************************" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "ID TRANSAKSI :" + IDT + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "Tanggal :" + tgl + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "Suplier :" + namaSupplier.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "***********************************************************************************************************************" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "Id Barang" + "\t" + "Nama Barang" + "\t" + "Satuan" + "\t" + "Jumlah" + "\t" + "Harga"+"\n");
        
        for (int i = 0; i < tabmodel.getRowCount(); i++) {
            String bk = (String) tabmodel.getValueAt(i, 0);
            String nm = (String) tabmodel.getValueAt(i, 1);
            String st = (String) tabmodel.getValueAt(i, 2);
            String jm = (String) tabmodel.getValueAt(i, 3);
            String hgt = (String) tabmodel.getValueAt(i, 4);
            strukpeminjaman.setText(strukpeminjaman.getText() + bk + "\t" + nm + "\t" + st + "\t" + jm + "\t" + hgt + "\n");
        }
        strukpeminjaman.setText(strukpeminjaman.getText() + "-----------------------------------------------------------------------------------------------------------------------" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "\t" + "HARGA TOTAL :" + hrgt + "\n" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "-----------------------------------------------------------------------------------------------------------------------" + "\n");
        strukpeminjaman.setText(strukpeminjaman.getText() + "\t" + "\t" + "Terima Kasih Telah berbelanja di " + "\n" + "\t" + "\t" + "\t" + "MY BOOK");
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
        Kasir = new javax.swing.JPanel();
        Cari = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_tjual = new javax.swing.JButton();
        btn_tpembelian = new javax.swing.JButton();
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
        hargaprd = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        batal = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        satuan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jumlahprd = new javax.swing.JTextField();
        TransaksiPenjualan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        mencari = new javax.swing.JTextField();
        HargaBayar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_barang = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        id_transaksi = new javax.swing.JTextField();
        jumlah_barang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        bayar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        kembali = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        CETAK = new javax.swing.JButton();
        bataltrn = new javax.swing.JButton();
        scan = new javax.swing.JButton();
        tanggalreal = new javax.swing.JLabel();
        kembalikecari = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        TransaksiPembelian = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_barang = new javax.swing.JTable();
        hargaTotal = new javax.swing.JTextField();
        mencari1 = new javax.swing.JTextField();
        jumlah = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        id_barangi1 = new javax.swing.JTextField();
        namabarang1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        print = new javax.swing.JButton();
        tambahprd = new javax.swing.JButton();
        btlprd = new javax.swing.JButton();
        scbtl = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        satuan1 = new javax.swing.JComboBox<>();
        namasatuan1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        harga1 = new javax.swing.JTextField();
        kembalikecari1 = new javax.swing.JButton();
        logout1 = new javax.swing.JButton();
        namaSupplier = new javax.swing.JTextField();

        strukpeminjaman.setColumns(20);
        strukpeminjaman.setRows(5);
        strukpnj.setViewportView(strukpeminjaman);

        strukpembelian.setColumns(20);
        strukpembelian.setRows(5);
        strukpemb.setViewportView(strukpembelian);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1280, 720));

        Kasir.setLayout(new java.awt.CardLayout());

        Cari.setBackground(new java.awt.Color(0, 9, 87));
        Cari.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel2.setBackground(new java.awt.Color(52, 76, 183));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Angkringan__1_-removebg-preview 1.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 291, 164));

        btn_tjual.setBackground(new java.awt.Color(252, 242, 6));
        btn_tjual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_tjual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Vector-2.png"))); // NOI18N
        btn_tjual.setText("TRANSAKSI JUAL");
        btn_tjual.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_tjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tjualActionPerformed(evt);
            }
        });
        jPanel2.add(btn_tjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(901, 32, 170, 59));

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
        jPanel2.add(btn_tpembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1087, 32, 170, 59));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DAFTAR PRODUK");

        jPanel3.setBackground(new java.awt.Color(116, 114, 203));

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
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("NAMA PRODUK");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("HARGA");

        hargaprd.setEditable(false);
        hargaprd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaprdActionPerformed(evt);
            }
        });

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

        jumlahprd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlahprdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahprdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlahprdKeyTyped(evt);
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
                .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CariLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(CariLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CariLayout.createSequentialGroup()
                                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(satuan, javax.swing.GroupLayout.Alignment.LEADING, 0, 319, Short.MAX_VALUE)
                                    .addComponent(namaprd, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hargaprd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jumlahprd, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(jLabel6))
                        .addContainerGap(56, Short.MAX_VALUE))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jumlahprd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hargaprd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(CariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(111, Short.MAX_VALUE))
                    .addGroup(CariLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
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

        mencari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mencariActionPerformed(evt);
            }
        });
        mencari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mencariKeyReleased(evt);
            }
        });

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

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("JUMLAH BARANG");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("BAYAR");

        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });
        bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayarKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("KEMBALI");

        jButton1.setBackground(new java.awt.Color(255, 232, 22));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/ant-design_plus-circle-outlined.png"))); // NOI18N
        jButton1.setText("TAMBAH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        CETAK.setBackground(new java.awt.Color(255, 232, 22));
        CETAK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/flat-color-icons_print (1).png"))); // NOI18N
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

        scan.setBackground(new java.awt.Color(255, 232, 22));
        scan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/scan.png"))); // NOI18N
        scan.setText("  SCAN");
        scan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanActionPerformed(evt);
            }
        });

        tanggalreal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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

        javax.swing.GroupLayout TransaksiPenjualanLayout = new javax.swing.GroupLayout(TransaksiPenjualan);
        TransaksiPenjualan.setLayout(TransaksiPenjualanLayout);
        TransaksiPenjualanLayout.setHorizontalGroup(
            TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(HargaBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(mencari, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tanggalreal)
                        .addGap(76, 76, 76))))
            .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addComponent(kembalikecari, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CETAK, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bataltrn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scan, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TransaksiPenjualanLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jumlah_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TransaksiPenjualanLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(31, 31, 31)
                                .addComponent(id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(98, 98, 98)
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(303, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(mencari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(tanggalreal))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jumlah_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(TransaksiPenjualanLayout.createSequentialGroup()
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(TransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembalikecari, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CETAK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bataltrn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        Kasir.add(TransaksiPenjualan, "card3");

        TransaksiPembelian.setBackground(new java.awt.Color(0, 9, 87));

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

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("SUPPLIER");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("JUMLAH");

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

        scbtl.setBackground(new java.awt.Color(255, 232, 22));
        scbtl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/scan.png"))); // NOI18N
        scbtl.setText("  SCAN");
        scbtl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scbtlActionPerformed(evt);
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

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("NAMA SATUAN");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("HARGA");

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

        namaSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaSupplierKeyReleased(evt);
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
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                .addGap(939, 939, 939)
                .addComponent(jLabel16)
                .addGap(12, 12, 12)
                .addComponent(mencari1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiPembelianLayout.createSequentialGroup()
                        .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23)
                                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel13)))
                            .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel27)
                                .addComponent(jLabel26)))
                        .addGap(18, 18, 18)))
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(id_barangi1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(namabarang1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(satuan1, 0, 243, Short.MAX_VALUE)
                    .addComponent(namasatuan1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(harga1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(namaSupplier))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(TransaksiPembelianLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(kembalikecari1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tambahprd, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btlprd, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scbtl, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logout1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(9, 9, 9)
                        .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id_barangi1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namabarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(23, 23, 23)
                        .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(satuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namasatuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(harga1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(TransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kembalikecari1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambahprd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btlprd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scbtl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        Kasir.add(TransaksiPembelian, "card3");

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

    private void btn_tjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tjualActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.repaint();
        Kasir.revalidate();
        
        Kasir.add(TransaksiPenjualan);
        Kasir.repaint();
        Kasir.revalidate();
        tampil_data();
        jumlahbarang();
        notransaksi();
        HargaTot();
    }//GEN-LAST:event_btn_tjualActionPerformed

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
            String sql = "SELECT id_barang, nama_barang, stok, eceran, grosir, harga_eceran, harga_grosir FROM barang where nama_barang LIKE '%"+cari.getText()+"%';";
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()){
                model.addRow(new Object[]{res.getString("id_barang"),
                res.getString("nama_barang"),res.getString("stok"),res.getString("eceran"), res.getString("grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
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
        
        hrg_ecer = (String) barang_barang.getValueAt(a, 5);
        hrg_grosir = (String) barang_barang.getValueAt(a, 6);
        
        
        Id_barang = (String) barang_barang.getValueAt(a, 0);

    }//GEN-LAST:event_barang_barangMouseClicked

    private void hargaprdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaprdActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_hargaprdActionPerformed

    private void jumlahprdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahprdKeyReleased
       hargaprd.setText("");
       String pilihan = satuan.getSelectedItem().toString();
           if (pilihan == "ECERAN") {
                String jl = jumlahprd.getText();
                double jm = Double.parseDouble(jl);
                double st = Double.parseDouble(hrg_ecer);

                double tt = jm*st;
                String hsl = String.format("%.2f", tt);
                hargaprd.setText(hsl);
           } else if (pilihan == "GROSIR"){
               String jl = jumlahprd.getText();
                double jm = Double.parseDouble(jl);
                double st = Double.parseDouble(hrg_grosir);

                double tt = jm*st;
                String hsl = String.format("%.2f", tt);
                hargaprd.setText(hsl);
           } else {
               hargaprd.setText("");
           }
       
    }//GEN-LAST:event_jumlahprdKeyReleased

    private void jumlahprdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahprdKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jumlahprdKeyTyped

    private void jumlahprdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahprdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahprdKeyPressed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        try {
            PilihSatuan();
            String sql = "INSERT INTO keranjang (no_faktur, Id_barang, nama_barang, satuan, jumlah, harga, keterangan) Values ('"+No_faktur+"','"+Id_barang+"', '"+namaprd.getText()+"', '"+SATUAN+"', '"+jumlahprd.getText()+"', '"+hargaprd.getText()+"', 'PENJUALAN')";
            java.sql.Connection conn =(Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            hapus();
            load_table();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Tidak berhasil Disimpan");
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "DELETE FROM keranjang;";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            load_table();
            hapus();
            tampil_data();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Dihapus");
        }
    }//GEN-LAST:event_batalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:Kasir.removeAll();
        Kasir.removeAll();
        Kasir.repaint();
        Kasir.revalidate();
        
        Kasir.add(Cari);
        Kasir.repaint();
        Kasir.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_tpembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tpembelianActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.repaint();
        Kasir.revalidate();
        
        Kasir.add(TransaksiPembelian);
        Kasir.repaint();
        Kasir.revalidate();
        tampil_barang();
    }//GEN-LAST:event_btn_tpembelianActionPerformed

    private void tambahprdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahprdActionPerformed
            if (satuan1.getSelectedItem() == "ECERAN") {
                try {
                    String sql = "INSERT INTO barang (Id_barang, nama_barang, stok, eceran, harga_eceran, tanggal) Values (?,?,?,?,?,?)";
                    java.sql.Connection conntt =(Connection) Koneksi.getkoneksi();
                    java.sql.PreparedStatement pst=conntt.prepareStatement(sql);
                    pst.setString(1, id_barangi1.getText());
                    pst.setString(2, namabarang1.getText());
                    pst.setString(3, jumlah.getText());
                    pst.setString(4, namasatuan1.getText());
                    pst.setString(5, harga1.getText());
                    pst.setString(6, tgl);
                    pst.executeUpdate();
                    tampil_barang();
                    satuan1.setSelectedItem("PILIH SATUAN");
                    namasatuan1.setText("");
                    harga1.setText("");
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Disimpan");
        }
            } else if (satuan1.getSelectedItem() == "GROSIR"){
                try {
                    String sql = "INSERT INTO barang (Id_barang, nama_barang, stok, grosir, harga_grosir, tanggal) Values (?,?,?,?,?,?)";
                    java.sql.Connection conntt =(Connection) Koneksi.getkoneksi();
                    java.sql.PreparedStatement pst=conntt.prepareStatement(sql);
                    pst.setString(1, id_barangi1.getText());
                    pst.setString(2, namabarang1.getText());
                    pst.setString(3, jumlah.getText());
                    pst.setString(4, namasatuan1.getText());
                    pst.setString(5, harga1.getText());
                    pst.setString(6, tgl);
                    pst.executeUpdate();
                    tampil_barang();
                    satuan1.setSelectedItem("PILIH SATUAN");
                    namasatuan1.setText("");
                    harga1.setText("");
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Disimpan");
        }
            }
            
// TODO add your handling code here:
    }//GEN-LAST:event_tambahprdActionPerformed

    private void scanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scanActionPerformed

    private void satuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_satuanActionPerformed

    private void mencariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mencariActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mencariActionPerformed

    private void bataltrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bataltrnActionPerformed
        // TODO add your handling code here:
        try {
            
            String sql = "DELETE FROM keranjang WHERE keterangan = 'PENJUALAN';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            tampil_data();
            jumlahbarang();
            HargaTot();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Dihapus");
        } 
    }//GEN-LAST:event_bataltrnActionPerformed

    private void btlprdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlprdActionPerformed
                // TODO add your handling code here:
        int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Anda Menghapus Data ini???","Confirmation",JOptionPane.YES_NO_OPTION);
        if(ok==0){
            try {
            
            String sql = "DELETE FROM barang WHERE id_barang='"+id_barangi1.getText()+"' + tanggal = '"+tgl+"';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            hapus();
            namaSupplier.setText("");
            id_barangi1.setText("");
                    namabarang1.setText("");
                    jumlah.setText("");
            satuan1.setSelectedItem("PILIH SATUAN");
                    namasatuan1.setText("");
                    harga1.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Dihapus");
        }
            tampil_barang();
        }
    }//GEN-LAST:event_btlprdActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
        print1();
        try {
                strukpembelian.print();
            } catch (java.awt.print.PrinterException e) {
                System.err.format("Tidak Ada Printer Yang Ditemukan", e.getMessage());
            }
    }//GEN-LAST:event_printActionPerformed

    private void mencari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mencari1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mencari1ActionPerformed

    private void mencari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mencari1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("SATUAN");
        model.addColumn("JUMLAH");
        model.addColumn("HARGA");

        try {
            String sql = "Select * From keranjang where keterangan = 'PEMBELIAN' + nama_barang LIKE '%"+mencari1.getText()+"%';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = cn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("Id_barang"), res.getString("nama_barang"), res.getString("satuan"), res.getString("jumlah"), res.getString("harga")});
            }

            table_barang.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak muncul");
        }
    }//GEN-LAST:event_mencari1KeyReleased

    private void mencariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mencariKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("SATUAN");
        model.addColumn("JUMLAH");
        model.addColumn("HARGA");
        model.addColumn("ACTION");

        try {
            String sql = "Select * From keranjang where nama_barang LIKE '%"+mencari.getText()+"%';";
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
    }//GEN-LAST:event_mencariKeyReleased

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarActionPerformed

    private void bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeyReleased
        // TODO add your handling code here:
        kembali.setText("");
        if (bayar.getText() == "") {
            kembali.setText("");
        } else {
            
        int membayar = Integer.parseInt(bayar.getText());
        int Hargatt = Integer.parseInt(totalmembayar);
        
        int totalkembali = membayar - Hargatt;
        kembali.setText("Rp. "+String.valueOf(totalkembali));
        }
    }//GEN-LAST:event_bayarKeyReleased

    private void satuan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuan1ActionPerformed
        // TODO add your handling code here:
//        try {
//            Object pilihan = satuan1.getSelectedItem();
//            String sql1 = "SELECT eceran, grosir, harga_eceran, harga_grosir FROM barang;";
//            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
//            java.sql.Statement st = conn.createStatement();
//            java.sql.ResultSet res = st.executeQuery(sql1);
//            res.next();
//            if (pilihan == "ECERAN") {
//                SATUANpmb = res.getString("eceran");
//            } else if (pilihan == "GROSIR"){
//                SATUANpmb = res.getString("grosir");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_satuan1ActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

    private void kembalikecariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalikecariActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.repaint();
        Kasir.revalidate();
        
        Kasir.add(Cari);
        Kasir.repaint();
        Kasir.revalidate();
    }//GEN-LAST:event_kembalikecariActionPerformed

    private void kembalikecari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalikecari1ActionPerformed
        // TODO add your handling code here:
        Kasir.removeAll();
        Kasir.repaint();
        Kasir.revalidate();
        
        Kasir.add(Cari);
        Kasir.repaint();
        Kasir.revalidate();
    }//GEN-LAST:event_kembalikecari1ActionPerformed

    private void logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_logout1ActionPerformed

    private void scbtlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scbtlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scbtlActionPerformed

    private void HargaBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HargaBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HargaBayarActionPerformed

    private void namaSupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaSupplierKeyReleased
        // TODO add your handling code here:
       try {
            String sql1 = "SELECT * FROM supplier where nama_supplier LIKE '%"+namaSupplier.getText()+"%';";
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
    }//GEN-LAST:event_namaSupplierKeyReleased

    private void CETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CETAKActionPerformed
        print();
        try {
                strukpeminjaman.print();
            } catch (java.awt.print.PrinterException e) {
                System.err.format("Tidak Ada Printer Yang Ditemukan", e.getMessage());
            }
    }//GEN-LAST:event_CETAKActionPerformed


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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CETAK;
    public javax.swing.JPanel Cari;
    private javax.swing.JTextField HargaBayar;
    private javax.swing.JPanel Kasir;
    public static final javax.swing.JTextField NamaKasir = new javax.swing.JTextField();
    private javax.swing.JPanel TransaksiPembelian;
    private javax.swing.JPanel TransaksiPenjualan;
    private javax.swing.JTable barang_barang;
    private javax.swing.JButton batal;
    private javax.swing.JButton bataltrn;
    private javax.swing.JTextField bayar;
    private javax.swing.JButton btlprd;
    private javax.swing.JButton btn_tjual;
    private javax.swing.JButton btn_tpembelian;
    private javax.swing.JTextField cari;
    private javax.swing.JTextField harga1;
    private javax.swing.JTextField hargaTotal;
    private javax.swing.JTextField hargaprd;
    private javax.swing.JTextField id_barangi1;
    private javax.swing.JTextField id_transaksi;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField jumlah_barang;
    private javax.swing.JTextField jumlahprd;
    private javax.swing.JTextField kembali;
    private javax.swing.JButton kembalikecari;
    private javax.swing.JButton kembalikecari1;
    private javax.swing.JTable list_barang;
    private javax.swing.JButton logout;
    private javax.swing.JButton logout1;
    private javax.swing.JTextField mencari;
    private javax.swing.JTextField mencari1;
    private javax.swing.JTextField namaSupplier;
    private javax.swing.JTextField namabarang1;
    private javax.swing.JTextField namaprd;
    private javax.swing.JTextField namasatuan1;
    private javax.swing.JButton print;
    private javax.swing.JComboBox<String> satuan;
    private javax.swing.JComboBox<String> satuan1;
    private javax.swing.JButton scan;
    private javax.swing.JButton scbtl;
    public javax.swing.JScrollPane strukpemb;
    public static javax.swing.JTextArea strukpembelian;
    public static javax.swing.JTextArea strukpeminjaman;
    public javax.swing.JScrollPane strukpnj;
    private javax.swing.JTable table_barang;
    private javax.swing.JButton tambah;
    private javax.swing.JButton tambahprd;
    private javax.swing.JLabel tanggalreal;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
public void namaSup(){ 
            try {
            String sql1 = "SELECT * FROM supplier where nama_supplier LIKE '%"+namaSupplier.getText()+"%';";
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
