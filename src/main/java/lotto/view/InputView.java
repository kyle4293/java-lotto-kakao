package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.validation.LottoNumbersValidator;
import lotto.validation.PurchaseValidator;
import lotto.validation.WinningNumbersValidator;

public class InputView {
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public int readPurchaseAmount() {
		while (true) {
			try {
				System.out.println("구입금액을 입력해 주세요.");
				int amount = parseInt(readLine());
				PurchaseValidator.validateAmount(amount);
				return amount;
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	public int readManualCount(int amount) {
		while (true) {
			try {
				System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
				int manualCount = parseInt(readLine());
				PurchaseValidator.validateManualCount(amount, manualCount);
				return manualCount;
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	public List<List<Integer>> readManualNumbers(int count) {
		if (count <= 0) {
			return List.of();
		}
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		List<List<Integer>> numbers = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			numbers.add(readManualNumbersLine());
		}
		PurchaseValidator.validateManualNumbersCount(count, numbers);
		return numbers;
	}

	public List<Integer> readWinningNumbers() {
		while (true) {
			try {
				System.out.println("지난 주 당첨 번호를 입력해 주세요.");
				List<Integer> numbers = parseNumbers(readLine());
				WinningNumbersValidator.validateWinningNumbers(numbers);
				return numbers;
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	public int readBonusNumber(List<Integer> winningNumbers) {
		while (true) {
			try {
				System.out.println("보너스 볼을 입력해 주세요.");
				int bonusNumber = parseInt(readLine());
				WinningNumbersValidator.validateBonusNumber(winningNumbers, bonusNumber);
				return bonusNumber;
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	private String readLine() {
		try {
			String line = reader.readLine();
			if (line == null) {
				throw new IllegalStateException("입력이 종료되었습니다.");
			}
			return line;
		} catch (IOException exception) {
			throw new IllegalStateException("입력을 읽을 수 없습니다.");
		}
	}

	private int parseInt(String input) {
		try {
			return Integer.parseInt(input.trim());
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
		}
	}

	private List<Integer> parseNumbers(String input) {
		return Arrays.stream(input.split(","))
			.map(String::trim)
			.map(this::validateToken)
			.map(this::parseInt)
			.collect(Collectors.toList());
	}

	private String validateToken(String token) {
		if (token.isEmpty()) {
			throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
		}
		if (token.chars().anyMatch(Character::isWhitespace)) {
			throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
		}
		return token;
	}

	private List<Integer> readManualNumbersLine() {
		while (true) {
			try {
				List<Integer> numbers = parseNumbers(readLine());
				LottoNumbersValidator.validateNumbers(numbers);
				return numbers;
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	private void printError(String message) {
		System.out.println(message);
	}
}
