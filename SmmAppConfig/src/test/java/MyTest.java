import com.demo.Service.Impl.UserServiceImpl;
import com.demo.config.SpringConfig;
import com.demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class MyTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void test() {
        for (User user : userService.selectUser()) {
            System.out.println(user);
        }
    }

}
