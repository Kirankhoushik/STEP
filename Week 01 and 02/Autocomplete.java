import java.util.*;

class Autocomplete {
    HashMap<String, Integer> freq = new HashMap<>();

    void addQuery(String q) {
        freq.put(q, freq.getOrDefault(q, 0) + 1);
    }

    List<String> search(String prefix) {
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> freq.get(a) - freq.get(b)
        );

        for (String q : freq.keySet()) {
            if (q.startsWith(prefix)) {
                pq.offer(q);
                if (pq.size() > 10) pq.poll();
            }
        }

        return new ArrayList<>(pq);
    }
}