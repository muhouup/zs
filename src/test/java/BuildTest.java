import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuildTest {
    @Test
    void should_a_equal_to_b_when_b_is_copy_of_a_given_string_a() {
        // given
        String stringA = "True";

        // when
        String stringB = stringA.toLowerCase();

        // then
        Assertions.assertEquals("true", stringB);
    }
}
