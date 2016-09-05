<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>沈工大作业管理</title>
	<meta name="description" content="">
	<meta name="author" content="">
	
	
	<style type="text/css">
		dd {
				color:#60686b;
				font-size:12px;
				margin-bottom:28px;
			}
	</style>
	
	<!-- Favicons
	    ================================================== -->
	<link rel="shortcut icon" href="<%=basePath%>/img/favicon.ico" type="image/x-icon">
	<link rel="apple-touch-icon" href="<%=basePath%>/img/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="<%=basePath%>/img/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="<%=basePath%>/img/apple-touch-icon-114x114.png">
	
	<!-- Bootstrap -->
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/fonts/font-awesome/css/font-awesome.css">
	
	<!-- Slider
	    ================================================== -->
	<link href="<%=basePath%>/css/owl.carousel.css" rel="stylesheet" media="screen">
	<link href="<%=basePath%>/css/owl.theme.css" rel="stylesheet" media="screen">
	
	<!-- circleButton -->
	<link href="<%=basePath%>/css/circleButton.css" rel="stylesheet" media="screen">
	
	<!-- Stylesheet
	    ================================================== -->
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/prettyPhoto.css">
	<link href='http://fonts.useso.com/css?family=Lato:400,700,900,300' rel='stylesheet' type='text/css'>
	<link href='http://fonts.useso.com/css?family=Open+Sans:400,700,800,600,300' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="js/modernizr.custom.js"></script>
	<!--
	作者：stevejobson@163.com
	时间：2015-10-10
	描述：登陆注册
-->
	<script type="text/javascript" src="<%=basePath%>/js/loginModel.js" ></script>
	<script type="text/javascript" src="<%=basePath%>/js/regModel.js" ></script>
</head>
	<body  style="background-color:#e5e5e5;">
		    <nav class="navbar navbar-custom  navbar-inverse top-nav-collapse" role="navigation">
		        <div class="container">
		            <div class="navbar-header">
		                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
		                    <i class="fa fa-bars"></i>
		                </button>
		                <a class="navbar-brand page-scroll" href="student/homepage">
		                    <i class="fa fa-terminal"></i>SUT作业管理
		                </a>
		            </div>
		
		            <!-- Collect the nav links, forms, and other content for toggling -->
		            <div class="collapse navbar-collapse navbar-left navbar-main-collapse">
		                <ul class="nav navbar-nav">
		                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
		                    <li>
		                        <a class="page-scroll" href="student/courseList">课程</a>
		                    </li>                    
		                    <li>
		                        <a class="page-scroll" href="student/resCourseList">分享</a>
		                    </li>                    
		                    <li>
		                        <a class="page-scroll" href="http://www.snmqc.cn" target="_blank">社区</a>
		                    </li>
		                </ul>
		            </div>
		            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
		                    <!-- 判断是否有用户登录 -->
		               <c:choose>  
						   <c:when test="${sessionScope.user!=null}">
						   	<ul class="nav navbar-nav">
				                   <li>
				                   	 <a class="page-scroll" href="javascript:void(0)" data-toggle="tooltip" data-placement="bottom" title="我的消息">
						   				<span class="glyphicon glyphicon-envelope" style="font-size: 20px;color:white;"></span>
				                   	 </a>
						   		  </li>
				                   
				                    <li>
				                    	<div style="margin-top:3px" class="page-scroll">
											<div class="dropdown">
						   				 <a    data-toggle="dropdown"  >
						   			 <img  style="height:40px;width:40px" class="img-circle" alt="用户头像" src="<%=basePath%>/img/1.jpg">
			                			</a>
			                			<ul class="dropdown-menu" >
										    <li><p>&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.user.sname}</p></li>
										     <li class="divider"></li>
										     <li><a href="build/underBuild">我的社区</a></li>
										      <li class="divider"></li>
										     <li><a href="student/changepwd">修改密码</a></li>
										      <li class="divider"></li>
										     <li><a href="logout">注销登陆</a></li>
										 </ul>
										 </div>
				                    	</div>
				                    </li>
			                </ul>
						   </c:when>  
						     
						   <c:otherwise> 
						   	 <ul class="nav navbar-nav">
			                    <li>
			                        <a class="page-scroll" href="javascript:showLogin()" >登录</a>
			                    </li>
			                </ul>
						   </c:otherwise>  
					  </c:choose> 
		            </div>
		            <!-- /.navbar-collapse -->
		        </div>
		        <!-- /.container -->
		    </nav>
		
		<!-- 内容 -->
		<div class="container" ">
			<div style="display:block;clear:both;">
				<!-- 课程部分 -->
				<div style="width:100%;border:1px solid #edf1f2;background-color:#fff;float:left;margin-top:30px;margin-bottom:50px;">
					<div style="padding:10px 50px 40px;" class="us">
						<p style="font-size:18px;line-height:49px;margin-bottom:2.1em;border-bottom:1px solid #eceff0;color:#363d40;">课程资源下载</p>
						<!-- 课程列表 -->
						<c:forEach items="${courseList}" var="course">
							<div class="course-list-div">
								<center><h3>${course.cname}</h3></center>
								<img src="./img/course/${course.cimage }" >
								
								<!-- 需要修改的地方 -->
								
								 <a href="student/upload/${course.cno}"><button type="button" class="btn btn-primary btn-lg">进入课程</button></a>
							</div>
						</c:forEach>
					</div>
				</div>	
			</div>
		</div>		
		
		 <!--
		 	作者：stevejobson@163.com
		 	时间：2015-10-08
		 	描述：页面底部信息
		 -->
		<nav id="footer">
		  <div class="container">
		    <div class="pull-left fnav">
		      <p>Copyright © 2016 沈阳工业大学</p>
		      <ul class="footer-social">
		         <li><a href="about/aboutUs">关于我们</a></li>
		        <li><a href="about/peopleWanted">人才招聘</a></li>
		        <li><a href="about/contractUs">联系我们</a></li>
		        <li><a href="about/adviceFeed">意见反馈</a></li>
		      </ul>
		    </div>
		    <div class="pull-right fnav">
		      <ul class="footer-social">
		        <li><a href="build/underBuild"><i class="fa fa-weibo"></i></a></li>
		        <li><a href="build/underBuild"><i class="fa fa-qq"></i></a></li>
		        <li><a href="build/underBuild"><i class="fa fa-weixin"></i></a></li>
		        <li><a href="build/underBuild"><i class="fa fa-tencent-weibo"></i></a></li>
		      </ul>
		    </div>
		  </div>
		</nav>
		<!-- 登陆注册块 -->
		<div id="loginDiv" style="background-color:white;width:350px;height:300px;z-index:2;display:none;position:absolute;border-top:none;">
		    <div onmousedown="titleMove()" id="login_title" style="width:auto;height:45px;background-color:white;border:1px solid silver;border-left:none;border-right:none;border-top:none;margin:0px 15px 3px 15px;">
					<div style="float: left;">
						<h3>登陆</h3>
					</div>
					<a href="javascript:hidLogin()" style="float:right;text-decoration:none;color:black;margin-right:2px;font-size:20px"><h3>&times;</h3></a>
		    </div>
		    <div style="clear:right;width:auto;height:270px;margin:20px 15px 0px 15px;">
				<form action="login" method="post" name="loginform">
					<div class="form-group">
							<input type="text" placeholder="请输入账号" class="form-control" name="email"/>
					</div>	
					<div class="form-group">
							<input type="password" placeholder="请输入密码" class="form-control" name="password"/>
					</div>	
					<div class="form-group">
							<input type="text" placeholder="请输入验证码"  name="verifyCode" style="border-radius:4px;border: 1px solid #ccc;width: 35%;height: 34px;padding: 6px 12px;font-size: 14px; line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;"/>
							<img alt="验证码" src="Kaptcha.jpg" id="MyVerifyCode" onclick="changeVerifyCode()">
					</div>
					<div class="form-group">
							<button type="button" class="btn btn-danger btn-block" onclick="loginModel()">登陆</button>
					</div>	
					<!-- 
					<div style="height: 45px;width: auto;margin:10px 15px 3px 15px;">
						<div class="sharesheet">
							<ul class="sharesheet-links list-inline">
							<li><a href="#nogo"><i class="fa fa-weibo"></i></a></li>
							<li><a href="#nogo"><i class="fa fa-weixin"></i></a></li>
							<li><a href="#nogo"><i class="fa fa-qq"></i></a></li>
							</ul>
						</div>
					</div>
					 -->
				</form>
		    </div>
		</div>
		
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
		<script type="text/javascript" src="<%=basePath%>/js/jquery.1.11.1.js"></script> 
		<!-- Include all compiled plugins (below), or include individual files as needed --> 
		<script type="text/javascript" src="<%=basePath%>/js/bootstrap.js"></script> 
		
		<!-- Javascripts
		    ================================================== --> 
		<script type="text/javascript">
			function loginModel(){
					document.loginform.submit();
			}
		</script>
		
		<!-- 导航栏，我的消息提示 -->
		<script type="text/javascript">
		$('.dropdown-toggle').dropdown()
			$(function () {
  			$('[data-toggle="tooltip"]').tooltip()
				})
		</script>
		<script type="text/javascript">
		 function changeVerifyCode(){
             $("#MyVerifyCode").attr("src","Kaptcha.jpg?"+Math.floor(Math.random()*100));
         }
		</script>
		
		<!-- circleButton -->
		<script>
	$(document).ready(function (ev) {
	    var toggle = $('#ss_toggle');
	    var menu = $('#ss_menu');
	    var rot;
	    $('#ss_toggle').on('click', function (ev) {
	        rot = parseInt($(this).data('rot')) - 180;
	        menu.css('transform', 'rotate(' + rot + 'deg)');
	        menu.css('webkitTransform', 'rotate(' + rot + 'deg)');
	        if (rot / 180 % 2 == 0) {
	            toggle.parent().addClass('ss_active');
	            toggle.addClass('close');
	        } else {
	            toggle.parent().removeClass('ss_active');
	            toggle.removeClass('close');
	        }
	        $(this).data('rot', rot);
	    });
	    menu.on('transitionend webkitTransitionEnd oTransitionEnd', function () {
	        if (rot / 180 % 2 == 0) {
	            $('#ss_menu div i').addClass('ss_animate');
	        } else {
	            $('#ss_menu div i').removeClass('ss_animate');
	        }
	    });
	});
	</script>
</body>
</html>
