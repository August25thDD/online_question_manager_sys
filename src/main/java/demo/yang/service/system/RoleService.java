package demo.yang.service.system;

import demo.yang.domain.system.AuthorBean;
import demo.yang.domain.system.Role;
import demo.yang.service.BaseService;

import java.util.List;

public interface RoleService extends BaseService<Role> {

    List<AuthorBean> findAuthorBeansById(String role_id);

    void updateModulesByRoleId(String roleId, String[] moduleIds);

    List<Role> findAllRoleByUserId(String userId);
}
