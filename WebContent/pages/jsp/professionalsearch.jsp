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
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href=" <%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>G-Hunter</title>

<!-- Bootstrap core CSS -->
<link href="./css/bootstrap.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">

</head>

<body style="background: #F5F5F5" onload="document.getElementById('queryText').focus()">
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
				<div class="row">
					<div class="row row-lg-offset-10"></div>
					<div class="center">
						<img src="./images/gsearch.png" class="img-responsive"
							alt="Responsive image" width="300px">
					</div>
				</div>
				<div class="myjumbotron2"></div>
				<div class="row row-lg-offset-4" style="height: 400px">
					<div class="col-md-10 col-md-offset-1">

						<!--  -->
						<form action="search.do" name="searchform" id="searchform">
							<div class="row">
								<div class="col-lg-12">
									<div class="row">
										<div class="col-lg-6 col-lg-offset-2">
											<input id="queryText" name="query"
												class="form-control input-lg" type="text"
												placeholder="Please enter your interest">
												
										</div>
										<div class="col-lg-3">
										<select id ="type" name="type" class="form-control input-lg" style="width:100px;font-size: 14px">
  											<option>All</option>
  											<option>Repos</option>
  											<option>Users</option>
										</select>
										</div>
										
										</div>

									<div class="row row-lg-offset-3">	
										<div class="center">
  
										<button type="button" class="btn btn-warning btn-lg"
											onclick="return searchQuery()" style="width:250px; font-size: 28px">&nbsp&nbsp G-Search
											&nbsp&nbsp</button>
											</div>
									</div>
								</div>
							</div>
						</form>
						<div class="myjumbotron2"></div>
						<div class="row row-lg-offset-1"></div>
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

	<script type="text/javascript">
		function searchQuery() {
			if (trim($("#queryText").val()) == "")
				return false;
			else
				$("#searchform").submit();
		}

		function trim(str) { //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
	</script>
    
    
</body>
</html>


