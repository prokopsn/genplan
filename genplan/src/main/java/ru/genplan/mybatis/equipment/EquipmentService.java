package ru.genplan.mybatis.equipment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ru.genplan.equipment.data.FixtureData;
import ru.genplan.equipment.data.PlanogramData;
import ru.genplan.equipment.data.SectionData;
import ru.genplan.mybatis.MyBatisUtils;

public class EquipmentService {
	public List<FixtureData> getAllFixture(int planogramId) {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		try {
			EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
			return equipmentMapper.getAllFixture(planogramId);
		} finally {
			sqlSession.close();
		}
	}
	public List<SectionData> getAllSection(int planogramId) {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		try {
			EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
			return equipmentMapper.getAllSection(planogramId);
		} finally {
			sqlSession.close();
		}
	}
	
	public PlanogramData getPlanogram(int planogramId) {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		try {
			EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
			return equipmentMapper.getPlanogram(planogramId);
		} finally {
			sqlSession.close();
		}
	}
}
