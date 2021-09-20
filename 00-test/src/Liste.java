import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Liste {
    public static Document document;
    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<String> sahaf = new ArrayList<>();
    public static void main(String[] args) {
        String link = "https://www.nadirkitap.com/" +
                "kitapara_sonuc.php?kelime=halit%20k%FDvan%E7&siralama=fiyatartan&bks=855&page=1";
        say(link,"ul li h4 a",list);
        say(link,"ul li span a",sahaf);

        System.out.println(list.get(11));
        System.out.println(sahaf.get(11));
        sahaf.forEach(e->{
            System.out.println(e);
        });

    }

    public static void say (String link, String searchString, ArrayList<String> list){
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
