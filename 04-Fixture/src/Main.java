import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] teamNames = {"Beşiktaş","Galatasaray","Fenerbahçe","Trabzonspor"};
        FixtureGenerator fixtureGenerator = new FixtureGenerator();
        fixtureGenerator.add(teamNames);
        fixtureGenerator.listTeam();
        fixtureGenerator.createFixture();

        System.out.println();


        for (var mw : fixtureGenerator.getMatchWeeks()){
            System.out.println("round : " + mw.getMatchWeek());
            for (var gw : mw.getMatch()){
                System.out.println(gw.getHomeTeam().getTeamName() +" vs " +gw.getAwayTeam().getTeamName());
            }
            System.out.println("------------------");
        }

        for (var mw : fixtureGenerator.getMatchWeeks()){
            System.out.println("round : " + (mw.getMatchWeek()+fixtureGenerator.getTeams().size()-1));
            for (var gw : mw.getMatch()){
                System.out.println(gw.getAwayTeam().getTeamName() +" vs " +gw.getHomeTeam().getTeamName());
            }
            System.out.println("------------------");
        }



    }
}