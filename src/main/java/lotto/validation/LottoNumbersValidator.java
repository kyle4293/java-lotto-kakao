package lotto.validation;

import static lotto.domain.LottoPolicy.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public final class LottoNumbersValidator {
	private LottoNumbersValidator() {
	}

	public static void validateNumbers(List<Integer> numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException("번호는 null일 수 없습니다.");
		}
		validateSize(numbers);
		validateElements(numbers);
		validateDistinct(numbers);
	}

	private static void validateSize(List<Integer> numbers) {
		if (numbers.size() != REQUIRED_LOTTO_SIZE) {
			throw new IllegalArgumentException(
				String.format("번호는 %d개여야 합니다.", REQUIRED_LOTTO_SIZE));
		}
	}

	private static void validateElements(List<Integer> numbers) {
		if (numbers.stream().anyMatch(Objects::isNull)) {
			throw new IllegalArgumentException("번호는 null일 수 없습니다.");
		}
		boolean outOfRange = numbers.stream()
			.anyMatch(LottoNumbersValidator::isOutOfRange);
		if (outOfRange) {
			throw new IllegalArgumentException(
				String.format("번호는 %d부터 %d까지여야 합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
		}
	}

	private static void validateDistinct(List<Integer> numbers) {
		if (new HashSet<>(numbers).size() != numbers.size()) {
			throw new IllegalArgumentException("번호는 중복될 수 없습니다.");
		}
	}

	public static boolean isOutOfRange(int number) {
		return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
	}
}
