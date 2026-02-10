package lotto.domain;

import lombok.Getter;

@Getter
public class WinningNumbers {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;

	private final Lotto numbers;
	private final Integer bonusNumber;

	private WinningNumbers(Lotto numbers, Integer bonusNumber) {
		validate(numbers, bonusNumber);
		this.numbers = numbers;
		this.bonusNumber = bonusNumber;
	}

	public static WinningNumbers of(Lotto numbers, Integer bonusNumber) {
		return new WinningNumbers(numbers, bonusNumber);
	}

	private static void validate(Lotto numbers, Integer bonusNumber) {
		validateNotNull(numbers);
		validateBonusNotNull(bonusNumber);
		validateBonusRange(bonusNumber);
		validateBonusDistinct(numbers, bonusNumber);
	}

	private static void validateNotNull(Lotto numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException("Winning numbers cannot be null.");
		}
	}

	private static void validateBonusNotNull(Integer bonusNumber) {
		if (bonusNumber == null) {
			throw new IllegalArgumentException("Bonus number cannot be null.");
		}
	}

	private static void validateBonusRange(Integer bonusNumber) {
		boolean inRange = bonusNumber >= MIN_NUMBER && bonusNumber <= MAX_NUMBER;
		if (!inRange) {
			throw new IllegalArgumentException("Bonus number must be between 1 and 45.");
		}
	}

	private static void validateBonusDistinct(Lotto numbers, Integer bonusNumber) {
		if (numbers.getNumbers().contains(bonusNumber)) {
			throw new IllegalArgumentException("Bonus number must not match winning numbers.");
		}
	}
}
