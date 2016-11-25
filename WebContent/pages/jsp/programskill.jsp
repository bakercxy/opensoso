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

<title>G-Hunter</title>

<!-- Bootstrap core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">

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
		

		<div class="row">
			<div class="col-lg-12">
				<div class="myjumbotron2">
					<div class="row">
						<div class="col-lg-12">
							<h1 class="text-oringe text-center">Language Usability Trends</h1>
							
						</div>
					</div>
					<div class="myjumbotron2"></div>
					<div class="row">
						<div class="col-md-10 col-md-offset-1">
							
							<!-- Program Skill -->
							<div class="row">
								<div class="col-lg-12">

									<!-- 此处应插入Program Skill词图 -->

											<img src="./images/programskill.png" class="img-responsive" alt="Responsive image" >

								</div>
								</div>
								<div class="myjumbotron2"></div>
									<div class="row row-lg-offset-1">
									
										<div class="col-lg-12">A history mining is used to balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala balabala ...</div>
									</div>
								</div>
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
  </div>
</footer>


    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>


