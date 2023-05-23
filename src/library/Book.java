package library;

public class Book {
    private int bookID;
    private String bookName;
    private String genre;
    private String price;

    public Book(int bookID, String bookName, String genre, String price) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.genre = genre;
        this.price = price;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
