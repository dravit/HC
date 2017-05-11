<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Resource List</title>
    <%@include file="../global.resources/responsiveResource.jsp" %>
</head>

<body oncopy="return false" oncut="return false" class="view">
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
                        <%@include file="filter.jsp" %>
                    </div>
                    <div class="col-lg-12">
                        <div class="box">
                            <header>
                                <div class="icons"><i class="fa fa-table"></i></div>
                                <h5>List Of Resources</h5>
                            </header>
                            <div id="collapse4" class="body">
                                <table id="dataTable"
                                       class="table table-bordered table-condensed table-hover table-striped">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Contact No.</th>
                                        <th>Institute</th>
                                        <th>Skills</th>
                                        <th>Stream</th>
                                        <th>Program</th>
                                        <th width="15%">Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${resourceDetailsList}" var="resourceDetails">
                                        <tr>
                                            <td nowrap="nowrap">
                                                <a class="resourceData"
                                                   href="${pageContext.request.contextPath}/resourceDetails-${resourceDetails.id}"
                                                   title="Resource Details for ${resourceDetails.name}">${resourceDetails.name}</a>
                                            </td>
                                            <td nowrap="nowrap">
                                                <h:outputLink value="mailto:${resourceDetails.emailId}">
                                                    ${resourceDetails.emailId}
                                                </h:outputLink>
                                            </td>
                                            <td nowrap="nowrap">${resourceDetails.contactNumber}</td>
                                            <td nowrap="nowrap">${resourceDetails.institute.instituteName}</td>
                                            <td nowrap="nowrap">${resourceDetails.skills}</td>
                                            <td nowrap="nowrap">${resourceDetails.stream.streamName}</td>
                                            <td nowrap="nowrap">${resourceDetails.program.programName}</td>
                                            <td width="15%">
                                                <a class="resourceData"
                                                   href="${pageContext.request.contextPath}/addClientHistory-${resourceDetails.id}"
                                                   title="Client History for ${resourceDetails.name}">
                                                <img src="<c:url value='/static/images/client-history.png'/>" width="20" height="20" alt="Client History"/>
                                                </a>
                                                <a class="resourceData"
                                                   href="${pageContext.request.contextPath}/editResource-${resourceDetails.id}"
                                                   title="Modify Details of ${resourceDetails.name}">
                                                    <img src="<c:url value='/static/images/edit-resource.png'/>" width="20" height="20" alt="Edit Resource"/>
                                                </a>
                                                <a class="resourceData"
                                                   href="${pageContext.request.contextPath}/sendToClient-${resourceDetails.id}"
                                                   title="Send ${resourceDetails.name} to Client">
                                                    <img src="<c:url value='/static/images/send-to-client.png'/>" width="20" height="20" alt="Send to Client"/>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <span class="floatRight">
                            <a class="addResource"
                               href="${pageContext.request.contextPath}/addResource">Add Resource</a>
                        </span>
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
        $(".resourceData").colorbox({rel: 'resourceData'});
        $(".addResource").colorbox({rel: 'addResource'});
        $("#adminPage").removeClass("active");
        $("#data").addClass("active");
        Metis.MetisTable();
    });
</script>