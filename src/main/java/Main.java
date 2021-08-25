import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        AtomicLong sum = new AtomicLong();

        List<Long> shop1 = Arrays.asList(100L, 100L, 100L);
        List<Long> shop2 = Arrays.asList(200L, 200L, 200L);
        List<Long> shop3 = Arrays.asList(300L, 300L, 300L);

        Thread thread1 = new Thread(null, () -> {
            shop1.stream().forEach(longAdder::add);
            sum.addAndGet(longAdder.sum());
        }, "Shop1"
        );

        Thread thread2 = new Thread(null, () -> {
            shop2.stream().forEach(longAdder::add);
            sum.addAndGet(longAdder.sum());
        }, "Shop2"
        );

        Thread thread3 = new Thread(null, () -> {
            shop3.stream().forEach(longAdder::add);
            sum.addAndGet(longAdder.sum());
        }, "Shop2"
        );

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(sum);
        System.out.println(longAdder.sum());
    }
}