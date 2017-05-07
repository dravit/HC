<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
	<%@include file="../global.resources/responsiveResource.jsp" %>
</head>

<body class="view">
<div class="" id="wrap">
	<%@include file="../global.resources/top.jsp" %>
	<%--<div id="left">
        <%@include file="../global.resources/leftUserDetail.jsp" %>
    </div>--%>
	<div id="content">
		<div class="outer">
			<div class="inner bg-light lter">
				<div class="row">
					<div class="col-lg-12">
						<div class="box">
							<header>
								<div class="icons"><i class="fa fa-table"></i></div>
								<h5>List Of Users</h5>
							</header>
							<div id="collapse4" class="body">
								<table id="dataTable"
									   class="table table-condensed table-hover table-striped">
									<thead>
									<tr>
										<th>Firstname</th>
										<th>Lastname</th>
										<th>Email</th>
										<th>SSO ID</th>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<th width="100"></th>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN')">
											<th width="100"></th>
										</sec:authorize>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${users}" var="user">
										<tr>
											<td>${user.firstName}</td>
											<td>${user.lastName}</td>
											<td>${user.email}</td>
											<td>${user.ssoId}</td>
											<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
												<td><a href="<c:url value='/edit-user-${user.ssoId}' />" class="btn btn-success custom-width">edit</a></td>
											</sec:authorize>
											<sec:authorize access="hasRole('ADMIN')">
												<td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger custom-width">delete</a></td>
											</sec:authorize>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>

    $(function () {
        Metis.MetisTable();
    });
</script>
</html>