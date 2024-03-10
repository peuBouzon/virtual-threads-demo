package org.phgb;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadsDemo {

    private static final int N_THREADS = 500;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i=0; i<N_THREADS; i++) {
            var thread = Thread.ofVirtual().unstarted(work());
            threads.add(thread);
        }

        threads.forEach(Thread::start);
        for (var thread : threads) {
            thread.join();
        }
    }

    private static Runnable work() {
        return () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Done");
        };
    }
}