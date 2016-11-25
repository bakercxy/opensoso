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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<div class="row " name="searchLogo">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="col-lg-4">
							<img src="./images/gsearch.png" class="img-responsive"
								alt="Responsive image" width="180px">
						</div>
					</div>
				</div>


				<div class="row row-lg-offset-2 " name="searchBar">
					<div class="col-lg-8 col-lg-offset-2">
						<form action="search.do" name="searchform" id="searchform">
							<div class="col-lg-8">
								<input id="queryText" name="query" value="${requestScope.query}"
									class="form-control input-lg" type="text"
									placeholder="Please enter your interest">
							</div>
							<div class="col-lg-2">
								<select id="type" name="type" value="${requestScope.type}"
									class="form-control input-lg"
									style="width: 100px; font-size: 14px">
									<c:choose>
										<c:when test="${requestScope.type == 'All'}">
											<option selected="selected">All</option>
										</c:when>
										<c:otherwise>
											<option>All</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${requestScope.type == 'Repos'}">
											<option selected="selected">Repos</option>
										</c:when>
										<c:otherwise>
											<option>Repos</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${requestScope.type == 'Users'}">
											<option selected="selected">Users</option>
										</c:when>
										<c:otherwise>
											<option>Users</option>
										</c:otherwise>
									</c:choose>

								</select>
							</div>

							<div class="col-lg-2">
								<button type="button" class="btn btn-warning btn-lg"
									onclick="return searchQuery()">G-Search</button>
							</div>
						</form>
					</div>
				</div>
				<br />

				<div class="row" name="searchDes">
					<div class="col-lg-8 col-lg-offset-2">
						<font color="#666666">The search results of
							"${requestScope.query}" are as belows (${searchtime} seconds)</font>
					</div>
				</div>
				<br /> <br />
				<div class="row" name="searchResult">
					<div class="col-lg-8 col-lg-offset-2">
						<TABLE class="table" id="searchRecords" cellpadding=4
							cellspacing=1 border=0>

							<!-- 循环遍历result -->
							<c:forEach items="${requestScope.result}" var="obj"
								varStatus="vs">
								<c:if test="${obj.type == 'USER'}">
									<TR>
										<TD>
											<div class="row">
												<div class="col-md-12">
													<div class="row">
														<div class="col-md-12">
															<a href="${obj.rlink}"><h2>
																	${obj.user}<img src="./images/user.png" />
																</h2></a>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<a href="${obj.rlink}">${obj.rlink}</a><br /> <b>Name:</b>
															<c:choose>
																<c:when test="${obj.user == ''}">
             								-
       									</c:when>
																<c:otherwise>
       										${obj.user}
       									</c:otherwise>
															</c:choose>


															&nbsp&nbsp <b>Skill:</b>
															<c:choose>
																<c:when test="${obj.tags == ''}">
             								-
       									</c:when>
																<c:otherwise>
       										${obj.tags}
       									</c:otherwise>
															</c:choose>

															&nbsp&nbsp <b>Location:</b>

															<c:choose>
																<c:when test="${obj.location == ''}">
             								-
       									</c:when>
																<c:otherwise>
       										${obj.location}
       									</c:otherwise>
															</c:choose>

															<br /> <b>Email:</b>
															<c:choose>
																<c:when test="${obj.email == ''}">
             								-
       									</c:when>
																<c:otherwise>
																	<a href="${obj.email}">${obj.email}</a>
																</c:otherwise>
															</c:choose>


															&nbsp&nbsp <b>Blog:</b>
															<c:choose>
																<c:when test="${obj.blog == ''}">
             								-
       									</c:when>
																<c:otherwise>
																	<a href="${obj.blog}">${obj.blog}</a>
																</c:otherwise>
															</c:choose>



															&nbsp&nbsp <br /> <b>Created Time:</b>


															<c:choose>
																<c:when test="${obj.created_at == ''}">
             								-
       									</c:when>
																<c:otherwise>
       										 ${obj.created_at}
       									</c:otherwise>
															</c:choose>


															&nbsp&nbsp <b>LastActive Time:</b>

															<c:choose>
																<c:when test="${obj.updated_at == ''}">
             								-
       									</c:when>
																<c:otherwise>
       										 ${obj.updated_at} 
       									</c:otherwise>
															</c:choose>
															&nbsp&nbsp
														</div>
													</div>
												</div>
											</div> <br />
										</TD>
									</TR>
								</c:if>

								<c:if test="${obj.type == 'REPO'}">
									<TR>
										<TD>
											<div class="row">
												<div class="col-md-12">
													<a href="${obj.rlink}"><h2>${obj.repo}
															<img src="./images/repo.png" />
														</h2></a> <a href="${obj.rlink}">${obj.rlink}</a><br /> <b>Owner:</b>

													<c:choose>
														<c:when test="${obj.owner == ''}">
             								-
       									</c:when>
														<c:otherwise>
															<a href="http://github.com/${obj.owner}">${obj.owner}</a>
														</c:otherwise>
													</c:choose>




													&nbsp&nbsp <b>Language:</b>
													<c:choose>
														<c:when test="${obj.language} == ''}">
             								-
       									</c:when>
														<c:otherwise>
       										${obj.language}
       									</c:otherwise>
													</c:choose>
													&nbsp&nbsp <b>Create Date:</b>

													<c:choose>
														<c:when test="${obj.create_date} == ''}">
             								-
       									</c:when>
														<c:otherwise>
       										${obj.create_date}
       									</c:otherwise>
													</c:choose>


													<br /> <b>Description:</b>

													<c:choose>
														<c:when test="${obj.description} == ''}">
             								-
       									</c:when>
														<c:otherwise>
       										${obj.description}
       									</c:otherwise>
													</c:choose>




												</div>
											</div> <br />
										</TD>
									</TR>
								</c:if>

							</c:forEach>

						</TABLE>
					</div>
				</div>
				<br />
				<div class="row" name="pageNavigation">
					<div class="col-md-8 col-md-offset-3" name="pageNavigation">
						<div class="col-md-1" id="thePrePage">
							<a href="javascript:prev()">Prev</a>
						</div>
						<div class="col-md-2">
							<label for="goto" class="col-md-3 control-label">Page</label>
							<div class="col-md-1">
								<select onChange="gotoPage(this.value)" name="goto" id="goto">
								</select>
							</div>
						</div>
						<div class="col-md-1" id="theNextPage">
							<a href="javascript:next()">Next</a>
						</div>
					</div>
				</div>
				<br />
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

	<SCRIPT LANGUAGE="JavaScript"> 
	var recordPerPage = 10;
	var totalRecords = window.searchRecords.rows.length;
	var pageTotal = ((totalRecords+recordPerPage-1)/recordPerPage)|0;
	var currentPage = 1;
	initSelector();
	showhiddenRecord(currentPage);
	
	function showhiddenRecord(currentPage){ 
		if(currentPage<=1){ 
			thePrePage.innerHTML="Prev"; 
		}else{ 
			thePrePage.innerHTML="<a href=\"javascript:prev()\">Prev</a>"; 
		} 
		
		if(currentPage>=pageTotal){ 
			theNextPage.innerHTML="Next"; 
		}else{ 
			theNextPage.innerHTML="<a href=\"javascript:next()\">Next</a>"; 	
		} 
		
		var startIndex = (currentPage-1)*recordPerPage;
		var endIndex = currentPage*recordPerPage;
		if(endIndex>totalRecords){
			endIndex == totalRecords;
		}

		for (i=0; i < window.searchRecords.rows.length; i++) { 
			if (inRange(startIndex,endIndex,i)){
				window.searchRecords.rows[i].style.display="";
			}
			else{
				window.searchRecords.rows[i].style.display="none";
			}
		}
	} 

	function inRange(start,end,current){
		if(current>=start && current<end)
			return 1;
		return 0
	}
	
	function updateGoto(page){
		window.goto.value=page;
	}
	
	function prev(){ 
		currentPage--; 
		updateGoto(currentPage);
		showhiddenRecord(currentPage); 
	} 

	function next(){ 
		currentPage++; 
		updateGoto(currentPage);
		showhiddenRecord(currentPage); 
	} 

	function gotoPage(num){ 
		currentPage = num;
		showhiddenRecord(num); 
	} 
	
	function initSelector(){
		selector = window.goto;
		for(i=0;i<pageTotal;i++){
			var varItem = new Option((i+1).toString(), i+1); 
			selector.options.add(varItem); 
		}	
	}
	
	function searchQuery(){
		if(trim($("#queryText").val()) == "")
			return false;
		else
			$("#searchform").submit();
	}
	
	function trim(str){ //删除左右两端的空格
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
</SCRIPT>

</body>
</html>


