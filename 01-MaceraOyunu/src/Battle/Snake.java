package Battle;

import java.util.Random;

public class Snake extends Monster{
     private static int randomDamage;

    public Snake() {

        super(4, "Yılan", getRandomSnakeDamage(), 12, 0);
    }

    public static int getRandomSnakeDamage() {
        Random random = new Random();
        System.out.println("Rastgele seçildi...");
        randomDamage = random.nextInt(3) + 3;
        return randomDamage;
    }


}
