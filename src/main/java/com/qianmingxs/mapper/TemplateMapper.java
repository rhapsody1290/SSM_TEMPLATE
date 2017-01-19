package com.qianmingxs.mapper;

import com.qianmingxs.domain.TemplateDomain;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by mingqian on 2017/1/18.
 */
public interface TemplateMapper extends Mapper<TemplateDomain> {

    /**
     * 关联查询
     */
    @Select("select * from tb_order a left join tb_user b on a.user_id = b.id where a.order_number = #{value}")
    public List<Map<String, Object>> selectOrderById(String orderNumber);
}
