package lotto.dto;

import static lotto.domain.LottoPolicy.*;

import java.util.List;

public record WinningNumbersRequest(List<Integer> winningNumbers, int bonusNumber) {

	public void validate() {
		validateWinningNumbers();
		validateBonusNumber();
	}

	private void validateWinningNumbers() {
		if (winningNumbers == null) {
			throw new IllegalArgumentException("Winning numbers cannot be null.");
		}

		if (winningNumbers.size() != REQUIRED_LOTTO_SIZE) {
			throw new IllegalArgumentException(
				String.format("Winning numbers must be %d.", REQUIRED_LOTTO_SIZE));
		}
	}

	private void validateBonusNumber() {
		if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException(
				String.format("Bonus number must be between %d and %d.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
		}
	}
}
