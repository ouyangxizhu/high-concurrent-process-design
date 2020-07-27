package third.two.eight;

import java.util.concurrent.*;

public class DivTask implements Runnable {
    int a;
    int b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re = a / b;
        System.out.println(re);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//        ThreadPoolExecutor executor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        System.out.println(executor.getActiveCount());
        for (int i = 0; i < 5; i++) {
            executor.submit(new DivTask(100, i));

//            Exception in thread "main" java.util.concurrent.ExecutionException: java.lang.ArithmeticException: / by zero
//            at java.util.concurrent.FutureTask.report(FutureTask.java:122)
//            at java.util.concurrent.FutureTask.get(FutureTask.java:192)
//            at java.main.third.two.eight.DivTask.main(DivTask.java:25)
//            Caused by: java.lang.ArithmeticException: / by zero
//            at java.main.third.two.eight.DivTask.run(DivTask.java:16)
//            at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
//            at java.util.concurrent.FutureTask.run(FutureTask.java:266)
//            at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
//            at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
//            at java.lang.Thread.run(Thread.java:748)
//            Future<?> submit = executor.submit(new DivTask(100, i));//只有异常没有结果
//            submit.get();

//            Exception in thread "pool-1-thread-1" java.lang.ArithmeticException: / by zero
//            at java.main.third.two.eight.DivTask.run(DivTask.java:16)
//            at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
//            at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
//            at java.lang.Thread.run(Thread.java:748)
//            100.0
//            25.0
//            33.0
//            50.0
//            executor.execute(new DivTask(100, i));//可以打印18行位置出错

        }
        System.out.println("--------");
        System.out.println("当前活跃线程数：" + executor.getActiveCount());
    }
}
