package lotto.domain;

public class LottoMachine {
	private static final int LOTTO_PRICE = 1_000;

	public Lottos issue(int amount) {
		validate(amount);
		int count = amount / LOTTO_PRICE;
		return Lottos.generate(count);
	}

	private void validate(int amount) {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException(String.format("Amount must be at least %s", LOTTO_PRICE));
		}
	}
}
