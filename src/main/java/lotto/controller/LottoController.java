package lotto.controller;

import lombok.RequiredArgsConstructor;
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
	}
}
