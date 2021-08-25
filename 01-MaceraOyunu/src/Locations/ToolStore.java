package Locations;

import Gamers.Armor;
import Gamers.Player;
import Gamers.Weapon;

public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        boolean showMenu = true;
        while (showMenu){
            System.out.println("Mağazaya hoşgeldiniz.");
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış Yap");
            System.out.print("Seçininiz : ");
            int selectCase = Location.scanner.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Geçersiz değer girdiniz lütfen tekrar girin : ");
                selectCase = scanner.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    byArmor();
                    break;
                case 3:
                    System.out.println("Yine bekleriz");
                    showMenu = false;
                    return true;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("Silahları getirdiniz");
        for (Weapon w : Weapon.weapons()) {
            System.out.println("ID :" + w.getId() + " " + w.getName() + " <Para  " + w.getPrice() +
                    " , Hasar " + w.getDamage() + " >");
        }
        System.out.println("# 0 - Çıkış Yap");
    }

    public void printArmor() {
        System.out.println("Zırhları getirdiniz.");
        for (Armor a: Armor.armors()) {
            System.out.println("ID :" + a.getId() + " -  " + a.getName() + " <Para : " + a.getPrice() +
                    " Blok : " + a.getBlock() + " >");
        }
        System.out.println("# 0 - Çıkış Yap");
    }

    public void buyWeapon() {
        System.out.print("Bir silah seçiniz : ");
        int selectWeapon = scanner.nextInt();
        while (selectWeapon < 0 || selectWeapon > Weapon.weapons().length) {
            System.out.println("Geçersiz değer, tekrar giriniz  : ");
            selectWeapon = scanner.nextInt();
        }

        if(selectWeapon != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeapon);
            System.out.println();
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Paranız yetersiz ");
                } else {
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);

                    System.out.println("Kalan paranız : " + this.getPlayer().getMoney());

                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Seçilen silah : " + this.getPlayer().getInventory().getWeapon().getName());

                }
            }
        }
    }

    public void byArmor(){
        System.out.print("Bir zırh seçiniz : ");
        int selectArmorId = scanner.nextInt();
        while (selectArmorId<1 || selectArmorId > Armor.armors().length){
            System.out.println("Geçersiz değer tekrar deneyin : ");
            selectArmorId = scanner.nextInt();
        }


        if(selectArmorId != 0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorId);
            if(selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Zırh için paranız yetersiz ");
                    System.out.println("zırh ücreti : " + selectedArmor.getPrice());
                    System.out.println("Bakiye : " + this.getPlayer().getMoney());
                } else {
                    System.out.println(selectedArmor.getName() + " zırhınız satın aldınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                }
            }
        }
    }
}
