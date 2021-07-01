package com.ztkj.friend.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.entity.User;
import com.ztkj.friend.entity.Apply;
import com.ztkj.friend.entity.Friend;
import com.ztkj.friend.service.IFriendService;
import com.ztkj.friend.service.impl.FriendServiceImpl;
import com.ztkj.friend.vo.ApplyInfo;
import com.ztkj.friend.vo.FriendInfo;
import com.ztkj.utils.Page;

@WebServlet("/front/friend/friendServlet")
public class FriendServlet extends HttpServlet {
	IFriendService friendService = new FriendServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String method = req.getParameter("method");
		if ("findUser".equals(method)) {
			findUser(req, resp);
		} else if ("findApplyList".equals(method)) {
			findApplyList(req, resp);
		} else if ("findAllFriendListLike".equals(method)) {
			findAllFriendListLike(req, resp);
		} else if ("findAllFriendBlackList".equals(method)) {
			findAllFriendBlackList(req, resp);
		}
	}

	protected void findUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		if ("add".equals(type)) {
			int userId = Integer.parseInt(req.getParameter("userId"));
			int friendId = Integer.parseInt(req.getParameter("friendId"));
			Apply apply = new Apply();
			apply.setUserId(userId);
			apply.setFriendId(friendId);
			boolean ifFlag = friendService.friendApply(apply, "if");
			if (ifFlag){
				resp.getWriter().print("<script>alert('您已在对方好友申请列表中,请勿重复申请!');</script>");
				resp.getWriter().print("<script>history.go(-1);</script>");
			}else {
				boolean flag = friendService.friendApply(apply, "add");
				req.setAttribute("flag", flag);
				req.getRequestDispatcher("/front/friend/friend_add.jsp").forward(req, resp);
			}
		} else {
			String userName = req.getParameter("userName");
			String userNickName = req.getParameter("userNickName");
			int userId = Integer.parseInt(req.getParameter("userId"));
			req.setAttribute("userName", userName);
			req.setAttribute("userNickName", userNickName);
			List<User> userList = friendService.findUser(userName, userNickName, userId);
			req.setAttribute("userList", userList);
			req.getRequestDispatcher("/front/friend/friend_add.jsp").forward(req, resp);
		}
	}

	protected void findApplyList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("yes".equals(action)) {
			int userId = Integer.parseInt(req.getParameter("userId"));
			int friendId = Integer.parseInt(req.getParameter("friendId"));
			Apply apply = new Apply();
			apply.setUserId(friendId);
			apply.setFriendId(userId);
			String type = req.getParameter("type");
			if ("agree".equals(type)) {
				Friend friend = new Friend();
				friend.setUserId(friendId);
				friend.setFriendId(userId);
				friendService.friendApply(apply, "agree");
				friendService.addFriend(friend, "two");
				boolean flag = friendService.addFriend(friend, "one");
				req.setAttribute("agreeFlag", flag);
			} else if ("refuse".equals(type)) {
				boolean flag = friendService.friendApply(apply, "refuse");
				req.setAttribute("refuseFlag", flag);
			}
		}
		String currentPage = req.getParameter("currentPage");
		ApplyInfo applyInfo = new ApplyInfo();
		applyInfo.setUserId(Integer.parseInt(req.getParameter("userId")));
		int cp = 1;
		if (currentPage != null && !"".equals(currentPage)) {
			cp = Integer.parseInt(currentPage);
		}
		Page<ApplyInfo> page = new Page<ApplyInfo>();
		page.setCurrentPage(cp);
		page.setPageSize(4);
		page.setEntity(applyInfo);
		friendService.findApplyList(page);
		req.setAttribute("applyListPage", page);
		req.getRequestDispatcher("/front/friend/friend_apply.jsp").forward(req, resp);
	}

	protected void findAllFriendListLike(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("yes".equals(action)) {
			int userId = Integer.parseInt(req.getParameter("userId"));
			int friendId = Integer.parseInt(req.getParameter("friendId"));
			Friend friend = new Friend();
			friend.setUserId(userId);
			friend.setFriendId(friendId);
			String type = req.getParameter("type");
			if ("delete".equals(type)) {
				friendService.deleteFriend(friend, "fromApply");
				friendService.deleteFriend(friend, "fromChat");
				boolean flag = friendService.deleteFriend(friend, "fromFriend");
				req.setAttribute("deleteFromListFlag", flag);
			} else if ("addBlack".equals(type)) {
				boolean flag = friendService.friendBlackList(friend, "add");
				req.setAttribute("addBlackFlag", flag);
			}
		}
		String currentPage = req.getParameter("currentPage");
		FriendInfo friendInfo = new FriendInfo();
		friendInfo.setUserId(Integer.parseInt(req.getParameter("userId")));
		friendInfo.setUserName(req.getParameter("userName"));
		friendInfo.setUserNickName(req.getParameter("userNickName"));
		int cp = 1;
		if (currentPage != null && !"".equals(currentPage)) {
			cp = Integer.parseInt(currentPage);
		}
		Page<FriendInfo> page = new Page<FriendInfo>();
		page.setCurrentPage(cp);
		page.setPageSize(4);
		page.setEntity(friendInfo);
		friendService.findAllFriendListLike(page);
		req.setAttribute("friendListPage", page);
		req.getRequestDispatcher("/front/friend/friend_list.jsp").forward(req, resp);
	}

	protected void findAllFriendBlackList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("yes".equals(action)) {
			int userId = Integer.parseInt(req.getParameter("userId"));
			int friendId = Integer.parseInt(req.getParameter("friendId"));
			Friend friend = new Friend();
			friend.setUserId(userId);
			friend.setFriendId(friendId);
			String type = req.getParameter("type");
			if ("remove".equals(type)) {
				boolean flag = friendService.friendBlackList(friend, "remove");
				req.setAttribute("removeFlag", flag);
			} else if ("delete".equals(type)) {
				friendService.deleteFriend(friend, "fromApply");
				friendService.deleteFriend(friend, "fromChat");
				boolean flag = friendService.deleteFriend(friend, "fromFriend");
				req.setAttribute("deleteFromBlackFlag", flag);
			}
		}
		String currentPage = req.getParameter("currentPage");
		FriendInfo friendInfo = new FriendInfo();
		friendInfo.setUserId(Integer.parseInt(req.getParameter("userId")));
		friendInfo.setUserName(req.getParameter("userName"));
		friendInfo.setUserNickName(req.getParameter("userNickName"));
		int cp = 1;
		if (currentPage != null && !"".equals(currentPage)) {
			cp = Integer.parseInt(currentPage);
		}
		Page<FriendInfo> page = new Page<FriendInfo>();
		page.setCurrentPage(cp);
		page.setPageSize(4);
		page.setEntity(friendInfo);
		friendService.findAllFriendBlackList(page);
		req.setAttribute("friendBlackListPage", page);
		req.getRequestDispatcher("/front/friend/friend_blackList.jsp").forward(req, resp);
	}
}
