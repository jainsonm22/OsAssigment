package SaAssingment.example.demo.Test1;

import java.util.*;

class Outcome {
    String name;
    int probability;

    Outcome(String name, int probability) {
        this.name = name;
        this.probability = probability;
    }
}

public class EventSimulation {
    private List<Outcome> outcomes;
    private Random random;

    public EventSimulation(List<Outcome> outcomes) {
        this.outcomes = outcomes;
        this.random = new Random();
    }

    public String generateOutcome() {
        int totalProbability = outcomes.stream().mapToInt(outcome -> outcome.probability).sum();
        int randomValue = random.nextInt(totalProbability);

        int cumulativeProbability = 0;
        for (Outcome outcome : outcomes) {
            cumulativeProbability += outcome.probability;
            if (randomValue < cumulativeProbability) {
                return outcome.name;
            }
        }

        // This should not be reached unless the probabilities are not properly defined
        throw new IllegalStateException("Invalid probabilities");
    }

    public Map<String, Integer> simulate(int numOccurrences) {
        Map<String, Integer> occurrenceCounts = new HashMap<>();
        for (Outcome outcome : outcomes) {
            occurrenceCounts.put(outcome.name, 0);
        }

        for (int i = 0; i < numOccurrences; i++) {
            String outcome = generateOutcome();
            occurrenceCounts.put(outcome, occurrenceCounts.get(outcome) + 1);
        }

        return occurrenceCounts;
    }

    public static void main(String[] args) {
        // Rolling of a six-faced biased dice example
        List<Outcome> diceOutcomes = Arrays.asList(
                new Outcome("1", 10),
                new Outcome("2", 30),
                new Outcome("3", 15),
                new Outcome("4", 15),
                new Outcome("5", 30),
                new Outcome("6", 0)
        );

        EventSimulation diceSimulation = new EventSimulation(diceOutcomes);
        Map<String, Integer> diceOccurrences = diceSimulation.simulate(1000);
        System.out.println("Dice occurrences: " + diceOccurrences);

        // Flipping of a coin example
        List<Outcome> coinOutcomes = Arrays.asList(
                new Outcome("Head", 35),
                new Outcome("Tail", 65)
        );

        EventSimulation coinSimulation = new EventSimulation(coinOutcomes);
        Map<String, Integer> coinOccurrences = coinSimulation.simulate(1000);
        System.out.println("Coin occurrences: " + coinOccurrences);
    }
}
