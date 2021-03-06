<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header">Edit item</h4>
<div class="row">
	<form:form class="col s12" method="POST" enctype="multipart/form-data"
		action="${pagesItem}/add" modelAttribute="formModel">

		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">name</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="year" disabled="${readonly}" />
				<form:errors path="year" cssClass="red-text" />
				<label for="year">year</label>
			</div>
		</div>

		<%-- <div class="row">
			<div class="input-field col s12">
				<form:input path="image" disabled="${readonly}" />
				<form:errors path="image" cssClass="red-text" />
				<label for="image">image</label>
			</div>
		</div>  --%>

		<sec:authorize access="hasAnyRole('admin','user')">
			<div>
				<table>
					<tr>
						<td>Image to upload:</td>
						<td><input type="file" name="file" /></td>
					</tr>
				</table>
			</div>
		</sec:authorize>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="startingPrice" disabled="${readonly}" />
				<form:errors path="startingPrice" cssClass="red-text" />
				<label for="startingPrice">starting price</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="text" type="text" disabled="${readonly}" />
				<form:errors path="text" cssClass="red-text" />
				<label for="text">text</label>
			</div>
		</div>

		<sec:authorize access="hasAnyRole('admin','user')">
			<div class="row">
				<div class="input-field col s12">
					<form:select path="durationId" disabled="${readonly}">
						<form:options items="${auctionDurationChoices}" />
					</form:select>
					<form:errors path="durationId" cssClass="red-text" />
					<label for="durationId">duration</label>
				</div>
			</div>
			<%-- <div class="row">
				<div class="input-field col s12">
					<form:input path="auctionEnd" disabled="${readonly}" />
					<form:errors path="auctionEnd" cssClass="red-text" />
					<label for="auctionEnd">auction end</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<form:select path="statusAuction" disabled="${readonly}">
						<form:options items="${statusAuctionChoices}" />
					</form:select>
					<form:errors path="statusAuction" cssClass="red-text" />
					<label for="statusAuction">status</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<form:select path="sellerId" disabled="${readonly}">
						<form:options items="${sellerChoices}" />
					</form:select>
					<form:errors path="sellerId" cssClass="red-text" />
					<label for="sellerId">user account</label>
				</div>
			</div> --%>
		</sec:authorize>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="categoryId" disabled="${readonly}">
					<form:options items="${categoryChoices}" />
				</form:select>
				<form:errors path="categoryId" cssClass="red-text" />
				<label for="categoryId">category</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="conditionId" disabled="${readonly}">
					<form:options items="${conditionChoices}" />
				</form:select>
				<form:errors path="conditionId" cssClass="red-text" />
				<label for="conditionId">condition</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="compositionId" disabled="${readonly}">
					<form:options items="${compositionChoices}" />
				</form:select>
				<form:errors path="compositionId" cssClass="red-text" />
				<label for="compositionId">composition</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="countryOriginId" disabled="${readonly}">
					<form:options items="${countryOriginChoices}" />
				</form:select>
				<form:errors path="countryOriginId" cssClass="red-text" />
				<label for="countryOriginId">country origin</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field  col s12">
				<form:select path="shippingMethodsIds" disabled="${readonly}"
					multiple="true">
					<option value=""disabled "${emptyformModel.shippingMethodsIds? "selected":""}">choose
						shipping methods</option>
					<form:options items="${shippingMethodChoices}" />
				</form:select>
				<form:errors path="shippingMethodsIds" cssClass="red-text" />
				<label for="shippingMethodsIds" class="multiselect-label">shipping
					methods</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field  col s12">
				<form:select path="paymentMethodsIds" disabled="${readonly}"
					multiple="true">
					<option value=""disabled ${emptyformModel.paymentMethodsIds? "selected":""}>choose
						payment methods</option>
					<form:options items="${paymentMethodChoices}" />
				</form:select>
				<form:errors path="paymentMethodsIds" cssClass="red-text" />
				<label for="paymentMethodsIds" class="multiselect-label">payment
					methods</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesItem}">list<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>


