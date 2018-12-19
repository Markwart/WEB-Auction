<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit feedback</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesFeedback}"
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
				<form:select path="userAccountFromId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userAccountFromId" cssClass="red-text" />
				<label for="userAccountFromId">from</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:select path="userAccountWhomId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userAccountWhomId" cssClass="red-text" />
				<label for="userAccountWhomId">whom</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input path="communication" type="text" disabled="${readonly}" />
				<form:errors path="communication" cssClass="red-text" />
				<label for="communication">communication</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input path="shippingTime" type="text" disabled="${readonly}" />
				<form:errors path="shippingTime" cssClass="red-text" />
				<label for="shippingTime">shipping time</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input path="shippingCharges" type="text" disabled="${readonly}" />
				<form:errors path="shippingCharges" cssClass="red-text" />
				<label for="shippingCharges">shipping charges</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input path="itemDescription" type="text" disabled="${readonly}" />
				<form:errors path="itemDescription" cssClass="red-text" />
				<label for="itemDescription">item description</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input path="comment" type="text" disabled="${readonly}" />
				<form:errors path="comment" cssClass="red-text" />
				<label for="comment">comment</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesFeedback}">к
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

