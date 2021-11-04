package demo.yang.service.system.impl;

import demo.yang.dao.system.UserDao;
import demo.yang.domain.system.User;
import demo.yang.service.BaseServiceImpl;
import demo.yang.service.system.UserService;
import demo.yang.utils.MD5Util;
import demo.yang.utils.MapperUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.UUID;


/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-28 10:46
 **/

public class UserServiceImpl extends BaseServiceImpl<User, UserDao> implements UserService {

    @Override
    protected void proceedBean(User user) {
        String uuid = UUID.randomUUID().toString();
        user.setId(uuid);

        String md5Pwd = MD5Util.md5(user.getPassword());
        user.setPassword(md5Pwd);

    }

    @Override
    protected UserDao createDao(SqlSession sqlSession) {
        return sqlSession.getMapper(UserDao.class);
    }

    @Override
    public void updateRole(String userId, String[] roleIds) {
        try {
            UserDao userDao = MapperUtils.getMapper(UserDao.class);
            userDao.deleteRole(userId);

            for (String roleId : roleIds) {
                if (roleId != null) {
                    userDao.updateRole(userId, roleId);
                }
            }
            MapperUtils.commit();

        } catch (Exception e) {
            MapperUtils.rollback();
            throw new RuntimeException();
        } finally {
            MapperUtils.close();
        }
    }
}
