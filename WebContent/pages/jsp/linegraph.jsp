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

		<!--line graph display-->
		<div class="row">
			<div class="myjumbotron2">
				<div class="row text-center" style="height: 1000px">
					<div class="col-lg-12">
						<h1 class="text-oringe text-center">Language Usability Trends</h1>
						<!--操作选择-->
						<div class="row row-lg-offset-2">
							<form class="form-inline" role="form">

								<div class="form-group">
									<div class="form-group">
										<label for="lang" class="control-label">Language Type</label>
									</div>

									&nbsp;&nbsp;
									<div class="form-group">
										<input type="checkbox" class="lang" name="JavaScript"
											checked=true>JavaScript </input> <input type="checkbox"
											class="lang" name="Java" checked=true>Java</input> <input
											type="checkbox" class="lang" name="Ruby" checked=true>Ruby</input>
										<input type="checkbox" class="lang" name="Python" checked=true>Python</input>
										<input type="checkbox" class="lang" name="PHP" checked=true>PHP</input>
										<br /> <input type="checkbox" class="lang" name="CSS"
											checked=true>CSS</input> &nbsp; <input type="checkbox"
											class="lang" name="C" checked=true>C</input> &nbsp; <input
											type="checkbox" class="lang" name="C++" checked=true>C++</input>
										&nbsp; <input type="checkbox" class="lang" name="Objective-C"
											checked=true>Objective-C</input> &nbsp; <input
											type="checkbox" class="lang" name="Shell" checked=true>Shell</input>
									</div>
								</div>

								<br /> <br />

								<div class="form-group">
									<div class="form-group" id="year-label">
										<label for="year" class="control-label">Year</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>

									<div class="form-group" id="year-select">
										<select class="form-group" id="year">
											<option selected="selected">All</option>
											<option>2014</option>
											<option>2013</option>
											<option>2012</option>
										</select>
									</div>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<div class="form-group" id="button">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-warning"
											onClick="sendRequest()">Go</button>
									</div>
								</div>
							</form>
						</div>


						<!--图片展示-->
						<div class="row row-lg-offset-3">
							<div class="col-lg-10 col-lg-offset-1">
								<div class="row">
									<div class="col-lg-12" id="container"></div>
								</div>
								<div class="row row-lg-offset-3">
									<div class="col-lg-10 col-lg-offset-1 text-left"
										style="font-size: 16px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										We collected nearly 10 million repos in Github in resent three
										years from Jan 2012 to Sep 2014 and calculated the create time
										of every language used in each repo. To visualize this trend,
										firstly, We count the frequency of all repository languages
										which appear in our dataset and then top ten frequent
										languages are selected; for instance, Shell, C, PHP, C++ and
										so on. At last, to show this tendency, we use line graph to do
										the visualization.
										<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbspWe put the trendency of the Top 10 popular
										language in this graph through analysis. The top ten popular
										languages used in Github are JavaScript, Java, Ruby, Python,
										PHP, CSS, C, C++, Objective-C, and Shell.</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /container -->
		</div>
	</div>

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
	<script src="./js/highcharts.js"></script>
	<script src="./js/modules/data.js"></script>
	<script src="./js/modules/exporting.js"></script>
	<!-- Additional files for the Highslide popup effect -->
	<script type="text/javascript"
		src="http://www.highcharts.com/media/com_demo/highslide-full.min.js"></script>
	<script type="text/javascript"
		src="http://www.highcharts.com/media/com_demo/highslide.config.js"
		charset="utf-8"></script>


	<script type="text/javascript">
		$(function() {
			sendRequest();
		});

		var XMLHttpReq;
		//创建XMLHttpRequest对象
		function createXMLHttpRequest() {
			if (window.XMLHttpRequest) { //Mozilla 浏览器
				XMLHttpReq = new XMLHttpRequest();
			} else if (window.ActiveXObject) { // IE浏览器
				try {
					XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
					}
				}
			}
		}

		//发送请求函数
		function sendRequest() {
			createXMLHttpRequest();
			var para1 = '';//for language
			//var para = $('#lang').val();
			var checkboxes = $('.lang');
			var para2 = $('#year').val();

			for (var i = 0; i < checkboxes.length; i++) {
				if (checkboxes[i]["checked"] == true) {
					if (para1 != '')
						para1 += '_' + checkboxes[i]["name"];
					else
						para1 += checkboxes[i]["name"];
				}

			}

			//console.log(para1);
			var url = "querylangtime.do?lang=" + encodeURIComponent(para1)
					+ "&year=" + encodeURIComponent(para2);
			XMLHttpReq.open("GET", url, true);
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
			XMLHttpReq.send(null); // 发送请求
		}

		//处理返回信息函数
		function processResponse() {
			if (XMLHttpReq.readyState == 4) { // 判断对象状态
				if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
					generateGraph();
					//setTimeout("sendRequest()", 10000);
				} else { //页面不正常
					window.alert("您所请求的页面有异常。");
				}
			}
		}

		function generateGraph() {
			var result = XMLHttpReq.responseText;
			var obj = eval(result);
			$('#container').highcharts({
				chart : {
					type : 'spline'
				},

				title : {
					text : ''
				},

				data : {
					columns : obj
				},

				yAxis : {
					title : {
						text : 'Create_Repo'
					}
				},

				xAxis : {
					title : {
						text : 'Time'
					}
				},

			});
		};
	</script>
</body>
</html>