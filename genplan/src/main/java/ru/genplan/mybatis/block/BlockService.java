package ru.genplan.mybatis.block;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ru.genplan.block.data.BlockData;
import ru.genplan.mybatis.MyBatisUtils;

public class BlockService {
	public List<BlockData> getAllBlock(int planogramId) {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		try {
			BlockMapper blockMapper = sqlSession.getMapper(BlockMapper.class);
			return blockMapper.getAllBlock(planogramId);
		} finally {
			sqlSession.close();
		}
	}
}
