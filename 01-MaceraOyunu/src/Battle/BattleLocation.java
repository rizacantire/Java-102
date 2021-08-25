package Battle;

import Gamers.CaseAward;
import Gamers.Player;
import Locations.Location;
import java.util.Random;

public class BattleLocation extends Location {

    private Monster monster;
    private int maxMonster;
    private CaseAward caseAward;


    public CaseAward getCaseAwards() {
        return caseAward;
    }

    public void setCaseAwards(CaseAward caseAward) {
        this.caseAward = caseAward;
    }


    public BattleLocation(Player player, String name, Monster monster, int maxMonster,CaseAward caseAward) {
        super(player, name);
        this.monster = monster;
        this.maxMonster = maxMonster;
        this.caseAward = caseAward;
    }

    public void printLocationDetail() {
        int monsterNumber = this.randomMonsterNumber();
        System.out.println("Bulunduğunuz konum : " + this.getName());
        System.out.println("Dikkat et. Buralar  " + monsterNumber + " tane " + this.getMonster().getName() + " canavarının mekanı.");
        System.out.println("Canavarım özellikleri ona göre hareket et");
        System.out.println("Konumun ödül durumu : " +this.getCaseAwards().getIsIsSuccess());
        System.out.println("---------------------------------------");
    }



    @Override
    public boolean onLocation() {
        int monsterNumber = this.randomMonsterNumber();
        System.out.println("**********************************\n");

        if (this.getCaseAwards().getIsIsSuccess() == false){
            printLocationDetail();
            oneMonsterStats();

            System.out.println("<S> Savaş veya <K> Kaç : ");
            String selectSituation = scanner.nextLine();
            selectSituation = selectSituation.toUpperCase();
            if (selectSituation.equals("S")) {
                System.out.println("Savaş seçildi.");

                if (combat(monsterNumber)) {
                    System.out.println(this.getName() + "mekanında ki tüm yaratıklar yok edildi.");
                    this.getCaseAwards().setIsSuccess(true);
                    return true;
                }

                if (this.getPlayer().getHealth() <= 0) {
                    System.out.println(this.getMonster().getName() + " yaratık tarafından öldürüldünüz.");
                    return false;
                }
            }
        }else {
            System.out.println("Mekan ödülleri toplandı.\n");
            System.out.println("**********************************\n");
        }
    return true;
    }

    public int randomMonsterNumber() {
        Random random = new Random();
        return random.nextInt(this.getMaxMonster()) + 1;
    }

    public void playerStats() {
        System.out.println("\nOyuncu Değerleri\n--------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());
    }

    public void monsterStats(int i) {
        System.out.println("\n" + i + "." + this.getMonster().getName() + " Değerleri\n--------------------");
        System.out.println("Sağlık: " + this.getMonster().getHealth());
        System.out.println("Hasar: " + this.getMonster().getDamage());
        System.out.println("Ödül: " + this.getMonster().getAward());
    }

    public void oneMonsterStats() {
        System.out.println("\n" + this.getMonster().getName() + " Değerleri\n--------------------");
        System.out.println("Sağlık: " + this.getMonster().getHealth());
        System.out.println("Hasar: " + this.getMonster().getDamage());
        System.out.println("Ödül: " + this.getMonster().getAward());
    }

    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " Canavarın canı : " + this.getMonster().getHealth());
        System.out.println();
    }


    public boolean combat(int maxMonster) {
        Random random = new Random();


        for (int i = 1; i <= maxMonster; i++) {
            this.getMonster().setHealth(this.getMonster().getDefaultHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.print("'S'aldır veya 'K'aç --->>> ");
                String selectCombat = scanner.nextLine().toUpperCase();
                if (selectCombat.equals("S")) {
                    int firstAttach = random.nextInt(2);
                    System.out.println("Saldırı başladı");
                    if (firstAttach == 1){
                        System.out.println("İlk darbeyi siz vurdunuz...");
                        this.monster.setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println();
                            int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (monsterDamage < 0) {
                                monsterDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                            afterHit();
                        }
                    }else {
                        System.out.println("ilk Saldırı canavar tarafından geçekleşti...");
                        afterHit();
                        if (this.getPlayer().getHealth() > 0) {
                            System.out.println();
                            this.monster.setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                            int playerDamage =this.getPlayer().getInventory().getArmor().getBlock()- this.getMonster().getDamage();
                            if (playerDamage < 0) {
                                playerDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - playerDamage);
                            afterHit();
                        }else {return false;}
                    }

                } else return false;
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Bravooo! " + this.getMonster().getName() + " canavarını yendiniz.");
                System.out.println(this.getMonster().getAward() + " para kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward());
                System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }

}
