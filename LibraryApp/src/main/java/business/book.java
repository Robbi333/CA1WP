package Business;

public class book {
    private int Bookid;
    private String Title;
    private int AuthorID;
    private int ISBN;
    private int PublicationYear;
    private int GenreID;
    private int TotalCopies;
    private String Descripition;

    public book(int bookid, String title, int authorID, int ISBN, int publicationYear, int genreID, int totalCopies, String descripition) {
        Bookid = bookid;
        Title = title;
        AuthorID = authorID;
        this.ISBN = ISBN;
        PublicationYear = publicationYear;
        GenreID = genreID;
        TotalCopies = totalCopies;
        Descripition = descripition;
    }

    public int getBookid() {
        return Bookid;
    }

    public void setBookid(int bookid) {
        Bookid = bookid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(int authorID) {
        AuthorID = authorID;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getPublicationYear() {
        return PublicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        PublicationYear = publicationYear;
    }

    public int getGenreID() {
        return GenreID;
    }

    public void setGenreID(int genreID) {
        GenreID = genreID;
    }

    public int getTotalCopies() {
        return TotalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        TotalCopies = totalCopies;
    }

    public String getDescripition() {
        return Descripition;
    }

    public void setDescripition(String descripition) {
        Descripition = descripition;
    }

    @Override
    public String toString() {
        return "book{" +
                "Bookid=" + Bookid +
                ", Title='" + Title + '\'' +
                ", AuthorID=" + AuthorID +
                ", ISBN=" + ISBN +
                ", PublicationYear=" + PublicationYear +
                ", GenreID=" + GenreID +
                ", TotalCopies=" + TotalCopies +
                ", Descripition='" + Descripition + '\'' +
                '}';
    }
}
