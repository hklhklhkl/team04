package com.ztkj.forum.post.service.dao.impl;

import java.util.List;

import com.ztkj.forum.entity.Block;
import com.ztkj.forum.entity.Post;
import com.ztkj.forum.post.dao.IPostDao;
import com.ztkj.forum.post.dao.impl.PostDaoImpl;
import com.ztkj.forum.post.service.dao.IPostServiceDao;
import com.ztkj.utils.Page;

public class PostService implements IPostServiceDao {

	IPostDao postDao = new PostDaoImpl();

	

	@Override
	public Post findPostById(int postId) {
		
		return postDao.findPostById(postId);
	}

	@Override
	public boolean deletePost(int postId) {
		
		return postDao.deletePost(postId);
	}

	@Override
	public boolean addPost(Post post) {
		
		return postDao.addPost(post);
	}

	@Override
	public boolean updatePost(Post post) {
		
		return postDao.updatePost(post);
	}

	@Override
	public void findAllPost(Page<Post> page) {
		int totalCount = postDao.findTotalCountByLike(page);
		page.setPageProperties(totalCount);
		List<Post> list = postDao.findAllPost(page);
		page.setList(list);
	}

	@Override
	public List<Post> findNameByBlockId() {
		
		return postDao.findNameByBlockId();
	}

	@Override
	public List<Post> findNameByUserId() {
		
		return postDao.findNameByUserId();
	}

	@Override
	public List<Post> findNameByState() {
		
		return postDao.findNameByState();
	}

	@Override
	public boolean updateGood(int postId, int postGood) {
		
		return postDao.updateGood(postId, postGood);
	}

	@Override
	public boolean updateUp(int postId, int postUp) {
		
		return postDao.updateUp(postId, postUp);
	}

	@Override
	public boolean updateState(int postState, int postId) {
		
		return postDao.updateState(postState, postId);
	}

	@Override
	public List<Post> findAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> findPostList(int blockId) {
		
		return postDao.findPostList(blockId);
	}

}
