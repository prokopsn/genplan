package ru.genplan.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ru.genplan.equipment.data.FixtureData;
import ru.genplan.equipment.data.PlanogramData;
import ru.genplan.equipment.data.SectionData;
import ru.genplan.mybatis.MyBatisUtils;

public class TestEquipmentService {
	public List<FixtureData> getAllFixture(int planogramId) {
		SqlSession sqlSession = MyBatisUtils.getTestSqlSession();
		try {
			TestEquipmentMapper equipmentMapper = sqlSession.getMapper(TestEquipmentMapper.class);
			return equipmentMapper.getAllFixture(planogramId);
		} finally {
			sqlSession.close();
		}
	}
	public List<SectionData> getAllSection(int planogramId) {
		SqlSession sqlSession = MyBatisUtils.getTestSqlSession();
		try {
			TestEquipmentMapper equipmentMapper = sqlSession.getMapper(TestEquipmentMapper.class);
			return equipmentMapper.getAllSection(planogramId);
		} finally {
			sqlSession.close();
		}
	}
	
	public PlanogramData getPlanogram(int planogramId) {
		SqlSession sqlSession = MyBatisUtils.getTestSqlSession();
		try {
			TestEquipmentMapper equipmentMapper = sqlSession.getMapper(TestEquipmentMapper.class);
			return equipmentMapper.getPlanogram(planogramId);
		} finally {
			sqlSession.close();
		}
	}
	
	public FixtureData getFixture(int fixtureId) {
		SqlSession sqlSession = MyBatisUtils.getTestSqlSession();
		try {
			TestEquipmentMapper equipmentMapper = sqlSession.getMapper(TestEquipmentMapper.class);
			return equipmentMapper.getFixture(fixtureId);
		} finally {
			sqlSession.close();
		}
	}
	
	public SectionData getSection(int sectionId) {
		SqlSession sqlSession = MyBatisUtils.getTestSqlSession();
		try {
			TestEquipmentMapper equipmentMapper = sqlSession.getMapper(TestEquipmentMapper.class);
			return equipmentMapper.getSection(sectionId);
		} finally {
			sqlSession.close();
		}
	}
	

}
