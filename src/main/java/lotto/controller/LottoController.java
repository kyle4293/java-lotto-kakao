package lotto.controller;

import lombok.RequiredArgsConstructor;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoStatistics;
import lotto.domain.WinningNumbers;
import lotto.dto.LottoPurchaseRequest;
import lotto.dto.WinningNumbersRequest;
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
		LottoPurchaseRequest lottoPurchaseRequest = inputView.readPurchaseInput();
		LottoPurchase purchase = issuePurchase(lottoPurchaseRequest);
		outputView.printLottos(purchase);

		WinningNumbersRequest winningNumbersRequest = inputView.readWinningInput();
		LottoStatistics statistics = LottoStatistics.of(purchase, toWinningNumbers(winningNumbersRequest));
		outputView.printResult(statistics);
	}

	private LottoPurchase issuePurchase(LottoPurchaseRequest request) {
		request.validate();
		return lottoMachine.issue(request.amount(), request.manualCount(), request.manualNumbers());
	}

	private WinningNumbers toWinningNumbers(WinningNumbersRequest request) {
		request.validate();
		Lotto numbers = Lotto.from(request.winningNumbers());
		LottoNumber bonusNumber = LottoNumber.from(request.bonusNumber());
		return WinningNumbers.of(numbers, bonusNumber);
	}
}
