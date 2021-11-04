package demo.yang.service.system.impl;

import demo.yang.dao.system.ModuleDao;
import demo.yang.domain.system.Module;
import demo.yang.service.BaseServiceImpl;
import demo.yang.service.system.ModuleService;
import org.apache.ibatis.session.SqlSession;

import java.util.UUID;

public class ModuleServiceImpl extends BaseServiceImpl<Module,ModuleDao> implements ModuleService {
    @Override
    protected void proceedBean(Module catalog) {
        String uuid = UUID.randomUUID().toString();
        catalog.setId(uuid);
    }

    @Override
    protected ModuleDao createDao(SqlSession sqlSession) {
        return sqlSession.getMapper(ModuleDao.class);
    }
}
