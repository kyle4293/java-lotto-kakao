package lotto.domain;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
    @Test
    void from_returnsExpectedResult() {
        WinningNumbers winningNumbers = WinningNumbers.of(
                Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                7
        );

        assertThat(LottoResult.from(Lotto.of(List.of(1, 2, 3, 10, 11, 12)), winningNumbers))
                .contains(LottoResult.THREE_MATCH);
        assertThat(LottoResult.from(Lotto.of(List.of(1, 2, 3, 4, 10, 11)), winningNumbers))
                .contains(LottoResult.FOUR_MATCH);
        assertThat(LottoResult.from(Lotto.of(List.of(1, 2, 3, 4, 5, 11)), winningNumbers))
                .contains(LottoResult.FIVE_MATCH);
        assertThat(LottoResult.from(Lotto.of(List.of(1, 2, 3, 4, 5, 7)), winningNumbers))
                .contains(LottoResult.FIVE_MATCH_WITH_BONUS);
        assertThat(LottoResult.from(Lotto.of(List.of(1, 2, 3, 4, 5, 6)), winningNumbers))
                .contains(LottoResult.SIX_MATCH);
        assertThat(LottoResult.from(Lotto.of(List.of(8, 9, 10, 11, 12, 13)), winningNumbers))
                .isEmpty();
    }
}
