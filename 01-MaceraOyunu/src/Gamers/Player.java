package Gamers;

import GameCharacters.Archer;
import GameCharacters.GameCharacter;
import GameCharacters.Knight;
import GameCharacters.Samurai;
import Locations.Location;
import Locations.SafeHouse;
import Locations.ToolStore;

import java.util.List;
import java.util.Scanner;

public class Player {
    private int defaultHealt;
    private int damage;
    private int health;
    private int money;
    private String name;
    private Inventory inventory;

    private final Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.inventory= new Inventory();
    }

    public int getDefaultHealt() {
        return defaultHealt;
    }

    public void setDefaultHealt(int defaultHealt) {
        this.defaultHealt = defaultHealt;
    }

    public  void selectChar(){
       GameCharacter[] charList = {new Samurai(),new Archer(),new Knight()};

       for(GameCharacter gameCharacter : charList){
           System.out.println(" Karakter : " + gameCharacter.getCharName() + "\t Hasar : " +
                   "" +gameCharacter.getDamage() + "\t Sağlık : "
                   +gameCharacter.getHealth() + "\t Para : "+gameCharacter.getMoney());
       }
        System.out.println("***********************************");
        System.out.println("1 - Samuari 2 - Okçu 3 - Şovalye ");
        System.out.println("***********************************");
        System.out.print("\nLütfen bir karakter seçin: ");
        int selectChar = scanner.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                System.out.println("Otomatik olarak Samuray oluşturuldu...");
                initPlayer(new Samurai());
        }
        System.out.println("\n"+ "Karakter : " + this.getName() + ", Hasar : " + this.getDamage() +
                ", Sağlık : "+this.getHealth() + ", Para : "+this.getMoney());

    }

    public void initPlayer(GameCharacter gameCharacter){
        this.setDamage(gameCharacter.getDamage());
        this.setHealth(gameCharacter.getHealth());
        this.setMoney(gameCharacter.getMoney());
        this.setName(gameCharacter.getCharName());
        this.setDefaultHealt(gameCharacter.getHealth());

    }
    public void printInfo(){
        System.out.println(
                "Silahınız : " + this.getInventory().getWeapon().getName() +
                 " , Zırh : " + this.getInventory().getArmor().getName() +
                 " , Bloklama : " + this.getInventory().getArmor().getBlock() +
                " , Hasarınız : " + this.getTotalDamage() +
                " , Sağlık : "+this.getHealth() + ", Para : "+this.getMoney()
        );
    }

    public Weapon getWeapon(){
        return this.getInventory().getWeapon();
    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {

        return damage;
    }

    public void setDamage(int damage) {

        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health <0){
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

}
