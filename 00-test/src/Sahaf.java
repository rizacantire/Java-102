import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Sahaf {
    public static Document document;
    public static ArrayList<String> sahafList = new ArrayList<>();
    public static ArrayList<String> searchList = new ArrayList<>();
    public static ArrayList<String> stockSahaf = new ArrayList<>();
    public static ArrayList<String> forfile = new ArrayList<>();
    public static ArrayList<String> title = new ArrayList<>();
    public static ArrayList<String> book = new ArrayList<>();


    public static void main(String[] args) {


        var link = "https://www.nadirkitap.com/sahaflar.php?ara=1&favori=0&rumuz=&sehir=35&page=";
        say(link+1,"li p a",sahafList);
        say(link+2,"li p a",sahafList);
        say(link+3,"li p a",sahafList);
        say(link+4,"li p a",sahafList);
        say(link+5,"li p a",sahafList);
        say(link+6,"li p a",sahafList);


        var linkSearch = "https://www.nadirkitap.com/" +
                "kitapara_sonuc.php?kelime=halit%20k%FDvan%E7&siralama=fiyatartan&bks=855&page=";
        for(int i = 1; i<=35;i++){
            say(linkSearch+i,"li span a",searchList);
            say(linkSearch+i,"ul li h4 a",title);
        }AtomicInteger count = new AtomicInteger();
        System.out.println(title.size());
        for (var ls : searchList){

            sahafList.forEach(e->{
                if (e.equals(ls)){
                    stockSahaf.add(ls);
                    book.add(title.get(count.get()));
                    System.out.println(count);
                    count.getAndIncrement();
                }

            });
        }
        System.out.println(book.size());
        System.out.println(count);
        Set<String> set = new HashSet<>(stockSahaf);
        stockSahaf.clear();
        stockSahaf.addAll(set);
        /*try {
            FileWriter myWriter = new FileWriter("filename.txt");
            FileWriter myWriter2 = new FileWriter("filename2.txt");
            for(var w : stockSahaf){
                myWriter.write(w+"\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/
    }


    public static void say (String link,String searchString,ArrayList<String> list){
        try {
            document = Jsoup.connect(link).get();
            var elements = document.select(searchString);
            for (var e : elements){
                list.add(e.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
