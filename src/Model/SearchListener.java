package Model;

public class SearchListener {
    
    static String search = null;
    static boolean iskategoriSearch = false;

    public boolean isIskategoriSearch() {
        return this.iskategoriSearch;
    }

    public void setIskategoriSearch(boolean iskategoriSearch) {
        this.iskategoriSearch = iskategoriSearch;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    
    
}
