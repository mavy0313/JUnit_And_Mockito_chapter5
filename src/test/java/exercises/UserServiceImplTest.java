package exercises;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceImplTest {

    @Test
    public void userShouldGetNewPassword() throws Exception {
        UserDAO userDAO = mock(UserDAO.class); //Test spy - verify indirect output userDAO.updateUser()
        SecurityService securityService = mock(SecurityService.class); //Dummy needs only as an argument
        User user = mock(User.class); //Dummy needs only as an argument

        UserServiceImpl userService = new UserServiceImpl(userDAO, securityService);
        userService.assignPassword(user);

        verify(userDAO).updateUser(user);
    }
}
