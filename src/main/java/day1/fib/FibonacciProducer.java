package day1.fib;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author malik
 */
public class FibonacciProducer implements Runnable 
{ 
    BlockingQueue<Integer> input;
    BlockingQueue<Integer> output;
    Document doc;

    public FibonacciProducer(BlockingQueue<Integer> input, BlockingQueue<Integer> output) 
    {
        this.input = input;
        this.output = output;
    }
    
    
    
    @Override
    public void run() 
    {
        boolean moreNumbersToFetch = true;
        
        while(moreNumbersToFetch)
        {
            int numb = input.poll();
            
            if(numb == 0)
            {
                moreNumbersToFetch = false;
            }
            else
            {
                output.add(fib(numb));
            }
        }
    }
    
    private int fib(int n)
    {
        if((n == 0) || (n == 1))
        {
            return n;
        }
        else
        {
            return fib(n - 1) + fib(n - 2);
        }
    }
}