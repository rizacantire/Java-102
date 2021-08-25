package Battle;

import Gamers.CaseAward;
import Gamers.Player;

public class River extends BattleLocation{
    public River(Player player,CaseAward caseAward) {
        super(player, "Nehir", new Bear(),2,caseAward);
    }
}
