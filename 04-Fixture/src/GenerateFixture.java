import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateFixture {
    List<Team> teams = new ArrayList<>();
    List<Team> tempChoiceTeams = new ArrayList<>(teams);
    List<Match> allMatches = new ArrayList<>();
    List<Match> matches = new ArrayList<>();

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

    Team choiceTeam(){
        Random random = new Random();
        Team team;
        if (tempChoiceTeams.size()==0){
            team = tempChoiceTeams.get(0);

            return team;
        }else {
            team = tempChoiceTeams.get(random.nextInt(tempChoiceTeams.size()));

            return team;
        }
    }

    Match choiceMatch(){
        List<Team> tempTeamMatch = tempChoiceTeams;
        Team home= choiceTeam();
        tempTeamMatch.remove(home);
        Team away= choiceTeam();
        tempTeamMatch.remove(away);
        Match match = new Match(home,away);
        return match;
    }

    List<Match> createRound(){
        List<Match> rounds = new ArrayList<>();

        while (true){
            if (rounds.size()==(teams.size()/2)){
                tempChoiceTeams.addAll(teams);
                break;
            }else {
                tempChoiceTeams.remove(choiceTeam());
                var match = choiceMatch();
                rounds.add(match);
                tempChoiceTeams.remove(match.getAwayTeam());
                tempChoiceTeams.remove(match.getHomeTeam());

            }
        }
        return rounds;
    }

    boolean checkMatch(){
        Match m1 = choiceMatch();
        if (allMatches.size()==0){
            allMatches.add(m1);
            return false;
        }else {
            if (allMatches.contains(m1)){
                return true;
            }
            else {
                return false;
            }
        }
    }
}
