package demo.yang.dao.system;

import demo.yang.dao.BaseDao;
import demo.yang.domain.system.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户映射接口
 */
public interface UserDao extends BaseDao<User> {
//    values (#{userId},#{roleId});
    void updateRole(@Param("userId") String userId, @Param("roleId") String roleId);

    void deleteRole(String userId);
}
