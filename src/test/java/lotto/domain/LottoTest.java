package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoTest {
    @Test
    void constructor_sortsNumbers() {
        Lotto lotto = Lotto.of(numbers(5, 1, 3, 2, 4, 6));

        assertThat(lotto.getNumbers())
                .extracting(LottoNumber::getValue)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void constructor_throwsForDuplicateNumbers() {
        List<LottoNumber> numbers = numbers(1, 2, 3, 4, 5, 5);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Lotto.of(numbers));
    }

    @Test
    void constructor_throwsForInvalidSize() {
        List<LottoNumber> numbers = numbers(1, 2, 3, 4, 5);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Lotto.of(numbers));
    }

    @Test
    void generateRandom_returnsSortedUniqueNumbersWithinRange() {
        Lotto lotto = Lotto.generateRandom();

        assertThat(lotto.getNumbers())
                .hasSize(6)
                .doesNotHaveDuplicates()
                .isSorted()
                .allMatch(number -> number.getValue() >= 1 && number.getValue() <= 45);
    }

    private List<LottoNumber> numbers(int... values) {
        return IntStream.of(values)
                .mapToObj(LottoNumber::of)
                .toList();
    }
}
