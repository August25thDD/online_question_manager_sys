package demo.yang.service.store;

import demo.yang.domain.store.QuestionItem;
import demo.yang.service.BaseService;

import java.util.List;

public interface QuestionItemService extends BaseService<QuestionItem> {
    List<QuestionItem> findByQuestionId(String questionId);
}
