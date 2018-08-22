package day2.rndnumberprodcon;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RandomNumberProducer /*implements Runnable*/ implements Callable<Integer> {

    public static final int MAX_NUMBERS_TO_PRODUCE = 100;
    public static final int MAX_RANDOM = 100;
    Random random = new Random();

    ArrayBlockingQueue<Integer> numbersProduced;
//
//    public RandomNumberProducer(ArrayBlockingQueue<Integer> numbersProduced) {
//        this.numbersProduced = numbersProduced;
//    }

//    @Override
//    public void run() {
////    throw new UnsupportedOperationException("You nedd to complete the run() method for this to work");
//        //Todo: Produce MAX_NUMBERS_TO_PRODUCE of random numbers between 0 and MAX_RANDOM and
//        //      place the numbers in numbersProduced
//        for (int i = 0; i < MAX_NUMBERS_TO_PRODUCE; i++) {
//            int random = (int) ((Math.random() * MAX_RANDOM + 1));
//            try {
//                numbersProduced.put(random);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }


    @Override
    public Integer call() throws Exception 
    {
        return random.nextInt();
    }

    //By now, you should know how to produce random numbers, but if not, use/run this as a guide
//  public static void main(String[] args) {
//    int MAX_RAND = 2;
//    for (int i = 0; i < 10; i++) {
//      int random = (int) ((Math.random() * MAX_RAND+1));
//      System.out.println(random);
//    }
//  }
}
