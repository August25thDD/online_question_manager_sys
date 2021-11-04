package demo.yang.domain.store;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QuestionItem {
    private String id;          	//ID
    private String questionId;  //题目ID
    private String content;     //选项内容
    private String picture;      //选项图片
    private String isRight;    //是否正确答案

}
