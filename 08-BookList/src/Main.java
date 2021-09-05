import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Medyum",384,"Stephen King",1977);
        Book book2 = new Book("Kırmızı Pazaretesi",120,"Gabriel Garcia Marquez",1981);
        Book book3 = new Book("Mahalleden Arkadaşlar",220,"Selçuk Aydemir",2015);
        Book book4 = new Book("Amok Koşucusu",85,"Stefan Sweig",1922);
        Book book5 = new Book("Bıçak Sırtı",412,"Tess Gerritsen",1990);
        Book book6 = new Book("Pal Sokağı Çocukları",221,"Frenc Molnar",1906);
        Book book7 = new Book("Şeker Portakalı",182,"Jose Mauro De Vasconcelos",1968);
        Book book8 = new Book("Başlangıç",536,"Dan Brown",2017);
        Book book9 = new Book("Lantona",656,"Jean Christophe Grange",2015);
        Book book10 = new Book("Nutuk",1197,"Mustafa Kemal ATATÜRK",1927);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);
        Map<String,String> bookDetail = new HashMap<>();

        books.stream().forEach(b->bookDetail.put(b.getBookName(),b.getAuthorName()));
        System.out.println(bookDetail);

        ArrayList<Book> filterBooks = new ArrayList<>();
        books.stream().filter(b->b.getPage()>100).forEach(b->filterBooks.add(b));
        filterBooks.forEach(b-> System.out.println(b.getBookName()));
    }
}
