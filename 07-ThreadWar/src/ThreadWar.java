import java.util.ArrayList;

public class ThreadWar implements Runnable{
    private ArrayList<Integer> numbers =new ArrayList<>();
    private ArrayList<Integer> oddNumbers = new ArrayList<>();
    private ArrayList<Integer> evenNumbers = new ArrayList<>();
    private Object LOCK = new Object();
    private final int range = 2500;
    int count = 0;

    public ThreadWar(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {

        synchronized (LOCK){
            Integer i = count;
            for ( i.intValue(); i<= range*(count+1);i++){
                if (numbers.get(i)%2 == 0){
                    evenNumbers.add(numbers.get(i));
                }else {
                    oddNumbers.add(numbers.get(i));
                }
            }
            count++;
        }

    }
    public void print(){
        for (var o:oddNumbers){
            System.out.println("Tek : " +o);
        }
        for (var e:evenNumbers){
            System.out.println("Çift : " +e);
        }
    }
}
