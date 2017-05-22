<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Client Status Report</title>
    <%@include file="../global.resources/responsiveResource.jsp" %>
</head>

<body oncopy="return false" oncut="return false" class="view">
<div class="" id="wrap">
    <%@include file="../global.resources/top.jsp" %>
    <div id="content">
        <div class="outer">
            <div class="inner bg-light lter">
                <div class="row">
                    <%--
                                        <div class="col-lg-12">
                                            <%@include file="filter.jsp" %>
                                        </div>
                    --%>
                    <div class="col-lg-12">
                        <div class="box">
                            <header>
                                <div class="icons"><i class="fa fa-table"></i></div>
                                <h5>Client Status Report</h5>
                            </header>
                            <div id="collapse4" class="body">
                                <table id="clientStatusReportTable"
                                       class="table table-bordered table-condensed table-hover table-striped">
                                    <thead>
                                    <tr>
                                        <th>Client</th>
                                        <th>Status</th>
                                        <th>Position</th>
                                        <th>Resource Name</th>
                                        <th>Resource Email</th>
                                        <th>Resource Contact</th>
                                        <th>Recruiter</th>
                                        <th width="15%">Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${allClientHistoryMap}" var="clientHistoryMap">
                                        <td nowrap="nowrap">${clientHistoryMap.clientName}</td>
                                        <td nowrap="nowrap">${clientHistoryMap.clientStatus}</td>
                                        <td nowrap="nowrap">${clientHistoryMap.positionName}</td>
                                        <td nowrap="nowrap">${clientHistoryMap.resourceName}</td>
                                        <td nowrap="nowrap">${clientHistoryMap.resourceEmail}</td>
                                        <td nowrap="nowrap">${clientHistoryMap.resourceContact}</td>
                                        <td nowrap="nowrap">${clientHistoryMap.recruiterName}</td>
                                        <td nowrap="nowrap">${clientHistoryMap.action}</td>
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
</div>
</body>
</html>

<script>

    $(function () {
        $("#adminPage").removeClass("active");
        $("#data").removeClass("active");
        $("#reports").addClass("active");
        Metis.MetisTable();
    });
</script>