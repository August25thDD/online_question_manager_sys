package demo.yang.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import demo.yang.dao.system.DeptDao;
import demo.yang.domain.system.Dept;
import demo.yang.service.system.DeptService;
import demo.yang.utils.MapperUtils;

import java.util.List;
import java.util.UUID;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-24 16:12
 **/

public class DeptServiceImpl implements DeptService {

    @Override
    public void save(Dept dept) {
        try {
            DeptDao mapper = MapperUtils.getMapper(DeptDao.class);

//            id使用UUID生成策略来获取
            String id = UUID.randomUUID().toString();
            dept.setId(id);
            int rel = mapper.save(dept);
//            System.out.println(rel);
            MapperUtils.commit();
//
        } catch (Exception e) {
            MapperUtils.rollback();
            e.printStackTrace();
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public void delete(Dept dept) {
        try {
            DeptDao mapper = MapperUtils.getMapper(DeptDao.class);
            int rel = mapper.delete(dept);
            MapperUtils.commit();

        } catch (Exception e) {
            MapperUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public void update(Dept dept) {
        try {
            DeptDao mapper = MapperUtils.getMapper(DeptDao.class);
            int update = mapper.update(dept);
            MapperUtils.commit();

        } catch (Exception e) {
            MapperUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public Dept findById(String id) {
        try {
            DeptDao mapper = MapperUtils.getMapper(DeptDao.class);
            return mapper.findById(id);

        } catch (Exception e) {
            MapperUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public List<Dept> findAll() {
        try {
            DeptDao mapper = MapperUtils.getMapper(DeptDao.class);
            List<Dept> depts = mapper.findAll();
            return depts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }

    @Override
    public PageInfo<Dept> findAll(int page, int size) {
        try {
            DeptDao mapper = MapperUtils.getMapper(DeptDao.class);
            PageHelper.startPage(page, size);
            List<Dept> depts = mapper.findAll();
            return new PageInfo<>(depts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            MapperUtils.close();
        }
    }
}
