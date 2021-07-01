package com.ztkj.friend.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.friend.entity.Chat;
import com.ztkj.friend.service.IFriendService;
import com.ztkj.friend.service.impl.FriendServiceImpl;
import com.ztkj.utils.Page;
import com.ztkj.friend.vo.FriendInfo;
import com.ztkj.friend.vo.MsgInfo;

@WebServlet("/front/friend/chatServlet")
public class ChatServlet extends HttpServlet {
    IFriendService friendService = new FriendServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String method = req.getParameter("method");
        if ("chat".equals(method)) {
            chat(req, resp);
        } else if ("findChatNoReadMsgList".equals(method)) {
            findChatNoReadMsgList(req, resp);
        } else if ("findAllChatRecordListLike".equals(method)) {
            findAllChatRecordListLike(req, resp);
        } else if ("findChatNoReadMsgByUser".equals(method)) {
            findChatNoReadMsgByUser(req, resp);
        } else {
            toChat(req, resp);
        }
    }

    protected void toChat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = this.getServletContext();
        int userId = Integer.parseInt(req.getParameter("userId"));
        int friendId = Integer.parseInt(req.getParameter("friendId"));
        FriendInfo friendInfo = new FriendInfo();
        friendInfo.setUserId(userId);
        friendInfo.setFriendId(friendId);
        friendInfo.setUserName(req.getParameter("userName"));
        friendInfo.setUserNickName(req.getParameter("userNickName"));
        friendInfo.setUserUri(req.getParameter("userUri"));
        friendInfo.setUserForumIntegral(Integer.parseInt(req.getParameter("friendForumIntegral")));
        friendInfo.setUserExamIntegral(Integer.parseInt(req.getParameter("friendExamIntegral")));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(req.getParameter("friendCreatDate"));
            friendInfo.setUserCreatDate(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean flag = friendService.ifFriend(userId,friendId);
        if (flag){
            List<Chat> msgList = friendService.findChatMsg(userId, friendId);
            application.setAttribute("msgList", msgList);
            application.setAttribute("friendInfo", friendInfo);
            application.getRequestDispatcher("/front/friend/friend_chat.jsp").forward(req, resp);
        }else {
            resp.getWriter().print("<script>alert('您不在对方好友列表中,无法发起聊天!');</script>");
            resp.getWriter().print("<script>history.go(-1);</script>");
        }
    }

    protected void chat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = this.getServletContext();
        int userId = Integer.parseInt(req.getParameter("userId"));
        int friendId = Integer.parseInt(req.getParameter("friendId"));
        Chat chat = new Chat();
        chat.setUserId(userId);
        chat.setFriendId(friendId);
        chat.setContent(req.getParameter("chatContent"));
        chat.setWhen(new Date());
        boolean flag = friendService.addChatMsg(chat);
        List<Chat> msgList = friendService.findChatMsg(userId, friendId);
        application.setAttribute("msgList", msgList);
        application.setAttribute("chat", chat);
        application.getRequestDispatcher("/front/friend/friend_chat.jsp").forward(req, resp);
    }

    protected void findChatNoReadMsgList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if ("ignoreMsg".equals(type)) {
            Chat chat = new Chat();
            chat.setUserId(Integer.parseInt(req.getParameter("userId")));
            chat.setFriendId(Integer.parseInt(req.getParameter("friendId")));
            boolean flag = friendService.ignoreMsg(chat);
            req.setAttribute("ignoreFlag", flag);
        } else if ("reply".equals(type)) {
            Chat chat = new Chat();
            chat.setUserId(Integer.parseInt(req.getParameter("userId")));
            chat.setFriendId(Integer.parseInt(req.getParameter("friendId")));
            chat.setContent(req.getParameter("chatContent"));
            chat.setWhen(new Date());
            boolean flag = friendService.addChatMsg(chat);
            friendService.ignoreMsg(chat);
            req.setAttribute("replyFlag", flag);
        }
        String currentPage = req.getParameter("currentPage");
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setUserId(Integer.parseInt(req.getParameter("userId")));
        msgInfo.setUserName(req.getParameter("userName"));
        msgInfo.setUserNickName(req.getParameter("userNickName"));
        int cp = 1;
        if (currentPage != null && !"".equals(currentPage)) {
            cp = Integer.parseInt(currentPage);
        }
        Page<MsgInfo> page = new Page<MsgInfo>();
        page.setCurrentPage(cp);
        page.setPageSize(4);
        page.setEntity(msgInfo);
        friendService.findChatNoReadMsgUserList(page);
        req.setAttribute("msgNoReadList", page);
        req.getRequestDispatcher("/front/friend/friend_noReadMsgList.jsp").forward(req, resp);
    }

    protected void findAllChatRecordListLike(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setUserId(Integer.parseInt(req.getParameter("userId")));
        msgInfo.setFriendId(Integer.parseInt(req.getParameter("friendId")));
        int ps = 4;
        if (pageSize != null && !"".equals(pageSize)) {
            ps = Integer.parseInt(pageSize);
        }
        int cp = 1;
        if (currentPage != null && !"".equals(currentPage)) {
            cp = Integer.parseInt(currentPage);
        }
        Page<MsgInfo> page = new Page<MsgInfo>();
        page.setCurrentPage(cp);
        page.setPageSize(ps);
        page.setEntity(msgInfo);
        friendService.findAllChatRecordListLike(page);
        req.setAttribute("chatMsgListPage", page);
        req.getRequestDispatcher("/front/friend/friend_chat_record.jsp").forward(req, resp);
    }

    protected void findChatNoReadMsgByUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("friendId"));
        int friendId = Integer.parseInt(req.getParameter("userId"));
        String userNickName = req.getParameter("userNickName");
        String type = req.getParameter("type");
        List<Chat> chatList = friendService.findChatNoReadMsgByUser(userId, friendId);
        req.setAttribute("friendId", friendId);
        req.setAttribute("userNickName", userNickName);
        req.setAttribute("chatList", chatList);
        req.getRequestDispatcher("/front/friend/friend_msgReply.jsp").forward(req, resp);
    }
}
