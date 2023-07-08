package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.channels.AcceptPendingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Barang;
import Model.User;
import javafx.scene.image.Image;

public class DatabaseModel {
    Connection dBConnection = null;

    public void ConnectToDataBase(String dBString) {
        try {
            String URL = "jdbc:sqlite:" + dBString;
            dBConnection = DriverManager.getConnection(URL);
            System.out.println("Connection Established");
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            System.out.println(e.getMessage());
        }
    }

    public void DisconnectFromDataBase() {
        try {
            dBConnection.close();
            System.out.println("Database Disconnected");
        } catch (SQLException e) {
            System.out.println("Database tidak hidup");
        }
    }

    public void InsertUser(String Nama, String Nomor, String Sandi) {
        String sql = "INSERT INTO DataUser(Nama,Nomor,Sandi) VALUES(?,?,?)";

        try (PreparedStatement pstmt = dBConnection.prepareStatement(sql)) {
            pstmt.setString(1, Nama);
            pstmt.setString(2, Nomor);
            pstmt.setString(3, Sandi);
            pstmt.executeUpdate();
            System.out.println("Data Berhasil Ditambah");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.print(e);
        }

    }

    public void InsertBarang(String Nama, String Harga, String Penjual,String Kondisi, String Ukuran, String Brand,
                             String Warna, String Katagori, String Deskripsi, File Gambar ) throws SQLException {
        String sql = "INSERT INTO DataProdukBarang(Nama,Harga,Penjual,Kondisi,Ukuran,Brand,Warna,Katagori,Deskripsi,Gambar) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = dBConnection.prepareStatement(sql);FileInputStream fis = new FileInputStream(Gambar);) {
            pstmt.setString(1, Nama);
            pstmt.setString(2, Harga);
            pstmt.setString(3, Penjual);
            pstmt.setString(4, Kondisi);
            pstmt.setString(5, Ukuran);
            pstmt.setString(6, Brand);
            pstmt.setString(7, Warna);
            pstmt.setString(8, Katagori);
            pstmt.setString(9, Deskripsi);
            pstmt.setBinaryStream(10,(InputStream)fis, (int)Gambar.length());
            pstmt.executeUpdate();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);
        }
        

    }

    public String GetProductDataNama(int ID) {
        String sql = "SELECT ID,Nama FROM DataProdukBarang";
        String Nama = "";
        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (ID == rs.getInt("ID")) {
                Nama = rs.getString("Nama");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Nama;
    }

    public String GetProductDataHarga(int ID) {
        String sql = "SELECT ID,Harga FROM DataProdukBarang";
        String Harga = "";
        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (ID == rs.getInt("ID")) {
                Harga = rs.getString("Harga");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Harga;
    }

    public String GetProductDataPenjual(int ID) {
        String sql = "SELECT ID,Penjual FROM DataProdukBarang";
        String Penjual = "";
        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (ID == rs.getInt("ID")) {
                Penjual = rs.getString("Penjual");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Penjual;
    }

    public Image GetProductDataGambar(int ID) throws IOException {
        String sql = "SELECT ID,Gambar FROM DataProdukBarang";
        Image image = null;
        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (ID == rs.getInt("ID")) {
                InputStream x = rs.getBinaryStream("Gambar");
                OutputStream y = new FileOutputStream(new File("Image.png"));
                byte[] content = new byte[1024];
                int size = 0;
                while ((size = x.read(content)) != -1) {
                    y.write(content, 0, size);
                }
                y.close();
                x.close();

                Image render = new Image("file:Image.png", 220, 220, false, false);
                image = render;

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return image;
    }

    public User SelectUser(int ID) {
        String sql = "SELECT ID,Nama,Nomor,Username,Alamat,Email,JenisKelamin,TanggalLahir,GambarProfile FROM DataUser";
        User temp = new User();
        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (ID == rs.getInt("ID")) {
                    temp.setID(rs.getInt("ID"));
                    temp.setNama(rs.getString("Nama"));
                    temp.setNomor(rs.getString("Nomor"));
                    temp.setUsername(rs.getString("Username"));
                    temp.setAlamat(rs.getString("Alamat"));
                    temp.setEmail(rs.getString("Email"));
                    temp.setJenisKelamin(rs.getString("JenisKelamin"));
                    temp.setTanggalLahir(rs.getString("TanggalLahir"));
                    InputStream iStream = rs.getBinaryStream("GambarProfile");
                    if (iStream != null){
                        Image Gambar = new Image(iStream);
                        temp.setGambarProfile(Gambar);
                    }
                }

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return temp;
    }

    public Barang SelectBarang(int ID) {
        String sql = "SELECT ID,Nama,Harga,Penjual,Gambar,Kondisi,Ukuran,Brand,Warna,Katagori,Deskripsi FROM DataProdukBarang";
        Barang temp = new Barang();
        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (ID == rs.getInt("ID")) {
                    temp.setID(rs.getInt("ID"));
                    temp.setNamaBarang(rs.getString("Nama"));
                    temp.setHargaBarang(rs.getString("Harga"));
                    temp.setUser(rs.getString("Penjual"));
                    temp.setKondisi(rs.getString("Kondisi"));
                    temp.setUkuranBarang(rs.getString("Ukuran"));
                    temp.setBrandBarang(rs.getString("Brand"));
                    temp.setWarnaBarang(rs.getString("Warna"));
                    temp.setKategoriBarang(rs.getString("Katagori"));
                    temp.setDeskripsiBarang(rs.getString("Deskripsi"));
                    InputStream iStream = rs.getBinaryStream("Gambar");
                    if (iStream != null){
                        Image Gambar = new Image(iStream);
                        temp.setGambar(Gambar);
                    }
                }

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return temp;
    }

    public int CountDataBarang(){
        int x = 0;
        String sql = "SELECT COUNT(ID) FROM DataProdukBarang";
        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            x = rs.getInt(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return x;
    }

    public List<Integer> SelectAllID() {
        String sql = "SELECT ID FROM DataProdukBarang";
        List <Integer> ID = new ArrayList<Integer>();
        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                        ID.add(rs.getInt("ID"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ID;
    }

    public boolean AuthenticationBySandiDanNama(String Sandi, String Nama) {
        boolean AccessAllowed = false;
        String sql = "SELECT Nama,Sandi FROM DataUser";

        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (Sandi.equals(rs.getString("Sandi")) && Nama.equals(rs.getString("Nama"))) {
                    AccessAllowed = true;
                    break;
                }
            }
        } catch (SQLException e) {

        }
        return AccessAllowed;

    }

    public int getID(String Sandi, String Nama) {

        String sql = "SELECT ID,Nama,Sandi FROM DataUser";
        int ID = 0;

        try (Statement stmt = dBConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (Sandi.equals(rs.getString("Sandi")) && Nama.equals(rs.getString("Nama"))) {
                    ID = rs.getInt("ID");
                    break;
                }
            }
        } catch (SQLException e) {

        }
        return ID;

    }

    public void Update(int ID, String Lokasi, String Data){
        String sql = "UPDATE DataUser SET "+ Lokasi +" = ?"+" WHERE ID = ?";
        try (PreparedStatement pstmt = dBConnection.prepareStatement(sql)) {
            pstmt.setString(1, Data);
            pstmt.setInt(2, ID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void UploadImageProfile(int ID,File image) throws SQLException{
        String sql = "UPDATE DataUser SET GambarProfile = ?"+" WHERE ID = ?";
        try (FileInputStream fis = new FileInputStream(image);PreparedStatement pstmt = dBConnection.prepareStatement(sql)) {
            pstmt.setBinaryStream(1,(InputStream)fis, (int)image.length());
            pstmt.setInt(2, ID);
            pstmt.executeUpdate();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Deletebarang(int ID){
        String sql = "DELETE FROM DataProdukBarang WHERE ID = ?";
        try (PreparedStatement pstmt = dBConnection.prepareStatement(sql)) {
            pstmt.setInt(1, ID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void UpdateBarang(int ID, String Nama, String Harga,String Penjual,String Kondisi, String Ukuran, String Brand,
        String Warna, String Katagori, String Deskripsi, File Gambar) throws FileNotFoundException, IOException{
        String sql = "UPDATE DataProdukBarang SET Nama = ? , Harga = ? , Penjual =? , Kondisi = ? , Ukuran = ? , Brand = ? , Warna = ? , Katagori = ? , Deskripsi = ? , Gambar = ?"+" WHERE ID = ?";

        try (PreparedStatement pstmt = dBConnection.prepareStatement(sql);FileInputStream fis = new FileInputStream(Gambar);) {
            pstmt.setString(1, Nama);
            pstmt.setString(2, Harga);
            pstmt.setString(3, Penjual);
            pstmt.setString(4, Kondisi);
            pstmt.setString(5, Ukuran);
            pstmt.setString(6, Brand);
            pstmt.setString(7, Warna);
            pstmt.setString(8, Katagori);
            pstmt.setString(9,Deskripsi);
            pstmt.setBinaryStream(10,(InputStream)fis, (int)Gambar.length());
            pstmt.setInt(11, ID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
                
    }

    public void UpdateBarang(int ID, String Nama, String Harga,String Penjual,String Kondisi, String Ukuran, String Brand,
        String Warna, String Katagori, String Deskripsi) throws FileNotFoundException, IOException{
        String sql = "UPDATE DataProdukBarang SET Nama = ? , Harga = ? , Penjual =? , Kondisi = ? , Ukuran = ? , Brand = ? , Warna = ? , Katagori = ?, Deskripsi = ?"+" WHERE ID = ?";

        try (PreparedStatement pstmt = dBConnection.prepareStatement(sql);) {
            pstmt.setString(1, Nama);
            pstmt.setString(2, Harga);
            pstmt.setString(3, Penjual);
            pstmt.setString(4, Kondisi);
            pstmt.setString(5, Ukuran);
            pstmt.setString(6, Brand);
            pstmt.setString(7, Warna);
            pstmt.setString(8, Katagori);
            pstmt.setString(9, Deskripsi);
            pstmt.setInt(10, ID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
                
    }
}

/*
 * public void CreateTable(){
 * Statement stmt = null;
 * 
 * try{
 * stmt = dBConnection.createStatement();
 * String sql = "CREATE TABLE student " +
 * "(ID INT PRIMATY KEY    NOT NULL, " +
 * " NAME           TEXT   NOT NULL, " +
 * " FACULTY        TEXT   NOT NULL, " +
 * " NIM            CHAR(8)) ";
 * 
 * stmt.executeUpdate(sql);
 * stmt.close();
 * }catch(Exception e){
 * System.err.println( e.getClass().getName() + ": " + e.getMessage() );
 * System.exit(0);
 * }
 * }
 */
