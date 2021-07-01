package com.ztkj.forum.block.dao;

import java.util.List;


import com.ztkj.forum.entity.Block;
import com.ztkj.utils.Page;

public interface IBlockDao {
	
	List<Block> findNameById();

	List<Block> findAllBlock(Page<Block> page);
	
	Block findBlockById(int blockId);
	
	List<Block> findTotalConut();
	
	boolean addBlock(Block block);
	
	boolean deleteBlock(int blockId);
	
	boolean updateBlock(Block block);

	int findTotalCountByLike(Page<Block> page);
	
	boolean buff(int blockId,int blockBuff);
	
}
