public class Fixture {
    public static void main(String[] args) {
        GenerateFixture generator = new GenerateFixture();

        String[] teamName = {"Beşiktaş","Fenerbahçe","Galatasaray","Trabzonspor","Başakşehir","Bursaspor"};

        generator.add(teamName);

        System.out.println("---------------\t  Takımlar\t----------------------");
        generator.listTeam();
        var round = generator.createRound();
        for(var i : round){
            System.out.println(i.getHomeTeam().getTeamName() + " vs " + i.getAwayTeam().getTeamName());
        }
    }
}
