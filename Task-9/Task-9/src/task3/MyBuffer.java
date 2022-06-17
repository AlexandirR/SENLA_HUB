package task3;

import java.util.ArrayList;
import java.util.List;

public class MyBuffer {

    private int size;
    private volatile List<Integer> buff;

    public MyBuffer(int size) {
        this.size = size;
        this.buff = new ArrayList<>();
    }
    public Integer deleteInt() throws SizeException {
        if(buff.size() > 0) {
            synchronized (this) {
                if(buff.size() > 0) {
                    //System.out.println(buff);
                    return buff.remove(buff.size() - 1);
                }
                else {
                    throw new SizeException();
                }
            }
        }
        else {
            throw new SizeException();
        }
    }

    public Integer addInt(int element) throws SizeException {
        if(buff.size() < size) {
            synchronized (this) {
                if(buff.size() < size) {
                    //System.out.println(buff);
                    buff.add(element);
                    return element;
                }
                else {
                    throw new SizeException();
                }
            }
        }
        else {
            throw new SizeException();
        }
    }
}
