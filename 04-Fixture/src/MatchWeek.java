import java.util.List;

public class MatchWeek {
    private List<Match> match;
    private int matchWeek;

    public MatchWeek(List<Match> match, int matchWeek) {
        this.match = match;
        this.matchWeek = matchWeek;
    }

    public List<Match> getMatch() {
        return match;
    }

    public void setMatch(List<Match> match) {
        this.match = match;
    }

    public int getMatchWeek() {
        return matchWeek;
    }

    public void setMatchWeek(int matchWeek) {
        this.matchWeek = matchWeek;
    }
}
