package lotto.domain;

import java.util.List;
import java.util.Optional;

import lombok.Getter;

@Getter
public enum LottoResult {
	THREE_MATCH(3, false, 5_000L),
	FOUR_MATCH(4, false, 50_000L),
	FIVE_MATCH(5, false, 1_500_000L),
	FIVE_MATCH_WITH_BONUS(5, true, 30_000_000L),
	SIX_MATCH(6, false, 2_000_000_000L);

	private final int matchCount;
	private final boolean bonusMatch;
	private final long prize;

	LottoResult(int matchCount, boolean bonusMatch, long prize) {
		this.matchCount = matchCount;
		this.bonusMatch = bonusMatch;
		this.prize = prize;
	}

	public static Optional<LottoResult> from(Lotto lotto, WinningNumbers winningNumbers) {
		int matchedCount = countMatches(lotto.getNumbers(), winningNumbers.getLotto().getNumbers());
		boolean bonusMatched = lotto.getNumbers().contains(winningNumbers.getBonusNumber());
		return matchResult(matchedCount, bonusMatched);
	}

	private static int countMatches(List<LottoNumber> lottoNumbers, List<LottoNumber> winningNumbers) {
		long count = lottoNumbers.stream()
			.filter(winningNumbers::contains)
			.count();
		return Math.toIntExact(count);
	}

	private static Optional<LottoResult> matchResult(int matchCount, boolean bonusMatch) {
		if (matchCount == SIX_MATCH.matchCount) {
			return Optional.of(SIX_MATCH);
		}
		if (matchCount == FIVE_MATCH.matchCount && bonusMatch) {
			return Optional.of(FIVE_MATCH_WITH_BONUS);
		}
		if (matchCount == FIVE_MATCH.matchCount) {
			return Optional.of(FIVE_MATCH);
		}
		if (matchCount == FOUR_MATCH.matchCount) {
			return Optional.of(FOUR_MATCH);
		}
		if (matchCount == THREE_MATCH.matchCount) {
			return Optional.of(THREE_MATCH);
		}
		return Optional.empty();
	}
}
