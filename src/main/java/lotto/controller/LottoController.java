package lotto.controller;

import lombok.RequiredArgsConstructor;
import lotto.domain.Lotto;
import lotto.domain.LottoStatistics;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

@RequiredArgsConstructor
public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;

	public static LottoController create() {
		return new LottoController(
			new InputView(),
			new OutputView()
		);
	}

	public void run() {
		int amount = inputView.readPurchaseAmount();
		Lottos lottos = Lottos.generate(amount);
		outputView.printLottos(lottos);

		Lotto winningNumbers = inputView.readWinningNumbers();
		int bonusNumber = inputView.readBonusNumber();
		WinningNumbers winning = WinningNumbers.of(winningNumbers, bonusNumber);
		LottoStatistics statistics = LottoStatistics.of(lottos, winning);

		outputView.printStatistics(statistics);
		outputView.printProfitRate(statistics.getProfitRate(amount));
	}
}
