package demo.yang.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.yang.dao.store.CompanyDao;
import demo.yang.domain.store.Company;
import demo.yang.service.store.CompanyService;
import demo.yang.utils.MapperUtils;

import java.util.List;
import java.util.UUID;


@SuppressWarnings("all")
/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-24 16:12
 **/

public class CompanyServiceImpl implements CompanyService {
    @Override
    public void save(Company company) {
        try {
            CompanyDao mapper = MapperUtils.getMapper(CompanyDao.class);

//            id使用UUID生成策略来获取
            String id = UUID.randomUUID().toString();
            company.setId(id);
            int rel = mapper.save(company);
//            System.out.println(rel);
            MapperUtils.commit();

        } catch (Exception e) {
            MapperUtils.rollback();
            e.printStackTrace();
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public void delete(Company company) {
        try {
            CompanyDao mapper = MapperUtils.getMapper(CompanyDao.class);
            int rel = mapper.delete(company);
            MapperUtils.commit();
        } catch (Exception e) {
            MapperUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public void update(Company company) {
        try {
            CompanyDao mapper = MapperUtils.getMapper(CompanyDao.class);
            int update = mapper.update(company);
            MapperUtils.commit();

        } catch (Exception e) {
            MapperUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public Company findById(String id) {
        try {
            CompanyDao mapper = MapperUtils.getMapper(CompanyDao.class);
            Company company = mapper.findById(id);
            return company;

        } catch (Exception e) {
            MapperUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public List<Company> findAll() {
        try {
            CompanyDao mapper = MapperUtils.getMapper(CompanyDao.class);
            List<Company> companies = mapper.findAll();
            return companies;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public PageInfo<Company> findAll(int page, int size) {
        try {
            CompanyDao mapper = MapperUtils.getMapper(CompanyDao.class);
            PageHelper.startPage(page, size);
            List<Company> companies = mapper.findAll();
            return new PageInfo<>(companies);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }
}
