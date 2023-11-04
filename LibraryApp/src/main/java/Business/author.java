package Business;

/**
 * @author destiny.
 */
public class author {

    private int authorid;
    private String firstname;
    private String lastname;
    private int bookid;

    public author(int authorid, String firstname, String lastname, int bookid) {
        this.authorid = authorid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bookid = bookid;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    @Override
    public String toString() {
        return "author{" +
                "authorid=" + authorid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", bookid=" + bookid +
                '}';
    }

}
