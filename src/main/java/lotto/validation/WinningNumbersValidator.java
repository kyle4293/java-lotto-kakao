package lotto.validation;

import static lotto.domain.LottoPolicy.*;

import java.util.List;

public final class WinningNumbersValidator {
	private WinningNumbersValidator() {
	}

	public static void validateWinningNumbers(List<Integer> winningNumbers) {
		LottoNumbersValidator.validateNumbers(winningNumbers);
	}

	public static void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
		if (LottoNumbersValidator.isOutOfRange(bonusNumber)) {
			throw new IllegalArgumentException(
				String.format("보너스 번호는 %d부터 %d까지여야 합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
		}

		if (winningNumbers.contains(bonusNumber)) {
			throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
		}
	}
}
