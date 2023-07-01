package Model;

public class Session{
    static int ID = 1;

    public Session(){

    }

    public Session(int ID){
        Session.ID = ID;
    }

    public int getID() {
        return Session.ID;
    }

    public void setID(int ID) {
        Session.ID = ID;
    }


}