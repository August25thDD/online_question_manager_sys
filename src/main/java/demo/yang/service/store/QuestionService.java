package demo.yang.service.store;

import com.github.pagehelper.PageInfo;
import demo.yang.domain.store.Question;

import javax.servlet.ServletOutputStream;
import java.util.List;

public interface QuestionService {
    /**
     * 添加
     * @param question
     * @return
     */
    void save(Question question);

    /**
     * 删除
     * @param question
     * @return
     */
    void delete(Question question);

    /**
     * 修改
     * @param question
     * @return
     */
    void update(Question question);

    /**
     * 查询单个
     * @param id 查询的条件（id）
     * @return 查询的结果，单个对象
     */
    Question findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Question> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo findAll(int page, int size);

    /**
     * 下载题目报表的方法
     * @param os
     * @param templatePath
     */
    void downloadExcel(ServletOutputStream os, String templatePath);
}
