<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"
	 import="java.io.*"	 
%>

<%
	String path = request.getContextPath();
	String realPath = request.getRealPath("/");
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; // 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量    
	pageContext.setAttribute("basePath", basePath); // 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。
%>


<!DOCTYPE html>
<html lang="en">
<head>

<base href=" <%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>G-Hunter</title>

<!-- Bootstrap core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
<link rel="shortcut icon" type="image/x-icon" href="./images/shortcut.png" media="screen" />
<link href="./css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />

<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="./js/star-rating.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/docs.min.js"></script>
</head>

<body style="background: #F5F5F5">
	<div class="container">
		<%
  		//String templatePath = ".\\templates\\titlebar.tmp";//路径 ，比如d: 标示D盘
  		FileReader fr = new FileReader(realPath + "/templates/titlebar.tmp"); //建立FileReader对象,并设定由fr对象变量引用
  		BufferedReader br = new BufferedReader(fr); //建立BufferedReader对象,并设定由br对象变量引
  		String line = "";
  		while((line=br.readLine()) != null)
  			out.println(line); //输出读取得的数据
  		br.close(); //关闭BufferedReader对象
  		fr.close(); //关闭文件
	%>

		<div class="row ">
			<div class="myjumbotron2">
				<div class="row" name="searchLogo">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="col-lg-4">
							<img src="./images/bigsoso.png" class="img-responsive"
								alt="Responsive image" width="200px">
						</div>
					</div>
				</div>
				<div class="row row-lg-offset-2 " name="searchBar">
					<div class="col-lg-8 col-lg-offset-2">
						<form action="reposearch.do" name="searchform" id="searchform">
							<div class="col-lg-8">
								<input id="queryText" name="query" value="${requestScope.query}"
									class="form-control input-lg" type="text"
									placeholder="Please enter your interest">
							</div>
							<div class="col-lg-4">
								<button type="button" class="btn btn-info btn-lg"
									onclick="return searchQuery()">Compare</button>
							</div>
						</form>
					</div>
				</div>
				<br />

				<div class="row" name="searchDes">
					<div class="col-lg-8 col-lg-offset-2">
							<span class="hidden" id="hiddenquery">${requestScope.query}</span>
							<span class="hidden" id="hiddenid1">${requestScope.id1}</span>
							<span class="hidden" id="hiddenid2">${requestScope.id2}</span>
					</div>
					
				</div>
				<br /> <br />
				<div class="row" name="searchResult">
					<div class="col-lg-8 col-lg-offset-2">
						<TABLE class="table" id="searchRecords" cellpadding=2 cellspacing=1 border=0>

							<!-- 循环遍历result -->
							<c:forEach items="${requestScope.result}" var="obj" varStatus="vs">
									<TR>
										<TD>
											<div class="row">
												<div class="row">
													<div class="col-md-9">
														<p><a href="${obj.url}"><h3>${obj.username} / ${obj.markedreponame}</h3></a></p>
													</div>
													<div class="col-md-3">
														<h4><a class="repo-list-stat-item tooltipped tooltipped-s" href="https://github.com/${obj.username}/${obj.reponame}/stargazers" aria-label="Stargazers">
															<span class="glyphicon glyphicon-star"></span>
															${obj.stargazers}
														</a>
														<a class="repo-list-stat-item tooltipped tooltipped-s" href="https://github.com/${obj.username}/${obj.reponame}/network" aria-label="Forks">
														 <span class="glyphicon glyphicon-tree-deciduous"></span>
															${obj.forks}
														 </a>
														 </h4>
													</div>
												</div>
													<p><b>Repository ID:</b><c:choose>
														<c:when test="${obj.id == ''}">
             												-
       													</c:when>
														<c:otherwise>
       														${obj.id}
       													</c:otherwise>
													</c:choose>
													</p>
													 <p><b>Description:</b><c:choose>
														<c:when test="${obj.des == ''}">
             												-
       													</c:when>
														<c:otherwise>
       														${obj.des}
       													</c:otherwise>
													</c:choose>
													</p>
													<p>
													<b>Update Date:</b>
													<c:choose>
														<c:when test="${obj.date == ''}">
             											-
       													</c:when>
														<c:otherwise>
       														${obj.date}
       													</c:otherwise>
													</c:choose>
													</p>
													<p>
													<b>Language:</b>
													<c:choose>
														<c:when test="${obj.language == ''}">
             											-
       													</c:when>
														<c:otherwise>
       														${obj.language}
       													</c:otherwise>
													</c:choose>
													</p>
													
													<p>
													<b>Tags:</b>
													<c:choose>
														<c:when test="${obj.tags == ''}">
             											-
       													</c:when>
														<c:otherwise>
       														${obj.tags}
       													</c:otherwise>
													</c:choose>
													</p>
													<c:if test="${requestScope.rank==true}">
             											<p>
             												<input id="input-id-${vs.index}" type="number" class="rating" min=0 max=5 step=1.0 data-size="xs" >
       													</p>
       												</c:if>	
												</div>
										</TD>
									</TR>
							</c:forEach>
						</TABLE>
					</div>
				</div>
				<br />
						<form action="pairsubmit.do" name="pairform" id="pairform">
							<input type="hidden" name="query" id="query" value="">
							<input type="hidden" name="id1" id="id1" value="">
							<input type="hidden" name="id2" id="id2" value="">
							<input type="hidden" name="result" id="result" value="">
							<div class="row center">
							<button type="button" class="btn btn-info btn-lg" onclick="return chooseFirst()">First</button>
							<button type="button" class="btn btn-info btn-lg" onclick="return chooseSecond()">Second</button>
						</form>
						<br />
				<br />		
			</div>
		</div>
	</div>
	<!-- /container -->
	<footer class="footer ">

		<div class="row footer-bottom">
			<ul class="list-inline text-center">
				<li><a href="http://base.sjtu.edu.cn/home/doku.php?id=mem:xycai">About Me</a></li>
				<li>Email:bakercxy@163.com</li>
			</ul>
			<ul class="list-inline text-center">
				<li>Copyright 2014-2016</li>
			</ul>
		</div>
	</footer>

	
	
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

	<SCRIPT LANGUAGE="JavaScript"> 
	function searchQuery() {
		if (trim($("#queryText").val()) == "")
			return false;
		else
		{
			document.searchform.action="repocompare.do";
			$("#searchform").submit();
		}
	}
	
	
	function trim(str){ //删除左右两端的空格
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	function chooseFirst(){
		document.getElementById("query").value=$("#hiddenquery").text();
		document.getElementById("id1").value=$("#hiddenid1").text();
		document.getElementById("id2").value=$("#hiddenid2").text();
		document.getElementById("result").value=$("#hiddenid1").text();
		$("#pairform").submit();
	}
	
	function chooseSecond(){
		document.getElementById("query").value=$("#hiddenquery").text();
		document.getElementById("id1").value=$("#hiddenid1").text();
		document.getElementById("id2").value=$("#hiddenid2").text();
		document.getElementById("result").value=$("#hiddenid2").text();
		$("#pairform").submit();
	}
	
</SCRIPT>

</body>
</html>


