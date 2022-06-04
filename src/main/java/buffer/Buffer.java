package buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Buffer {
    private List<Integer> buffer = new ArrayList<>();
    private Random r = new Random();

    public void produce() throws InterruptedException {
        while(true) {
            while (this.buffer.size() == 5) {
                synchronized (this) {
                    wait();
                }
            }
            synchronized (this) {
                int valor = Math.abs(this.r.nextInt());
                this.buffer.add(valor);
                System.out.println("O valor " + valor + " foi produzido e inserido no buffer.");
                notifyAll();
            }
            Thread.sleep((r.nextInt(5)+1)*1000);
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            while (this.buffer.size() == 0) {
                synchronized (this) {
                    wait();
                }
            }
            synchronized (this) {
                int valor = this.buffer.remove(0);
                System.out.println("O valor " + valor + " foi consumido e removido do buffer.");
                notifyAll();
            }
            Thread.sleep((r.nextInt(5)+1)*1000);
        }
    }

    public List<Integer> bufferState() throws InterruptedException {
        while(true) {
            synchronized (this) {
                System.out.println(this.buffer.size() + " elementos: " + this.buffer);
            }
            Thread.sleep((r.nextInt(5) + 1) * 1000);
        }
    }
}
