package demo.yang.service.store.impl;

import demo.yang.dao.store.CatalogDao;
import demo.yang.domain.store.Catalog;
import demo.yang.service.BaseServiceImpl;
import demo.yang.service.store.CatalogService;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.UUID;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-28 14:53
 **/

public class CatalogServiceImpl extends BaseServiceImpl<Catalog, CatalogDao> implements CatalogService {

    @Override
    protected void proceedBean(Catalog catalog) {
        String id = UUID.randomUUID().toString().replace("-", "");
        catalog.setId(id);
        catalog.setCreateTime(new Date());

    }

    @Override
    protected CatalogDao createDao(SqlSession sqlSession) {
        return sqlSession.getMapper(CatalogDao.class);
    }
}
