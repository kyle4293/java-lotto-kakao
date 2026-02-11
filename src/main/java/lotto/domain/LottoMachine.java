package lotto.domain;

import java.util.List;

public class LottoMachine {
	private static final int LOTTO_PRICE = 1_000;
	private final LottoGenerator generator;

	public LottoMachine() {
		this(new LottoGenerator());
	}

	public LottoMachine(LottoGenerator generator) {
		validateGenerator(generator);
		this.generator = generator;
	}

	public List<Lotto> issue(int amount) {
		validate(amount);
		int count = amount / LOTTO_PRICE;
		return generator.generate(count);
	}

	private void validate(int amount) {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException(String.format("Amount must be at least %s", LOTTO_PRICE));
		}
	}

	private void validateGenerator(LottoGenerator generator) {
		if (generator == null) {
			throw new IllegalArgumentException("Generator cannot be null.");
		}
	}
}
