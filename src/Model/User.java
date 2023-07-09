package Model;

import javafx.scene.image.Image;

public class User {
    static int ID = 1;
    String Nama;
    String Username;
    String Nomor;
    String Sandi;
    String Alamat;
    String Email;
    String JenisKelamin;
    String TanggalLahir;
    Image GambarProfile;

    public User(){

    }

    public User(int ID, String Nama,String Username,String Nomor,String Sandi,String Alamat,String JenisKelamin,String TanggalLahir){
        this.ID = ID;
        this.Nama = Nama;
        this.Username = Username;
        this.Nomor = Nomor;
        this.Sandi = Sandi;
        this.Alamat = Alamat;
        this.JenisKelamin = JenisKelamin;
        this.TanggalLahir = TanggalLahir;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNama() {
        return this.Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getNomor() {
        return this.Nomor;
    }

    public void setNomor(String Nomor) {
        this.Nomor = Nomor;
    }

    public String getSandi() {
        return this.Sandi;
    }

    public void setSandi(String Sandi) {
        this.Sandi = Sandi;
    }

    public String getAlamat() {
        return this.Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public String getJenisKelamin() {
        return this.JenisKelamin;
    }

    public void setJenisKelamin(String JenisKelamin) {
        this.JenisKelamin = JenisKelamin;
    }

    public String getTanggalLahir() {
        return this.TanggalLahir;
    }

    public void setTanggalLahir(String TanggalLahir) {
        this.TanggalLahir = TanggalLahir;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Image getGambarProfile() {
        return this.GambarProfile;
    }

    public void setGambarProfile(Image GambarProfile) {
        this.GambarProfile = GambarProfile;
    }
}
