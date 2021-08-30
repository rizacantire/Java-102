import java.util.List;

public class MatchWeek {
    private List<Match> weekMatches;
    private int week;

    public MatchWeek(List<Match> weekMatches, int week) {
        this.weekMatches = weekMatches;
        this.week = week;
    }

    public List<Match> getWeekMatches() {
        return weekMatches;
    }

    public void setWeekMatches(List<Match> weekMatches) {
        this.weekMatches = weekMatches;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
