import java.util.*;

public class ChatBot {

    private NaiveBayes model = new NaiveBayes();
    private Map<String, String> responses = new HashMap<>();

    public ChatBot() {

        // Training data
        train("greeting", "hello hi hey");
        train("java", "what is java java language");
        train("swing", "java swing gui");
        train("learn", "how to learn java");
        train("bye", "bye goodbye");
        train("thanks", "thank you thanks");

        // Responses
        responses.put("greeting", "Hello! How can I help you?");
        responses.put("java", "Java is an object-oriented programming language.");
        responses.put("swing", "Java Swing is used to build desktop GUI applications.");
        responses.put("learn", "You can learn Java by practicing basics and building projects.");
        responses.put("bye", "Goodbye. Have a great day.");
        responses.put("thanks", "You are welcome.");
    }

    private void train(String intent, String data) {
        model.train(intent, data);
    }

    public String getResponse(String input) {
        String intent = model.predict(input);
        return responses.getOrDefault(intent,
                "I am still learning. Please ask something related to Java.");
    }
}
