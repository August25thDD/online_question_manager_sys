package demo.yang.domain.store;

import lombok.Data;

import java.util.Date;

@Data
public class Catalog {
    private String id;
    private String name;
    private String remark;
    private String state;
    private Date createTime;
    private String courseId;

    private Course course;


}
