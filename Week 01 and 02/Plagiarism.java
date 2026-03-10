import java.util.*;

class PlagiarismDetector {
    HashMap<String, Set<String>> index = new HashMap<>();

    List<String> ngrams(String doc, int n) {
        String[] words = doc.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i + n <= words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < i + n; j++) sb.append(words[j]).append(" ");
            list.add(sb.toString().trim());
        }
        return list;
    }

    void addDocument(String id, String text) {
        for (String g : ngrams(text, 5)) {
            index.putIfAbsent(g, new HashSet<>());
            index.get(g).add(id);
        }
    }

    int similarity(String text) {
        int count = 0;
        for (String g : ngrams(text, 5)) {
            if (index.containsKey(g)) count++;
        }
        return count;
    }
}