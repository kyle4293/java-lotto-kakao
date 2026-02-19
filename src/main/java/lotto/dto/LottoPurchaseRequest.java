package lotto.dto;

import static lotto.domain.LottoPolicy.*;

import java.util.List;
import java.util.Objects;

public record LottoPurchaseRequest(int amount, int manualCount, List<List<Integer>> manualNumbers) {

	public void validate() {
		validateAmount();
		validateManualCount();
		validateManualNumbers();
	}

	private void validateAmount() {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException(String.format("Amount must be at least %s", LOTTO_PRICE));
		}
	}

	private void validateManualCount() {
		if (manualCount < 0) {
			throw new IllegalArgumentException("Manual count cannot be negative.");
		}
		int totalCount = amount / LOTTO_PRICE;
		if (manualCount > totalCount) {
			throw new IllegalArgumentException("Manual count exceeds purchasable count.");
		}
	}

	private void validateManualNumbers() {
		if (manualNumbers == null) {
			throw new IllegalArgumentException("Manual numbers cannot be null.");
		}
	
		if (manualNumbers.stream().anyMatch(Objects::isNull)) {
			throw new IllegalArgumentException("Manual numbers cannot contain null.");
		}

		if (manualNumbers.size() != manualCount) {
			throw new IllegalArgumentException("Manual numbers count does not match manual count.");
		}
	}
}
