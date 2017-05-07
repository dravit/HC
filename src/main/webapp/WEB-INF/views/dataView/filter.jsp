<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="POST" modelAttribute="resourceDetails" class="form-horizontal">
    <div class="row form-group" style="padding-top: 10px; margin-bottom: 1px;">
        <div class="col-lg-3">
            <form:input type="text" class="form-control" path="name" id="name" placeholder="Name"/>
        </div>
        <div class="col-lg-3">
            <form:input type="text" class="form-control" path="contactNumber" id="contactNumber"
                        placeholder="Contact No."/>
        </div>
        <div class="col-lg-3">
            <form:input type="text" class="form-control" path="emailId" id="emailId" placeholder="Email Id"/>
        </div>
        <div class="col-lg-3">
            <input type="submit" value="Search" class="floatRight btn btn-metis-6 btn-sm"/>
        </div>
    </div>
</form:form>