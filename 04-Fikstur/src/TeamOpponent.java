import java.util.List;

public class TeamOpponent {
    private Team team;
    private List<Team> opponents;

    public TeamOpponent(Team team, List<Team> opponents) {
        this.team = team;
        this.opponents = opponents;
    }
    public TeamOpponent() {

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Team> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Team> opponents) {
        this.opponents = opponents;
    }
}
