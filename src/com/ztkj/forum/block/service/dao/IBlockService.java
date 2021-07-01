package com.ztkj.forum.block.service.dao;







import java.util.List;

import com.ztkj.forum.entity.Block;
import com.ztkj.utils.Page;

public interface IBlockService {

	List<Block> findNameById();
	
	Block findBlockById(int blockId);
	
	boolean addBlock(Block block);
	
	boolean deleteBlock(int blockId);
	
	void findAllBlock(Page<Block> page);
	
	boolean updateBlock(Block block);
	
	boolean buff(int blockId,int blockBuff);
	
	List<Block> findTotalConut();
}
