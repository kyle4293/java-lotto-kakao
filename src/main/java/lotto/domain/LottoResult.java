package lotto.domain;

import java.util.List;

import lombok.Getter;

@Getter
public enum LottoResult {
	THREE_MATCH(3, false, 5000L),
	FOUR_MATCH(4, false, 50000L),
	FIVE_MATCH(5, false, 1500000L),
	FIVE_MATCH_WITH_BONUS(5, true, 30000000L),
	SIX_MATCH(6, false, 2000000000L),
	NO_MATCH(0, false, 0L);

	private final int matchCount;
	private final boolean bonusMatch;
	private final long prize;

	LottoResult(int matchCount, boolean bonusMatch, long prize) {
		this.matchCount = matchCount;
		this.bonusMatch = bonusMatch;
		this.prize = prize;
	}

	public static LottoResult from(Lotto lotto, WinningNumbers winningNumbers) {
		int matchedCount = countMatches(lotto.getNumbers(), winningNumbers.getLotto().getNumbers());
		boolean bonusMatched = lotto.getNumbers().contains(winningNumbers.getBonusNumber());
		return matchResult(matchedCount, bonusMatched);
	}

	private static int countMatches(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
		long count = lottoNumbers.stream()
			.filter(winningNumbers::contains)
			.count();
		return Math.toIntExact(count);
	}

	private static LottoResult matchResult(int matchCount, boolean bonusMatch) {
		if (matchCount == SIX_MATCH.matchCount) {
			return SIX_MATCH;
		}
		if (matchCount == FIVE_MATCH.matchCount && bonusMatch) {
			return FIVE_MATCH_WITH_BONUS;
		}
		if (matchCount == FIVE_MATCH.matchCount) {
			return FIVE_MATCH;
		}
		if (matchCount == FOUR_MATCH.matchCount) {
			return FOUR_MATCH;
		}
		if (matchCount == THREE_MATCH.matchCount) {
			return THREE_MATCH;
		}
		return NO_MATCH;
	}
}
