<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Clients History</title>
    <%@include file="../global.resources/responsiveResource.jsp" %>
</head>

<body>
    <div class="col-lg-12" style="max-height: 800px; min-height: 500px;">
        <div class="box">
            <header>
                <div class="icons"><i class="fa fa-table"></i></div>
                <h5>Client History</h5>
            </header>
            <div id="collapse4" class="body">
                <table id="dataTableClientHistory"
                       class="table table-condensed table-hover table-striped">
                    <thead>
                    <tr>
                        <th>Client</th>
                        <th>Position</th>
                        <th>Status</th>
                        <th>Recruiter</th>
                        <th>Added Date</th>
                        <th>Client Remarks</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${clientHistoryList}" var="clientHistory">
                        <tr>
                            <td nowrap="nowrap">${clientHistory.client.clientName}</td>
                            <td nowrap="nowrap">${clientHistory.position}</td>
                            <td nowrap="nowrap">${clientHistory.clientStatus.clientStatusName}</td>
                            <td nowrap="nowrap">${clientHistory.addedBy}</td>
                            <td nowrap="nowrap">${clientHistory.addedDate}</td>
                            <td>${clientHistory.remarks}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
</div>
</body>
</html>

<script>
    $(document).ready(function() {
        $('#dataTableClientHistory').dataTable();
    });
</script>