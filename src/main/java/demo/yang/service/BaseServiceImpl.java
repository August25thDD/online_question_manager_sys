package demo.yang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.yang.dao.BaseDao;
import demo.yang.factory.MapperFactory;
import demo.yang.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/*
    T 代表实体的类型
    TDao 代表Dao的类型
 */
public abstract class BaseServiceImpl<T, TDao extends BaseDao<T>> implements BaseService<T> {

    public void save(T t) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
//            tDao tDao = MapperFactory.getMapper(sqlSession, tDao.class);
            TDao tDao = createDao(sqlSession);
            //进一步处理Bean对象（比如设置id，比如密码加密等等）
            proceedBean(t);

            //3.调用Dao层操作
            tDao.save(t);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void proceedBean(T t);

    protected abstract TDao createDao(SqlSession sqlSession);

    public void delete(T t) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            TDao tDao = createDao(sqlSession);
            //3.调用Dao层操作
            tDao.delete(t);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(T t) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            TDao tDao = createDao(sqlSession);
            //3.调用Dao层操作
            tDao.update(t);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public T findById(String id) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            TDao tDao = createDao(sqlSession);
            //3.调用Dao层操作
            return tDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<T> findAll() {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            TDao tDao = createDao(sqlSession);
            //3.调用Dao层操作
            return tDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            TDao tDao = createDao(sqlSession);
            //3.调用Dao层操作
            PageHelper.startPage(page, size);
            List<T> all = tDao.findAll();
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
