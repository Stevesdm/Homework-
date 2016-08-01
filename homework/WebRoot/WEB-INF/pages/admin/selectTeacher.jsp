<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>沈工大作业管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	   
	<!-- BOOTSTRAP STYLES-->
    <link href="<%=basePath%>/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="<%=basePath%>/fonts/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- PAGE LEVEL STYLES -->
    <link href="<%=basePath%>/css/prettyPhotoAdmin.css" rel="stylesheet" />
    <!--CUSTOM BASIC STYLES-->
    <link href="<%=basePath%>/css/basic.css" rel="stylesheet" />
    <!--CUSTOM MAIN STYLES-->
    <link href="<%=basePath%>/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.useso.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

  </head>
  
  <body>
    <div id="wrapper">
             <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="admin/homepage">沈工大作业管理</a>
            </div>

            <div class="header-right">

                <a href="javascript:void(0);" class="btn btn-info" title="New Message"><b>30 </b><i class="fa fa-envelope-o fa-2x"></i></a>
                <a href="javascript:void(0);" class="btn btn-primary" title="New Task"><b>40 </b><i class="fa fa-bars fa-2x"></i></a>

            </div>
        </nav>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <div class="user-img-div">
                            <img src="<%=basePath%>/img/suticon.png" class="img-thumbnail" />
                            <div class="inner-text">
                           		${sessionScope.user.aname}
                            <br />
                                <small> </small>
                            </div>
                        </div>
                    </li>
               
                    <li>
                        <a href="javascript:void(0);"><i class="fa fa-desktop "></i>学生管理<span class="fa arrow"></span></a>
                         <ul class="nav nav-second-level">
                            <li>
                                <a href="admin/addStudent"><i class="fa fa-desktop"></i>添加学生</a>
                            </li>
                            <li>
                                <a href="admin/selectStudent"><i class="fa fa-code "></i>查询学生</a>
                            </li>
                       
                        </ul>
                    </li>
                     <li>
                        <a href="javascript:void(0);"><i class="fa fa-cube "></i>教师管理<span class="fa arrow"></span></a>
                         <ul class="nav nav-second-level">
                            <li>
                                <a href="admin/addTeacher"><i class="fa fa-desktop"></i>增加教师</a>
                            </li>
                            <li>
                                <a href="admin/selectTeacher"><i class="fa fa-flash "></i>查询教师</a>
                            </li>                    
                        </ul>
                    </li>

                     <li>
                        <a href="javascript:void(0);"><i class="fa fa-rocket "></i>课程管理<span class="fa arrow"></span></a>
                         <ul class="nav nav-second-level">
                           
                             <li>
                                <a href="admin/addCourse"><i class="fa fa-desktop "></i>增加课程</a>
                            </li>
                             <li>
                                <a href="admin/selectCourse"><i class="fa fa-code "></i>查询课程</a>
                            </li>
                           
                        </ul>
                    </li>
                    <li>
                        <a href="admin/setCourse"><i class="fa fa-wrench "></i>设置学生课程</a>
                    </li>
					
					 <li>
                        <a href="admin/manageHomework"><i class="fa fa-square-o "></i>管理学生作业</a>
                    </li>
                    
                    <li>
                        <a href="admin/manageAdvice"><i class="fa fa-square-o "></i>管理反馈信息</a>
                    </li>
                    
                    <li>
                        <a href="admin/manageError"><i class="fa fa-square-o "></i>管理错误信息</a>
                    </li>
					
                    <li>
                        <a href="admin/changepwd"><i class="fa fa-dot-circle-o "></i>修改密码</a>
                    </li>
					
					 <li>
                        <a href="logout"><i class="fa fa-sign-in "></i>注销登陆</a>
                    </li>
                  
                </ul>

            </div>

        </nav>
            <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">沈工大作业管理后台</h1>
                        <h1 class="page-subhead-line">教师管理 </h1>

                    </div>
                </div>
                <!-- /. ROW  -->
                <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
               <div class="panel panel-info">
						<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
								  <div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingOne">
									  <h4 class="panel-title">
										<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
											按照教师号查询
										</a>
									  </h4>
									</div>
									<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
									  <div class="panel-body">
											<form role="form" action="admin/selectTeacherByTno" method="post">
													<div class="form-group">
														<label>教师号</label>													
														  <input class="form-control" type="text" name="tno">
													</div>							
													<button type="submit" class="btn btn-info">确认</button>
											</form>
									  </div>
									</div>
								  </div>
								  <div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingTwo">
									  <h4 class="panel-title">
										<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
										 按照教师姓名查询
										</a>
									  </h4>
									</div>
									<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
									  <div class="panel-body">
											<form role="form" action="admin/selectTeacherByTname" method="post">
													<div class="form-group">
														<label>教师名</label>													
														  <input class="form-control" type="text" name="tname">
													</div>	
													<button type="submit" class="btn btn-info">确认</button>
											</form>
									  </div>
									</div>
								  </div>
							</div>
		   
                        <div class="panel-heading">
                        	   管理教师
                        </div>
                        <div class="panel-body">
                            <form role="form" action="" method="">
								 <div class="row">
									<div class="col-md-12">
									  <!--   Kitchen Sink -->
										<div class="panel panel-default">
											
											<div class="panel-body">
												<div class="table-responsive">
													<table class="table table-striped table-bordered table-hover">
														<thead>
															<tr>
																<th>教师账号</th>
																<th>姓名</th>														
																<th>操作</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${teacherList}" var="t">
															<tr>
																<td>${t.tno}</td>
																<td>${t.tname}</td>													
																<td><a href="admin/updateTeacher?tno=${t.tno}" class="btn btn-info btn-xs " role="button">修改</a>|
																	<a href="admin/deleteTeacher?tid=${t.tid}" class="btn btn-danger btn-xs" role="button">删除</a>
																</td>
															</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
									
								</div>

                            </form>
                            </div>
                        </div>
                        </div>

					</div>


            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
		<!--footer-->
    <div id="footer-sec">
        Copyright &copy; 2016.沈阳工业大学All rights reserved.
    </div>
    <!-- /. FOOTER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="<%=basePath%>/js/jquery-1.11.3.min.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="<%=basePath%>/js/bootstrap.js"></script>
     <!-- PAGE LEVEL SCRIPTS -->
    <script src="<%=basePath%>/js/jquery.prettyPhoto.js"></script>
    <script src="<%=basePath%>/js/jquery.mixitup.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="<%=basePath%>/js/jquery.metisMenu.js"></script>
    <!-- CUSTOM SCRIPTS -->
    <script src="<%=basePath%>/js/custom.js"></script>
     <!-- CUSTOM Gallery Call SCRIPTS -->
    <script src="<%=basePath%>/js/galleryCustom.js"></script>
  </body>
</html>