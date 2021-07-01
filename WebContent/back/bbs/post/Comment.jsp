<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/back/css/css/bbs_content_details.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/back/js/js/bbs.js"></script>
	</head>

	<body>
		<!--帖子详细内容展示开始-->
		<div id="content_details_main">
			<div class="main_adrress">
				<ul>
					<li style="margin-left: 0;">当前位置</li>
					<li>:</li>
					<li>论坛</li>
					<li>&gt;</li>
					<li>论坛话题</li>
					<li>&gt;</li>
					<li>帖子</li>
				</ul>
			</div>
			<div class="content_update">
				<!--第一块-->
				<!--这是一个帖子内容详情板块-->
				<div class="content_box" style="margin: 0;">
					<div class="content_box_details">
						<div class="details_title">
							<div class="details_title_icon"> </div>
							<div class="details_title_info">
								<div class="info_name">${post.userName}</div>
								<div class="info_grade">
									<ul>
										<li>等级&nbsp;:&nbsp;</li>
										<li>英勇青铜</li>
										<li style="float: right;">1</li>
										<li style="float: right;">积分&nbsp;:&nbsp;</li>
									</ul>
								</div>
							</div>
							<div class="details_title_idetity">
								<div class="dots"></div>
								<div class="owner">楼主</div>
							</div>
						</div>
						<div class="details_article">${post.postComment}</div>
					</div>
				</div>
				<c:forEach items="${comment}" var="comment">
				<div class="content_box">
					<div class="content_box_details">
						<div class="details_title">
							<div class="details_title_icon"> </div>
							<div class="details_title_info">
								<div class="info_name"></div>
								<div class="info_grade">
									<ul>
										<li>等级&nbsp;:&nbsp;</li>
										<li>英勇青铜</li>
										<li style="float: right;">1</li>
										<li style="float: right;">积分&nbsp;:&nbsp;</li>
									</ul>
								</div>
							</div>
							<div class="details_title_idetity">
								<div class="dots"></div>
								<div class="owner">沙发</div>
							</div>
						</div>
						
						<div class="details_article">${comment.commentContent }</div>
						
						<div class="comments">
							<div class="comments_status">
								<span  class="status_text">隐藏评论</span>
								<img id="status_img" class="shImg" src="img/show_arrow.png" onclick="showComments(this.parentNode)" />
								<!--评论可隐藏区域-->
								<!--隐藏域1-->
								
								<div class="hidden_comments">
									<!--评论1-->
									<c:forEach items="${reply}" var="reply">
									<c:choose>
									<c:when test="${reply.commentId==comment.commentId}">
										<div class="comments_reply">
											<div class="preIcon"></div>
												<div class="reply_info">
													<ul>
														<li class="link_style">
															<a href="#">百度</a>
														</li>
														<li>回复</li>
														<li class="link_style">
															<a href="#">腾讯</a>
														</li>
														<li class="link_style reply" style="float: right;" onclick="openMessage(this.parentNode)">
															<a href="#">回复</a>
														</li>
													</ul>
												</div>
											<div id="comments_details" class="comments_details">${reply.replyContent}</div>
										</div>
										</c:when>
									</c:choose>
										
									</c:forEach>
								<!--回复层-->

							<form action="${pageContext.request.contextPath }/front/reply/replyServlet?method=addReply&commentId=${comment.commentId}" method="post">
								
										<input id="replyContent" type="text"  class="online_content_inp" name="replyContent"/>
									
										<input type="submit" value="回复">
									
								</form>
								
							</div>

						</div>
					</div>
				</div>
			</div>
		</c:forEach>
				
				<div class="quick_reply">
					<div class="quick_reply_name">
						<div class="blue_line"></div>
						<div class="quick_reply_name_text">快速回复层主</div>
					</div>
					<form action="${pageContext.request.contextPath }/front/comment/commentServlet?method=addComment&postId=${post.postId}" method="post">
					<input id="replyContent"  class="online_content_inp" type="text" name="commentContent">
					<input type="submit" value="回复">
					</form>
				</div>
			</div>

		</div>

	</body>

</html>