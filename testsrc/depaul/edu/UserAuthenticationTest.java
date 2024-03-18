package depaul.edu;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserAuthenticationTest {

	@Test
    public void testAuthenticateUser_ValidCredentials() {
        // Arrange
        UserAuthentication userAuthentication = new UserAuthentication();
        String username = "nimi";
        String password = "1234";

        // Act
        boolean authenticated = userAuthentication.authenticateUser(username, password);

        // Assert
        assertTrue(authenticated);
    }

    @Test
    public void testAuthenticateUser_InvalidCredentials() {
        // Arrange
        UserAuthentication userAuthentication = new UserAuthentication();
        String username = "invalidUser";
        String password = "invalidPassword";

        // Act
        boolean authenticated = userAuthentication.authenticateUser(username, password);

        // Assert
        assertFalse(authenticated);
    }

}
