<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit step block</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesStepBlock}"
		modelAttribute="formModel">
		
		<form:input path="id" type="hidden" />
		
		
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">name</label>
			</div>
			<div class="input-field col s12">
				<form:input path="step_1" disabled="${readonly}" />
				<form:errors path="step_1" cssClass="red-text" />
				<label for="step_1">step 1</label>
			</div>
			<div class="input-field col s12">
				<form:input path="step_2" disabled="${readonly}" />
				<form:errors path="step_2" cssClass="red-text" />
				<label for="step_2">step 2</label>
			</div>
			<div class="input-field col s12">
				<form:input path="step_3" disabled="${readonly}" />
				<form:errors path="step_3" cssClass="red-text" />
				<label for="step_3">step 3</label>
			</div>
			<div class="input-field col s12">
				<form:input path="step_4" disabled="${readonly}" />
				<form:errors path="step_4" cssClass="red-text" />
				<label for="step_4">step 4</label>
			</div>
			<div class="input-field col s12">
				<form:input path="step_5" disabled="${readonly}" />
				<form:errors path="step_5" cssClass="red-text" />
				<label for="step_5">step 5</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesStepBlock}">list<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>



