SELECT transaksi_penjualan.no_faktur, transaksi_penjualan.username, transaksi_penjualan.Harga_Total, transaksi_penjualan.bayar, transaksi_penjualan.kembali, detail_transaksi_penjualan.Id_barang, detail_transaksi_penjualan.jumlah_ecer, detail_transaksi_penjualan.jumlah_grosir, detail_transaksi_penjualan.satuan, detail_transaksi_penjualan.Harga, barang.nama_barang, petugas.nama
FROM transaksi_penjualan 
JOIN detail_transaksi_penjualan 
ON transaksi_penjualan.no_faktur = detail_transaksi_penjualan.no_faktur 
JOIN barang 
ON barang.id_barang = detail_transaksi_penjualan.Id_barang
JOIN petugas
ON petugas.username = transaksi_penjualan.username;