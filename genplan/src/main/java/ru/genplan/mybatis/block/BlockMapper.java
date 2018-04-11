package ru.genplan.mybatis.block;

import java.util.List;

import ru.genplan.block.data.BlockData;

public interface BlockMapper {
   public List<BlockData> getAllBlock(int planogramId);
}
