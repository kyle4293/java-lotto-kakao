package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoNumbersTest {
	@Test
	void constructor_sortsNumbers() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(5, 1, 3, 2, 4, 6));

		assertThat(lottoNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
	}

	@Test
	void constructor_throwsForOutOfRangeNumbers() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

		assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoNumbers.of(numbers));
	}

	@Test
	void constructor_throwsForDuplicateNumbers() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

		assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoNumbers.of(numbers));
	}

	@Test
	void constructor_throwsForInvalidSize() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5);

		assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoNumbers.of(numbers));
	}

	@Test
	void generateRandom_returnsSortedUniqueNumbersWithinRange() {
		LottoNumbers lottoNumbers = LottoNumbers.generateRandom();

		assertThat(lottoNumbers.getNumbers())
			.hasSize(6)
			.doesNotHaveDuplicates()
			.isSorted()
			.allMatch(number -> number >= 1 && number <= 45);
	}
}
