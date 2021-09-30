import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;

public class Sahaf {
    public static Document document;
    public static ArrayList<String> sahafList = new ArrayList<>();
    public static ArrayList<String> searchList = new ArrayList<>();
    public static ArrayList<String> stockSahaf = new ArrayList<>();
    public static ArrayList<String> title = new ArrayList<>();
    public static ArrayList<String> prices= new ArrayList<>();
    public static int pageNo;

    public static void main(String[] args) {
        /*var link = "https://www.nadirkitap.com/sahaflar.php?ara=1&favori=0&rumuz=&sehir=35&page=";
        say(link+1,"li p a",sahafList);
        say(link+2,"li p a",sahafList);
        say(link+3,"li p a",sahafList);
        say(link+4,"li p a",sahafList);
        say(link+5,"li p a",sahafList);
        say(link+6,"li p a",sahafList);*/
        File file = new File("filename.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                stockSahaf.add(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String q = "HALİT";

        q = q.replace(" ","+");
        q = q.replace("Ş","%DE");
        q = q.replace("ş","%FD%FE");
        q. replace("Ç","%C7");
        q = q.replace("İ","%DD");

        var linkSearch = "https://www.nadirkitap.com/kitapara_sonuc.php?kelime="+q+"&siralama=fiyatartan&bks=30&page=";
        lastPage(linkSearch,".pagination-product-bottom");
        System.out.println(pageNo);
        /*for(int i = 1; i<=2;i++){
            say(linkSearch+i,"li span a",searchList);
            say(linkSearch+i,"ul li h4 a",title);
            say(linkSearch+i,".product-list-price",prices);
        }

        for (int i=0;i<searchList.size();i++){
            int finalI = i;
            stockSahaf.forEach(o->{
                if (o.equals(searchList.get(finalI))){
                    System.out.print(searchList.get(finalI));
                    System.out.print("\t ");
                    System.out.print(title.get(finalI));
                    System.out.print("\t");
                    System.out.print(prices.get(finalI));
                    System.out.println();
                }
            });
        }*/





        //AtomicInteger count = new AtomicInteger();
       /* for (var ls : searchList){
            count.getAndIncrement();
            sahafList.forEach(e->{
                if (e.equals(ls)){
                    stockSahaf.add(ls);
                    book.add(title.get(count.get()));
                }

            });
        }*/
        //System.out.println(book.size());
        //System.out.println(count);


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
    public static void lastPage(String link, String searchString){
        try {
            document = Jsoup.connect(link).get();
            var element = document.select(searchString);
            System.out.println(element);
            //pageNo = Integer.parseInt(element.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
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
