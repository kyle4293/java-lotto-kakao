package lotto.domain;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoTest {
    @Test
    void constructor_sortsNumbers() {
        Lotto lotto = Lotto.of(List.of(5, 1, 3, 2, 4, 6));

        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void constructor_throwsForOutOfRangeNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Lotto.of(numbers));
    }

    @Test
    void constructor_throwsForDuplicateNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Lotto.of(numbers));
    }

    @Test
    void constructor_throwsForInvalidSize() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

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
                .allMatch(number -> number >= 1 && number <= 45);
    }
}
