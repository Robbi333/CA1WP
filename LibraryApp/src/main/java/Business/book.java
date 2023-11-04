package Business;

import java.util.Objects;

public class book {
    private int Bookid;
    private String Title;
    private int AuthorID;
    private int ISBN;
    private int PublicationYear;
    private int GenreID;
    private int TotalCopies;
    private String Description;

    public book(int bookid, String title, int authorID, int ISBN, int publicationYear, int genreID, int totalCopies, String description) {
        Bookid = bookid;
        Title = title;
        AuthorID = authorID;
        this.ISBN = ISBN;
        PublicationYear = publicationYear;
        GenreID = genreID;
        TotalCopies = totalCopies;
        Description = description;
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

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        Description = description;
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
                ", Description='" + Description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        book book = (book) o;
        return Bookid == book.Bookid && AuthorID == book.AuthorID && ISBN == book.ISBN && PublicationYear == book.PublicationYear && GenreID == book.GenreID && TotalCopies == book.TotalCopies && Objects.equals(Title, book.Title) && Objects.equals(Description, book.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Bookid, Title, AuthorID, ISBN, PublicationYear, GenreID, TotalCopies, Description);
    }
}
