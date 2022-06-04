package buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Buffer {
    private List<Integer> buffer = new ArrayList<>();
    private Random r = new Random();

    public synchronized int produce() throws InterruptedException {
        while(this.buffer.size() == 5){
            wait();
        }
        int valor = Math.abs(this.r.nextInt());
        this.buffer.add(valor);
        return valor;
    }

    public synchronized int consume() throws InterruptedException {
        while(this.buffer.size() == 0){
            wait();
        }
        int valor = this.buffer.remove(0);
        return valor;
    }
}
