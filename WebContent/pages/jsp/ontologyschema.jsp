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
%>%>


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
						<h1 class="text-oringe text-center">Ontology Schema</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-10 col-lg-offset-1">

						<div class="row">
							<div class="col-lg-12">

								<div id="container" style="height: 800px">
									<style>
#graph-container {
	top: 10;
	bottom: 10;
	left: 10;
	right: 10;
	position: static;
}
</style>
									<div id="graph-container" style="height: 800px"></div>
								</div>

							</div>
						</div>
						<div class="myjumbotron2"></div>
						<div class="row row-lg-offset-1">

							<div class="col-lg-12 text-left" style="font-size: 16px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							Ontology Schema shows the
								relationship between property and class, class and class. We can
								reference the inner relationship between property and
								class, class and class.The original node is a class which has many
								edges pointer to the property. You can click a class in the
								Schema, then it will show the properties which have relationship
								with it. Or you can click a property, then it will show the
								Classes which have relationship with it. Besides, you can also
								find some inner relationship which reference by the ontology
								schema in the ontology schema if you look carefully enough.</div>
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
		// Add a method to the graph model that returns an
		// object with every neighbors of a node inside:
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
		sigma.parsers.gexf('ontology.do?word=oop', {
			container : 'graph-container'
		}, function(s) {
			// We first need to save the original colors of our
			// nodes and edges, like this:
			s.graph.nodes().forEach(function(n) {
				n.originalColor = n.color;
				n.originalLabel = n.label;
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


