<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="java.io.*"%>

<%
	String path = request.getContextPath();
	String realPath = request.getRealPath("/");
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; // 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量    
	pageContext.setAttribute("basePath", basePath); // 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。
%>




<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href=" <%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/docs.min.js"></script>
	<script src="./js/showdown.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

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

		<div class="row ">
			<div class="myjumbotron2">
				<div class="row">
					<div class="center">
						<h3><Strong>标签评估系统</Strong></h3>
					</div>
				</div>
				<div class="row row-lg-offset-4">
					<div class="col-md-10 col-md-offset-1">
							<div class="row">
								<div class="col-lg-12">
									<div class="row">
										<div class="row" id="title">
											<div class="col-lg-8 col-lg-offset-2">
												<font size="3" color="#000000"><Strong>Repository Name:</Strong> ${requestScope.title}</font><br/><br/><br/>
												<div id = "repoid" value = "${requestScope.id}"><font size="3" color="#000000"><Strong>Repository ID:</Strong> ${requestScope.id}</font></div><br/><br/><br/>
												<font size="3" color="#000000"><Strong>Language:</Strong> ${requestScope.lang}</font><br/><br/><br/>
												<font size="3" color="#000000"><Strong>Description:</Strong> ${requestScope.des}</font><br/><br/><br/>
												<font size="3" color="#000000"><Strong>Readme Text:</Strong></font> <font size="3" id="readme">${requestScope.rm}</font><br/><br/><br/>
												<font size="3" color="#000000"><Strong>Tags:</Strong></font>
												
												<c:forEach items="${requestScope.tags}" var="obj" varStatus="vs">
													<c:if test="${vs.index % 6 == '0'}">
													<div class="checkbox">
													</c:if>
														<label> <input type="checkbox" id="blankCheckbox_${vs.index}" value="${obj}"> ${obj} </label>
													<c:if test="${vs.index % 6 == '5' || vs.last}">
													</div>
													</c:if>
												</c:forEach>
												<input type="hidden" id="repoid" value="${requestScope.id}"/>
											</div>
										</div>
									</div>

									<div class="row row-lg-offset-3">
										<div class="col-lg-4 col-lg-offset-4">
											<button type="button" class="btn btn-warning"
												onclick="return submit()"
												style="width: 120px; font-size: 20px">
												Submit</button>
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


	<script type="text/javascript">
	
	$(function(){ 
		var converter = new showdown.Converter();
	    var text = $("#readme").text();
	   // var text = '#hello, markdown!';
	    var html = converter.makeHtml(text);
	    $("#readme").html(html);
	    
	}); 

	</script>

	<script type="text/javascript">
	
		function submit() {
			var boxes = document.getElementsByTagName("input");
			var val = [];
			for(i=0;i<boxes.length;i++){
		        if(boxes[i].checked == true){
		            val.push(boxes[i].value);
		        }
		    }
			if(val != "")
			{
				 var id = $("#repoid").attr("value");
				    $.post("submittaganalysis?tagresult=" + val + "&repoid=" + id, function(data) {
				    	 	window.location.href="taganalysis.html"; 
				    	});
			}
		}
		
	</script>
</body>
</html>


