package third.two.eight;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExcutor extends ThreadPoolExecutor {
    public TraceThreadPoolExcutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Runnable wrap(Runnable task, Exception clientTrace, String name) {
        return () -> {
            try {
                task.run();
            } catch (Exception e) {
//                java.lang.Exception: client stack trace
//                at third.two.eight.TraceThreadPoolExcutor.clientTrace(TraceThreadPoolExcutor.java:37)
//                at third.two.eight.TraceThreadPoolExcutor.execute(TraceThreadPoolExcutor.java:17)
//                at third.two.eight.DivTask.main(DivTask.java:49)
//                Exception in thread "pool-1-thread-1" java.lang.ArithmeticException: / by zero
//                at third.two.eight.DivTask.run(DivTask.java:16)
//                at third.two.eight.TraceThreadPoolExcutor.lambda$wrap$0(TraceThreadPoolExcutor.java:28)
//                at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
//                at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
//                at java.lang.Thread.run(Thread.java:748)
                clientTrace.printStackTrace();

//                java.lang.ArithmeticException: / by zero
//                at third.two.eight.DivTask.run(DivTask.java:16)
//                at third.two.eight.TraceThreadPoolExcutor.lambda$wrap$0(TraceThreadPoolExcutor.java:28)
//                at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
//                at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
//                at java.lang.Thread.run(Thread.java:748)
//                Exception in thread "pool-1-thread-1" java.lang.ArithmeticException: / by zero
//                at third.two.eight.DivTask.run(DivTask.java:16)
//                at third.two.eight.TraceThreadPoolExcutor.lambda$wrap$0(TraceThreadPoolExcutor.java:28)
//                at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
//                at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
//                at java.lang.Thread.run(Thread.java:748)
//                e.printStackTrace();
                throw e;
            }
        };
    }

    private Exception clientTrace() {
        return new Exception("client stack trace");
    }


}
