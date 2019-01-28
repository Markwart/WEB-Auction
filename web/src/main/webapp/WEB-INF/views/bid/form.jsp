<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>


<h4 class="header"><mytaglib:i18n key="section.bid-edit"></mytaglib:i18n></h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesBid}"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="itemId" disabled="${readonly}">
					<form:options items="${itemsChoices}" />
				</form:select>
				<form:errors path="itemId" cssClass="red-text" />
				<label for="itemId"><mytaglib:i18n key="table.column.item-name"></mytaglib:i18n></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="priceBid" type="text" disabled="${readonly}" />
				<form:errors path="priceBid" cssClass="red-text" />
				<label for="priceBid"><mytaglib:i18n key="table.column.bid"></mytaglib:i18n></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="userBidId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userBidId" cssClass="red-text" />
				<label for="userBidId"><mytaglib:i18n key="table.column.user-account"></mytaglib:i18n></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="statusBid" disabled="${readonly}">
					<form:options items="${statusBidChoices}" />
				</form:select>
				<form:errors path="statusBid" cssClass="red-text" />
				<label for="statusBid"><mytaglib:i18n key="table.column.status"></mytaglib:i18n></label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n key="save"></mytaglib:i18n></button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesBid}"><mytaglib:i18n key="list"></mytaglib:i18n><i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>




