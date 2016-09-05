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
 	function showMessage(){
 		var d=document.getElementById("scfile").value;
 		var c=document.getElementById("sctimes").value;
 		if(!d){
 			alert("作业不能为空！请重新上传");
 			document.getElementById("scfile").select();
 			return false;
 		}
 		else{
 			alert("作业名:"+d+"\n作业次:"+c+"\n确认要上传吗？");
 			document.modifyForm.submit();
	 		
	 	}
 	}
	function judgeFile(target){
		var name=target.value;
        var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
        if(fileName !="doc" && fileName !="docx"){
          alert("请选择文件格式文件上传(doc和docx)！");
            target.value="";
            return
        }
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
						<p style="font-size:18px;line-height:49px;margin-bottom:2.1em;border-bottom:1px solid #eceff0;color:#363d40;">提交作业</p>
						<!-- 提交作业 -->
						<div class="content_left">
					<div style="background:url(./img/uploadbg2.jpg);height:225px;width:92%;border:5px solid #fff;margin:0px 10px 10px 10px">
						<p style="text-align:center;font-size:22;color:#53A3C7;"><u>${course.cname}</u></p>
						<p>专业:${sessionScope.user.smajor}</p>
						<p>班级:${sessionScope.user.sclass}</p>
						<p>学号:${sessionScope.user.sno}</p>
						<p>姓名:${sessionScope.user.sname}</p>
					</div>
					<div style="background:url(./img/uploadbg4.jpg);height:310px;width:92%;border:5px solid #FFF;margin:10px 10px 10px 10px 	">
						<p style="margin-bottom:15px;text-align:center;font-size:22;color:#53A3C7"><u>上传作业</u></p>
						
						<form id="uploadHome" name="modifyForm" method="post" action="student/${cno}/upfile" enctype="multipart/form-data" style="font-size:18" >
							<span class="left_sp">选择作业</span><br/>
							<input class="left_ip" type="file" id="scfile" name="attach" onchange="judgeFile(this);"/><br/>
							<span class="left_sp">作业次数</span>	
							
							<button  id="showWork" type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" style="float:right;margin-right:15px;" >
								作业内容
							</button>
							
							<br/>
							<select class="left_ip" id="sctimes"  name="times"> 
								<c:forEach items="${tasklist}" var="task">
									<option value="${task.sctimes}">第${task.sctimes }次&nbsp;&nbsp;&nbsp;${task.task_title} </option>
								</c:forEach>
							</select><br/>
							<input type="button" value="提交" class="btn btn-primary btn-sm" onclick="showMessage()"  style="margin:10px 10px 10px auto;float:right;"/><br/>
							<span style="color:#f00;font-text:10;">${message}</span>
						</form>
					</div>	
				</div>
				
				<div class="content_right">
					<div style="height:125px;width:100%; margin:5px;">
						<!-- 图片 -->
						<img src="./img/upload-bg.jpg"style="border:4px solid #FFF;width:98%;height:125px" >
					</div>
					<div  style="margin:5px;height:420px;font-size:18; /* border:1px solid #53A3C7; */">
             			<table class="tables"style="width:100%; margin-top:8px; table-layout:fixed;" >
							<tr >
								<td style="width: 15%;"><strong>学号</strong></td>
								<td style="width: 15%;"><strong>上传时间</strong></td>
								<td style="width: 10%;"><strong>次数</strong></td>
								<td style="width: 40%;"><strong>作业</strong></td>
								<td style="width: 10%;"><strong>下载</strong></td>
								<td style="width: 10%;"><strong>查看评语</strong></td>
								
							</tr>						

			     			<c:forEach  items="${sclistbypage}" var="s">
			         			<tr>
					   				<td>${s.sno}</td>
					   			<!--  	<td>${s.cno }</td>-->
					   				<td><fmt:formatDate value="${s.uploadtime}"  type="date" dateStyle="default"/></td>
					   				<td >${s.sctimes }</td>
					   				<td><p>${s.scfilename }</p> </td>
					   				<td><a href="student/${s.sno}/download?scfilename=${s.scfilename}&cno=${s.cno}" style="color:blue;"><img src="./img/download1.jpg" style="width:20px;height:20px"></a></td>
					   				<td><a href="javascript:void(0)" style="color:blue;">查看</a></td>
				       			</tr>
				   			</c:forEach>
  	          			</table>
  	          			
         			</div>
         			<div>
         				<jsp:include page="/WEB-INF/pages/util/pageroll.jsp">
         					<jsp:param value="student/upload/${course.cno}" name="action"/>
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
		<!-- 显示作业内容框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">第<span id="TaskSctimes"></span>次作业---<span id="TaskTitle"></span></h4>
		      </div>
		      <div class="modal-body">
		        <div id="homeworkContent">
		        
		        </div>
		      </div>
		      <div class="modal-footer">
		      	<span style="float:left;font-size:12px;" >截止时间:<p id="TaskStoptime"></p></span>
		      	<a   id="downloadTask" class="btn btn-primary " role="button" style="display:none" >下载作业附件</a>
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		      </div>
		    </div>
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
		
		
		<script>
			$(function(){
				$("#showWork").click(function(){
					$.post("student/getHomework",{
						sctimes:$("#sctimes").val(),
						cno:${course.cno}
					},function(data){
						$("#TaskSctimes").html(data.sctimes);
						$("#TaskTitle").html(data.task_title);
						$("#homeworkContent").html(data.task_content);
						$("#TaskStoptime").html(data.stoptime);
						
						if(data.task_file!=null){
						var down="teacher/download/${course.cno}?taskfilename="+data.task_file;
						$("#downloadTask").css("display","");
						$("#downloadTask").attr("href",down);
						}else{
						$("#downloadTask").css("display","none");
						}
					});
					
				});
			})
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
