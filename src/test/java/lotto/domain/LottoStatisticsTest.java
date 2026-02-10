package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoStatisticsTest {
	@Test
	void of_countsEachResult() {
		WinningNumbers winningNumbers = WinningNumbers.of(
			Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
			7
		);
		Lottos lottos = new Lottos(List.of(
			Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
			Lotto.of(List.of(1, 2, 3, 4, 5, 7)),
			Lotto.of(List.of(1, 2, 3, 4, 5, 8)),
			Lotto.of(List.of(1, 2, 3, 4, 7, 10)),
			Lotto.of(List.of(1, 2, 3, 4, 9, 10)),
			Lotto.of(List.of(1, 2, 3, 9, 10, 11)),
			Lotto.of(List.of(8, 9, 10, 11, 12, 13))
		));

		LottoStatistics statistics = LottoStatistics.of(lottos, winningNumbers);

		assertThat(statistics.getCounts().get(LottoResult.SIX_MATCH)).isEqualTo(1);
		assertThat(statistics.getCounts().get(LottoResult.FIVE_MATCH_WITH_BONUS)).isEqualTo(1);
		assertThat(statistics.getCounts().get(LottoResult.FIVE_MATCH)).isEqualTo(1);
		assertThat(statistics.getCounts().get(LottoResult.FOUR_MATCH)).isEqualTo(2);
		assertThat(statistics.getCounts().get(LottoResult.THREE_MATCH)).isEqualTo(1);
	}

	@Test
	void of_calculatesTotalPrize() {
		WinningNumbers winningNumbers = WinningNumbers.of(
			Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
			7
		);
		Lottos lottos = new Lottos(List.of(
			Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
			Lotto.of(List.of(1, 2, 3, 4, 5, 7)),
			Lotto.of(List.of(1, 2, 3, 4, 9, 10)),
			Lotto.of(List.of(1, 2, 3, 9, 10, 11)),
			Lotto.of(List.of(8, 9, 10, 11, 12, 13))
		));

		LottoStatistics statistics = LottoStatistics.of(lottos, winningNumbers);

		assertThat(statistics.getTotalPrize()).isEqualTo(2_030_055_000L);
	}

	@Test
	void getProfitRate_calculatesBasedOnPurchaseAmount() {
		WinningNumbers winningNumbers = WinningNumbers.of(
			Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
			7
		);
		Lottos lottos = new Lottos(List.of(
			Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
			Lotto.of(List.of(1, 2, 3, 4, 5, 7)),
			Lotto.of(List.of(1, 2, 3, 4, 9, 10)),
			Lotto.of(List.of(1, 2, 3, 9, 10, 11)),
			Lotto.of(List.of(8, 9, 10, 11, 12, 13))
		));

		LottoStatistics statistics = LottoStatistics.of(lottos, winningNumbers);

		assertThat(statistics.getProfitRate(10_000)).isEqualTo(203_005.5);
	}
}
