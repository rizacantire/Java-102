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
                    System.out.println("no break");
                    if (am.getHomeTeam().equals(am.getHomeTeam())&& m.getAwayTeam().equals(am.getAwayTeam())){
                        is =false;
                        System.out.println("break");
                        System.out.println("size : " +allMatches.size());
                        break;
                    }
                }return is;
            }
        }return is;
    }



    List<MatchWeek> createMatchWeeks(){
        this.matchWeeks = new ArrayList<>();
        int round = 1;
        int weekSize = teams.size() - 1;
        while (true){
            if (weekSize == 0){
                break;
            }else {
                System.out.println("size : " +allMatches.size());
                System.out.println("burda");
                    System.out.println("size  2: " +allMatches.size());
                    while (true){
                        var gw = generateWeek();
                        if (checkWeek(gw)==false){
                            gw = generateWeek();

                        }else {
                            MatchWeek mw = new MatchWeek(gw,round);
                            this.matchWeeks.add(mw);
                            round++;
                            weekSize--;
                            for (var gm : gw){
                                allMatches.add(gm);
                                allMatches.add(new Match(gm.getAwayTeam(),gm.getHomeTeam()));
                            }
                            break;

                    }
                }

            }
        }
        return matchWeeks;
    }

    List<MatchWeek> createFixture(){
        matchWeeks = new ArrayList<>();
        int i = teams.size();
        int round = 1;
        while (true){
            if (i == 1){
                break;
            }else {
                var createRound1= generateWeek();
                MatchWeek m = new MatchWeek(createRound1,round);
                matchWeeks.add(m);
                this.allMatches.add(this.match);
                this.allMatches.add(new Match(this.match.getAwayTeam(),this.match.getHomeTeam()));
                round++;
                i--;
            }
        }
        return matchWeeks;
    }

    /*Match createMatch(){
        boolean isTrue = true;
        if(allMatches.size()>0){
            while (isTrue){
                Team home = choiceTeam();
                Team away = choiceTeam();
                this.match = new Match(home,away);
                var a = allMatches.stream().filter(o->o.getHomeTeam().equals(this.match.getHomeTeam())&&o.getAwayTeam().equals(this.match.getAwayTeam())).findAny();
                if (a.isEmpty()){
                    isTrue = false;
                    break;
                }else {
                    this.tempTeams.add(home);
                    this.tempTeams.add(away);
                    System.out.println("else çalıştı");
                    if (tempTeams.size()<=2 && a.isEmpty() ==false){
                        this.tempTeams.add(home);
                        this.tempTeams.add(away);
                    }
                    if (this.allMatches.size() >= 30){
                        System.out.println("kırdı");
                        isTrue = false;
                    }
                    System.out.println(allMatches.size());
                    this.match = new Match(home,away);

                }
            }
        }else {
            Team home = choiceTeam();
            Team away = choiceTeam();
            this.match = new Match(home,away);
            var a = allMatches.stream().filter(o->o.getHomeTeam().equals(this.match.getHomeTeam())&&o.getAwayTeam().equals(this.match.getAwayTeam())).findAny();
        }
        return this.match;
    }
*/


}
