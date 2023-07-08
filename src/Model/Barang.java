package Model;

import javafx.scene.image.Image;

public class Barang {
    static int ID = 0;
    String NamaBarang;
    String HargaBarang;
    String UkuranBarang;
    String Kondisi;
    String BrandBarang;
    String WarnaBarang;
    String DeskripsiBarang;
    String KategoriBarang;

    String User;
    Image GambarBarang;

    public Barang(){

    }

    public Barang(String NamaBarang, String HargaBarang,String User, String Gambar){
        this.NamaBarang = NamaBarang;
        this.HargaBarang = HargaBarang;
        this.User = User;
    }

        public Barang(int ID,String NamaBarang, String HargaBarang,String User, String Gambar){
        this.NamaBarang = NamaBarang;
        this.HargaBarang = HargaBarang;
        this.User = User;
        this.ID = ID;
    }

    public Barang(String NamaBarang, String HargaBarang,String User){
        this.NamaBarang = NamaBarang;
        this.HargaBarang = HargaBarang;
        this.User = User;
    }

        public Barang(int ID,String NamaBarang, String HargaBarang,String User){
        this.NamaBarang = NamaBarang;
        this.HargaBarang = HargaBarang;
        this.User = User;
        this.ID = ID;
    }

    public String getNamaBarang(){
        return NamaBarang;
    }

    public void setNamaBarang(String NamaBarang){
        this.NamaBarang = NamaBarang;
    }

    public String getHargaBarang(){
        return HargaBarang;
    }

    public void setHargaBarang(String HargaBarang){
        this.HargaBarang = HargaBarang;
    }

    public String getUser(){
        return User;
    }

    public void setUser(String User){
        this.User = User;
    }

    public Image getGambar(){
        return GambarBarang;
    }

    public void setGambar(Image Gambar){
        this.GambarBarang = Gambar;
    }

    public int getID(){
        return ID;
    }

    public String getUkuranBarang() {
        return this.UkuranBarang;
    }

    public void setUkuranBarang(String UkuranBarang) {
        this.UkuranBarang = UkuranBarang;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKondisi() {
        return this.Kondisi;
    }

    public void setKondisi(String Kondisi) {
        this.Kondisi = Kondisi;
    }

    public String getBrandBarang() {
        return this.BrandBarang;
    }

    public void setBrandBarang(String BrandBarang) {
        this.BrandBarang = BrandBarang;
    }

    public String getWarnaBarang() {
        return this.WarnaBarang;
    }

    public void setWarnaBarang(String WarnaBarang) {
        this.WarnaBarang = WarnaBarang;
    }

    public String getDeskripsiBarang() {
        return this.DeskripsiBarang;
    }

    public void setDeskripsiBarang(String DeskripsiBarang) {
        this.DeskripsiBarang = DeskripsiBarang;
    }

    public String getKategoriBarang() {
        return this.KategoriBarang;
    }

    public void setKategoriBarang(String KategoriBarang) {
        this.KategoriBarang = KategoriBarang;
    }

}
