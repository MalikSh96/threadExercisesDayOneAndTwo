package day2.webscrapprodcon;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//This is the class template for the four Producer Threads P1-P4 in the exercise figure
public class DocumentProducer implements Runnable {

    BlockingQueue<String> urlsToUse;
    BlockingQueue<Document> producedDocuments;
    Document doc;

    public DocumentProducer(BlockingQueue<String> urlsToUse, BlockingQueue producedDocuments) {
        this.urlsToUse = urlsToUse;
        this.producedDocuments = producedDocuments;
    }

    @Override
    public void run() {
        synchronized (producedDocuments) {
            boolean moreUrlsToFetch = true;
            while (moreUrlsToFetch) {
                try {
                    String url = urlsToUse.poll(1, TimeUnit.SECONDS);//TODO: Use the right method on urlsToUse to set this value to either a string (with a url) or null

                    if (url == null) {
                        moreUrlsToFetch = false;
                    } else {

                        doc = Jsoup.connect(url).get();
                        //TODO Use the right method on producedDocuments to add this doc to the queue
                        producedDocuments.add(doc);

                    }

                } catch (Exception ex) {
                    Logger.getLogger(DocumentProducer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public BlockingQueue<String> getUrlsToUse() {
        return urlsToUse;
    }

    public BlockingQueue<Document> getProducedDocuments() {
        return producedDocuments;
    }

    public Document getDoc() {
        return doc;
    }
}
