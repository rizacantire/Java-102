import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i<=10000;i++){
            numbers.add(i);
        }

        ThreadWar threadWar = new ThreadWar(numbers);
        Thread odd1 = new Thread(threadWar);
        Thread odd2 =new Thread(threadWar);
        Thread odd3 =new Thread(threadWar);
        Thread odd4 =new Thread(threadWar);

        odd1.start();
        odd1.join();
        odd2.start();
        odd2.join();
        odd3.start();
        odd3.join();
        odd4.start();
        odd4.join();
        threadWar.print();


    }
}
