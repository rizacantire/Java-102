public class Main {
    public static void main(String[] args) {
        String[] teamNames = {"Beşiktaş","Galatasaray","Fenerbahçe","Trabzonspor","Başakşehir"};
        FixtureGenerator fixtureGenerator = new FixtureGenerator();
        fixtureGenerator.add(teamNames);
        fixtureGenerator.listTeam();
        System.out.println();

        fixtureGenerator.createFixture();
        for (var mw : fixtureGenerator.getMatchWeeks()){
            System.out.println("round : " + mw.getWeek());
            for (var gw : mw.getWeekMatches()){
                System.out.println(gw.getHomeTeam().getTeamName() +" vs " +gw.getAwayTeam().getTeamName());
            }
            System.out.println("------------------");
        }
        System.out.println("maç size : " +fixtureGenerator.getAllMatches().size());


    }
}
