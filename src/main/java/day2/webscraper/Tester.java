package day2.webscraper;

/*
Exercise 1:
a)
Run the main method in the Tester class, and make sure you understand conceptually what happens. 
Especially you should note that these lines : tcn.run() probably takes a noticeable amount of time (why?)

b)
Refactor the TagCounter class to extend the Thread class. This should be very simple (why ?)

c)
Change the Tester class to not call run(), but start the three threads (what's the BIG difference?)
This will most likely mean that all your system.out’s will be empty or null (why?)
Fix the problem to show content in the system.out’s

Let's see whether we gained anything by executing the three calculations in parallel, or if we could have 
achieved the same result via sequential execution.

First lets see how many Kernels your system offers. Add this line to the beginning of your main():
System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());

Use the following skeleton to measure execution time for sequential execution (observe that 
we are calling the run() method, not start() to get sequential execution (one more time, make sure you understand the BIG difference).:

long start = System.nanoTime();
tc1.run();
tc2.run();
tc3.run(); 
…
long end = System.nanoTime();
System.out.println("Time Sequential: "+(end-start));

Now use the same principle to measure execution time for parallel execution (don't get the end time before all threads has stopped)

Explain the results

Link to exercise: https://docs.google.com/document/d/1wfhJVtA_1-UkiTVNk1Bu6JVx8Jfd2MT614t39RjW3Fo/edit
*/
public class Tester {
  
  public static void main(String[] args) throws InterruptedException 
  {
    System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
  
    TagCounter tc1 = new TagCounter("http://www.fck.dk");
    Thread t1 = new Thread(tc1);
    
    TagCounter tc2= new TagCounter("http://www.google.com");
    Thread t2 = new Thread(tc2);
    
    TagCounter tc3= new TagCounter("http://politiken.dk/");
    Thread t3 = new Thread(tc3);
    
    long start = System.nanoTime();
//    tc1.run();
//    tc2.run();
//    tc3.run();


    t1.start();
    t2.start();
    t3.start();

    t1.join(); //<-- join means the thread ends
    t2.join();
    t3.join();
    
    System.out.println("Title: "+tc1.getTitle());
    System.out.println("Div's: "+tc1.getDivCount());
    System.out.println("Body's: "+tc1.getBodyCount());         
    
    System.out.println("Title: "+tc2.getTitle());
    System.out.println("Div's: "+tc2.getDivCount());
    System.out.println("Body's: "+tc2.getBodyCount());   
    
    System.out.println("Title: "+tc3.getTitle());
    System.out.println("Div's: "+tc3.getDivCount());
    System.out.println("Body's: "+tc3.getBodyCount());    
    
    long end = System.nanoTime();
//    System.out.println("Time sequential " + (end - start));
    System.out.println("Time parallel " + (end - start));
  }
  /*
  Exercise 1
  a) The idea is that the program goes to 3 different sites and grabs some info
  tc.run takes 2.775seconds, not long?? But an answer could be it is because it is sequential
  
  b) Once you extend, you have to override the run method, because the parent class Thread has its own run
  
  c) The reason why they are null because the threads are looking for the info, but is not gathering the info
  in time enough for the system out --- altså trådene kører, men når ikke at hente infoen ud
  A solution would be to use the join method
  
  d) 
  */
}
