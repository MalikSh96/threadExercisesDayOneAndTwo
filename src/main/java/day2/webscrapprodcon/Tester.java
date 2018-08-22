
package day2.webscrapprodcon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jsoup.nodes.Document;

/*
https://docs.google.com/document/d/1wfhJVtA_1-UkiTVNk1Bu6JVx8Jfd2MT614t39RjW3Fo/edit

Link to exercises^
*/

public class Tester {
  
  public static void main(String[] args) throws InterruptedException {

    //The list of URL's that must be processed. This is Q1 in the exercise figure
    ArrayBlockingQueue<String> urls = new ArrayBlockingQueue(40); //amount of urls allowed
    
    urls.add("http://www.fck.dk");
    urls.add("http://www.google.com");
    urls.add("http://politikken.dk");
    urls.add("https://cphbusiness.mrooms.net/");
    urls.add("https://www.fcbarcelona.com/");
    urls.add("https://brondby.com/");
    //TODO: Add some more URL's of your own choice

    //Holds the Documents produced by the producers. This is Q2 in the exercise figure
    ArrayBlockingQueue<Document> producedDocuments = new ArrayBlockingQueue(10);
   
    ExecutorService es = Executors.newCachedThreadPool();
    //Create and start the four Producers (P1-P4)
    es.execute(new DocumentProducer(urls, producedDocuments)); 
    es.execute(new DocumentProducer(urls, producedDocuments)); 
    es.execute(new DocumentProducer(urls, producedDocuments)); 
    es.execute(new DocumentProducer(urls, producedDocuments)); 
    
    //Create and start the single Consumer Thead (c1)
    es.execute(new DocumentConsumer(producedDocuments)); 
    
    es.shutdown();
    es.awaitTermination(5,TimeUnit.SECONDS);
    System.out.println("Closing Down");
    
  }
  /*
  d1) not the same order
  d2) Reason is the fetching from producer, p3 might fetch faster than p2 etc - Altså de 
  forskellige udhivnings processer kan tage længere tid end andre
  
  obs: use poll over take, because poll is "user friendly" where take will let you wait for a long time
  */
}
