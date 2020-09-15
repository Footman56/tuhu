import com.sun.org.apache.bcel.internal.generic.NEW;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 内部类才实现了资源共享
 * 将线程作为内部类
 * @PackageName:PACKAGE_NAME
 * @ClassName: ThreadMain
 * @Description:
 * @author:裴立志
 * @date: 2020/9/1417:11
 */

public class ThreadMain {
    private int j = 150;
    private String name;
    
    public static void main(String[] args) {
        
        // 创建阻塞队列
        BlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(16);
        // 创建线程池
        /***
         * public ThreadPoolExecutor(int corePoolSize,
         *                               int maximumPoolSize,
         *                               long keepAliveTime,
         *                               TimeUnit unit,
         *                               BlockingQueue<Runnable> workQueue) {
         *         this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
         *              Executors.defaultThreadFactory(), defaultHandler);
         *     }
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10,
                                                                 20,
                                                                 0L,
                                                                 TimeUnit.SECONDS,
                                                                 arrayBlockingQueue);
        
        ThreadMain threadMain = new ThreadMain("threadName");
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                poolExecutor.execute(threadMain.new ThreadInnerA());
            }
            else {
                poolExecutor.execute(threadMain.new ThreadInnerB());
            }
            System.out.println("i = " + i);
            
        }
        
    }
    
    public ThreadMain(String name) {
        this.name = name;
    }
    
    private synchronized void inc() {
        System.out.println("线程A：" + name + " 执行了 j=" + j);
        j++;
    }
    
    private synchronized void dec() {
        System.out.println("线程B：" + name + " 执行了 j=" + j);
        j--;
    }
    
    class ThreadInnerA implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                inc();
            }
            
        }
    }
    
    class ThreadInnerB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                dec();
            }
        }
    }
    
}
