package day1.fib;

import java.util.concurrent.BlockingQueue;
import org.jsoup.nodes.Document;

/**
 *
 * @author malik
 */
public class FibonacciConsumer implements Runnable
{
    private long sum = 0;
    private long number;
    BlockingQueue<Integer> producedNumbers;
    Integer sumOfNumbers;

    public FibonacciConsumer(BlockingQueue<Integer> producedNumbers) {
        this.producedNumbers = producedNumbers;
    }

    

    @Override
    public void run() 
    {
        boolean moreNumberssToConsume = true;
        while(moreNumberssToConsume)
        {
            sumOfNumbers = producedNumbers.poll();
            if(sumOfNumbers != null)
            {
                sum += number;
            }
            else
            {
                moreNumberssToConsume = false;
            }
        }
        System.out.println("Sum + number is " + sum);
//        System.out.println("Current number: " + number);
    }
}
