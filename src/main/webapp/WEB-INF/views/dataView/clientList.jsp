<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Clients List</title>
    <%@include file="../global.resources/responsiveResource.jsp" %>
</head>

<body class="view">
<div class="" id="wrap">
    <%@include file="../global.resources/top.jsp" %>
    <div id="content">
        <div class="outer">
            <div class="inner bg-light lter">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="box">
                            <header>
                                <div class="icons"><i class="fa fa-table"></i></div>
                                <h5>List Of Clients</h5>
                                <span><a href="<c:url value='/addClient' />" style="float:right; margin-right:10px; padding-top: 5px">Add Client</a></span>
                            </header>
                            <div id="collapse4" class="body">
                                <table id="dataTable"
                                       class="table table-condensed table-hover table-striped">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th colspan="2">Contact No.</th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${clients}" var="client">
                                        <tr>
                                            <td>${client.clientName}</td>
                                            <td>${client.emailID}</td>
                                            <td>${client.clientContactNumber}</td>
                                            <sec:authorize access="hasRole('ADMIN')">
                                                <td style="float: right;"><a href="<c:url value='/edit-client-${client.id}' />" class="btn btn-success custom-width">Edit</a>
                                                        <a href="<c:url value='/delete-client-${client.id}' />" class="btn btn-danger custom-width">Delete</a>
                                                </td>
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