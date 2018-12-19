<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit deferred bid</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesDeferredBid}"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="itemId" disabled="${readonly}">
					<form:options items="${itemsChoices}" />
				</form:select>
				<form:errors path="itemId" cssClass="red-text" />
				<label for="itemId">item</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="priceBid" type="text" disabled="${readonly}" />
				<form:errors path="priceBid" cssClass="red-text" />
				<label for="priceBid">price</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="userAccountId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userAccountId" cssClass="red-text" />
				<label for="userAccountId">user account</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="statusBid" type="text" disabled="${readonly}" />
				<form:errors path="statusBid" cssClass="red-text" />
				<label for="statusBid">status</label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">сохранить</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesDeferredBid}">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>


<c:if test='${param["showAlerts"]}'>
	<!-- checks the URL parameter -->


	<script src="${contextPath}/resources/js/sample-alert-with-params.js"></script>
	<script>
		showMessage('${contextPath}'); // execute function defined somewhere above
	</script>

</c:if>

