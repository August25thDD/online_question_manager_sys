package demo.yang.dao.system;

import demo.yang.dao.BaseDao;
import demo.yang.domain.system.AuthorBean;
import demo.yang.domain.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao extends BaseDao<Role> {
    List<AuthorBean> findAuthorBeansById(String role_id);

    void deleteModulesByRoleId(String roleId);

    void saveModuleAndRole(@Param("roleId") String roleId, @Param("moduleId") String moduleId);

    List<Role> findAllRoleByUserId(String userId);
}
