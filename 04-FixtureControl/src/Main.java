import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] teamNames = {"Beşiktaş","Galatasaray","Fenerbahçe","Trabzonspor","Başakşehir"};
        FixtureGenerator fixtureGenerator = new FixtureGenerator();
        fixtureGenerator.add(teamNames);
        fixtureGenerator.listTeam();
        fixtureGenerator.createMatchWeeks();

        System.out.println();

        //fixtureGenerator.createFixture();
        for (var mw : fixtureGenerator.getMatchWeeks()){
            System.out.println("round : " + mw.getWeek());
            for (var gw : mw.getWeekMatches()){
                System.out.println(gw.getHomeTeam().getTeamName() +" vs " +gw.getAwayTeam().getTeamName());
            }
            System.out.println("------------------");
        }
        System.out.println();
        System.out.println("maç size : " +fixtureGenerator.getAllMatches().size());

        for (var gg: fixtureGenerator.getAllMatches()){
            System.out.println(gg.getHomeTeam() + " vs " +gg.getAwayTeam());
        }


    }
}
