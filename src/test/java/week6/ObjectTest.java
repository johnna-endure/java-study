package week6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ObjectTest {

    @Test
    public void testObject() {
        String a = "a";
        String b = "b";
        assertThat(a.equals(b)).isTrue();
    }
    
}
