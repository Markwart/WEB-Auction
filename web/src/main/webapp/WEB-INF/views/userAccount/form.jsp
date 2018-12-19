<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit user account</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesUserAccount}"
		modelAttribute="formModel">
		
		<form:input path="id" type="hidden" />
		
		
		<div class="row">
			<div class="input-field col s12">
				<form:input path="email" type="text" disabled="${readonly}" />
				<form:errors path="email" cssClass="red-text" />
				<label for="email">email</label>
			</div>
			<div class="input-field col s12">
				<form:input path="password" type="text" disabled="${readonly}" />
				<form:errors path="password" cssClass="red-text" />
				<label for="password">password</label>
			</div>
			<div class="input-field col s12">
				<form:input path="role" disabled="${readonly}" />
				<form:errors path="role" cssClass="red-text" />
				<label for="role">role</label>
			</div>
		</div>
		
		<div class="row">
            <div class="input-field  col s12">
                <form:input path="personalData.firstName" disabled="${readonly}" />
                <form:errors path="personalData.firstName" cssClass="red-text" />
                <label for="personalData.firstName">first name</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field  col s12">
                <form:input path="personalData.lastName" disabled="${readonly}" />
                <form:errors path="personalData.lastName" cssClass="red-text" />
                <label for="personalData.lastName">last name</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field  col s12">
                <form:input path="personalData.adress" disabled="${readonly}" />
                <form:errors path="personalData.adress" cssClass="red-text" />
                <label for="personalData.adress">adress</label>
            </div>
        </div>
         <div class="row">
            <div class="input-field  col s12">
                <form:input path="personalData.userName" disabled="${readonly}" />
                <form:errors path="personalData.userName" cssClass="red-text" />
                <label for="personalData.userName">username</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesUserAccount}">к списку<i
					class="material-icons right"></i>
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

