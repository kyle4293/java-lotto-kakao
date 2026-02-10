package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoMachineTest {
	@ParameterizedTest
	@CsvSource({
		"1000, 1",
		"1500, 1",
		"2500, 2"
	})
	void issue_returnsExpectedCount(int amount, int expectedCount) {
		LottoMachine machine = new LottoMachine();

		Lottos lottos = machine.issue(amount);

		assertThat(lottos.lottos()).hasSize(expectedCount);
	}

	@Test
	void issue_throwsForAmountLessThanPrice() {
		LottoMachine machine = new LottoMachine();

		assertThatIllegalArgumentException()
			.isThrownBy(() -> machine.issue(999));
	}
}
