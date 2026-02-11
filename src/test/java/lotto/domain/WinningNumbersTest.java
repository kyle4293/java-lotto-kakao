package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class WinningNumbersTest {
	@Test
	void of_createsWinningNumbers() {
		Lotto numbers = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

		WinningNumbers winningNumbers = WinningNumbers.of(numbers, LottoNumber.from(7));

		assertThat(winningNumbers.getLotto()).isEqualTo(numbers);
		assertThat(winningNumbers.getBonusNumber()).isEqualTo(LottoNumber.from(7));
	}

	@Test
	void of_throwsForDuplicateBonusNumber() {
		Lotto numbers = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

		assertThatIllegalArgumentException()
			.isThrownBy(() -> WinningNumbers.of(numbers, LottoNumber.from(6)));
	}

	@Test
	void of_throwsForNullNumbers() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> WinningNumbers.of(null, LottoNumber.from(7)));
	}

	@Test
	void of_throwsForNullBonusNumber() {
		Lotto numbers = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

		assertThatIllegalArgumentException()
			.isThrownBy(() -> WinningNumbers.of(numbers, null));
	}
}
