package day2.deadlock;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
a) Program never ends, perhaps a thread is stuck in a deadlock and waits for some ressorce to get released

b) Deadlock is detected, program still doesn't end

c) The resource classes NEVER released their locks when they were done, so they got stuck, 
which means each thread had to wait for each other

d) Problem lied in the resource classed, the finally block had: 
resource.releaseResourceNumbers();
resource.releaseResourceWords();
^This lead to the threads waiting for each other, the solutions is to move these lines to after they have been used
Forstået sådan at linjerne rykkes ind under de linjer som udfører handlingen, så når en bruger var færdig 
med f.eks at tilføje numre/ord i en array, så skulle de løslades lige efter


OBS, implemented own end method, because the program kept going
 */
public class Tester {

    public static void main(String[] args) 
    {
        try 
        {
            DeadLockDetector detector = new DeadLockDetector();
            detector.start();
            
            ResourceContainer resources = new ResourceContainer();
            ResourceUser1 t1 = new ResourceUser1(resources);
            ResourceUser2 t2 = new ResourceUser2(resources);
            t1.start();
            t2.start();
            
            t1.join();
            t2.join();
            detector.end();

            System.out.println("Done");
            System.out.println("Words produced: " + resources.getResourceWords().size());
            System.out.println("Numbers produced: " + resources.getResourceNumbers().size());
        } catch (InterruptedException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
