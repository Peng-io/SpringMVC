import com.demo.Service.Impl.UserServiceImpl;
import com.demo.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserServiceImpl bean = context.getBean(UserServiceImpl.class);
        for (User user : bean.selectUser()) {
            System.out.println(user);
        }
    }
}
