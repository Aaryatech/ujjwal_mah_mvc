<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Ujjwal Billing Soft</title>
<c:url var="getUniqueCompanyCheck" value="/getUniqueCompanyCheck" />


<meta name="description" content="Sufee Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="apple-icon.png">
<link rel="shortcut icon" href="favicon.ico">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/normalize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/flag-icon.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/cs-skin-elastic.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/lib/chosen/chosen.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/scss/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/lib/chosen/chosen.min.css">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/lib/datatable/dataTables.bootstrap.min.css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>

<style type="text/css">
.right {
	text-align: right;
}

.left {
	text-align: left;
}
</style>
<script>
function validate(){
	if(document.myForm.comp_name.value == " "){
		alert( "Please provide your Company Name!" );
        document.myForm.comp_name.focus() ;
        return false;
	}
	return true;
}
</script>
</head>
<body>


	<!-- Left Panel -->
	<jsp:include page="/WEB-INF/views/common/left.jsp"></jsp:include>
	<!-- Left Panel -->


	<!-- Header-->
	<jsp:include page="/WEB-INF/views/common/right.jsp"></jsp:include>
	<!-- Header-->



	<div class="content mt-3">
		<div class="animated fadeIn">

			<div class="row">
				<c:choose>
					<c:when test="${isError==1}">
						<div class="col-sm-12">
							<div
								class="sufee-alert alert with-close alert-danger alert-dismissible fade show">

								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<strong>Data not Submitted</strong>
							</div>
						</div>
					</c:when>

					<c:when test="${isError==2}">
						<div class="col-sm-12">
							<div
								class="sufee-alert alert with-close alert-success alert-dismissible fade show">

								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<strong>Data Submitted Successfully</strong>
							</div>
						</div>
					</c:when>

				</c:choose>

				<div class="col-xs-12 col-sm-12">
					<div class="card">
						<div class="card-header">
							<div class="col-md-2">
								<strong>${title}</strong>
							</div>
							<div class="col-md-8"></div>
							<div class="col-md-2" align="left">
								<a href="${pageContext.request.contextPath}/showCompList"><strong></strong></a>
							</div>

						</div>
						<div class="card-body card-block">
							<form action="${pageContext.request.contextPath}/insertCompany"
								id="submitForm" method="post" enctype="multipart/form-data" name="myForm" onsubmit="validate()">
								<input type="hidden" name="comp_id" id="comp_id"
									value="${editComp.compId}">
								
						
								<div class="form-group"></div>
								<div class="row">



										<div class="col-md-2">Company Name*</div>

									<div class="col-md-4">
											<input type="text" id="comp_name" name="comp_name"
											oninvalid="setCustomValidity('Please enter correct company name')"
											onchange="try{setCustomValidity('')}catch(e){}" maxlength="100"
											value="${editComp.compName}" 
											style="width: 100%;" autocomplete="off" class="form-control"
											required>
												<span id="cName"></span>
									</div>


									<div class="col-md-2">Company Address*</div>

									<div class="col-md-4">
									<textarea id="comp_add" name="comp_add" class="form-control"
											style="width: 100%;" autocomplete="off" 
											oninvalid="setCustomValidity('Please enter Company address')"
											maxlength="250"	onchange="try{setCustomValidity('')}catch(e){}" 
											required>${editComp.address}</textarea>
									</div>
								</div>


								<%-- <%-- <div class="form-group"></div>
								<div class="row">


									<div class="col-md-2">Factory Address*</div>

									<div class="col-md-4">
										<textarea id="fact_add" name="fact_add" class="form-control"
											style="width: 100%;" autocomplete="off"
											oninvalid="setCustomValidity('Please enter factory address')"
											maxlength="200"
											onchange="try{setCustomValidity('')}catch(e){}" required>${editComp.compFactAdd}</textarea>
									</div>
 
								</div>
 --%>
								<div class="form-group"></div>
								<div class="row">
									<div class="col-md-2">Email ID*</div>
									<div class="col-md-4">
										<input type="text" id="email" name="email" required
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter correct email')"
											pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
											maxlength="50" value="${editComp.email}"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>
									
									
										

									</div>

									<div class="col-md-2">Contact Number*</div>
									<div class="col-md-4">
									<input type="text" id="mob_no" name="mob_no"
											style="width: 100%;" class="form-control"
											value="${editComp.phoneNo}" autocomplete="off"
											required />
										<span class="error" aria-live="polite"></span>

									</div>
								</div>

								<div class="form-group"></div>

								<div class="row">
									<div class="col-md-2">PAN No*</div>
									<div class="col-md-4">
										<input type="text" id="pan_no" name="pan_no" required
											style="width: 100%;" class="form-control" autocomplete="off"
											oninvalid="setCustomValidity('Please enter PAN no')"
											maxlength="10" value="${editComp.panNo}"
											pattern="[A-Za-z]{5}\d{4}[A-Za-z]{1}"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>
									</div>

									<div class="col-md-2">GST No*</div>
									<div class="col-md-4">
										<input type="text" id="gst_no" name="gst_no" required
											onblur="getCheck()" style="width: 100%;" class="form-control"
											autocomplete="off"
											oninvalid="setCustomValidity('Please enter GST no')"
											maxlength="20" value="${editComp.gstid}"
											pattern="^([0]{1}[1-9]{1}|[1-2]{1}[0-9]{1}|[3]{1}[0-7]{1})([a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9a-zA-Z]{1}[zZ]{1}[0-9a-zA-Z]{1})+$"
											onkeydown="upperCaseF(this)"
											onchange="try{setCustomValidity('')}catch(e){}" /> <span
											class="error" aria-live="polite"></span>

									</div>
								</div>

								<%-- <div class="form-group"></div>

								<div class="row">

									<div class="col-md-2">Mobile No*</div>
									<div class="col-md-4">
										<input type="text" id="mob_no" name="mob_no"
											style="width: 100%;" class="form-control"
											value="${editComp.contactNo1}" autocomplete="off"
											oninvalid="setCustomValidity('Please enter correct mob no')"
											pattern="^[1-9]{1}[0-9]{9}$" maxlength="10"
											onchange="try{setCustomValidity('')}catch(e){}" required />
										<span class="error" aria-live="polite"></span>

									</div>
									<div class="col-md-2">Telephone No(Optional)</div>
									<div class="col-md-4">
										<input type="text" id="tel_no" name="tel_no" maxlength="10"
											style="width: 100%;" class="form-control"
											value="${editComp.contactNo2}" autocomplete="off"
											oninvalid="setCustomValidity('Please enter tel no')"
											onchange="try{setCustomValidity('')}catch(e){}"
											pattern="^[1-9]{1}[0-9]{9}$" /> <span class="error"
											aria-live="polite"></span>

									</div>


								</div> --%>

								<div class="form-group"></div>

								<div class="row">
									<div class="col-md-2">Logo*</div>
									<div class="col-md-4">
										<input type="file" id="file" name="file"  size="100"
											style="width: 100%;" class="form-control"
											
											class="form-control"
											required> <span class="error"
											aria-live="polite"></span>


									</div>

								</div>
								<div class="form-group"></div>
								<div class="col-lg-4"></div>
								<div class="col-lg-3">
									<input type="submit" class="btn btn-primary" value="Submit"
										id="submitButton"
										style="align-content: center; width: 113px; margin-left: 40px; background-color: #272c33;">

								</div>
								<div class="col-lg-3">
									<input type="reset" class="btn btn-primary" value="Clear"
										style="align-content: center; width: 113px; margin-left: 40px; background-color: #272c33;">

								</div>
							</form>
						</div>
						
					<div class="card-body card-block">
							<form
								action="${pageContext.request.contextPath}/deleteRecordofCompany"
								method="post">


								<table id="bootstrap-data-table"
									class="table table-striped table-bordered">
									<thead>
										<tr>
											<th class="check" style="text-align: center; width: 5%;"><input
												type="checkbox" name="selAll" id="selAll" /></th>
											<th style="text-align: center; width: 5%;">Sr</th>
											<th style="text-align: center">Company Name</th>
											<th style="text-align: center">Company Address</th>

											<th style="text-align: center">Mobile No.</th>
											<th style="text-align: center">Email</th>
											<th style="text-align: center">GST No.</th>
											<th style="text-align: center">PAN No.</th>
											<!-- <th style="text-align: center">Logo</th> -->
											<th style="text-align: center; width: 5%;">Action</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${compList}" var="comp" varStatus="count">
											<tr>
												<td><input type="checkbox" class="chk"
													name="companyIds" id="companyIds${count.index+1}"
													value="${comp.compId}" /></td>
											 	<td style="text-align: center">${count.index+1}</td>


												<td style="text-align: left"><c:out
														value="${comp.compName}" /></td>

												<td style="text-align: left"><c:out
														value="${comp.address}" /></td>

												<td style="text-align: center">${comp.phoneNo}</td>
												
												<td style="text-align: center">${comp.email}</td>
												
												<td style="text-align: center">${comp.gstid}</td>
												
												<td style="text-align: center">${comp.panNo}</td>
												
												<%-- <td style="text-align: center">${comp.logo}</td> --%>
												

												<td style="text-align: center"><a
													href="${pageContext.request.contextPath}/editCompany/${comp.compId}"><i
														class="fa fa-edit" title="Edit"></i> <span class="text-muted"></span></a>
													&nbsp; <a
													href="${pageContext.request.contextPath}/deleteCompany/${comp.compId}"
													onClick="return confirm('Are you sure want to delete this record');"><i
														class="fa fa-trash-o" title="Delete"></i></a></td>


											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="col-lg-1">

									<input type="submit" class="btn btn-primary" value="Delete"
										id="deleteId"
										onClick="var checkedVals = $('.chk:checkbox:checked').map(function() { return this.value;}).get();checkedVals=checkedVals.join(',');if(checkedVals==''){alert('No Rows Selected');return false;	}else{   return confirm('Are you sure want to delete record');}"
										style="align-content: center; width: 113px; margin-left: 40px;  background-color: #272c33;">


								</div>
							</form>

						</div>
					</div>
				</div>
			</div>


		</div>
		<!-- .animated -->
	</div>


	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<!-- Footer -->


	<script
		src="${pageContext.request.contextPath}/resources/assets/js/vendor/jquery-2.1.4.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/plugins.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>


	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/datatables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/dataTables.buttons.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/buttons.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/jszip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/pdfmake.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/vfs_fonts.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/buttons.html5.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/buttons.print.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/buttons.colVis.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/data-table/datatables-init.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/chosen/chosen.jquery.min.js"></script>

<script>
$('#selAll').click(function(e){
    var table= $(e.target).closest('table');
    $('td input:checkbox',table).prop('checked',this.checked);
});
</script>

	<script type="text/javascript">
	var minLength = 10;
	var maxLength = 50;
	$(document).ready(function(){
	    $('#comp_name').blur(function(){
	        var field = $(this).val();
	        var charLength = $(this).val().length;
	        if(charLength < minLength){
	            $('#cName').text('Invalid Company Name.');
	        }else if(charLength > maxLength){
	            $('#cName').text('Invalid Company Name.');
	            $(this).val(field.substring(0, maxLength));
	        }else{
	            $('#cName').text('');
	        }
	    });
	});	
	</script>
<script>
$("#mob_no").on("keypress keyup blur",function (event) {
    //this.value = this.value.replace(/[^0-9\.]/g,'');
	$(this).val($(this).val().replace(/[^0-9\.]/g,''));
    if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
        event.preventDefault();
    }
});
</script>

	<script>
		jQuery(document).ready(function() {
			jQuery(".standardSelect").chosen({
				disable_search_threshold : 2,
				no_results_text : "Oops, nothing found!",
				width : "100%"
			});
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#bootstrap-data-table-export').DataTable();
		});
	</script>



	<script>
		function upperCaseF(a) {
			setTimeout(function() {
				a.value = a.value.toUpperCase();
			}, 1);
		}
	</script>


	<script type="text/javascript">
		$(function() {
			$('#submitForm').submit(
					function() {
						$("input[type='submit']", this).val("Please Wait...")
								.attr('disabled', 'disabled');
						return true;
					});
		});
	</script>



	<script type="text/javascript">
		function getCheck() {

			var gstNo = $("#gst_no").val();
			var comp_id = document.getElementById("comp_id").value;

			$
					.getJSON(
							'${getUniqueCompanyCheck}',
							{

								gstNo : gstNo,
								comp_id : comp_id,

								ajax : 'true',

							},
							function(data) {

								if (comp_id == 0) {
									if (data.error == true) {
										alert("Company Already Exist");

										document.getElementById("gst_no").value = "";

										document.getElementById("submitButton").disabled = true;
									} else {
										document.getElementById("submitButton").disabled = false;

									}
								}
							}

					);

		}
	</script>
</body>
</html>
















<!-- ************************************************************************************ -->
<!-- ************************************************************************************ -->
<!-- ************************************************************************************ -->
<!-- ************************************************************************************ -->
