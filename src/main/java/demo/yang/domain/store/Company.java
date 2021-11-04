package demo.yang.domain.store;

import lombok.Data;

import java.util.Date;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-24 15:03
 **/
@Data
public class Company {
    private String id;
    private String name;
    private Date expirationDate;
    private String address;
    private String licenseId;
    private String representative;
    private String phone;
    private String companySize;
    private String industry;
    private String remarks;
    private Integer state;
    private String city;

}
