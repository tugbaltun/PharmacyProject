<%@ page import="com.example.pharmacyproject.connection.DatabaseConnection" %>
<%@ page import="com.example.pharmacyproject.dao.PharmacyDaoImpl" %>
<%@ page import="com.example.pharmacyproject.dao.BaseDaoImpl" %>
<%@ page import="com.example.pharmacyproject.model.Pharmacy" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.pharmacyproject.model.User" %>
<%@ page import="com.example.pharmacyproject.model.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Panel</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <%@include file="/includes/navbar-user.jsp"%>
</header>
<br>

<%


    DatabaseConnection databaseConnection = new DatabaseConnection();
    PharmacyDaoImpl pharmacyDao = new PharmacyDaoImpl(databaseConnection.getConnection());
    BaseDaoImpl baseDaoImpl ;
    pharmacyDao = new PharmacyDaoImpl(databaseConnection.getConnection());
    baseDaoImpl = new BaseDaoImpl(pharmacyDao);
    List<Pharmacy> listPharmacy = baseDaoImpl.getAll();
    request.setAttribute("listPharmacy", listPharmacy);

    Logger logger = new Logger();

    if (listPharmacy != null) {
        request.setAttribute("listPharmacy", listPharmacy);
    }
%>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container" >
        <h3 class="text-center">List of Pharmacies</h3>
        <hr>
        <div class="row ">
                <div class="offset-md-3 col-md-6  ">
                        <input type="search" class="form-control" id="mySearchInput" placeholder="Live Searching....."
                               onkeyup="searchKey()" value = "<c:out value='${logger.value}' />">
                </div>

        </div>

        <h3 id="error" class="text-center mt-3"></h3>

        <div class="row ">
            <div class="offset-md-1 col-md-10 ">
                <form action="pharmacy" method="post">
                    <table class="table table-bordered"  id="myTable">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Phone Number</th>
                            <th>Address</th>
                            <th>Working Hours</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="pharmacy" items="${listPharmacy}">

                            <tr>
                                <td><c:out value="${pharmacy.name}" /></td>
                                <td><c:out value="${pharmacy.phone_number}" /></td>
                                <td><c:out value="${pharmacy.address}" /></td>
                                <td><c:out value="${pharmacy.working_hours}" /></td>
                            </tr>
                        </c:forEach>

                        </tbody>

                    </table>
                </form>
            </div>
        </div>

    </div>
</div>

<script>
    const searchKey = () =>{
        let filter = document.getElementById('mySearchInput').value.toUpperCase();
        let myTable = document.getElementById('myTable');
        let tr = myTable.getElementsByTagName('tr');
        let tdSize = myTable.getElementsByTagName('td').length/(tr.length-1);
        let check = false;
        for(let i=0; i<tr.length; i++){
            for(let j=0; j<tdSize; j++) {
                let td = tr[i].getElementsByTagName('td')[j];
                if (td) {
                    let textValue = td.textContent || td.innerHTML;
                    if (textValue.toUpperCase().indexOf(filter) > -1) {
                        check = true;
                    }
                }
            }
                if(check){
                    tr[i].style.display = "";
                    check = false;
                }else{
                    tr[i].style.display = "none";
                }
        }
    }
</script>
</body>

</html>

