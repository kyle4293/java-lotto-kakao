package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottosTest {
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 5})
	void generate_returnsExpectedCount(int count) {
		Lottos lottos = Lottos.generate(count);

		assertThat(lottos.lottos()).hasSize(count);
	}

	@Test
	void generate_throwsForNonPositiveCount() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Lottos.generate(0));
	}
}
