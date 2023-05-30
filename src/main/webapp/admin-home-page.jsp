<%@ page import="com.example.pharmacyproject.connection.DatabaseConnection" %>
<%@ page import="com.example.pharmacyproject.dao.PharmacyDaoImpl" %>
<%@ page import="com.example.pharmacyproject.dao.BaseDaoImpl" %>
<%@ page import="com.example.pharmacyproject.model.Pharmacy" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<%
    DatabaseConnection databaseConnection = new DatabaseConnection();
    PharmacyDaoImpl pharmacyDao = new PharmacyDaoImpl(databaseConnection.getConnection());
    BaseDaoImpl baseDaoImpl ;
    pharmacyDao = new PharmacyDaoImpl(databaseConnection.getConnection());
    baseDaoImpl = new BaseDaoImpl(pharmacyDao);
    List<Pharmacy> listPharmacy = baseDaoImpl.getAll();

    if (listPharmacy != null) {
        request.setAttribute("listPharmacy", listPharmacy);
    }
%>

<header>
    <%@include file="/includes/navbar.jsp"%>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Pharmacies</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New Pharmacy</a>
        </div>

        <br>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Working Hours</th>
                <th>Edit/Delete</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="pharmacy" items="${listPharmacy}">

                <tr>
                    <td><c:out value="${pharmacy.name}" /></td>
                    <td><c:out value="${pharmacy.phone_number}" /></td>
                    <td><c:out value="${pharmacy.address}" /></td>
                    <td><c:out value="${pharmacy.working_hours}" /></td>

                    <td><a href="edit?id=<c:out value='${pharmacy.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${pharmacy.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>
</body>
</html>
