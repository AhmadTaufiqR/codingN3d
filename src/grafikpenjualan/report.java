/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikpenjualan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javaswingdev.chart.ModelChart;
import n3dshop.Koneksi;

/**
 *
 * @author HP
 */
public class report {
    
    
    public List<ModelChart> getData() throws  SQLException{
           List<ModelChart> list = new ArrayList<>();
            String sql = "SELECT DATE_FORMAT(tanggal,'%M') AS tanggal, COUNT(transaksi_penjualan.no_faktur) AS id FROM detail_transaksi_penjualan JOIN transaksi_penjualan ON detail_transaksi_penjualan.no_faktur = transaksi_penjualan.no_faktur GROUP BY tanggal ORDER BY tanggal DESC;";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement st = cn.prepareStatement(sql);
            java.sql.ResultSet rs = st.executeQuery();
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
