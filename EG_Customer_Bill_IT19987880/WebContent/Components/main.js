$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();

});

function validateItemForm() {
	
	// Account number------------------------
	let AcNumber = $("#AcNumber").val().trim();
	if (!$.isNumeric(AcNumber)) {
		return "Insert a numerical value for Account number.";
	}
	// Days-------------------------------
	if ($("#days").val().trim() == "") {
		return "Insert Customer days.";
	}
	
	// Units-------------------------------
	if ($("#units").val().trim() == "") {
		return "Insert Customer units.";
	}
	
	// month-------------------------------
	let month = $("#month").val().trim();
	if (!$.isNumeric(month)) {
		return "Insert a numerical value for month.";
	}
	
	// Amount-------------------------------
	if ($("#amount").val().trim() == "") {
		return "Insert Customer amount.";
	}

	

	return true;

}

//Save Func
function onAccountSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidCustomerIDSave").val("");
	$("#form_Bill")[0].reset();
}


// Save Btn
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------  
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------  
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------  
	var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
		{
			url: "Cus_Bill",
			type: type,
			data: $("#form_Bill").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onAccountSaveComplete(response.responseText, status);
			}
		});
});


// UPDATE CLICK
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidCustomerIDSave").val($(this).closest("tr").find('#hidCustomerIDUpdate').val());
	$("#AcNumber").val($(this).closest("tr").find('td:eq(0)').text());
	$("#days").val($(this).closest("tr").find('td:eq(1)').text());
	$("#units").val($(this).closest("tr").find('td:eq(2)').text());
	$("#month").val($(this).closest("tr").find('td:eq(3)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(4)').text());
	
});


//Delete Func
function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


// DELETE Click
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "CusAccount",
			type: "DELETE",
			data: "Bill_id=" + $(this).data("Bill_id"),
			dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response.responseText, status);
			}
		});
});
