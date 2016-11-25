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
			<div class="col-lg-10 col-lg-offset-1">
				<div class="myjumbotron2">
					<div class="row text-center" style="height: 1000px">
						<div class="col-lg-12">
							<h1 class="text-oringe text-center">Hot Topic</h1>


							<!--表格展示-->
							<div class="row row-lg-offset-3">
								<table class="table">
								
									<thead>
										<tr>
											<th>Topic</th>
											<th colspan=10 >Words</th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<td>#1: </td>
											<td>kernel</td>
											<td>shell</td>
											<td>adapter</td>
											<td>comment</td>
											<td>system</td>
											<td>gui</td>
											<td>server</td>
											<td>dropbox</td>
											<td>ssl</td>
											<td>linux</td>
										</tr>
										<tr>
											<td>#2: </td>
											<td>web</td>
											<td>website</td>
											<td>javascript</td>
											<td>parser</td>
											<td>personal</td>
											<td>animation</td>
											<td>real</td>
											<td>argument</td>
											<td>thread</td>
											<td>postgresql</td>
										</tr>
										<tr>
											<td>#3: </td>
											<td>software</td>
											<td>java</td>
											<td>twitter</td>
											<td>access</td>
											<td>spring</td>
											<td>fast</td>
											<td>allow</td>
											<td>reader</td>
											<td>engineering</td>
											<td>minercraft</td>
										</tr>
										<tr>
											<td>#4: </td>
											<td>processing</td>
											<td>grid</td>
											<td>distributed</td>
											<td>streaming</td>
											<td>export</td>
											<td>data</td>
											<td>hadoop</td>
											<td>slider</td>
											<td>system</td>
											<td>library</td>
										</tr>
										<tr>
											<td>#5: </td>
											<td>port</td>
											<td>building</td>
											<td>angular</td>
											<td>ssl</td>
											<td>input</td>
											<td>web</td>
											<td>library</td>
											<td>web-based</td>
											<td>websocket</td>
											<td>socket</td>
										</tr>
										<tr>
											<td>#6: </td>
											<td>sort</td>
											<td>analysis</td>
											<td>algorithm</td>
											<td>book</td>
											<td>setting</td>
											<td>board</td>
											<td>data</td>
											<td>haskell</td>
											<td>function</td>
											<td>series</td>
										</tr>
										<tr>
											<td>#7: </td>
											<td>ruby</td>
											<td>rail</td>
											<td>turorial</td>
											<td>gem</td>
											<td>document</td>
											<td>web</td>
											<td>provide</td>
											<td>iphone</td>
											<td>main</td>
											<td>application</td>
										</tr>
										<tr>
											<td>#8: </td>
											<td>game</td>
											<td>rpg</td>
											<td>github</td>
											<td>html5</td>
											<td>play</td>
											<td>snippet</td>
											<td>community</td>
											<td>javascript</td>
											<td>minecraft</td>
											<td>svn</td>
										</tr>
										<tr>
											<td>#9: </td>
											<td>server</td>
											<td>service</td>
											<td>node.js</td>
											<td>web</td>
											<td>http</td>
											<td>javascript</td>
											<td>layout</td>
											<td>proxy</td>
											<td>application</td>
											<td>button</td>
										</tr>
										<tr>
											<td>#10: </td>
											<td>mobile</td>
											<td>created</td>
											<td>practice</td>
											<td>application</td>
											<td>phonegap</td>
											<td>hackathon</td>
											<td>converter</td>
											<td>sm</td>
											<td>matlab</td>
											<td>enables</td>
										</tr>
										<tr>
											<td>#11: </td>
											<td>c++</td>
											<td>library</td>
											<td>visual</td>
											<td>qt</td>
											<td>generating</td>
											<td>sdk</td>
											<td>studio</td>
											<td>opengl</td>
											<td>swift</td>
											<td>galaxy</td>
										</tr>
										<tr>
											<td>#12: </td>
											<td>bundle</td>
											<td>dynamic</td>
											<td>live</td>
											<td>backup</td>
											<td>symfony2</td>
											<td>css3</td>
											<td>popular</td>
											<td>global</td>
											<td>rpg</td>
											<td>oriented</td>
										</tr>
										<tr>
											<td>#13: </td>
											<td>system</td>
											<td>management</td>
											<td>object</td>
											<td>store</td>
											<td>recipe</td>
											<td>simple</td>
											<td>alternative</td>
											<td>based</td>
											<td>socket.io</td>
											<td>capability</td>
										</tr>
										<tr>
											<td>#14: </td>
											<td>type</td>
											<td>running</td>
											<td>xml</td>
											<td>playing</td>
											<td>description</td>
											<td>started</td>
											<td>cordova</td>
											<td>academy</td>
											<td>activerecord</td>
											<td>data</td>
										</tr>
										<tr>
											<td>#15: </td>
											<td>course</td>
											<td>workflow</td>
											<td>var</td>
											<td>hosted</td>
											<td>inside</td>
											<td>redmine</td>
											<td>actual</td>
											<td>meiawiki</td>
											<td>ticket</td>
											<td>associated</td>
										</tr>
										<tr>
											<td>#16: </td>
											<td>monodb</td>
											<td>lab</td>
											<td>menu</td>
											<td>location</td>
											<td>id</td>
											<td>scanner</td>
											<td>extended</td>
											<td>minimalistic</td>
											<td>loop</td>
											<td>ported</td>
										</tr>
										<tr>
											<td>#17: </td>
											<td>testing</td>
											<td>authentication</td>
											<td>ansible</td>
											<td>security</td>
											<td>flask</td>
											<td>gallery</td>
											<td>role</td>
											<td>yeoman</td>
											<td>genertator</td>
											<td>tool</td>
										</tr>
										<tr>
											<td>#18: </td>
											<td>scirpt</td>
											<td>various</td>
											<td>python</td>
											<td>syntax</td>
											<td>file</td>
											<td>sql</td>
											<td>read</td>
											<td>info</td>
											<td>html</td>
											<td>ruby</td>
										</tr>
										<tr>
											<td>#19: </td>
											<td>local</td>
											<td>heroku</td>
											<td>chart</td>
											<td>header</td>
											<td>city</td>
											<td>pretty</td>
											<td>story</td>
											<td>vote</td>
											<td>section</td>
											<td>submit</td>
										</tr>
										<tr>
											<td>#20: </td>
											<td>event</td>
											<td>bootstrap</td>
											<td>twitter</td>
											<td>css3</td>
											<td>using</td>
											<td>dom</td>
											<td>table</td>
											<td>css</td>
											<td>html</td>
											<td>orm</td>
										</tr>
										<tr>
											<td>#21: </td>
											<td>function</td>
											<td>basic</td>
											<td>useful</td>
											<td>own</td>
											<td>password</td>
											<td>bit</td>
											<td>handling</td>
											<td>automated</td>
											<td>mail</td>
											<td>router</td>
										</tr>
										<tr>
											<td>#22: </td>
											<td>database</td>
											<td>sql</td>
											<td>mysql</td>
											<td>handle</td>
											<td>viewer</td>
											<td>allowing</td>
											<td>select</td>
											<td>switch</td>
											<td>interaction</td>
											<td>ability</td>
										</tr>
										<tr>
											<td>#23: </td>
											<td>algotithm</td>
											<td>robot</td>
											<td>firebox</td>
											<td>monitoring</td>
											<td>original</td>
											<td>suite</td>
											<td>private</td>
											<td>markup</td>
											<td>add-on</td>
											<td>firmware</td>
										</tr>
										<tr>
											<td>#24: </td>
											<td>extension</td>
											<td>sample</td>
											<td>browser</td>
											<td>chrome</td>
											<td>firefox</td>
											<td>stack</td>
											<td>app</td>
											<td>application</td>
											<td>mode</td>
											<td>using</td>
										</tr>
										<tr>
											<td>#25: </td>
											<td>php</td>
											<td>convert</td>
											<td>mvc</td>
											<td>backend</td>
											<td>cakephp</td>
											<td>asp.net</td>
											<td>spring</td>
											<td>automation</td>
											<td>using</td>
											<td>library</td>
										</tr>
										<tr>
											<td>#26: </td>
											<td>related</td>
											<td>visualization</td>
											<td>host</td>
											<td>internet</td>
											<td>skeleton</td>
											<td>graphic</td>
											<td>look</td>
											<td>college</td>
											<td>data</td>
											<td>science</td>
										</tr>
										<tr>
											<td>#27: </td>
											<td>site</td>
											<td>assignment</td>
											<td>web</td>
											<td>product</td>
											<td>release</td>
											<td>training</td>
											<td>parse</td>
											<td>branck</td>
											<td>history</td>
											<td>auto</td>
										</tr>
										<tr>
											<td>#28: </td>
											<td>framework</td>
											<td>lightweight</td>
											<td>spring</td>
											<td>web</td>
											<td>application</td>
											<td>authentication</td>
											<td>javascript</td>
											<td>objective-c</td>
											<td>swift</td>
											<td>based</td>
										</tr>
										<tr>
											<td>#29: </td>
											<td>create</td>
											<td>allows</td>
											<td>easily</td>
											<td>lua</td>
											<td>bukkit</td>
											<td>file</td>
											<td>definition</td>
											<td>startup</td>
											<td>nested</td>
											<td>extend</td>
										</tr>
										<tr>
											<td>#30: </td>
											<td>display</td>
											<td>manager</td>
											<td>task</td>
											<td>change</td>
											<td>note</td>
											<td>tracking</td>
											<td>widget</td>
											<td>issue</td>
											<td>value</td>
											<td>simple</td>
										</tr>
									</tbody>
									
								</table>
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
</body>
</html>







