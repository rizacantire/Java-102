import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateFixture {

    List<Team> teams = new ArrayList<>();
    List<Team> temporaryTeams;
    List<MatchWeek> matchWeeks;
    List<TeamOpponent> teamOpponents = new ArrayList<>();
    List<Team> tempChoiceTeams;
    List<Match> allMatch = new ArrayList<>();

    int round = 1;

    void add(String[] teamName){
        for (var i : teamName){
            teams.add(new Team(i));
        }
        if (teamName.length%2 !=0){
            teams.add(new Team("Bay"));
        }
    }

    void listTeam(){
        for (Team team : teams){
            System.out.println(team.getTeamName());
        }
    }
    Team choiceTeam(List<Team> tempTeams){
        Random random = new Random();
        Team team =tempTeams.get(random.nextInt(tempTeams.size()));
        tempTeams.remove(team);
        return team;
    }

    boolean check(){

        return false;
    }

    Match choiceMatch(){
        Team home =choiceTeam(tempChoiceTeams);
        Team away =choiceTeam(tempChoiceTeams);
        Match match = new Match(home,away);
        if (allMatch.size()>0){
            var t = allMatch.stream().filter(o->o.getAwayTeam().getTeamName().equals(match.getHomeTeam().getTeamName())).findAny();
            if(!t.isEmpty()){
                System.out.println("-------------");
                System.out.println(t.get().getHomeTeam().getTeamName());
            }
        }
        tempChoiceTeams.remove(home);
        tempChoiceTeams.remove(away);
        return match;
    }

    List<Match> createRound(){
        tempChoiceTeams = new ArrayList<>(teams);
        List<Match> matches = new ArrayList<>();
        List<Team> awayTeams = new ArrayList<>();
        TeamOpponent teamOpponent = new TeamOpponent();
        while (true){
            if(tempChoiceTeams.size()<=0){
                tempChoiceTeams = new ArrayList<>(teams);
                break;
            }else {
                Match match = choiceMatch();
                Team homeTeam = match.getHomeTeam();
                Team awayTeam = match.getAwayTeam();
                matches.add(match);
                allMatch.add(match);
                awayTeams.add(awayTeam);
                teamOpponent.setTeam(homeTeam);
                teamOpponent.setOpponents(awayTeams);
                teamOpponents.add(teamOpponent);
            }
        }
        this.round++;
        return matches;
    }

    List<MatchWeek> createFixture(){
        matchWeeks = new ArrayList<>();
        int i = 1;
        List<Match> createRound;
        while (true){
            if (teams.size()/2 == this.round-1){
                break;
            }else {
                createRound = createRound();
                MatchWeek m = new MatchWeek(createRound,i);
                matchWeeks.add(m);
                i++;
                }
            }
        return matchWeeks;
    }
}
