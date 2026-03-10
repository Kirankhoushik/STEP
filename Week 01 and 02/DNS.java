import java.util.*;

class DNSCache {
    static class Entry {
        String ip;
        long expiry;

        Entry(String ip, long ttl) {
            this.ip = ip;
            this.expiry = System.currentTimeMillis() + ttl;
        }
    }

    HashMap<String, Entry> map = new HashMap<>();
    int hits = 0, misses = 0;

    String resolve(String domain) {
        Entry e = map.get(domain);
        long now = System.currentTimeMillis();

        if (e != null && e.expiry > now) {
            hits++;
            return e.ip;
        }

        misses++;
        String ip = "172.217.14.206";
        map.put(domain, new Entry(ip, 300000));
        return ip;
    }

    double hitRate() {
        return hits * 1.0 / (hits + misses);
    }
}