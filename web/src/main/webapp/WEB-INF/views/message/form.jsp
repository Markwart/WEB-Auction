<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>


<h4 class="header">
	<mytaglib:i18n key="section.message-edit" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST"
		action="${pagesMessage}/${formModelItem.id}/send"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />

		<sec:authorize access="hasAnyRole('admin', 'moderator')">
			<div class="row">
				<div class="input-field col s12">
					<form:select path="itemId" disabled="${readonly}">
						<form:options items="${itemsChoices}" />
					</form:select>
					<form:errors path="itemId" cssClass="red-text" />
					<label for="itemId"><mytaglib:i18n
							key="table.column.item-name" /></label>
				</div>
			</div>
		</sec:authorize>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="theme" type="text" disabled="${readonly}" />
				<form:errors path="theme" cssClass="red-text" />
				<label for="theme"><mytaglib:i18n key="table.column.theme" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="text" type="text" disabled="${readonly}" />
				<form:errors path="text" cssClass="red-text" />
				<label for="text"><mytaglib:i18n key="table.column.content" /></label>
			</div>
		</div>

		<sec:authorize access="hasAnyRole('admin', 'moderator')">
			<div class="row">
				<div class="input-field col s12">
					<form:select path="userFromId" disabled="${readonly}">
						<form:options items="${userAccountsChoices}" />
					</form:select>
					<form:errors path="userFromId" cssClass="red-text" />
					<label for="userFromId"><mytaglib:i18n
							key="table.column.from" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:select path="userWhomId" disabled="${readonly}">
						<form:options items="${userAccountsChoices}" />
					</form:select>
					<form:errors path="userWhomId" cssClass="red-text" />
					<label for="userWhomId"><mytaglib:i18n
							key="table.column.seller" /></label>
				</div>
			</div>
		</sec:authorize>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<sec:authorize access="hasAnyRole('admin', 'moderator')">
					<a class="btn waves-effect waves-light right color-button"
						href="${pagesMessage}"><mytaglib:i18n key="list" /><i
						class="material-icons right"></i></a>
				</sec:authorize>
			</div>

			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right color-button"
						type="submit">
						<mytaglib:i18n key="send" />
					</button>
				</c:if>
			</div>
		</div>
	</form:form>
</div>


