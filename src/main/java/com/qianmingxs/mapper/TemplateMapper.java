package com.qianmingxs.mapper;

import com.qianmingxs.domain.TemplateDomain;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mingqian on 2017/1/18.
 */
public interface TemplateMapper {

    /**
     * 单表查询，返回的Map中key为数据库的字段，value为行中的值
     */
    @Select("select * from tb_user where id = #{id}")
    public Map<String, Object> selectUserByid(long id);

    @Select("select * from tb_user")
    public List<Map<String, Object>> selectAllUser();

    @Select("select * from tb_user where id = #{id}")
    public TemplateDomain selectUserDomainById(long id);

    @Insert("insert into tb_user(name) values(#{domain.name})")
    public void insertDomain(TemplateDomain domain);

    /**
     * 关联查询
     */
    @Select("select * from tb_order a left join tb_user b on a.user_id = b.id where a.order_number = #{value}")
    public List<Map<String, Object>> selectOrderById(String orderNumber);
}
