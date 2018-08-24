package day1.fib;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author malik
 */
public class Main 
{
    public static void main(String[] args) throws InterruptedException 
    {
        ArrayBlockingQueue<Integer> input = new ArrayBlockingQueue(40);
        ArrayBlockingQueue<Integer> output = new ArrayBlockingQueue(40);
        ExecutorService executor = Executors.newCachedThreadPool();
        
        executor.execute(new FibonacciProducer(input, output));
        executor.execute(new FibonacciProducer(input, output));
        executor.execute(new FibonacciProducer(input, output));
        executor.execute(new FibonacciProducer(input, output));
        
        executor.execute(new FibonacciConsumer(output));
        
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        
        System.out.println("Closing Down");

    }
}
