package lotto.validation;

import static lotto.domain.LottoPolicy.*;

import java.util.List;
import java.util.Objects;

public final class PurchaseValidator {
	private PurchaseValidator() {
	}

	public static void validateAmount(int amount) {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException(String.format("구입 금액은 %d원 이상이어야 합니다.", LOTTO_PRICE));
		}
	}

	public static void validateManualCount(int amount, int manualCount) {
		if (manualCount < 0) {
			throw new IllegalArgumentException("수동 구매 수량은 0 이상이어야 합니다.");
		}
		int totalCount = amount / LOTTO_PRICE;
		if (manualCount > totalCount) {
			throw new IllegalArgumentException("수동 구매 수량이 구매 가능한 수량을 초과했습니다.");
		}
	}

	public static void validateManualNumbersCount(int manualCount, List<List<Integer>> manualNumbers) {
		if (manualNumbers == null) {
			throw new IllegalArgumentException("수동 번호는 null일 수 없습니다.");
		}
		if (manualNumbers.stream().anyMatch(Objects::isNull)) {
			throw new IllegalArgumentException("수동 번호에 null이 포함될 수 없습니다.");
		}
		if (manualNumbers.size() != manualCount) {
			throw new IllegalArgumentException("수동 번호 개수가 수동 구매 수량과 일치하지 않습니다.");
		}
	}
}
