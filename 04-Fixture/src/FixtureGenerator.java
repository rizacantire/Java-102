import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FixtureGenerator {
    private List<Team> teams = new ArrayList<>();
    private List<Team> tempTeams = new ArrayList<>();
    private List<Match> allMatches = new ArrayList<>();
    private List<MatchWeek> matchWeeks;
    private List<Match> gameWeek;
    private Match match;

    public FixtureGenerator() {
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTempTeams() {
        return tempTeams;
    }

    public void setTempTeams(List<Team> tempTeams) {
        this.tempTeams = tempTeams;
    }

    public List<Match> getGameWeek() {
        return gameWeek;
    }

    public void setGameWeek(List<Match> gameWeek) {
        this.gameWeek = gameWeek;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<Match> getAllMatches() {
        return allMatches;
    }

    public void setAllMatches(List<Match> allMatches) {
        this.allMatches = allMatches;
    }

    public List<MatchWeek> getMatchWeeks() {
        return matchWeeks;
    }

    public void setMatchWeeks(List<MatchWeek> matchWeeks) {
        this.matchWeeks = matchWeeks;
    }

    void add(String[] teamNames){
        for (var teamName : teamNames){
            teams.add(new Team(teamName));
        }
        if (teamNames.length % 2 != 0){
            teams.add(new Team("Bay"));
        }
    }

    void listTeam(){
        System.out.println("------\tTakımlar\t------");
        for (var team:teams){
            System.out.println(team.getTeamName());
        }
    }

    Team choiceTeam(){
        Team team;
        Random random  = new Random();
        team = tempTeams.get(random.nextInt(tempTeams.size()));
        tempTeams.remove(team);
        return team;
    }

    Match createMatch(){

        Team home= choiceTeam();
        Team away = choiceTeam();
        this.match = new Match(home,away);
        return this.match;
    }

    List<Match> generateWeek(){
        gameWeek  = new ArrayList<>();
        tempTeams.addAll(teams);
        while (true){
            if (tempTeams.size()==0){
                break;
            }else {
                createMatch();
                gameWeek.add(this.match);

            }
        }
        return gameWeek;
    }
    boolean  checkWeek(List<Match> gw){
        boolean is = true;
        while (is){
            for (var m : gw){
                for (var am : allMatches){
                    var at = m.getAwayTeam().equals(am.getAwayTeam());
                    var ht = m.getHomeTeam().equals(am.getHomeTeam());
                    if ((ht && at) == true){
                        is =false;
                        break;
                    }
                }return is;
            }
        }return is;
    }

    List<MatchWeek> createFixture(){
        matchWeeks = new ArrayList<>();
        int i = teams.size();
        int round = 1;
        while (true){
            if (i == 1){
                break;
            }else {var createRound1= generateWeek();
                while (true){

                    if (checkWeek(createRound1) == false){
                        createRound1= generateWeek();
                    }else {
                        MatchWeek m = new MatchWeek(createRound1,round);
                        matchWeeks.add(m);
                        for (var cr : createRound1){
                            this.allMatches.add(cr);
                            this.allMatches.add(new Match(cr.getAwayTeam(),cr.getHomeTeam()));
                        }
                        round++;
                        i--;
                        break;
                    }
                }
            }
        }
        return matchWeeks;
    }
}