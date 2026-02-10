package lotto.domain;

import lombok.Getter;

@Getter
public class WinningNumbers {
	private final Lotto lotto;
	private final LottoNumber bonusNumber;

	private WinningNumbers(Lotto lotto, LottoNumber bonusNumber) {
		validate(lotto, bonusNumber);
		this.lotto = lotto;
		this.bonusNumber = bonusNumber;
	}

	public static WinningNumbers of(Lotto numbers, LottoNumber bonusNumber) {
		return new WinningNumbers(numbers, bonusNumber);
	}

	private static void validate(Lotto numbers, LottoNumber bonusNumber) {
		validateNotNull(numbers);
		validateNotNull(bonusNumber);
		validateBonusDistinct(numbers, bonusNumber);
	}

	private static void validateNotNull(Lotto numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException("Winning numbers cannot be null.");
		}
	}

	private static void validateNotNull(LottoNumber bonusNumber) {
		if (bonusNumber == null) {
			throw new IllegalArgumentException("Bonus number cannot be null.");
		}
	}

	private static void validateBonusDistinct(Lotto numbers, LottoNumber bonusNumber) {
		if (numbers.getNumbers().contains(bonusNumber)) {
			throw new IllegalArgumentException("Bonus number must not match winning numbers.");
		}
	}
}
