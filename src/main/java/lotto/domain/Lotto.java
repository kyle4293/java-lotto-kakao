package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import lombok.Getter;

@Getter
public class Lotto {
	private static final int REQUIRED_SIZE = 6;

	private final List<LottoNumber> numbers;

	private Lotto(List<LottoNumber> numbers) {
		validate(numbers);
		List<LottoNumber> copied = new ArrayList<>(numbers);
		this.numbers = Collections.unmodifiableList(sort(copied));
	}

	public static Lotto of(List<LottoNumber> numbers) {
		return new Lotto(numbers);
	}

	public static Lotto generateRandom() {
		List<LottoNumber> lottoNumberPool = LottoNumber.getPool();
		Collections.shuffle(lottoNumberPool);
		List<LottoNumber> picked = new ArrayList<>(lottoNumberPool.subList(0, REQUIRED_SIZE));
		return Lotto.of(picked);
	}

	private static void validate(List<LottoNumber> numbers) {
		validateNotNull(numbers);
		validateSize(numbers);
		validateDistinct(numbers);
	}

	private static void validateNotNull(List<LottoNumber> numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException("Numbers cannot be null.");
		}
		boolean hasNull = numbers.stream().anyMatch(number -> number == null);
		if (hasNull) {
			throw new IllegalArgumentException("Lotto numbers cannot be null.");
		}
	}

	private static void validateSize(List<LottoNumber> numbers) {
		if (numbers.size() != REQUIRED_SIZE) {
			throw new IllegalArgumentException(String.format("Lotto numbers must be %d.", REQUIRED_SIZE));
		}
	}

	private static void validateDistinct(List<LottoNumber> numbers) {
		int uniqueCount = new HashSet<>(numbers).size();
		if (uniqueCount != numbers.size()) {
			throw new IllegalArgumentException("Lotto numbers must be unique.");
		}
	}

	private static List<LottoNumber> sort(List<LottoNumber> numbers) {
		List<LottoNumber> sorted = new ArrayList<>(numbers);
		Collections.sort(sorted);
		return sorted;
	}
}
