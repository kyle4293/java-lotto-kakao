package lotto.domain;

import lombok.Getter;

@Getter
public class WinningNumbers {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;

	private final Lotto lotto;
	private final int bonusNumber;

	private WinningNumbers(Lotto lotto, int bonusNumber) {
		validate(lotto, bonusNumber);
		this.lotto = lotto;
		this.bonusNumber = bonusNumber;
	}

	public static WinningNumbers of(Lotto numbers, int bonusNumber) {
		return new WinningNumbers(numbers, bonusNumber);
	}

	private static void validate(Lotto numbers, int bonusNumber) {
		validateNotNull(numbers);
		validateBonusRange(bonusNumber);
		validateBonusDistinct(numbers, bonusNumber);
	}

	private static void validateNotNull(Lotto numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException("Winning numbers cannot be null.");
		}
	}

	private static void validateBonusRange(int bonusNumber) {
		boolean inRange = bonusNumber >= MIN_NUMBER && bonusNumber <= MAX_NUMBER;
		if (!inRange) {
			throw new IllegalArgumentException("Bonus number must be between 1 and 45.");
		}
	}

	private static void validateBonusDistinct(Lotto numbers, int bonusNumber) {
		if (numbers.getNumbers().contains(bonusNumber)) {
			throw new IllegalArgumentException("Bonus number must not match winning numbers.");
		}
	}
}
