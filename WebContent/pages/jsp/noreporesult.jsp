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

		<div class="row" >
			<div class="myjumbotron2" >
				<div class="row " name="searchLogo">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="col-lg-4">
							<img src="./images/bigsoso.png" class="img-responsive"
								alt="Responsive image" width="180px">
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

							<div class="col-lg-2">
								<button type="button" class="btn btn-info btn-lg"
									onclick="return searchQuery()">SOSO!</button>
							</div>
						</form>
					</div>
				</div>
				<br />

				<div class="row" name="searchDes">
					<div class="col-lg-8 col-lg-offset-2">
						<font color="#666666">Sorry, We cannot find any results based on your keywords "${requestScope.query}". (${searchtime} seconds)</font>
					</div>
				</div>
				<div class="row row-lg-offset-5" style="height: 500px">
					<div class="col-lg-5 col-lg-offset-2">
						<h3>
							&nbsp <b>Here are some advices:</b>
						</h3>
						<ul>
							<li><h4>Please check whether your input words are correct.</h4></li>
							<li><h4>Please try some other keywords.</h4></li>
							<li><h4>Please try some common words.</h4></li>
							<li><h4>Please try to reduce the number of word.</h4></li>
						</ul>
					</div>
					<div class="col-lg-4">
							<img src="./images/noresult.png" class="img-responsive"
								alt="Responsive image" width="200px">
					</div>
					
					
				</div>

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


	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/docs.min.js"></script>
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
	
	function searchQuery(){
		if(trim($("#queryText").val()) == "")
			return false;
		else
			$("#searchform").submit();
	}
	
	function trim(str){ //删除左右两端的空格
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
</SCRIPT>

</body>
</html>


