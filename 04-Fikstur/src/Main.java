import java.util.*;

public class Main {
    public static void main(String[] args) {
        GenerateFixture generator = new GenerateFixture();

        String[] teamName = {"Beşiktaş","Fenerbahçe","Galatasaray","Trabzonspor","Başakşehir","Bursaspor"};

        generator.add(teamName);

        System.out.println("---------------\t  Takımlar\t----------------------");
        generator.listTeam();

        var fiks = generator.createFixture();

        for (var i : fiks){
            System.out.println("\t\t  Round : " + i.getMatchWeek());
            for (var j : i.getMatch()){
                System.out.println(j.getHomeTeam().getTeamName() + "\t"+ " vs " +"\t" +j.getAwayTeam().getTeamName());
            }
        }


        for (var i : fiks){
            System.out.println("\t\t  Round : " + (i.getMatchWeek()+3));
            for (var j : i.getMatch()){
                System.out.println(j.getAwayTeam().getTeamName() + "\t"+ " vs " +"\t" +j.getHomeTeam().getTeamName());
            }
        }

    }
}
