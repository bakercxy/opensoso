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
<link href="./css/bootstrap.css" rel="stylesheet">
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
							<img src="./images/githublogo.png" class="img-responsive"
								alt="Responsive image" width="200px">
						</div>
					</div>
				</div>
				<div class="row row-lg-offset-2 " name="searchBar">
					<div class="col-lg-8 col-lg-offset-2">
						<form action="githubsearch.do" name="searchform" id="searchform">
							<div class="col-lg-8">
								<input id="queryText" name="query" value="${requestScope.query}"
									class="form-control input-lg" type="text"
									placeholder="Please enter your interest">
							</div>
							<div class="col-lg-4">
								<button type="button" class="btn btn-inverse btn-lg"
									onclick="return searchQuery()">Search</button>
								<button type="button" class="btn btn-inverse btn-lg"
									onclick="return rateQuery()">Rate</button>
							</div>
						</form>
					</div>
				</div>
				<br />

				<div class="row" name="searchDes">
					<div class="col-lg-8 col-lg-offset-2">
						<font color="#666666">The search results of
							"${requestScope.query}" are as belows (${searchtime} seconds)</font>
							<span class="hidden" id="hiddenquery">${requestScope.query}</span>
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
													
													 <p><b>Description:</b><c:choose>
														<c:when test="${obj.des} == ''}">
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
														<c:when test="${obj.date} == ''}">
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
														<c:when test="${obj.language} == ''}">
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
														<c:when test="${obj.tags} == ''}">
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
				<c:choose>
					<c:when test="${requestScope.rank==true}">
						<form action="ratesubmit.do" name="rateform" id="rateform">
							<input type="hidden" name="query" id="query" value="">
							<input type="hidden" name="score" id="score" value="">
							<input type="hidden" name="engine" id="engine" value="">
							<button type="button" class="btn btn-inverse btn-lg center" onclick="return submitRate()">Submit Rate</button>
						</form>
						<br />
					</c:when>
					<c:otherwise> 
						<div class="row" name="pageNavigation">
						<div class="col-md-6 col-md-offset-3" name="pageNavigation">
						<div class="col-md-1 col-md-offset-2" id="thePrePage">
							<a href="javascript:prev()">Prev</a>
						</div>
						<div class="col-md-4 center">
							<label for="goto" class="col-md-3 control-label">Page</label>
							<div class="col-md-1">
								<select onChange="gotoPage(this.value)" name="goto" id="goto">
								</select>
							</div>
						</div>
						<div class="col-md-1" id="theNextPage">
							<a href="javascript:next()">Next</a>
						</div>
					</div>
				</div>
					</c:otherwise>
				</c:choose>
				<br />
			</div>
		</div>
	</div>
	<!-- /container -->
	<footer class="footer ">

		<div class="row footer-bottom">
			<ul class="list-inline text-center">
				<li><a href="#" target="_blank">About Us</a></li>
				<li>Email:G-Hunter@yeah.net</li>
			</ul>
			<ul class="list-inline text-center">
				<li>Copyright 2014</li>
			</ul>
		</div>

	</footer>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

	<SCRIPT LANGUAGE="JavaScript"> 
	var recordPerPage = 10;
	var totalRecords = window.searchRecords.rows.length;
	var pageTotal = ((totalRecords+recordPerPage-1)/recordPerPage)|0;
	var currentPage = 1;
	initSelector();
	showhiddenRecord(currentPage);
	
	function showhiddenRecord(currentPage){ 
		if(currentPage<=1){ 
			thePrePage.innerHTML="Prev"; 
		}else{ 
			thePrePage.innerHTML="<a href=\"javascript:prev()\">Prev</a>"; 
		} 
		
		if(currentPage>=pageTotal){ 
			theNextPage.innerHTML="Next"; 
		}else{ 
			theNextPage.innerHTML="<a href=\"javascript:next()\">Next</a>"; 	
		} 
		
		var startIndex = (currentPage-1)*recordPerPage;
		var endIndex = currentPage*recordPerPage;
		if(endIndex>totalRecords){
			endIndex == totalRecords;
		}

		for (i=0; i < window.searchRecords.rows.length; i++) { 
			if (inRange(startIndex,endIndex,i)){
				window.searchRecords.rows[i].style.display="";
			}
			else{
				window.searchRecords.rows[i].style.display="none";
			}
		}
	} 

	function inRange(start,end,current){
		if(current>=start && current<end)
			return 1;
		return 0
	}
	
	function updateGoto(page){
		window.goto.value=page;
	}
	
	function prev(){ 
		currentPage--; 
		updateGoto(currentPage);
		showhiddenRecord(currentPage); 
	} 

	function next(){ 
		currentPage++; 
		updateGoto(currentPage);
		showhiddenRecord(currentPage); 
	} 

	function gotoPage(num){ 
		currentPage = num;
		showhiddenRecord(num); 
	} 
	
	function initSelector(){
		selector = window.goto;
		for(i=0;i<pageTotal;i++){
			var varItem = new Option((i+1).toString(), i+1); 
			selector.options.add(varItem); 
		}	
	}
	
	function searchQuery() {
		if (trim($("#queryText").val()) == "")
			return false;
		else
		{
			document.searchform.action="githubsearch.do";
			$("#searchform").submit();
		}
	}
	
	function rateQuery() {
		if (trim($("#queryText").val()) == "")
			return false;
		else
		{
			document.searchform.action="githubrate.do";
			$("#searchform").submit();
		}
	}
	
	function trim(str){ //删除左右两端的空格
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	function submitRate(){
		for(i = 0;i<10;i++)
		{	
			if($("#input-id-"+i).val() == 0)
			{
				alert("All results should be rated!");
				return;
			}
		}
		var arr = new Array();
		for(i = 0;i<10;i++)
		{
			arr[i] = $("#input-id-"+i).val();
			json = JSON.stringify(arr);
		}
		//alert(json);
		document.getElementById("query").value=$("#hiddenquery").text();
		document.getElementById("score").value=json;
		document.getElementById("engine").value="github";
		$("#rateform").submit();
	}
	
</SCRIPT>

</body>
</html>


