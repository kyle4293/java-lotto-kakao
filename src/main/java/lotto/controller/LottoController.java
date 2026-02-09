package lotto.controller;

import lombok.RequiredArgsConstructor;
import lotto.service.LottoResultService;
import lotto.view.InputView;
import lotto.view.OutputView;

@RequiredArgsConstructor
public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;
	private final LottoResultService lottoResultService;

	public static LottoController create() {
		return new LottoController(
			new InputView(),
			new OutputView(),
			new LottoResultService()
		);
	}

	public void run() {
	}
}
