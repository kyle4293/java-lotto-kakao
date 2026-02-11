package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {
	@DisplayName("로또를 지정한 개수만큼 생성해야 한다")
	@Test
	void generate_withCount_returnsExpectedLottoCount() {
		LottoGenerator generator = new LottoGenerator();

		List<Lotto> lottos = generator.generate(3);

		assertThat(lottos).hasSize(3);
	}

	@DisplayName("생성 개수가 1 미만이면 IllegalArgumentException이 발생해야 한다")
	@Test
	void generate_withNonPositiveCount_throwsIllegalArgumentException() {
		LottoGenerator generator = new LottoGenerator();

		assertThatIllegalArgumentException()
			.isThrownBy(() -> generator.generate(0));
	}

	@DisplayName("생성된 번호는 정렬되고 중복이 없으며 범위 내여야 한다")
	@Test
	void generate_withCount_returnsSortedUniqueNumbersWithinRange() {
		LottoGenerator generator = new LottoGenerator();

		Lotto lotto = generator.generate(1).getFirst();

		assertThat(lotto.getNumbers())
			.hasSize(6)
			.doesNotHaveDuplicates()
			.isSorted()
			.allMatch(number -> number.getValue() >= 1 && number.getValue() <= 45);
	}
}
