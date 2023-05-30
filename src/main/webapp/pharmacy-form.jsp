<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <%@include file="/includes/head.jsp"%>
</head>
<body>

<br>

<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <c:if test="${pharmacy != null}">
      <form action="update" method="post">
        </c:if>
        <c:if test="${pharmacy == null}">
        <form action="insert" method="post">
          </c:if>

          <caption>
            <h2>
              <c:if test="${pharmacy != null}">
                Edit Pharmacy
              </c:if>
              <c:if test="${pharmacy == null}">
                Add New Pharmacy
              </c:if>
            </h2>
          </caption>

          <c:if test="${pharmacy != null}">
            <input type="hidden" name="id" value="<c:out value='${pharmacy.id}' />" />
          </c:if>

          <fieldset class="form-group">
            <label>Pharmacy Name</label> <input type="text"
                                            value="<c:out value='${pharmacy.name}' />" class="form-control"
                                            name="name" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Pharmacy Phone Number</label> <input type="text"
                                             value="<c:out value='${pharmacy.phone_number}' />" class="form-control"
                                             name="phone_number">
          </fieldset>

          <fieldset class="form-group">
            <label>Pharmacy Address</label> <input type="text"
                                               value="<c:out value='${pharmacy.address}' />" class="form-control"
                                               name="address">
          </fieldset>

            <fieldset class="form-group">
              <label>Pharmacy Working Hours</label> <input type="text"
                                                     value="<c:out value='${pharmacy.working_hours}' />" class="form-control"
                                                     name="working_hours">
            </fieldset>

          <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
  </div>
</div>
</body>
</html>
