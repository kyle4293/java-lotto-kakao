package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public record Lottos(List<Lotto> lottos) {
	public static Lottos generate(int count) {
		validateCount(count);
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(Lotto.generateRandom());
		}
		return new Lottos(lottos);
	}

	private static void validateCount(int count) {
		if (count < 1) {
			throw new IllegalArgumentException("Count must be at least 1.");
		}
	}
}
