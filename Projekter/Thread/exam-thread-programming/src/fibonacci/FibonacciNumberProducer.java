package fibonacci;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author xu
 */
public class FibonacciNumberProducer implements Runnable{

    protected BlockingQueue<Long> numbersProduced; // interface <E> extends Queue
    protected BlockingQueue<Long> numbersConsumer; // støtter opreations der sørger for at vente køen har noget når der skal hentes
    private static volatile boolean ready;
    
    public FibonacciNumberProducer(BlockingQueue<Long> numbersProduced, BlockingQueue<Long> numbersConsumer) {
        this.numbersProduced = numbersProduced;
        this.numbersConsumer = numbersConsumer;
    }
    
    private synchronized long fib(long n) {
    if ((n == 0) || (n == 1)) {
      return n;
    } else {
      return fib(n - 1) + fib(n - 2);
    }
  }
    
    @Override
    public void run() {
        while(numbersProduced.size() > 0){ // hvis der er noget i producer køen
            try{
                long firstNumber  = numbersProduced.poll(); // poll tager og fjerner 1. af køen og returneres, ved godt vi returnerer et tal
                System.out.println("Taget fra Producer: " + firstNumber);
                long fibNumber = fib(firstNumber);
                System.out.println("FibNumber: ("+ fibNumber+ ")");
                numbersConsumer.put(fibNumber); // vi tager talen og sætter ind i consumer køen
                Thread.sleep(2000);
            }
            catch(InterruptedException ex){
                System.out.println("Fejl i producer!");
                ex.printStackTrace();
            }
        }
    }
    
}
