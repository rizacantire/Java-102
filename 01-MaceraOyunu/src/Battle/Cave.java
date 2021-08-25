package Battle;

import Gamers.CaseAward;
import Gamers.Player;

public class Cave extends BattleLocation{
    public Cave(Player player,CaseAward caseAward) {
        super(player, "Mağara", new Zombie(),4,caseAward);
    }
}