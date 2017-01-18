package cn.apeius.usermanage.domain;

import java.util.List;

/**
 * Created by Asus on 2016/10/7.
 */
public class EasyUIPage {
    /**
     * 封装EasyUIPage需要的数据
     */
    private Long total;

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    private List<?> rows;
}
