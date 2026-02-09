package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import lombok.Getter;

@Getter
public class Lotto {
    private static final int REQUIRED_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> copied = new ArrayList<>(numbers);
        this.numbers = Collections.unmodifiableList(sort(copied));
    }

    public static Lotto of(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public static Lotto generateRandom() {
        List<Integer> candidates = createCandidates();
        Collections.shuffle(candidates);
        List<Integer> picked = new ArrayList<>(candidates.subList(0, REQUIRED_SIZE));
        return Lotto.of(picked);
    }

    private static List<Integer> createCandidates() {
        List<Integer> candidates = new ArrayList<>();
        for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
            candidates.add(number);
        }
        return candidates;
    }

    private static void validate(List<Integer> numbers) {
        validateNotNull(numbers);
        validateSize(numbers);
        validateRange(numbers);
        validateDistinct(numbers);
    }

    private static void validateNotNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Numbers cannot be null.");
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException("Lotto numbers must be 6.");
        }
    }

    private static void validateRange(List<Integer> numbers) {
        boolean allInRange = numbers.stream().allMatch(Lotto::isInRange);
        if (!allInRange) {
            throw new IllegalArgumentException("Lotto numbers must be between 1 and 45.");
        }
    }

    private static void validateDistinct(List<Integer> numbers) {
        int uniqueCount = new HashSet<>(numbers).size();
        if (uniqueCount != numbers.size()) {
            throw new IllegalArgumentException("Lotto numbers must be unique.");
        }
    }

    private static boolean isInRange(int number) {
        return number >= MIN_NUMBER && number <= MAX_NUMBER;
    }

    private static List<Integer> sort(List<Integer> numbers) {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        return sorted;
    }
}
