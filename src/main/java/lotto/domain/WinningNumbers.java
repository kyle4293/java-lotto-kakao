package lotto.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WinningNumbers {
    private final LottoNumbers numbers;
    private final int bonusNumber;
}
