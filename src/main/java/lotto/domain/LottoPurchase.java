package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

@Getter
public class LottoPurchase {
	private final List<Lotto> lottos;
	private final int manualCount;
	private final int autoCount;
	private final int amount;

	public LottoPurchase(List<Lotto> lottos, int manualCount, int autoCount, int amount) {
		this.lottos = Collections.unmodifiableList(new ArrayList<>(lottos));
		this.manualCount = manualCount;
		this.autoCount = autoCount;
		this.amount = amount;
	}
}
