package demo.yang.dao;

import java.util.List;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-27 19:54
 **/

/**
 * 实体映射的基类
 * @param <T>
 */
public interface BaseDao<T> {
    
    int save(T t);

    int delete(T t);

    int update(T t);

    T findById(String id);

    List<T> findAll();
}
