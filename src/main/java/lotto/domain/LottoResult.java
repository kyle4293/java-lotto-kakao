package lotto.domain;

import lombok.Getter;

@Getter
public enum LottoResult {
    THREE_MATCH(3, false, 5000),
    FOUR_MATCH(4, false, 50000),
    FIVE_MATCH(5, false, 1500000),
    FIVE_MATCH_WITH_BONUS(5, true, 30000000),
    SIX_MATCH(6, false, 2000000000),
    NO_MATCH(0, false, 0);

    private final int matchCount;
    private final boolean bonusMatch;
    private final int prize;

    LottoResult(int matchCount, boolean bonusMatch, int prize) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }
}
