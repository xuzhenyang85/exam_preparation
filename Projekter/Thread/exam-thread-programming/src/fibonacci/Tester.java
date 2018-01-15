
package fibonacci;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author xu
 */
public class Tester {
    
    public static void main(String[] args) {
        // hører ind under concurrent pakke
        // class der implementere BlockingQueue
        // en array der baseret af blockingqueue, som tager FIFO, som har en række metoder
        ArrayBlockingQueue<Long> s1 = new ArrayBlockingQueue<>(20); 
        ArrayBlockingQueue<Long> s2 = new ArrayBlockingQueue<>(20); 
        
        s1.add(4l);
        s1.add(5l);
        s1.add(8l);
        s1.add(12l);
        s1.add(21l);
        s1.add(22l);
        s1.add(34l);
        s1.add(35l);
        s1.add(36l);
        s1.add(37l);
        s1.add(42l);
        
        // Executor er superinterface som har metoder kan producere Future for tracking process for en eller flere asynchronous tasks
        // Future er interface, er repræsenter resultat af en asynchronous opreation. Tjek om opreation er færdig, er wait eller recieve
        // ExecutorService er interface 
        // opret en tråd poll med ExcutorService 
        // newCachedThreadPool opret en tråd pool og genbrug tidligere bygget tråder og bruger dens ThreadFactory til opret ny tråd når der er behov
        ExecutorService pool = Executors.newCachedThreadPool();
        
        //opret og start fire producer tråder
        pool.execute(new FibonacciNumberProducer(s1, s2));
        pool.execute(new FibonacciNumberProducer(s1, s2));
        pool.execute(new FibonacciNumberProducer(s1, s2));
        pool.execute(new FibonacciNumberProducer(s1, s2));
        
        //opret og start consumer tråd
        pool.execute(new FibonacciNumberConsumer(s1, s2));
        
        //luk for ny tasks, men lad nuværende tasks udføres
        pool.shutdown();
    }
}
