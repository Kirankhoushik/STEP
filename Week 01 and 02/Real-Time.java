import java.util.*;

class Analytics {
    HashMap<String, Integer> pageViews = new HashMap<>();
    HashMap<String, Set<String>> uniqueUsers = new HashMap<>();
    HashMap<String, Integer> sources = new HashMap<>();

    void processEvent(String url, String userId, String source) {
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueUsers.putIfAbsent(url, new HashSet<>());
        uniqueUsers.get(url).add(userId);

        sources.put(source, sources.getOrDefault(source, 0) + 1);
    }

    List<String> topPages() {
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> pageViews.get(a) - pageViews.get(b)
        );

        for (String p : pageViews.keySet()) {
            pq.offer(p);
            if (pq.size() > 10) pq.poll();
        }

        return new ArrayList<>(pq);
    }
}
