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
  </head>
  
  <body>
	<form action="${param.action}" method="get" name="pageRollForm">
		当前是第${pageroll.currPage}页|
		共${pageroll.pageCount }页|
		共${pageroll.totalCount}条记录|
		
		<input type="hidden" name="currPage" value="${pageroll.currPage}">
		<input type="hidden" name="pageCount" value="${pageroll.pageCount}">
		
		<c:if test="${param.para!=null}">
		<input type="hidden" name="sctimes" value="${param.para}">
		<input type="hidden" name="sclass" value="${param.para1}">
		</c:if>
		
		<c:if test="${param.para2!=null}">
		<input type="hidden" name="sno" value="${param.para2}">
		</c:if>
		
		<c:if test="${param.para3!=null}">
		<input type="hidden" name="sname" value="${param.para3}">
		</c:if>
		
		<input type="button" value="首页" onclick="firstPage()"/>
		<input type="button" value="上一页" onclick="prePage()"/>
		<input type="button" value="下一页" onclick="nextPage()"/>
		<input type="button" value="尾页" onclick="lastPage()"/>
	</form>
	
	<script>
		function firstPage(){
			var _currentPage=document.pageRollForm.currPage.value;
			if(_currentPage==1){
				alert("已经是第一页了");
				return;
			}
			document.pageRollForm.currPage.value=1;
			document.pageRollForm.submit();
		}
		function prePage(){
			var _currentPage=document.pageRollForm.currPage.value;
			if(_currentPage==1){
				alert("已经是第一页了");
				return;
			}
			document.pageRollForm.currPage.value=--_currentPage;
			document.pageRollForm.submit();
		}
		function nextPage(){
			var _currentPage=document.pageRollForm.currPage.value;
			var _pageCount=document.pageRollForm.pageCount.value;
			if(_pageCount==_currentPage){
				alert("已经是最后一页了");
				return;
			}
			document.pageRollForm.currPage.value=++_currentPage;
			document.pageRollForm.submit();
		}
		function lastPage(){
			var _currentPage=document.pageRollForm.currPage.value;
			var _pageCount=document.pageRollForm.pageCount.value;
			if(_pageCount==_currentPage){
				alert("已经是最后一页了");
				return;
			}
			document.pageRollForm.currPage.value=_pageCount;
			document.pageRollForm.submit();
		}
	</script>
	
  </body>
</html>
