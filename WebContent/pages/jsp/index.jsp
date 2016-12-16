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

<!-- Custom styles for this template -->
<link href="./css/bootstrap-theme.css" rel="stylesheet">

<!-- Bootstrap core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
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
		<div class="row">
			<div class="myjumbotron2">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="text-oringe text-center">DATA FROM GITHUB,&nbsp&nbsp&nbsp&nbspSHOW IN G-HUNTER.</h1>
						<h1 class="text-oringe text-center"></h1>
					</div>
				</div>
				<div class="row">
					<div class="myjumbotron2"></div>
					<div class="col-lg-6">
						<div class="myjumbotron2">
							<!-- Ontology Schema -->
							<div class="row">
								<div class="col-lg-12">
									<div class="row">
										<h3 class="text-red">Ontology Schema</h3>
									</div>
									<div class="row">
										<div class="col-lg-offset-1 col-lg-10">
											<img
												src="./images/ontologyschema.png" class="img-responsive"
												alt="Responsive image" width="350px">
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11 text-right">
											<a href="ontologyschema.html" class="clickme">Click here</a>
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11">&nbsp&nbsp&nbsp&nbspThe ontology
											schema module shows the relationship between property and
											class, class and class. We can reference the inner
											relationship between property and class, class and class. The
											original node is a class which has many edges pointer to the
											property.</div>
									</div>
								</div>
							</div>

							<!-- Time&Language-->
							<div class="row row-lg-offset-2">
								<div class="col-lg-12">
									<div class="row">
										<h3 class="text-red">Language Trendency Graph</h3>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<img src="./images/linegraph.png"
												class="img-responsive" alt="Responsive image" width="420px">
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11 text-right">
											<a href="linegraph.html" class="clickme">Click here</a>
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11">&nbsp&nbsp&nbsp&nbspTime-language
											trending graph has two dimensions including time dimension
											(x-axis) and language of repositories, which are created in
											the corresponding time (y-axis). We count the frequency of
											all repository languages which appear in our dataset and then
											top ten frequent languages are selected; for instance, Shell,
											C, PHP, C++ and so on. At last, to show this tendency, we use
											line graph to do the visualization.</div>
									</div>
								</div>
							</div>

							<!-- Semantic Search-->
							<div class="row row-lg-offset-2">
								<div class="col-lg-12">
									<div class="row">
										<h3 class="text-red">Expert Search</h3>
									</div>
									<div class="row">
										<div class="col-lg-offset-1 col-lg-8">
											<img
												src="./images/gsearch.png" class="img-responsive"
												alt="Responsive image" width="350px">
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11 text-right">
											<a href="professionalsearch.html" class="clickme">Click
												here</a>
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11">&nbsp&nbsp&nbsp&nbspG-Search is a search engine 
											to provide repository or developers in software engineering expert field. Words like "how to use java". The search engine can
											give you the top 100 user or repo which is related to the
											word "java". We use linked data to help you to find more
											related users or repos. For example, if you want to find
											script language, you can input "how to use script to write
											web page". Then our search engine will not only give the
											result including "script" or "web",but also give you the
											result include "css3","python","xml","ruby" since they are a
											kind of script language.</div>
									</div>
								</div>
							</div>
							


						</div>

					</div>

					<div class="col-lg-6">
						<div class="myjumbotron2">

							<!-- Word Cloud -->
							<div class="row">
								<div class="col-lg-12">
									<div class="row">
										<h3 class="text-red">Word Cloud Graph</h3>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<img
												src="./images/wordcloud.png" class="img-responsive"
												alt="Responsive image" width="430px">
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11 text-right">
											<a href="wordcloud.html" class="clickme">Click here</a>
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11">&nbsp&nbsp&nbsp&nbspThe data
											visualization module is intended to show the relation between
											repositories and users from different point of view. Besides,
											it reveals the usability trends and popularity of different
											programming languages.</div>
									</div>
								</div>
							</div>
							
							<!-- Collaboration Pattern -->
							<div class="row row-lg-offset-2">
								<div class="col-lg-12">
									<div class="row">
										<h3 class="text-red">Collaborative Pattern</h3>
									</div>
									<div class="row">
										<div class="col-lg-offset-1 col-lg-10">
											<img
												src="./images/collaborationpattern.png"
												class="img-responsive" alt="Responsive image" width="450px">
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11 text-right">
											<a href="collaborativepattern.html" class="clickme">Click
												here</a>
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11">&nbsp&nbsp&nbsp&nbspCollaborative
											Pattern which reflects the structure of developers’
											collaboration is shown in this section. To estimate this
											pattern, we use frequent subgraph which appears in
											collaborative graph to substitute. Finally, we use Gspan
											Algorithm and Ullmann Algorithm to retrieve 14 subgraphs
											reflecting 14 kinds of collaborative pattern in our
											collaborative graph totally.</div>
									</div>
								</div>
							</div>

							<!-- Repository Search-->
							<div class="row row-lg-offset-2">
								<div class="col-lg-12">
									<div class="row">
										<h3 class="text-red">Open-source Repository Search</h3>
									</div>
									<div class="row">
										<div class="col-lg-offset-1 col-lg-8">
											<img
												src="./images/bigsoso.png" class="img-responsive"
												alt="Responsive image" width="350px">
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11 text-right">
											<a href="repositorysearch.html" class="clickme">Click here</a>
										</div>
									</div>
									<div class="row row-lg-offset-1">
										<div class="col-lg-11">&nbsp&nbsp&nbsp&nbsp
										OpenSoSo is an application for searching repositories in open source community. In this engine, we not only consider the repository’s name, but also software characteristics. Those characteristics has been represented by tags. To complete this work, we need the help of a software programming oriented taxonomy that is built by us with tags from StackOverflow.</div>
									</div>
								</div>
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
				<li><a href="http://base.sjtu.edu.cn/home/doku.php?id=mem:xycai">About Me</a></li>
				<li>Email:bakercxy@163.com</li>
			</ul>
			<ul class="list-inline text-center">
				<li>Copyright 2014-2016</li>
			</ul>
		</div>
	</footer>

	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="./js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>