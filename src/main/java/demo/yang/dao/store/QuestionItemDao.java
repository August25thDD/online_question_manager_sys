package demo.yang.dao.store;

import demo.yang.dao.BaseDao;
import demo.yang.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemDao extends BaseDao<QuestionItem> {

    List<QuestionItem> findByQuestionId(String questionId);
}
