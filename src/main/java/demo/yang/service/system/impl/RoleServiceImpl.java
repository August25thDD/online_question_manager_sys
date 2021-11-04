package demo.yang.service.system.impl;

import demo.yang.dao.system.RoleDao;
import demo.yang.domain.system.AuthorBean;
import demo.yang.domain.system.Role;
import demo.yang.factory.MapperFactory;
import demo.yang.service.BaseServiceImpl;
import demo.yang.service.system.RoleService;
import demo.yang.utils.MapperUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class RoleServiceImpl extends BaseServiceImpl<Role, RoleDao> implements RoleService {
    @Override
    protected void proceedBean(Role catalog) {
        String uuid = UUID.randomUUID().toString();
        catalog.setId(uuid);
    }

    @Override
    protected RoleDao createDao(SqlSession sqlSession) {
        return sqlSession.getMapper(RoleDao.class);
    }

    @Override
    public List<AuthorBean> findAuthorBeansById(String role_id) {
        SqlSession sqlSession = MapperFactory.getSqlSession();
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);

        List<AuthorBean> list = roleDao.findAuthorBeansById(role_id);

        sqlSession.close();
        return list;
    }

    @Override
    public void updateModulesByRoleId(String roleId, String[] moduleIds) {
        SqlSession sqlSession = MapperFactory.getSqlSession();
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
        //跟新模块和角色的关系
        roleDao.deleteModulesByRoleId(roleId);

        if (moduleIds != null) {
            for (String moduleId : moduleIds) {
                roleDao.saveModuleAndRole(roleId, moduleId);
            }
        }

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public List<Role> findAllRoleByUserId(String userId) {
        try {
            RoleDao mapper = MapperUtils.getMapper(RoleDao.class);
            return mapper.findAllRoleByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            MapperUtils.close();
        }
    }
}
