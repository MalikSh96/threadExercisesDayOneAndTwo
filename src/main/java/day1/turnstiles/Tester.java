package day1.turnstiles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
link to exercise: https://docs.google.com/document/d/1CK0kLPddFaIKsKSEL0q4Gwavf-CLcxrGL0JiKeRCUI8/edit

a) Answer has been less than 40.000, but we don't get the same result all time

b) Problem is in Turnstile.java, the run method, issue is all threads have access, 
which means when can get a result before another thread is done.
Forstået sådan at de tråde kan køre, hvor de forstyrre hinanden, så en tråd ikke kan nå at køre færdigt uforstyrret

c) We synchronized the run method in Turnstile.java on the object counter
 */
public class Tester 
{
    static final int NUMBER_OF_TURNSTILES = 40;
    static Turnstile[] turnStiles = new Turnstile[NUMBER_OF_TURNSTILES];

    public static void main(String[] args) throws InterruptedException 
    {   
        //This is the shared Counter used by all turnstilles
        TurnstileCounter sharedCounter = new TurnstileCounter();

        for (int i = 0; i < NUMBER_OF_TURNSTILES; i++) 
        {
            turnStiles[i] = new Turnstile(sharedCounter);
        }

        //This example uses a ThreadPool to handle threads
        /*
        newcachedThreadPool:
        Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
         */
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < NUMBER_OF_TURNSTILES; i++) 
        {
            es.execute(turnStiles[i]);
        }
        
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("All turnstiles are done");
        //Print the updated value
        System.out.println(sharedCounter.getValue());
    }
}
