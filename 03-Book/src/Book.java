import java.util.Comparator;

public class Book implements Comparable<Book> {
    private String name;
    private int page;
    private String author;
    private int releaseDate;

    public Book(String name, int page, String author, int releaseDate) {
        this.name = name;
        this.page = page;
        this.author = author;
        this.releaseDate = releaseDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int compareTo(Book o) {

        return  new String(this.getName()).compareTo(o.getName());

    }
    static class PageSort implements Comparator {
        public int compare(Object b1, Object b2) {
            int b1Page = ((Book) b1).getPage();
            int b2Page = ((Book) b2).getPage();
            if (b1Page > b2Page)
                return 1;
            else if (b1Page < b2Page)
                return -1;
            else
                return 0;
        }


    }


}


