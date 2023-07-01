package Model;

public class Barang {
    int ID;
    String NamaBarang;
    String HargaBarang;
    String User;
    String Gambar;

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

    public String getGambar(){
        return Gambar;
    }

    public void setGambar(String Gambar){
        this.Gambar = Gambar;
    }

    public int getID(){
        return ID;
    }

}
