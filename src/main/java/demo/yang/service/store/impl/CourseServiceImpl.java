package demo.yang.service.store.impl;

import demo.yang.dao.store.CourseDao;
import demo.yang.domain.store.Course;
import demo.yang.service.BaseServiceImpl;
import demo.yang.service.store.CourseService;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.UUID;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-27 20:58
 **/

public class CourseServiceImpl extends BaseServiceImpl<Course, CourseDao> implements CourseService {
    @Override
    protected void proceedBean(Course course) {
        String uuid = UUID.randomUUID().toString();
        course.setId(uuid);
        course.setCreateTime(new Date());

    }

    @Override
    protected CourseDao createDao(SqlSession sqlSession) {
        return sqlSession.getMapper(CourseDao.class);
    }
}
