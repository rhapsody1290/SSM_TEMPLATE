import com.qianmingxs.domain.TemplateDomain;
import com.qianmingxs.mapper.TemplateMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 2016/10/19.
 */
public class TemplateTest {

    private TemplateMapper mapper;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml","applicationContext-mybatis.xml","applicationContext-tx.xml"});
        mapper = context.getBean(TemplateMapper.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSelectUserByid(){
        Map<String, Object> map = mapper.selectUserByid(1);
        for(Map.Entry<String, Object> entry : map.entrySet()){
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
    }

    @Test
    public void testSelectAllUser(){
        List<Map<String, Object>> maps = mapper.selectAllUser();
        for(Map<String, Object> map : maps){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testSelectUserDomainById(){
        TemplateDomain domain = mapper.selectUserDomainById(1);
        System.out.println(domain);
    }

    @Test
    public void testSelectOrderById(){
        List<Map<String, Object>> maps = mapper.selectOrderById("20140921001");
        for(Map<String, Object> map : maps){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testInsertDomain(){

    }
    /*
    @Test
    public void testSelectOne() throws Exception {
        //创建TemplateDomain对象，设置的属性作为查询的约束
        TemplateDomain TemplateDomain = new TemplateDomain();
        TemplateDomain.setId(1L);
        //使用selectOne必须保证结果唯一，如果结果太多则会报错
        System.out.println(mapper.selectOne(TemplateDomain));
    }

    @Test
    public void testSelect() throws Exception {
        TemplateDomain record = new TemplateDomain();
        //可以设置属性增加约束，如果没有约束可以直接传入null
        record.setPassword("000000");

        List<TemplateDomain> TemplateDomains = mapper.select(record);
        for(TemplateDomain TemplateDomain : TemplateDomains)
            System.out.println(TemplateDomain);
    }

    @Test
    public void testSelectCount() throws Exception {
        //查询符合要求的数据
        TemplateDomain record = new TemplateDomain();
        record.setPassword("000000");
        int count = mapper.selectCount(record);
        System.out.println(count);
    }

    @Test
    public void testSelectByPrimaryKey() throws Exception {
        //通过主键查询对象，参数表示主键的值
        System.out.println(mapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testInsert() throws Exception {
        //插入记录，未明确设置属性的值默认为null，不推荐！！！！
        TemplateDomain record = new TemplateDomain();
        record.setTemplateDomainName("踩雷");
        record.setName("bajie");
        record.setPassword("123456");
        record.setBirthday(new Date());

        mapper.insert(record);
    }

    @Test
    public void testInsertSelective() throws Exception {
        //与testInsert的区别：只添加设置值得字段，其余让数据库默认，推荐！！！
        TemplateDomain record = new TemplateDomain();
        record.setTemplateDomainName("李璇");
        record.setName("bajie");
        record.setPassword("123456");
        record.setBirthday(new Date());

        mapper.insert(record);
    }

    @Test
    public void testDelete() throws Exception {
        //删除符合条件的数据
        TemplateDomain record = new TemplateDomain();
        record.setTemplateDomainName("李璇");

        mapper.delete(record);
    }

    @Test
    public void testDeleteByPrimaryKey() throws Exception {
        //通过主键删除对象
        mapper.deleteByPrimaryKey(51L);
    }

    @Test
    public void testUpdateByPrimaryKey() throws Exception {
        //根据主键更新记录，未明确赋值的参数自动复制为null，不推荐！！
        TemplateDomain record = new TemplateDomain();
        record.setId(53L);
        record.setTemplateDomainName("踩雷");

        mapper.updateByPrimaryKey(record);
    }

    @Test
    public void testUpdateByPrimaryKeySelective() throws Exception {
        //只更新设置的值，推荐！！！！！！！
        TemplateDomain record = new TemplateDomain();
        record.setId(53L);
        record.setTemplateDomainName("踩雷");

        mapper.updateByPrimaryKeySelective(record);
    }


    @Test
    public void testExampleBasic(){
        //进行复杂查询
        Example example = new Example(TemplateDomain.class);
        Example.Criteria criteria = example.createCriteria();
        //greater or equal 年龄大于等于30
        criteria.andGreaterThanOrEqualTo("age",30);
        //添加and条件，密码为123456
        criteria.andEqualTo("password","123456");
        List<TemplateDomain> TemplateDomains = mapper.selectByExample(example);

        for(TemplateDomain u : TemplateDomains)
            System.out.println(u);
    }

    @Test
    //SELECT AGE,NAME,UPDATED,TemplateDomain_NAME TemplateDomainNAME,BIRTHDAY,ID,CREATED,SEX,PASSWORD FROM tb_TemplateDomain
    // WHERE ( AGE >= ? and NAME like ? ) or ( PASSWORD = ? and ID in(?,?) )
    public void testExampleOr(){
        //进行复杂查询：or，需要创建两个多个criteria
        Example example = new Example(TemplateDomain.class);
        Example.Criteria criteria = example.createCriteria();
        //年龄大于等于30
        criteria.andGreaterThanOrEqualTo("age",30);
        //模糊查找
        criteria.andLike("name","%xx%");

        Example.Criteria criteria12 = example.createCriteria();
        //添加and条件，密码为123456
        criteria12.andEqualTo("password","123456");
        List<Object> ids = new ArrayList<Object>();
        ids.add("1");
        ids.add("2");
        criteria12.andIn("id",ids);

        //多个criteria之间是or的关系
        example.or(criteria12);

        List<TemplateDomain> TemplateDomains = mapper.selectByExample(example);
        for(TemplateDomain u : TemplateDomains)
            System.out.println(u);
    }

    @Test
    public void testExampleSort(){
        Example example = new Example(TemplateDomain.class);
        example.setOrderByClause("age desc,id desc");
        List<TemplateDomain> TemplateDomains = mapper.selectByExample(example);

        for(TemplateDomain u : TemplateDomains){
            System.out.println(u);
        }
    }*/
}
