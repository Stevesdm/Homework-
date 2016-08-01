<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	
	<script type="text/javascript">
//全选
function selectAll(obj){
	for(var i = 0;i<obj.elements.length;i++)
	if(obj.elements[i].type == "checkbox")
	obj.elements[i].checked = true;
}
//反选
function selectOther(obj){
	for(var i = 0;i<obj.elements.length;i++)
	if(obj.elements[i].type == "checkbox" ){
		if(!obj.elements[i].checked)
			obj.elements[i].checked = true;
		else
			obj.elements[i].checked = false;

	}
}
//取消全部
function clearAll(obj){
	for(var i = 0;i<obj.elements.length;i++)
	if(obj.elements[i].type == "checkbox")
	obj.elements[i].checked = false;
}
</script>
	
</head>
	<body  style="background-color:#e5e5e5;">
		    <nav class="navbar navbar-custom  navbar-inverse top-nav-collapse" role="navigation">
		        <div class="container">
		            <div class="navbar-header">
		                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
		                    <i class="fa fa-bars"></i>
		                </button>
		                <a class="navbar-brand page-scroll" href="teacher/homepage">
		                    <i class="fa fa-terminal"></i>SUT作业管理
		                </a>
		            </div>
		
		            <!-- Collect the nav links, forms, and other content for toggling -->
		            <div class="collapse navbar-collapse navbar-left navbar-main-collapse">
		                <ul class="nav navbar-nav">
		                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
		                    <li>
		                        <a class="page-scroll" href="teacher/courseList">课程</a>
		                    </li>                    
		                    <li>
		                        <a class="page-scroll" href="build/underBuild">分享</a>
		                    </li>                    
		                    <li>
		                        <a class="page-scroll" href="build/underBuild">社区</a>
		                    </li>
		                </ul>
		            </div>
		            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
		                    <!-- 判断是否有用户登录 -->
		               <c:choose>  
						   <c:when test="${sessionScope.user!=null}">
						   	<ul class="nav navbar-nav">
				                   <li>
				                   	 <a class="page-scroll" href="build/underBuild" data-toggle="tooltip" data-placement="bottom" title="我的消息">
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
										    <li><p>&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.user.tname}</p></li>
										     <li class="divider"></li>
										     <li><a href="build/underBuild">我的社区</a></li>
										      <li class="divider"></li>
										     <li><a href="teacher/changepwd">修改密码</a></li>
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
						<p style="font-size:18px;line-height:49px;margin-bottom:2.1em;border-bottom:1px solid #eceff0;color:#363d40;">查看作业</p>
						
						<!-- 查看作业 -->
					<div class="content_left" style="height:auto">			    			    
					  <div class="panel panel-default" style="height:160px;">
					   		<div class="panel-heading" >
					      		<h4 class="panel-title" >					       		
					        		欢迎进入${course.cname}课程					       		
					      		</h4>
					    	</div>
					   		<div id="collapseOne" class="panel-collapse collapse in">
					    		<div class="panel-body">
					        		<div style="font-size:14px;">
				    					<p style="margin:20px auto  5px 25px">帐号：${sessionScope.user.tno}</p>
				    					<p style="margin:auto auto 10px 25px">姓名：${sessionScope.user.tname}</p>
					        		</div>
					      		</div>
					    	</div>
					  </div>
					  <div class="panel-group" id="accordion" style="margin-top:0;">
					  	<div class="panel panel-default">
					    	<div class="panel-heading">
					     		<h4 class="panel-title">
					        		<a data-toggle="collapse" data-parent="#accordion" 
					            	href="#collapseTwo">
					            	 按班级次数查询
					       			</a>
					      		</h4>
					    	</div>
					    	<div id="collapseTwo" class="panel-collapse collapse">
					      	  <div class="panel-body">
					       		<div>
					       			<form action="teacher/lookHomeworkByClass/${course.cno}" method="post">
					       				<span>班级</span>
					       				<select name="sclass" style="width:85%;">
					       					<c:forEach items="${sclasslist}" var="c">
					       						<option value="${c.smajor}.${c.sclass}">${c.smajor}&nbsp;&nbsp;&nbsp;${c.sclass}班</option>
					       				 	</c:forEach>
					       				</select><br/> 
					       				<span>次数</span>
					       				<select name="sctimes" style="width:85%;margin:10px auto;">
					       					<c:forEach items="${ tasklist}" var="task">
												<option value="${task.sctimes }">第${task.sctimes }次&nbsp;&nbsp;&nbsp;${task.task_title} </option>
											</c:forEach>
					       				</select><br/>
					       				<input type="submit" value="确定" style="margin-right:2%;float:right;width:100px;"/>
					       			</form>
					       		</div>
					      	  </div>
					    	</div>
					 	</div>
					 	<div class="panel panel-default">
					        <div class="panel-heading">
					      		<h4 class="panel-title">
					        		<a data-toggle="collapse" data-parent="#accordion" 
					          			href="#collapseThree">
					          			按学生学号查询
					        		</a>
					      		</h4>
					    	</div>
					    	<div id="collapseThree" class="panel-collapse collapse">
					      		<div class="panel-body">
					        		<form action="teacher/lookHomeworkByNo/${course.cno}"  method="post">
					        			<span>学号</span> 
					        			<input type="text" name="sno" style="width:85%;magin-top:10px;"><br/>
					       	 			<input type="submit" value="确定" style="margin-right:2%;float:right;width:100px;margin-top:10px;"/>
					        		</form>
					      		</div>
					    	</div>
					 	</div>
					 	<div class="panel panel-default">
					        <div class="panel-heading">
					      		<h4 class="panel-title">
					        		<a data-toggle="collapse" data-parent="#accordion" 
					          			href="#collapseThree0">
					          			按学生姓名查询
					        		</a>
					      		</h4>
					    	</div>
					    	<div id="collapseThree0" class="panel-collapse collapse">
					      		<div class="panel-body">
					        		<form action="teacher/lookHomeworkByName/${course.cno}"  method="post">
					        			<span>姓名</span> 
					        			<input type="text" name="sname" style="width:85%;magin-top:10px;"><br/>
					       	 			<input type="submit" value="确定" style="margin-right:2%;float:right;width:100px;margin-top:10px;"/>
					        		</form>
					      		</div>
					    	</div>
					 	</div>
					 	<div class="panel panel-default">
					        <div class="panel-heading">
					      		<h4 class="panel-title">
					        		<a data-toggle="collapse" data-parent="#accordion" 
					          			href="#collapseFour">
					          			未交作业学生单
					        		</a>
					      		</h4>
					    	</div>
					    	<div id="collapseFour" class="panel-collapse collapse">
					      		<div class="panel-body">
					        	<div>
					       			<form action="teacher/lookHomeworkUnupload/${course.cno}" method="post">
					       				<span>班级</span>
					       				<select name="sclass" style="width:85%;">
					       					<c:forEach items="${sclasslist}" var="c">
					       						<option value="${c.smajor}.${c.sclass}">${c.smajor}&nbsp;&nbsp;&nbsp;${c.sclass}班</option>
					       				 	</c:forEach>
					       				</select><br/>
					       				<span>次数</span>
					       				<select name="sctimes" style="width:85%;margin:10px auto;">
					       					<c:forEach items="${ tasklist}" var="task">
												<option value="${task.sctimes }">第${task.sctimes }次&nbsp;&nbsp;&nbsp;${task.task_title } </option>
											</c:forEach>
					       				</select><br/>
					       				<input type="submit" value="查询" style="margin-right:2%;float:right;width:100px;"/>
					       			</form>
					       		</div>
					      		</div>
					    	</div>
					 	</div>
					 	<div class="panel panel-default">
					        <div class="panel-heading">
					      		<h4 class="panel-title">
					        		<a href="teacher/setTask/${course.cno}">
					          			设置作业
					        		</a>
					      		</h4>
					    	</div>
					 	</div>
				    </div>
				</div>
				
				<div class="content_right" style="height:750px" >
					<div  style="margin:5px;height:700px;font-size:14;width:100%;">
						<button class="btn btn-primary btn-xs" onclick="selectAll(document.check)">全选</button>
						<button class="btn btn-primary btn-xs" onclick="selectOther(document.check)">反选</button>
						<button class="btn btn-primary btn-xs" onclick="clearAll(document.check)">取消</button>
						<a href="teacher/downloadAllByclass?major=${smajor}&class=${sclass}&times=${sctimes}&cno=${course.cno}" class="btn btn-primary btn-xs " role="button">下载本班本次全部作业</a>
						<a href="teacher/downloadUnByclass?major=${smajor}&class=${sclass}&times=${sctimes}&cno=${course.cno}" class="btn btn-primary btn-xs " role="button">下载本班本次未下载的作业</a>
						<button class="btn btn-primary btn-xs"  style="float:right;" onclick="download()">下载所选作业</button>
             			<table class="table table-hover" id="table_comment" >
							<thead>
							 	<tr style="text-align:center;">
							 		<th style="width: 5%;"><strong></strong></th>
									<th style="width: 10%;"><strong>姓名</strong></th>
									<th style="width: 15%;"><strong>学号</strong></th>
									<th style="width: 20%;"><strong>上传时间</strong></th>
									<th style="width: 10%;"><strong>次第</strong></th>
									<th style="width: 20%;"><strong>作业</strong></th>
									<th style="width: 15%;"><strong>评阅</strong></th>
									<th style="width: 15%;"><strong>下载</strong></th>
								</tr>	
							</thead>
								<tbody>
								  <form name="check" method="post" action="teacher/downloadAll">
								  <c:forEach   items="${sclistbypage}" var="s">
									<tr style="overflow:hidden;text-overflow:ellipsis;">
										<th style="width: 5%;"><input type="checkbox" name="fileSelected" value="${s.sc_id}"></th>
										<th style="width: 14%;">${s.sname }</th>
										<th style="width: 15%;">${s.sno }</th>
										<th style="width: 10%;"><fmt:formatDate value="${s.uploadtime}"  type="date" dateStyle="default"/></th>
										<th style="width: 10%;">${s.sctimes }</th>
										<th style="width: 35%;">${s.scfilename }</th>
										<th style="width: 8%;">
											<a href=""><img src="./img/comment.jpg" style="width:20px;height:20px"></a>
										</th>
										<th style="width: 8%;">
										<c:choose>
											<c:when test="${s.downloaded!='1'}">
												<a href="teacher/${s.sno}/download?sc_id=${s.sc_id}&scfilename=${s.scfilename}"><img src="./img/download1.jpg" style="width:20px;height:20px"></a>
											</c:when>
											<c:otherwise>
												<a href="teacher/${s.sno}/download?sc_id=${s.sc_id}&scfilename=${s.scfilename}"><img src="./img/download2.jpg" style="width:20px;height:20px"></a>
											</c:otherwise>
										</c:choose>
										</th>	
									</tr>
								  </c:forEach>	
								  </form>
								</tbody>					
  	          			</table>
         			</div>
         			
         			<div>
         				<jsp:include page="/WEB-INF/pages/util/pageroll.jsp">
         					<jsp:param value="teacher/lookHomeworkByClass/${course.cno}" name="action"/>
         					<jsp:param value="${sctimes}"  name="para"/>
         					<jsp:param value="${sclass}"  name="para1"/>
         				</jsp:include>
         			</div>
         			
				     </div> 
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
		
		<script>
		function download(){
			document.check.submit();
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
