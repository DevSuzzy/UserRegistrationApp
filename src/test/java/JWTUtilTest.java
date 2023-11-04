import com.Brilloconnetz.jwt.JWTUtil;
import com.Brilloconnetz.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JWTUtilTest {

    private static JWTUtil jwtUtil;

    @BeforeAll
    public static void setup() {
        jwtUtil = new JWTUtil();
    }

    @Test
    public void testGenerateAndVerifyJWT() {
        User user = new User();

        String jwt = jwtUtil.generateJWT(user);

        String verificationResult = jwtUtil.verifyJWT(jwt);

        assertEquals("verification pass", verificationResult);
    }

    @Test
    public void testInvalidJWT() {
        String invalidToken = "your-invalid-token";

        String verificationResult = jwtUtil.verifyJWT(invalidToken);

        assertEquals("verification fails", verificationResult);
    }
}
