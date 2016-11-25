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
			<div class="myjumbotron2">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="text-oringe text-center">Collaborative Graph</h1>
					</div>
				</div>
				<div class="myjumbotron2"></div>
				<div class="row">
					<div class="col-md-10 col-md-offset-1">

						<!--  -->
						<div class="row">
							<div class="col-lg-12">

								<div id="container" style="height: 800px">
									<style>
#graph-container {
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	position: absolute;
}
</style>
									<div id="graph-container" style="height: 800px"></div>
								</div>
							</div>
						</div>

						<div class="myjumbotron2"></div>
						<div class="myjumbotron2"></div>
						<div class="myjumbotron2"></div>

						<div class="row">
							<div class="col-lg-12">
								<h1 class="text-oringe text-center">Collaborative Patterns
									Mining</h1>
							</div>
						</div>

						<div class="row row-lg-offset-2">
							<div class="col-lg-12">
								<!-- outside table -->
								<table class="table">
									<tr>
										<th style="text-align: center">Time Interval(Days):</th>
										<td style="text-align: center"><b>Pattern</b></td>
										<td style="text-align: center"><b>Nodes</b></td>
										<td style="text-align: center"><b>Links</b></td>
										<td style="text-align: center"><b>Count</b></td>
										<td style="text-align: center"><b>Minimum Support</b></td>
									</tr>

									<!-- interval=5 -->
									<tr>
										<th rowspan="14" style="text-align: center; font-size: 60px">5</th>
										<td style="text-align: center"><img
											src="./images/pattern/2.png" width="50px" /></td>
										<td style="text-align: center">2</td>
										<td style="text-align: center">1</td>
										<td style="text-align: center">2348</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/4.png" width="50px" /></td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">2</td>
										<td style="text-align: center">1830</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/5.png" width="50px" /></td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">1443</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/6.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">1016</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/7.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">889</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/8.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">6</td>
										<td style="text-align: center">766</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/9.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">408</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/10.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">419</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/11.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">413</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/12.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">1036</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/13.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">423</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/14.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">430</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/15.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">111</td>
										<td style="text-align: center">130</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/16.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">22</td>
										<td style="text-align: center">130</td>
									</tr>

									<!-- interval=30 -->
									<tr>
										<th rowspan="8" style="text-align: center; font-size: 60px">30</th>
										<td style="text-align: center"><img
											src="./images/pattern/2.png" width="50px" /></td>
										<td style="text-align: center">2</td>
										<td style="text-align: center">1</td>
										<td style="text-align: center">2763</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/4.png" width="50px" /></td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">2</td>
										<td style="text-align: center">2135</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/5.png" width="50px" /></td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">1928</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/6.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">1178</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/7.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">1120</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/8.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">6</td>
										<td style="text-align: center">1052</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/12.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">1187</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/15.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">1187</td>


										<td style="text-align: center">170</td>
									</tr>


									<!-- interval=90 -->
									<tr>
										<th rowspan="13" style="text-align: center; font-size: 60px">90</th>
										<td style="text-align: center"><img
											src="./images/pattern/2.png" width="50px" /></td>
										<td style="text-align: center">2</td>
										<td style="text-align: center">1</td>
										<td style="text-align: center">2933</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/4.png" width="50px" /></td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">2</td>
										<td style="text-align: center">2220</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/5.png" width="50px" /></td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">2117</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/6.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">1212</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/7.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">1173</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/8.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">6</td>
										<td style="text-align: center">1158</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/17.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">6</td>
										<td style="text-align: center">474</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/18.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">476</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/12.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">1214</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/19.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">475</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/14.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">477</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/15.png" width="50px" /></td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">3</td>
										<td style="text-align: center">115</td>
										<td style="text-align: center">170</td>
									</tr>
									<tr>
										<td style="text-align: center"><img
											src="./images/pattern/16.png" width="50px" /></td>
										<td style="text-align: center">5</td>
										<td style="text-align: center">4</td>
										<td style="text-align: center">23</td>
										<td style="text-align: center">170</td>
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
	<script src="./js/src/sigma.core.js"></script>
	<script src="./js/src/conrad.js"></script>
	<script src="./js/src/utils/sigma.utils.js"></script>
	<script src="./js/src/utils/sigma.polyfills.js"></script>
	<script src="./js/src/sigma.settings.js"></script>
	<script src="./js/src/classes/sigma.classes.dispatcher.js"></script>
	<script src="./js/src/classes/sigma.classes.configurable.js"></script>
	<script src="./js/src/classes/sigma.classes.graph.js"></script>
	<script src="./js/src/classes/sigma.classes.camera.js"></script>
	<script src="./js/src/classes/sigma.classes.quad.js"></script>
	<script src="./js/src/captors/sigma.captors.mouse.js"></script>
	<script src="./js/src/captors/sigma.captors.touch.js"></script>
	<script src="./js/src/renderers/sigma.renderers.canvas.js"></script>
	<script src="./js/src/renderers/sigma.renderers.webgl.js"></script>
	<script src="./js/src/renderers/sigma.renderers.def.js"></script>
	<script src="./js/src/renderers/webgl/sigma.webgl.nodes.def.js"></script>
	<script src="./js/src/renderers/webgl/sigma.webgl.nodes.fast.js"></script>
	<script src="./js/src/renderers/webgl/sigma.webgl.edges.def.js"></script>
	<script src="./js/src/renderers/webgl/sigma.webgl.edges.fast.js"></script>
	<script src="./js/src/renderers/webgl/sigma.webgl.edges.arrow.js"></script>
	<script src="./js/src/renderers/canvas/sigma.canvas.labels.def.js"></script>
	<script src="./js/src/renderers/canvas/sigma.canvas.hovers.def.js"></script>
	<script src="./js/src/renderers/canvas/sigma.canvas.nodes.def.js"></script>
	<script src="./js/src/renderers/canvas/sigma.canvas.edges.def.js"></script>
	<script src="./js/src/renderers/canvas/sigma.canvas.edges.curve.js"></script>
	<script src="./js/src/renderers/canvas/sigma.canvas.edges.arrow.js"></script>
	<script
		src="./js/src/renderers/canvas/sigma.canvas.edges.curvedArrow.js"></script>
	<script src="./js/src/middlewares/sigma.middlewares.rescale.js"></script>
	<script src="./js/src/middlewares/sigma.middlewares.copy.js"></script>
	<script src="./js/src/misc/sigma.misc.animation.js"></script>
	<script src="./js/src/misc/sigma.misc.bindEvents.js"></script>
	<script src="./js/src/misc/sigma.misc.drawHovers.js"></script>
	<!-- END SIGMA IMPORTS -->
	<script src="./js/plugins/sigma.parsers.gexf/gexf-parser.js"></script>
	<script src="./js/plugins/sigma.parsers.gexf/sigma.parsers.gexf.js"></script>

	<script>
		sigma.classes.graph
				.addMethod(
						'neighbors',
						function(nodeId) {
							var k, neighbors = {}, index = this.allNeighborsIndex[nodeId]
									|| {};

							for (k in index)
								neighbors[k] = this.nodesIndex[k];

							return neighbors;
						});

		sigma.parsers.gexf('./datas/collaboration.gexf', {
			container : 'graph-container'
		}, function(s) {
			// We first need to save the original colors of our
			// nodes and edges, like this:
			s.graph.nodes().forEach(function(n) {
				n.originalColor = n.color;
				n.originalLabel = n.label;//save the original label of the node
			});
			s.graph.edges().forEach(function(e) {
				e.originalColor = e.color;
			});

			// When a node is clicked, we check for each node
			// if it is a neighbor of the clicked one. If not,
			// we set its color as grey, and else, it takes its
			// original color.
			// We do the same for the edges, and we only keep
			// edges that have both extremities colored.
			s.bind('clickNode',
					function(e) {
						var nodeId = e.data.node.id, toKeep = s.graph
								.neighbors(nodeId);
						toKeep[nodeId] = e.data.node;

						s.graph.nodes().forEach(function(n) {
							if (toKeep[n.id]) {
								n.color = n.originalColor;
								n.label = n.originalLabel;
							} else {
								n.color = '#eee';
								n.label = "";
							}

						});

						s.graph.edges().forEach(function(e) {
							if (toKeep[e.source] && toKeep[e.target])
								e.color = e.originalColor;
							else
								e.color = '#eee';
						});

						// Since the data has been modified, we need to
						// call the refresh method to make the colors
						// update effective.
						s.refresh();
					});

			// When the stage is clicked, we just color each
			// node and edge with its original color.
			s.bind('clickStage', function(e) {
				s.graph.nodes().forEach(function(n) {
					n.color = n.originalColor;
					n.label = n.originalLabel;
				});

				s.graph.edges().forEach(function(e) {
					e.color = e.originalColor;
				});

				// Same as in the previous event:
				s.refresh();
			});
		});
	</script>

</body>
</html>


