package demo.yang.service.store.impl;

import demo.yang.dao.store.QuestionItemDao;
import demo.yang.domain.store.QuestionItem;
import demo.yang.factory.MapperFactory;
import demo.yang.service.BaseServiceImpl;
import demo.yang.service.store.QuestionItemService;
import demo.yang.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class QuestionItemServiceImpl extends BaseServiceImpl<QuestionItem,QuestionItemDao> implements QuestionItemService {
    @Override
    protected void proceedBean(QuestionItem catalog) {
        String uuid = UUID.randomUUID().toString();
        catalog.setId(uuid);
    }

    @Override
    protected QuestionItemDao createDao(SqlSession sqlSession) {
        return sqlSession.getMapper(QuestionItemDao.class);
    }

    @Override
    public List<QuestionItem> findByQuestionId(String questionId) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionItemDao mapper = sqlSession.getMapper(QuestionItemDao.class);
            //3.调用Dao层操作
            return mapper.findByQuestionId(questionId);
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
