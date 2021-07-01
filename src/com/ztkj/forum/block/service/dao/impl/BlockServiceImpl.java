package com.ztkj.forum.block.service.dao.impl;

import java.util.List;


import com.ztkj.forum.block.dao.IBlockDao;
import com.ztkj.forum.block.dao.impl.BlockDaoImpl;
import com.ztkj.forum.block.service.dao.IBlockService;
import com.ztkj.forum.entity.Block;
import com.ztkj.utils.Page;

public class BlockServiceImpl implements IBlockService {

	IBlockDao blockDao = new BlockDaoImpl();
	
	

	
	public Block findBlockById(int blockId) {
		
		return blockDao.findBlockById(blockId);
	}

	@Override
	public boolean addBlock(Block block) {
		
		return blockDao.addBlock(block);
	}

	@Override
	public boolean deleteBlock(int blockId) {
		
		return blockDao.deleteBlock(blockId);
	}

	@Override
	public boolean updateBlock(Block block) {
		
		return blockDao.updateBlock(block);
	}


	@Override
	public void findAllBlock(Page<Block> page) {
		int totalCount = blockDao.findTotalCountByLike(page);
		page.setPageProperties(totalCount);
		List<Block> list = blockDao.findAllBlock(page);
		page.setList(list);
	}

	@Override
	public List<Block> findNameById() {
		
		return blockDao.findNameById();
	}

	@Override
	public boolean buff(int blockId, int blockBuff) {
		
		return blockDao.buff(blockId, blockBuff);
	}

	@Override
	public List<Block> findTotalConut() {
		
		return blockDao.findTotalConut();
	}

	
}
