import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<E> implements StackInterface{
    ArrayList<E> list;

    public ArrayListStack(){
        list = new ArrayList<E>();
    }

    public boolean empty(){
        return list.size() == 0;
    }

    public E peek(){
        if (!empty()){
            return list.get(0);
        }
        throw new EmptyStackException();
    }

    public E pop(){
        if (!empty()){
            E top = list.get(0);
            list.remove(0);
            return top;
        }
        throw new EmptyStackException();
    }

    public E push(Object obj){
        list.add(0,(E)obj);
        return list.get(0);
    }
}
