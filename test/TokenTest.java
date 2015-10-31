import org.junit.Test;

import static junit.framework.Assert.assertFalse;

public class TokenTest {

    @Test
    public void testshouldGiveFalseIfTheTokenNumberIsNotSame() throws Exception {
        Token token = new Token();
        assertFalse(token.equals(new Token()));
    }
}