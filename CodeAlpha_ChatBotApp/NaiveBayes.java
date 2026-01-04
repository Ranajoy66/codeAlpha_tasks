import java.util.*;

public class NaiveBayes {

    private Map<String, Map<String, Integer>> wordCounts = new HashMap<>();
    private Map<String, Integer> classCounts = new HashMap<>();
    private Set<String> vocabulary = new HashSet<>();
    private int totalSamples = 0;

    public void train(String category, String sentence) {
        classCounts.put(category, classCounts.getOrDefault(category, 0) + 1);
        totalSamples++;

        wordCounts.putIfAbsent(category, new HashMap<>());
        Map<String, Integer> words = wordCounts.get(category);

        for (String word : sentence.toLowerCase().split("\\s+")) {
            vocabulary.add(word);
            words.put(word, words.getOrDefault(word, 0) + 1);
        }
    }

    public String predict(String sentence) {
        double bestScore = Double.NEGATIVE_INFINITY;
        String bestClass = null;

        for (String category : classCounts.keySet()) {
            double score = Math.log(classCounts.get(category) / (double) totalSamples);

            for (String word : sentence.toLowerCase().split("\\s+")) {
                int wordFreq = wordCounts.get(category).getOrDefault(word, 0);
                score += Math.log((wordFreq + 1.0) / (vocabulary.size() + 1));
            }

            if (score > bestScore) {
                bestScore = score;
                bestClass = category;
            }
        }
        return bestClass;
    }
}
