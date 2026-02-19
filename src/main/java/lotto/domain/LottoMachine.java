package lotto.domain;

import static lotto.domain.LottoPolicy.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
	public LottoPurchase issue(int amount, int manualCount, List<List<Integer>> manualNumbers) {
		int autoCount = amount / LOTTO_PRICE - manualCount;
		List<Lotto> lottos = new ArrayList<>(manualNumbers.stream().map(Lotto::from).toList());
		lottos.addAll(generate(autoCount));
		return new LottoPurchase(lottos, manualCount, autoCount, amount);
	}

	private List<Lotto> generate(int count) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(generateLotto());
		}
		return lottos;
	}

	private Lotto generateLotto() {
		List<LottoNumber> lottoNumberPool = LottoNumber.getPool();
		Collections.shuffle(lottoNumberPool);
		List<Integer> values = lottoNumberPool.subList(0, REQUIRED_LOTTO_SIZE)
			.stream()
			.map(LottoNumber::getValue)
			.toList();
		return Lotto.from(values);
	}
}
