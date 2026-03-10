import java.util.*;

class RateLimiter {
    static class Bucket {
        int tokens;
        long lastRefill;
    }

    HashMap<String, Bucket> map = new HashMap<>();
    int limit = 1000;
    long window = 3600000;

    synchronized boolean allow(String client) {
        long now = System.currentTimeMillis();

        map.putIfAbsent(client, new Bucket());
        Bucket b = map.get(client);

        if (now - b.lastRefill > window) {
            b.tokens = limit;
            b.lastRefill = now;
        }

        if (b.tokens > 0) {
            b.tokens--;
            return true;
        }

        return false;
    }
}