package Business;

/**
 * @author destiny.
 */
public class genre {

    private int genreid;
    private String genre1;
    private String genre2;

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    public String getGenre1() {
        return genre1;
    }

    public void setGenre1(String genre1) {
        this.genre1 = genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public void setGenre2(String genre2) {
        this.genre2 = genre2;
    }

    @Override
    public String toString() {
        return "genre{" +
                "genreid=" + genreid +
                ", genre1='" + genre1 + '\'' +
                ", genre2='" + genre2 + '\'' +
                '}';
    }

    public genre(int genreid, String genre1, String genre2) {
        this.genreid = genreid;
        this.genre1 = genre1;
        this.genre2 = genre2;



    }
}
