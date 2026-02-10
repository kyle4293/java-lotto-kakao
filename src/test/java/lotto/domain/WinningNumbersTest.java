package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumbersTest {
	@Test
	void of_createsWinningNumbers() {
		Lotto numbers = Lotto.of(List.of(1, 2, 3, 4, 5, 6));

		WinningNumbers winningNumbers = WinningNumbers.of(numbers, 7);

		assertThat(winningNumbers.getLotto()).isEqualTo(numbers);
		assertThat(winningNumbers.getBonusNumber()).isEqualTo(7);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46})
	void of_throwsForBonusNumberOutOfRange(int bonusNumber) {
		Lotto numbers = Lotto.of(List.of(1, 2, 3, 4, 5, 6));

		assertThatIllegalArgumentException()
			.isThrownBy(() -> WinningNumbers.of(numbers, bonusNumber));
	}

	@Test
	void of_throwsForDuplicateBonusNumber() {
		Lotto numbers = Lotto.of(List.of(1, 2, 3, 4, 5, 6));

		assertThatIllegalArgumentException()
			.isThrownBy(() -> WinningNumbers.of(numbers, 6));
	}

	@Test
	void of_throwsForNullNumbers() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> WinningNumbers.of(null, 7));
	}

}
