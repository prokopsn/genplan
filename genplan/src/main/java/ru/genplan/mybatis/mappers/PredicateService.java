package ru.genplan.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ru.genplan.mybatis.MyBatisUtils;
import ru.genplan.predicate.GenplanPredicate;

public class PredicateService {
	public List<GenplanPredicate> getAllPredicates(int planogramId) {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		try {
			PredicateMapper predicateMapper = sqlSession.getMapper(PredicateMapper.class);
			return predicateMapper.getAllPredicates(planogramId);
		} finally {
			sqlSession.close();
		}
	}
}

