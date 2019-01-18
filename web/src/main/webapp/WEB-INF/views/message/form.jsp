<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit message</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesMessage}"
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
				<form:input path="theme" type="text" disabled="${readonly}" />
				<form:errors path="theme" cssClass="red-text" />
				<label for="theme">theme</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="text" type="text" disabled="${readonly}" />
				<form:errors path="text" cssClass="red-text" />
				<label for="text">content</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:select path="userFromId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userFromId" cssClass="red-text" />
				<label for="userFromId">from</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:select path="userWhomId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userWhomId" cssClass="red-text" />
				<label for="userWhomId">whom</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesMessage}">list<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>


