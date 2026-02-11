package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoGeneratorTest {
	@Test
	void generate_returnsExpectedCount() {
		LottoGenerator generator = new LottoGenerator();

		List<Lotto> lottos = generator.generate(3);

		assertThat(lottos).hasSize(3);
	}

	@Test
	void generate_throwsForNonPositiveCount() {
		LottoGenerator generator = new LottoGenerator();

		assertThatIllegalArgumentException()
			.isThrownBy(() -> generator.generate(0));
	}

	@Test
	void generate_returnsSortedUniqueNumbersWithinRange() {
		LottoGenerator generator = new LottoGenerator();

		Lotto lotto = generator.generate(1).getFirst();

		assertThat(lotto.getNumbers())
			.hasSize(6)
			.doesNotHaveDuplicates()
			.isSorted()
			.allMatch(number -> number.getValue() >= 1 && number.getValue() <= 45);
	}
}
