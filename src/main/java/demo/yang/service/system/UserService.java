package demo.yang.service.system;

import demo.yang.domain.system.User;
import demo.yang.service.BaseService;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-28 10:45
 **/

public interface UserService extends BaseService<User> {
    void updateRole(String userId, String[] roleIds);

}
