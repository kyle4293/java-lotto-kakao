package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoTest {
	@Test
	void constructor_sortsNumbers() {
		Lotto lotto = Lotto.from(List.of(5, 1, 3, 2, 4, 6));

		assertThat(lotto.getNumbers())
			.extracting(LottoNumber::getValue)
			.containsExactly(1, 2, 3, 4, 5, 6);
	}

	@Test
	void constructor_throwsForDuplicateNumbers() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

		assertThatIllegalArgumentException()
			.isThrownBy(() -> Lotto.from(numbers));
	}

	@Test
	void constructor_throwsForInvalidSize() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5);

		assertThatIllegalArgumentException()
			.isThrownBy(() -> Lotto.from(numbers));
	}

}
