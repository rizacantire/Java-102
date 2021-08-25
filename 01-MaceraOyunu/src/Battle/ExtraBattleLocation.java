package Battle;

import Gamers.Armor;
import Gamers.Player;
import Gamers.Weapon;
import Locations.Location;

import java.util.Random;

public class ExtraBattleLocation extends Location {
    private Monster monster;
    private int maxMonster;

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

    public ExtraBattleLocation(Player player, String name, Monster monster, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.maxMonster = maxMonster;
    }

    public int randomMonsterNumber() {
        Random random = new Random();
        return random.nextInt(this.getMaxMonster()) + 1;
    }

    public void printLocationDetail() {
        int monsterNumber = this.randomMonsterNumber();
        System.out.println("Bulunduğunuz konum : " + this.getName());
        System.out.println("Dikkat et. Buralar  " + monsterNumber + " tane " + this.getMonster().getName() + " canavarının mekanı.");
        System.out.println("Canavarım özellikleri ona göre hareket et");
        System.out.println("---------------------------------------");
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

    @Override
    public boolean onLocation() {

        int monsterNumber = this.randomMonsterNumber();
        System.out.println("**********************************\n");
        System.out.println("Ekstra savaş alanı iten kazanmaya ne dersin?");


        printLocationDetail();
        oneMonsterStats();

        System.out.println("<S> Savaş veya <K> Kaç : ");
        String selectSituation = scanner.nextLine();
        selectSituation = selectSituation.toUpperCase();
        if (selectSituation.equals("S")) {
            System.out.println("Ekstra Savaş seçildi.");

            if (combat(monsterNumber)) {
                System.out.println(this.getName() + "mekanında ki tüm yaratıklar yok edildi.");
                return true;
            }

            if (this.getPlayer().getHealth() <= 0) {
                System.out.println(this.getMonster().getName() + " yaratık tarafından öldürüldünüz.");
                return false;
            }

        }
        return true;
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
                    if (firstAttach == 1) {
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
                    } else {
                        System.out.println("ilk Saldırı canavar tarafından geçekleşti...");
                        afterHit();
                        if (this.getPlayer().getHealth() > 0) {
                            System.out.println();
                            this.monster.setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                            int playerDamage = this.getPlayer().getInventory().getArmor().getBlock() - this.getMonster().getDamage();
                            if (playerDamage < 0) {
                                playerDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - playerDamage);
                            afterHit();
                        } else {
                            return false;
                        }
                    }

                } else return false;
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Bravooo! " + this.getMonster().getName() + " canavarını yendiniz.");
                System.out.println(this.getMonster().getAward() + " para kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward());
                System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                this.getSnakeAvard();
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " Canavarın canı : " + this.getMonster().getHealth());
        System.out.println();
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

    public void getSnakeAvard(){
        Random rand = new Random();
        int possibility = rand.nextInt(101);

        if (possibility<=15){
            int weaponPossibility = rand.nextInt(101);
            System.out.println("Silah kazandınız : ");

            if (weaponPossibility <=20){
                System.out.println("Kazandığın Silah #Tüfek#");
                randomWeaponAward(2);
            }else if (weaponPossibility<=50){
                randomWeaponAward(1);
            }else {
                randomWeaponAward(0);
            }

        }else if (possibility<=30){
            System.out.println("Zırh kazandınız : ");
            int armorPossibiliity = rand.nextInt(101);
            if (armorPossibiliity <=15){
                this.randomArmorAward(3);
            }else if (armorPossibiliity <=50){
                this.randomArmorAward(2);
            }else {
                this.randomArmorAward(1);
            }
        }else if ((possibility<=55)){
            int moneyPossibility = rand.nextInt(101);
            System.out.println("Para kazandınız : ");

            if (moneyPossibility<=20){
                randomMoneyAward(10);
            }else if(moneyPossibility <=50){
                randomMoneyAward(10);
            }else {
                randomMoneyAward(1);
            }
        }else {
            System.out.println();
            System.out.println("--------Elin boş dönüyorsun-----------");
        }
    }
    public void randomWeaponAward(int id){
        Weapon[] weapons = Weapon.weapons();
        this.getPlayer().getInventory().setWeapon(weapons[id]);
        System.out.println("Gelen silah : " + weapons[id].getName());
    }
    public void randomArmorAward(int id){
        this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(id));
        System.out.println( Armor.getArmorObjById(id).getName() + "kazndınız");
    }
    public void randomMoneyAward(int id){
        this.getPlayer().setMoney(id+ this.getPlayer().getMoney());
        System.out.println(id + " para kazandınız...");
    }
}
