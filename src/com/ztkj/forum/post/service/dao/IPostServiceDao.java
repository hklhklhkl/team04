package com.ztkj.forum.post.service.dao;

import java.util.List;


import com.ztkj.forum.entity.Post;
import com.ztkj.utils.Page;

public interface IPostServiceDao {
	
	Post findPostById(int postId);
	
	boolean deletePost(int postId);
	
	boolean addPost(Post post);
	
	boolean updatePost(Post post);
	
	void findAllPost(Page<Post> page);
	
	List<Post> findNameByBlockId();
	
	List<Post> findNameByUserId();
	
	List<Post> findNameByState();
	
	boolean updateGood(int postId,int postGood);
	
	boolean updateUp(int postId,int postUp);
	
	boolean updateState(int postState,int postId);

	List<Post> findAllPost();
	
	List<Post> findPostList(int blockId);

}
