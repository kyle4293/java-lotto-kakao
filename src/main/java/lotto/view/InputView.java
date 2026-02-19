package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.LottoPurchaseRequest;
import lotto.dto.WinningNumbersRequest;

public class InputView {
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public LottoPurchaseRequest readPurchaseInput() {
		int amount = readPurchaseAmount();
		int manualCount = readManualCount();
		List<List<Integer>> manualNumbers = readManualNumbers(manualCount);
		return new LottoPurchaseRequest(amount, manualCount, manualNumbers);
	}

	public WinningNumbersRequest readWinningInput() {
		List<Integer> winningNumbers = readWinningNumbers();
		int bonusNumber = readBonusNumber();
		return new WinningNumbersRequest(winningNumbers, bonusNumber);
	}

	public int readPurchaseAmount() {
		System.out.println("구입금액을 입력해 주세요.");
		return parseInt(readLine());
	}

	public int readManualCount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return parseInt(readLine());
	}

	public List<List<Integer>> readManualNumbers(int count) {
		if (count <= 0) {
			return List.of();
		}
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		List<List<Integer>> numbers = new java.util.ArrayList<>();
		for (int i = 0; i < count; i++) {
			numbers.add(parseNumbers(readLine()));
		}
		return numbers;
	}

	public List<Integer> readWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return parseNumbers(readLine());
	}

	public int readBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return parseInt(readLine());
	}

	private String readLine() {
		try {
			return reader.readLine();
		} catch (IOException exception) {
			throw new IllegalStateException("Failed to read input.");
		}
	}

	private int parseInt(String input) {
		return Integer.parseInt(input.trim());
	}

	private List<Integer> parseNumbers(String input) {
		return Arrays.stream(input.split(","))
			.map(String::trim)
			.filter(value -> !value.isEmpty())
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}
}
