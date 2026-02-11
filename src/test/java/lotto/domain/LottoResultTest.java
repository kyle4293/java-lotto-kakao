package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
    @Test
    void of_returnsExpectedResult() {
        assertThat(LottoResult.of(3, false)).contains(LottoResult.THREE_MATCH);
        assertThat(LottoResult.of(4, false)).contains(LottoResult.FOUR_MATCH);
        assertThat(LottoResult.of(5, false)).contains(LottoResult.FIVE_MATCH);
        assertThat(LottoResult.of(5, true)).contains(LottoResult.FIVE_MATCH_WITH_BONUS);
        assertThat(LottoResult.of(6, false)).contains(LottoResult.SIX_MATCH);
        assertThat(LottoResult.of(2, false)).isEmpty();
    }
}
