package day1.fib;

import java.util.ArrayList;
import java.util.List;

/*
Link to exercise: https://docs.google.com/document/d/13f18buNu2WgTocikJ2Quj6TaetkujWouRCaibq2VCu8/edit
*/

public class FibonacciTask extends Thread 
{
    private long tal;
    private List<FibonacciObserver> observers = new ArrayList();

    public void registerFibonacciObserver(FibonacciObserver o) 
    {
        observers.add(o);
    }

    public FibonacciTask(long n) 
    {
        this.tal = n;
    }

    @Override
    public void run() {
        //Call the Fibonacci method from here
        fib(tal);
        //long tal = ......
        
        for (FibonacciObserver observer : observers) {
            observer.dataReady(tal);
        }
    }
    
    private long fib(long n)
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
