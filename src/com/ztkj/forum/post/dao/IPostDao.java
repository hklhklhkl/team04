package com.ztkj.forum.post.dao;

import java.util.List;

import com.ztkj.forum.entity.Post;
import com.ztkj.utils.Page;

public interface IPostDao {

    List<Post> findAllPost(Page<Post> page);
	
    Post findPostById(int postId);
	
	boolean deletePost(int postId);
	
	boolean addPost(Post post);
	
	boolean updatePost(Post post);
	
	int findTotalCountByLike(Page<Post> page);
	
	List<Post> findNameByBlockId();
	
	List<Post> findNameByUserId();
	
	List<Post> findNameByState();
	
	boolean updateGood(int postId,int postGood);
	
	boolean updateUp(int postId,int postUp);
	
	boolean updateState(int postState,int postId);
	
	List<Post> findPostList(int blockId);

}
