package demo.yang.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaseService<T> {

    /**
     * 添加
     * @param t
     * @return
     */
    void save(T t);

    /**
     * 删除
     * @param t
     * @return
     */
    void delete(T t);

    /**
     * 修改
     * @param t
     * @return
     */
    void update(T t);

    /**
     * 查询单个
     * @param id 查询的条件
     * @return 查询的结果————单个对象
     */
    T findById(String id);

    /**
     *查询全部的数据
     * @return 全部数据的列表对象
     */
    List<T> findAll();

    /**
     *分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo<T> findAll(int page, int size);

}
