package SaAssingment.example.demo.Test3;

import java.util.concurrent.*;

public class RateLimiter {
    private int maxRequests;
    private int tokens;
    private Semaphore semaphore;

    public RateLimiter(int maxRequests) {
        this.maxRequests = maxRequests;
        this.tokens = maxRequests;
        this.semaphore = new Semaphore(maxRequests);
    }

    public void acquire() throws InterruptedException {
        semaphore.acquire();
        synchronized (this) {
            tokens--;
        }
    }

    public void release() {
        synchronized (this) {
            tokens++;
        }
        semaphore.release();
    }
}
