/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaranagTampil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javaswingdev.chart.ModelChart;
import n3dshop.Koneksi;
import n3dshop.MenuOwner;

/**
 *
 * @author HP
 */
public class BarangDash {
    
    MenuOwner mn = new MenuOwner();
    public List<ModelChart> getData() throws  SQLException{
           List<ModelChart> list = new ArrayList<>();
                String sql1 = "SELECT barang.nama_barang, COUNT(detail_transaksi_penjualan.Id_barang) AS M FROM detail_transaksi_penjualan JOIN barang ON detail_transaksi_penjualan.Id_barang = barang.id_barang where barang.nama_barang LIKE '%"+mn.barangdashboard.getText()+"%';";
                java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
                java.sql.Statement st = conn.createStatement();
                java.sql.ResultSet rs = st.executeQuery(sql1);
                
                while(rs.next()){
                String gr = rs.getString(1);
                double dbj = rs.getDouble(2);
                list.add(new ModelChart(gr, new double[]{dbj}));
                }
                
            rs.close();
            st.close();
            
            return list;
  }
}
