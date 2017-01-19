import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mingqian on 2017/1/18.
 */
public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-mybatis.xml"});
        UserMapper bean = context.getBean(UserMapper.class);

        bean.deleteByPrimaryKey(51L);
    }
}
