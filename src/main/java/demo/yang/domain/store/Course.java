package demo.yang.domain.store;

import lombok.Data;

import java.util.Date;

@Data
public class Course {
    private String id;
    private String name;
    private String remark;
    private String state;//是否显示
    private String deptId;
    private Date createTime;

}
