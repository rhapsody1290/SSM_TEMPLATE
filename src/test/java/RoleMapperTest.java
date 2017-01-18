import cn.apeius.usermanage.domain.Resource;
import cn.apeius.usermanage.domain.Role;
import cn.apeius.usermanage.mapper.RoleMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Set;

/**
 * Created by Asus on 2016/10/19.
 */
public class RoleMapperTest {

    private RoleMapper mapper;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml","applicationContext-mybatis.xml","applicationContext-tx.xml"});
        mapper = ac.getBean(RoleMapper.class);
    }

    @Test
    public void testSelect(){
        List<Role> roles = mapper.select(null);

        for(Role r : roles){
            System.out.println(r);
        }
    }

    @Test
    public void testQueryRolesByUserId(){
        List<Role> roles = mapper.queryRolesByUserId(1L);
        for(Role r : roles)
            System.out.println(r);
    }

    @Test
    public void testQueryResourcesByUserId(){
        List<Resource> resources = mapper.queryResourcesByUserId(1L);
        System.out.println(resources);
    }

    @Test
    public void testQueryResourceByRoleId(){
        List<Resource> resources = mapper.queryResourceByRoleId(3L);
        System.out.println(resources);
    }

}
