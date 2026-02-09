package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public record Lottos(List<Lotto> lottos) {
	private static final int LOTTO_PRICE = 1000;

	public static Lottos generate(int amount) {
		validateAmount(amount);
		int count = amount / LOTTO_PRICE;
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(Lotto.generateRandom());
		}
		return new Lottos(lottos);
	}

	private static void validateAmount(int amount) {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException("Amount must be at least 1000.");
		}
	}
}
