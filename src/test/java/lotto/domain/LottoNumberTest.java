package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46})
	void constructor_throwsForOutFromRangeValue(int value) {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> LottoNumber.of(value));
	}
}
