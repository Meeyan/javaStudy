package com.std.video.geyiming.tec2;

/**
 * 加锁讲解(2) - 锁作用于实例方法，加锁依赖于具体的实例，除非实例为相同的，否则无法线程同步。
 *
 * @author zhaojy
 * @date 2017-04-19
 */
public class D4_AccountingSync implements Runnable {

    private static int count = 0;

    /**
     * 该方法为实例方法，锁作用域实例上。即：加锁依赖于具体的实例，除非实例为相同的，否则无法线程同步
     */
    public synchronized void increase() {
        count++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000000L; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        D4_AccountingSync instance = new D4_AccountingSync();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        // 注意：如下写法无法达到同步的目的，由于实例不同，则锁不同
        // Thread t3 = new Thread(new D4_AccountingSync());
        // Thread t4 = new Thread(new D4_AccountingSync());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(count);
    }
}
