<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.Cus_Bill"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>

	<div class="container">
		<div class="card">
			<div class="card-header bg-info text-light d-flex align-items-center">
				<h1>Bill Details</h1>
			</div>
			<div class="card-body">
				<form id="form_Bill" name="form_Bill" method="post" action="Cus_Bill.jsp">
					Account Number: <input id="AcNumber" name="AcNumber" type="text"
						class="form-control form-control-sm"> <br> No of days: <input
						id="days" name="days" type="text"
						class="form-control form-control-sm"> <br> No of units: <input
						id="units" name="units" type="text"
						class="form-control form-control-sm"> <br> Months : <input
						id="month" name="month" type="text"
						class="form-control form-control-sm"><br> Amount:
					: <input id="amount" name="amount" type="text"
						class="form-control form-control-sm"> <br>
					<div class="text-right">
						<input id="btnSave" name="btnSave" type="button" value="SAVE"
							class="btn btn-primary"> <input type="hidden"
							id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
					</div>
				</form>
				<div id="alertSuccess" class="alert alert-success" style="margin-top: 15px"></div>
				<div id="alertError" class="alert alert-danger" style="margin-top: 15px"></div>
				<div id="divItemsGrid" class="table-responsive">
					<%
					

								Cus_Bill Cus_Bill = new Cus_Bill();
								out.print(Cus_Bill.readDetails());
					%>
				</div>
			</div>

		</div>
	</div>
</body>
</html>