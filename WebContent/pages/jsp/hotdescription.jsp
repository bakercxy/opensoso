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

			<div class="row myjumbotron2">
			<!--词图选择导航栏-->
			<div class="col-lg-2">
				<div class="myjumbotron4">
					<div class="row" style="height: 1000px">
						<ul class="nav nav-sidebar">
							<h4 class="text-oringe text-center">HOT WORDS</h4>
							<li class="text-left"><a href="hotlanguage.html">▪
									Languages Words</a></li>
							<li class="text-left"><a href="hotcommit.html">▪ Commits
									Words</a></li>
							<li class="text-left"><a href="hotdescription.html">▪
									Descriptions Words</a></li>
						</ul>
					</div>
				</div>
			</div>


				<!--词图展示区-->
				<div class="col-lg-10 ">
					<div class="myjumbotron2">
						<div class="row text-center" style="height: 1000px">
							<div class="col-lg-12">
								<h1 class="text-oringe text-center">Hot Descriptions Words</h1>
								<!--操作选择-->
								<div class="row row-lg-offset-2">
									<form class="form-inline" role="form">

										<div class="form-group">
											<label for="frequency" class="control-label">Least
												Language Frequency</label> <select class="form-control" id="frequency">
												<option>All</option>
												<option>1%</option>
												<option>0.5%</option>
												<option>0.1%</option>
												<option>0.05%</option>
												<option>0.01%</option>
												<option>0.005%</option>
												<option>0.001%</option>
												<option selected="selected">0.0001%</option>
											</select>
										</div>

										<div class="form-group">
											<label for="Min FontSize" class="control-label">Min
												FontSize</label> <select class="form-control" id="minsize">
												<option>5</option>
												<option selected="selected">10</option>
												<option>15</option>
												<option>20</option>
												<option>25</option>
											</select>
										</div>
										
										<div class="form-group">
											<label for="Max FontSize" class="control-label">Max
												FontSize</label> <select class="form-control" id="maxsize">
												<option>60</option>
												<option>70</option>
												<option selected="selected">80</option>
												<option>90</option>
												<option>100</option>
											</select>
										</div>
										<button type="button" class="btn btn-warning" onClick="sendRequest()">Go</button>
									</form>
								</div>


								<!--词图图片展示-->
								<div class="row row-lg-offset-3">
										<div class="col-lg-12">
											<!-- Social Graph -->
											<div class="row" style="height: 500px">
												<div class="col-lg-12" id="wordGraph"></div>
											</div>
											<div class="row row-lg-offset-2">
										<div class="col-lg-12 text-left" style="font-size: 16px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										The hot descriptions words graph describes the most common and
											popular words the developers always used to describe what they would do in this project when they created the repository in the recent three
											years. we calculated from nearly 2.5 million repositories and
											gave the statistics that there're nearly 150 words have a
											high frequency, in which "library", "project", "framework" are the top 3
											development words the developers would like to use in their
											commits. In addition, the usage percent of "application", "plugin",
											"web", "file", "api", "data", "repository" and
											other four words are all above 1.5% in the open source community.</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /container -->
			</div>
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
				<script src="./js/d3.js"></script>
				<script src="./js/d3.layout.cloud.js"></script>


				<script type="text/javascript">
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
			var url = "queryword.do?type=des&" + "freq=" + $('#frequency').val() + "&minsize=" + $('#minsize').val() + "&maxsize=" + $('#maxsize').val();
			XMLHttpReq.open("GET", encodeURI(url), true);
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
			XMLHttpReq.send(null); // 发送请求
		}
		
		//处理返回信息函数
		function processResponse() {
			if (XMLHttpReq.readyState == 4) { // 判断对象状态
				if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
					generateWordCloud();
					//setTimeout("sendRequest()", 10000);
				} else { //页面不正常
					window.alert("您所请求的页面有异常。");
				}
			}
		}
		
		function generateWordCloud() {
			
			var result = XMLHttpReq.responseText;
			var json = eval("(" + result + ")");
			//window.alert(json.wordlist);
			var word = new Array();
			var weight = new Array();
			
			for(var i=0; i<json.wordlist.length; i++){
				//content += json.wordlist[i].word+":"+json.wordlist[i].weight+"\n";
				word[i] = json.wordlist[i].word;
				weight[i] = json.wordlist[i].weight;
			}
			
			$("#wordGraph").empty();
			word_cloud_show(word,weight);
		};
		
		function word_cloud_show(array_words, array_weight) {

			var fill = d3.scale.category20();

			d3.layout.cloud().size([ 900,500]).words(
					array_words.map(function(d, index) {
						return {
							text : d,
							size : array_weight[index]
						};
					})).padding(1).rotate(function() {
				return ~~(Math.random() * 120) - 60
			}).font("Impact").fontSize(function(d) {
				return d.size;
			}).on("end", draw).start();

			function draw(words) {
				d3.select("#wordGraph").append("svg").attr("width", 900).attr(
						"height", 500).append("g").attr("transform",
						"translate(450,250)").selectAll("text").data(words)
						.enter().append("text").style("font-size", function(d) {
							return d.size + "px";
						}).style("font-family", "Impact").style("fill",
								function(d, i) {
									return fill(i);
								}).attr("text-anchor", "middle").attr(
								"transform",
								function(d) {
									return "translate(" + [ d.x, d.y ]
											+ ")rotate(" + d.rotate + ")";
								}).text(function(d) {
							return d.text;
						});
			}
		}
		
		$(function() {
			sendRequest();
		});
		
	</script>
</body>
</html>







