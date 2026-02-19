package lotto.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoStatistics;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

@RequiredArgsConstructor
public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;
	private final LottoMachine lottoMachine;

	public static LottoController create() {
		return new LottoController(
			new InputView(),
			new OutputView(),
			new LottoMachine()
		);
	}

	public void run() {
		LottoPurchase purchase = issuePurchase();
		outputView.printLottos(purchase);

		LottoStatistics statistics = LottoStatistics.of(purchase, readWinningNumbers());
		outputView.printResult(statistics);
	}

	private LottoPurchase issuePurchase() {
		int amount = inputView.readPurchaseAmount();
		int manualCount = inputView.readManualCount(amount);
		List<List<Integer>> manualNumbers = inputView.readManualNumbers(manualCount);
		return lottoMachine.issue(amount, manualCount, manualNumbers);
	}

	private WinningNumbers readWinningNumbers() {
		List<Integer> winningNumbers = inputView.readWinningNumbers();
		int bonusNumber = inputView.readBonusNumber(winningNumbers);
		return WinningNumbers.of(Lotto.from(winningNumbers), LottoNumber.from(bonusNumber));
	}
}
