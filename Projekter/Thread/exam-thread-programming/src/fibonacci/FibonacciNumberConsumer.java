package fibonacci;

import static java.lang.Thread.sleep;
import java.util.concurrent.BlockingQueue;

/**
 *  
 * @author xu week 5
 */
public class FibonacciNumberConsumer implements Runnable // objektet implementer tråd run()
{
    //fordel: begræsnser størrelsen, typsik bruger i denne, og trådesikkert
    protected BlockingQueue<Long> numbersProducer;
    protected BlockingQueue<Long> numbersConsumer;
    private static volatile boolean ready;
        
    public FibonacciNumberConsumer(BlockingQueue<Long> numbersProducer, BlockingQueue<Long> numbersConsumer) 
    {
        this.numbersProducer = numbersProducer;
        this.numbersConsumer = numbersConsumer;
    }

    long totalSum = 0;
    
    @Override
    public void run() {
        while(numbersConsumer.size() > 0){ // hvis der er noget i consumer køen
            try
            {
                // take() henter og fjerne det first af køen og venter indtil et objekt er tilgændeligt
                // returner det første af køen
                System.out.println("Consumer array: "  + numbersConsumer);
                Long result = numbersConsumer.take(); 
                
                totalSum += result; // tilføjes i totalsum den variable
                System.out.println("Sum: " + totalSum);
                
                System.out.println("Consumer taget num: ("+ result+")");
                
                Thread.sleep(2000); // vent 1 sek og derefter tager næst iteration
            }
            catch(InterruptedException ex){ // take() kan risikere kaste exceptions ud
                System.out.println("Fejl i Consumer");
            }
        }
    }
    
    public long getTotalSum(){
        return totalSum;
    }
    
}
