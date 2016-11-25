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

				<div class="myjumbotron2"></div>
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
					
						<!-- language association table -->
						<div class="row">
							<div class="col-lg-12">
								<h1 class="text-center">Language Association</h1>
							</div>
						</div>
						
						<div class="row row-lg-offset-2">
							<div class="col-lg-12">
								<table class="table">
									<TR>
				  						<th><h4>No.</h4></th>
				  						<th><h4>Language Association</h4></th>
				 						 <th><h4>P(x,y)</h4></th>
									</TR>
									<TR>
										<TD>1</TD>
										<TD>JavaScript,&nbsp CSS</TD>
										<TD>0.164302325581</TD>
									</TR>
									<TR>
										<TD>2</TD>
										<TD>Python,&nbsp Shell</TD>
										<TD>0.115581395349</TD>
									</TR>
									<TR>
										<TD>3</TD>
										<TD>Shell,&nbsp JavaScript</TD>
										<TD>0.111976744186</TD>
									</TR>
									<TR>
										<TD>4</TD>
										<TD>C,&nbsp Shell</TD>
										<TD>0.102093023256</TD>
									</TR>
									<TR>
										<TD>5</TD>
										<TD>C,&nbsp C++</TD>
										<TD>0.0998837209302</TD>
									</TR>
									<TR>
										<TD>6</TD>
										<TD>Shell,&nbsp CSS</TD>
										<TD>0.0861627906977</TD>
									</TR>
									<TR>
										<TD>7</TD>
										<TD>JavaScript,&nbsp Ruby</TD>
										<TD>0.0841860465116</TD>
									</TR>
									<TR>
										<TD>8</TD>
										<TD>Shell,&nbsp C++</TD>
										<TD>0.0741860465116</TD>
									</TR>
									<TR>
										<TD>9</TD>
										<TD>C,&nbsp Shell,&nbsp C++</TD>
										<TD>0.0741860465116</TD>
									</TR>
									<TR>
										<TD>10</TD>
										<TD>Python,&nbsp JavaScript</TD>
										<TD>0.0717441860465</TD>
									</TR>
									<TR>
										<TD>11</TD>
										<TD>JavaScript,&nbsp PHP</TD>
										<TD>0.0663953488372</TD>
									</TR>
									<TR>
										<TD>12</TD>
										<TD>Shell,&nbsp Perl</TD>
										<TD>0.0658139534884</TD>
									</TR>
									<TR>
										<TD>13</TD>
										<TD>Shell,&nbsp Ruby</TD>
										<TD>0.0638372093023</TD>
									</TR>
									<TR>
										<TD>14</TD>
										<TD>Python,&nbsp C</TD>
										<TD>0.0636046511628</TD>
									</TR>
									<TR>
										<TD>15</TD>
										<TD>Shell,&nbsp JavaScript,&nbsp CSS</TD>
										<TD>0.063023255814</TD>
									</TR>
									<TR>
										<TD>16</TD>
										<TD>Python,&nbsp CSS</TD>
										<TD>0.058023255814</TD>
									</TR>
									<TR>
										<TD>17</TD>
										<TD>C,&nbsp Objective-C</TD>
										<TD>0.0573255813953</TD>
									</TR>
									<TR>
										<TD>18</TD>
										<TD>Python,&nbsp C++</TD>
										<TD>0.0556976744186</TD>
									</TR><TR>
										<TD>19</TD>
										<TD>Ruby,&nbsp CSS</TD>
										<TD>0.0552325581395</TD>
									</TR>
									<TR>
										<TD>20</TD>
										<TD>Ruby,&nbsp Perl</TD>
										<TD>0.025</TD>
									</TR><TR>
										<TD>21</TD>
										<TD>C,&nbsp Objective-C,&nbsp Perl</TD>
										<TD>0.023488372093</TD>
									</TR><TR>
										<TD>22</TD>
										<TD>C,&nbsp C++,&nbsp Shell,&nbsp Perl,&nbsp Objective-C</TD>
										<TD>0.0225581395349</TD>
									</TR>
									<TR>
										<TD>23</TD>
										<TD>Python,&nbsp PHP</TD>
										<TD>0.0218604651163</TD>
									</TR>
									<TR>
										<TD>24</TD>
										<TD>C,&nbsp Assembly,&nbsp C++</TD>
										<TD>0.0187209302326</TD>
									</TR>
									<TR>
										<TD>25</TD>
										<TD>Python,&nbsp C++,&nbsp CSS</TD>
										<TD>0.0179069767442</TD>
									</TR>
									<TR>
										<TD>26</TD>
										<TD>C#,&nbsp CSS</TD>
										<TD>0.011511627907</TD>
									</TR>
									<TR>
										<TD>27</TD>
										<TD>JavaScript,&nbsp Ruby,&nbsp C++</TD>
										<TD>0.0103488372093</TD>
									</TR>
									<TR>
										<TD>28</TD>
										<TD>ASP,&nbsp JavaScript</TD>
										<TD>0.0103488372093</TD>
									</TR>
									<TR>
										<TD>29</TD>
										<TD>C#,&nbsp C++</TD>
										<TD>0.01</TD>
									</TR>
									<TR>
										<TD>30</TD>
										<TD>Ruby,&nbsp C++,&nbsp Java</TD>
										<TD>0.00848837209302</TD>
									</TR>
								</table>
							</div>
						</div>
					
						<div class="myjumbotron2"></div>

						<!-- association rule table -->
						<div class="row">
							<div class="col-lg-12">
								<h1 class="text-center">Association Rule</h1>
							</div>
						</div>
						
						<div class="row row-lg-offset-2">
							<div class="col-lg-12">
								<table class="table">
									<tr>
									  <th>No.</th>
									  <th>Rule</th>
									  <th>P(y|x)</th>
									</tr>

									<tr>
										<td>1</td>
										<td>(Assembly,C++) -> C</td>
										<td>1.0</td>
									</tr>
									<tr>
										<td>2</td>
										<td>(Objective-C,Assembly) -> C</td>
										<td>1.0</td>
									</tr>
									<tr>
										<td>3</td>
										<td>(Python,JavaScript,CSS,C++) -> C</td>
										<td>0.94</td>
									</tr>
									<tr>
										<td>4</td>
										<td>(Python,Objective-C,Shell,CSS) -> C++</td>
										<td>0.93</td>
									</tr>
									<tr>
										<td>5</td>
										<td>(Python,Objective-C,CSS) -> (C,C++)</td>
										<td>0.92</td>
									</tr>
									<tr>
										<td>6</td>
										<td>(Shell,Assembly,Perl) -> (Objective-C,C++)</td>
										<td>0.87</td>
									</tr>
									<tr>
										<td>7</td>
										<td>(C,Shell,Objective-C,CSS) -> Python</td>
										<td>0.72</td>
									</tr>
									<tr>
										<td>8</td>
										<td>(Shell,C++,Perl) -> (Python,C)</td>
										<td>0.69</td>
									</tr>
									<tr>
										<td>9</td>
										<td>(Objective-C,Shell,C++) -> Python</td>
										<td>0.63</td>
									</tr>
									<tr>
										<td>10</td>
										<td>(C,CSS,C++) -> (Objective-C,Shell)</td>
										<td>0.58</td>
									</tr>
									<tr>
										<td>11</td>
										<td>(Python,Shell,C++) -> (C,Perl)</td>
										<td>0.52</td>
									</tr>
									<tr>
										<td>12</td>
										<td>(C,CSS,C++) -> (Python,Objective-C) -> Objective-C</td>
										<td>0.42</td>
									</tr>
									<tr>
										<td>13</td>
										<td>(Python,Ruby) -> (Shell,C++)</td>
										<td>0.42</td>
									</tr>
									<tr>
										<td>14</td>
										<td>(Objective-C,Perl) -> (C,CSS)</td>
										<td>0.42</td>
									</tr>
									<tr>
										<td>15</td>
										<td>(Python,Objective-C,C++) -> (C,CSS)</td>
										<td>0.39</td>
									</tr>
									<tr>
										<td>16</td>
										<td>(C,JavaScript,C++) -> (Python,Shell,CSS)</td>
										<td>0.39</td>
									</tr>
									<tr>
										<td>17</td>
										<td>(CSS,C++) -> (C,Shell,Python,JavaScript)</td>
										<td>0.38</td>
									</tr>
									<tr>
										<td>18</td>
										<td>(C,CSS) -> (Python,Perl)</td>
										<td>0.36</td>
									</tr>
									<tr>
										<td>19</td>
										<td>(C,Shell,JavaScript) -> Ruby</td>
										<td>0.36</td>
									</tr>
									<tr>
										<td>20</td>
										<td>(Shell,Ruby) -> CSS</td>
										<td>0.36</td>
									</tr>
									<tr>
										<td>21</td>
										<td>(Objective-C,Shell) -> CSS</td>
										<td>0.35</td>
									</tr>
									<tr>
										<td>22</td>
										<td>(C,Shell,JavaScript) -> (Python,Objective-C)</td>
										<td>0.35</td>
									</tr>
									<tr>
										<td>23</td>
										<td>(Python,CSS) -> (C,Shell)</td>
										<td>0.35</td>
									</tr>
									<tr>
										<td>24</td>
										<td>(C,Shell,CSS) -> (JavaScript,C++,Perl)</td>
										<td>0.33</td>
									</tr>
									<tr>
										<td>25</td>
										<td>(Python,C) ->CSS</td>
										<td>0.33</td>
									</tr>
									<tr>
										<td>26</td>
										<td>(PHP,CSS) -> Perl</td>
										<td>0.33</td>
									</tr>
									<tr>
										<td>27</td>
										<td>(Python,C) -> (Shell,JavaScript)</td>
										<td>0.33</td>
									</tr>
									<tr>
										<td>28</td>
										<td>Objective-C -> (Python,Shell)</td>
										<td>0.32</td>
									</tr>
									<tr>
										<td>29</td>
										<td>C -> (Shell,C++,CSS)</td>
										<td>0.16</td>
									</tr>
									<tr>
										<td>30</td>
										<td>(Shell,Perl) -> (C,JavaScript,Objective-C,C++)</td>
										<td>0.16</td>
									</tr>
									
								</table>
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
	</footer>
	
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>


</body>
</html>


