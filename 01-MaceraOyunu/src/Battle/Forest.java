package Battle;

import Gamers.CaseAward;
import Gamers.Player;

public class Forest extends BattleLocation{
    public Forest(Player player,CaseAward caseAward) {
        super(player, "Orman", new Vampire(),3,caseAward);
    }
}
