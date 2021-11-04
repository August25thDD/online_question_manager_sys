package demo.yang.domain.store;

import lombok.Data;

import java.util.Date;

@Data
public class Question {
    private String id;			//题目ID

    private String companyId;	//所属企业
    private String catalogId;	//题目所属目录ID

    private String picture; //保存题干图片地址

    private String remark;		//题目简介

    private String subject;     //题干

    private String analysis;	//题目分析

    private String type;       	//题目类型  1:单选，2：多选，3：简答
    private String difficulty; 	//难易程度： 1极易 2容易 3普通  4困难  5极难

    private String isClassic; 	//是否经典面试题 0：否 1：是
    private String state;   	//题目状态  0：不可用  1：可用（只有审核通过的题目才可以设置）

    private String reviewStatus;//审核状态  -1 审核不通过  0 审核中   1 审核通过
    private Date createTime;

    private Company company;
    private Catalog catalog;


}
