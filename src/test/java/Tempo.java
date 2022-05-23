import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Tempo {
    static Integer incrementation = 0;


    public static void main(String[] args) {

        for (int a = 0; a < 10_000; a++) {
            new Thread("thread: " + a) {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    myCustomIncrementation();
                }
            }.start();
        }

        System.out.println("size is: " + incrementation);
    }

    synchronized static void myCustomIncrementation() {
        synchronized (incrementation) {
            incrementation++;
            System.out.println("size is: " + incrementation);
        }
    }
}
