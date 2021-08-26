import java.util.Arrays;

public class MyList<T> implements IList<T> {
    private int count = 0;

    private static final int defautlCapacity =10;

    private Object[] list;

    public MyList() {
        list = new Object[defautlCapacity];
    }
    public MyList(int capacity) {
        list = new Object[capacity];
    }


    @Override
    public void add(T entity) {
        if (count == list.length){
            checkAdd();
        }
        list[count] = entity;
        count++;
    }

    public void checkAdd(){
        int newCapacity = list.length * 2;
        list = Arrays.copyOf(list,newCapacity);
    }

    @Override
    public T delete(int number) {
        Object item = list[number];
        int newArray = list.length - ( number + 1 ) ;
        System.arraycopy( list, number + 1, list, number, newArray ) ;
        count--;
        return (T) item;

    }
    public Object set(int t1, T t2){
        if (t1<=0 && t1 > list.length){
            return null;
        }
        return (list[t1] = t2);
    }

    @Override
    public int getCapacity() {
        return list.length;
    }
    @Override
    public String toString(){
        String to = new String();
        to +=('[');
        if(count!=0){
            for(int i = 0; i < count ;i++) {
                to += (list[i].toString());
                if(i<count-1){
                    to += (",");
                }
            }
        }
        to += (']');
        return to.toString();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public T get(int number) {
        if (number >= count || number<0){
            System.out.println("Hatalı indeks değeri");
        }

        return (T) list[number];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int indexOf(T entity) {
        int j = 0;
        for (var i : list){
            if (i == entity){
                return j;
            }else{
                j++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T entity) {
        int j = count;
        for (int i = count;i>=0;i--){
            if (list[i] == entity){
                return j;
            }else {
                j--;
            }
        }
        return -1;
    }

    @Override
    public T[] toArray() {

        Object array = Arrays.copyOf(list, count);
        return (T[]) array;
    }

    @Override
    public MyList<T> subList(int a, int b) {
        int items = b - a + 1;
        MyList<T> sublist = new MyList<>(items);
        for(int i = a; i <= b; i++) {
            sublist.add((T) this.list[i]);
        }
        return sublist;
    }

    @Override
    public boolean contains(T entity) {
        boolean isContain = false;
        for (var i : list){
            if (i == entity){
                return isContain=true;
            }
        }return isContain;
    }

    @Override
    public void clear() {
        list = new Object[0];
        count =0;
    }
}
