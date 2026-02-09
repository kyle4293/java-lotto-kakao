package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottosTest {
	@ParameterizedTest
	@CsvSource({
		"1000, 1",
		"1500, 1",
		"2500, 2"
	})
	void fromAmount_returnsExpectedCount(int amount, int expectedCount) {
		Lottos lottos = Lottos.generate(amount);

		assertThat(lottos.lottos()).hasSize(expectedCount);
	}

	@Test
	void generate_throwsForAmountLessThanPrice() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Lottos.generate(999));
	}
}
