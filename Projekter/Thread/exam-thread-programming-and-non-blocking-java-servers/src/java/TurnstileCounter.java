package java;

/**
 *
 * @author xu
 */
public class TurnstileCounter {

    // bruges til share data, blev kun oprettes en gang i stack
    static final int DELAY_VAL = 10000;
    int count = 0;
    
    public long getValue() {
        return count;
    }

    public synchronized void incr() {
//   If the program initially does "never" fail, replace the count line with the lines below
//    long n = count;
//    //Spend some time to force preemtion
//    for(long a=0; a<LockDemo.DELAY_VAL; a++);
//    n = n + 1;
//    count = n;

        count++;
    }
}
