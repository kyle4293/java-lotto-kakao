package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
	private static final int REQUIRED_SIZE = 6;

	public List<Lotto> generate(int count) {
		validateCount(count);
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(generateLotto());
		}
		return lottos;
	}

	private Lotto generateLotto() {
		List<LottoNumber> lottoNumberPool = LottoNumber.getPool();
		Collections.shuffle(lottoNumberPool);
		List<Integer> values = lottoNumberPool.subList(0, REQUIRED_SIZE)
			.stream()
			.map(LottoNumber::getValue)
			.toList();
		return Lotto.from(values);
	}

	private void validateCount(int count) {
		if (count < 1) {
			throw new IllegalArgumentException("Count must be at least 1.");
		}
	}
}
