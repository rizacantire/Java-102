import java.util.*;

public class Main {
    public static void main(String[] args) {
        Book kitap1 = new Book("Bi Dünya Futbol",192,"Serkan Akkoyun",2020);
        Book kitap2 = new Book("Bi Memleket Futbol",192,"Serkan Akkoyun",2021);
        Book kitap3 = new Book("Seyir",348,"Piraye",2019);
        Book kitap4 = new Book("Ben Kirke",408,"Madeline Miller",2019);
        Book kitap5 = new Book("Satranç",77,"Stefan Zweig",2012);

        Set<Book> books = new HashSet<>();
        books.add(kitap1);
        books.add(kitap2);
        books.add(kitap3);
        books.add(kitap4);
        books.add(kitap5);
        for (Book book: books){
            System.out.println("Kitap adı : " +book.getName() + " Sayfa : " +book.getPage());
        }

        List<Book> sortBooks = new ArrayList<>();
        sortBooks.addAll(books);
        Collections.sort(sortBooks);
        System.out.println();
        System.out.println("iSME GÖRE SIRALI");
        for (Book book:sortBooks){

            System.out.println("Kitap adı : " +book.getName() + " Sayfa : " +book.getPage());
        }
        System.out.println("-------------------------");


        List<Book> pp = new ArrayList<>();
        pp.addAll(books);
        Collections.sort(pp, new Book.PageSort());
        System.out.println("Sayfa sayısına göre sıralama");
        for (Book book:pp){

            System.out.println(book.getName() + " " + book.getPage());

        }

    }
}
