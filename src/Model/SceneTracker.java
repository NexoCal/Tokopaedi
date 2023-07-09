package Model;

public class SceneTracker {
    static boolean fromMenu = true;

    public void setTrack(boolean track){
        this.fromMenu = track;
    }

    public boolean isFromMainMenu(){
        return fromMenu;
    }
}
