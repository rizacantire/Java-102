import Battle.*;
import Gamers.CaseAward;
import Gamers.Player;
import Locations.Location;
import Locations.SafeHouse;
import Locations.ToolStore;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);

    public  void start(){
        System.out.println("Macera Oyunumuza hoşgeldiniz...");
        System.out.print("Kullanıcı adınızı giriniz : ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + playerName + " oyunumuza hoşgeldin.");
        player.selectChar();
        Location location = null;
        CaseAward caseAwardCave = new CaseAward(1,"Yemek",false);
        CaseAward caseAwardForest = new CaseAward(2,"Odun",false);
        CaseAward caseAwardRiver = new CaseAward(3,"Su",false);
        CaseAward[] caseAwards = {caseAwardCave,caseAwardForest,caseAwardRiver};
        String snakeAward = "";
        CaseAward caseAwardSnake = new CaseAward(4,snakeAward,false);





        while (true){
            player.printInfo();
            System.out.println("Bölgeler...");
            System.out.println("1 - Güvenli Ev");
            System.out.println("2 - Eşya Marketi");
            System.out.println("3 - Mağara - Mağara bölümüne git. (Zombilerin mekanı)");
            System.out.println("4 - Orman - Orman bölümüne git. (Vampilerin mekanı)");
            System.out.println("5 - Nehir - Nehir bölümüne git. (Ayıların mekanı)");
            System.out.println("6 - Maden - Maden alanına gider. (Yılan avı mekanı, rastgele item kazan)");
            System.out.println("7 - Mekan ödül durumu listele.");
            System.out.println("0 - Çıkış yap");
            System.out.print("Lütfen gitmek istediğiniz mekanı seçiniz : ");
            int selectLoc  = scanner.nextInt();
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    System.out.println();
                    int totalAward = 0;
                    for (CaseAward caseAward : caseAwards){
                        if (caseAward.getIsIsSuccess() == true){
                            totalAward += 1;
                        }
                    }
                    if (totalAward == 3){
                        System.out.println("**************");
                        System.out.println(" ************ ");
                        System.out.println("  **********  ");
                        System.out.println("Bütün ödüller toplandı oyun başarıyla bitirildi....");
                    }else {
                        System.out.println("####  -  Daha toplanacak ödüller var.  -  ###");
                    }
                    System.out.println();

                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player,caseAwardCave);
                    break;
                case 4:
                    location = new Forest(player,caseAwardForest);
                    break;
                case 5:
                    location= new River(player,caseAwardRiver);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                case 7:
                    System.out.println("***********************************");
                    System.out.println("Yemek \t- Mağara\nOdun \t- Orman\nSu  \t- Nehir");
                    System.out.println("Bölüm ödülleri durumu: \n");
                    for (CaseAward caseAward : caseAwards){
                        System.out.println(caseAward.getIsIsSuccess()? caseAward.getName()+" ödülü toplandı!" : caseAward.getName()+" ödülü toplanabilir....");
                    }
                    location = new SafeHouse(player);
                    break;

                default:
                    System.out.println("####Geçerli bir bölge değeri girmediniz####");
                    System.out.println();
                    break;


            }
            if(location == null){
                System.out.println("Oyun bitti hoşçakalın....");
                break;
            }

            if(!location.onLocation()){
                System.out.println("Oyun Bitti....");
                break;
            }
        }

    }

}
