package lotto.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

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

	public static Lotto from(List<Integer> values) {
		validateValues(values);
		List<LottoNumber> numbers = values.stream()
			.map(LottoNumber::of)
			.toList();
		return new Lotto(numbers);
	}

	public static Lotto generateRandom() {
		List<LottoNumber> lottoNumberPool = LottoNumber.getPool();
		Collections.shuffle(lottoNumberPool);
		List<LottoNumber> picked = new ArrayList<>(lottoNumberPool.subList(0, REQUIRED_SIZE));
		return Lotto.fromLottoNumbers(picked);
	}

	private static Lotto fromLottoNumbers(List<LottoNumber> numbers) {
		return new Lotto(numbers);
	}

	private static void validate(Collection<LottoNumber> numbers) {
		validateNotNull(numbers);
		validateSize(numbers);
		validateDistinct(numbers);
	}

	private static void validateNotNull(Collection<LottoNumber> numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException("Numbers cannot be null.");
		}
		boolean hasNull = numbers.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new IllegalArgumentException("Lotto numbers cannot be null.");
		}
	}

	private static void validateSize(Collection<LottoNumber> numbers) {
		if (numbers.size() != REQUIRED_SIZE) {
			throw new IllegalArgumentException(String.format("Lotto numbers must be %d.", REQUIRED_SIZE));
		}
	}

	private static void validateDistinct(Collection<LottoNumber> numbers) {
		int uniqueCount = new HashSet<>(numbers).size();
		if (uniqueCount != numbers.size()) {
			throw new IllegalArgumentException("Lotto numbers must be unique.");
		}
	}

	private static void validateValues(List<Integer> values) {
		if (values == null) {
			throw new IllegalArgumentException("Numbers cannot be null.");
		}
		boolean hasNull = values.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new IllegalArgumentException("Lotto numbers cannot be null.");
		}
	}

	private static List<LottoNumber> sort(List<LottoNumber> numbers) {
		List<LottoNumber> sorted = new ArrayList<>(numbers);
		Collections.sort(sorted);
		return sorted;
	}
}
