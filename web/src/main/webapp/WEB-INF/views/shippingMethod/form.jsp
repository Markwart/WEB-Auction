<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit shipping method</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesShippingMethod}"
		modelAttribute="formModel">
		
		<form:input path="id" type="hidden" />
		
		
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">name</label>
			</div>
			<div class="input-field col s12">
				<form:input path="cost" type="text" disabled="${readonly}" />
				<form:errors path="cost" cssClass="red-text" />
				<label for="cost">cost</label>
			</div>
			<div class="input-field col s12">
				<form:input path="deliveryTime" type="text" disabled="${readonly}" />
				<form:errors path="deliveryTime" cssClass="red-text" />
				<label for="deliveryTime">delivery time</label>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesShippingMethod}">list<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>


