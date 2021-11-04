package demo.yang.domain.system;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String email;       //邮箱
    private String userName;    //姓名
    private String password;    //密码
    private Long state;         //状态
    private String gender;      //性别
    private String telephone;   //电话
    private Date birthday;      //出生年月
    private Date joinDate;      //入职时间
    private String deptId;      //部门id

    private Dept dept;//所属部门

}
