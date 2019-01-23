<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="grid-container">
	<div class="column-2">
		<div class="ct-one">
			<ul class="information-list">
				<li class="item-name"><c:out value="${formView.name}" /></li>
				<li class="divider" tabindex="-1"></li>
				<li>Condition: &#8194<q><c:out
							value="${formView.conditionName}" /></q></li>
				<li>Starting price: <b><i>&#8194 US $<c:out
								value="${formView.startingPrice}"></c:out></i></b></li>
				<li>Auction ends: &#8194<q><fmt:formatDate
							pattern="dd MMMM yyyy HH:mm:ss" value="${formView.auctionEnd}" /></q></li>
				<li>Time left:</li>
			</ul>
		</div>
		<div class="ct-two">
			<table class="striped additional-inf">

				<tbody>
					<tr>
						<td>Current price: &#8194<b><i>US $<c:out
										value="${formView.startingPrice}"></c:out></i></b></td>
						<td><span class="count-bids">(0 bids)</span></td>
					</tr>
					<tr>
						<form:form method="POST"
							action="${pagesBid}/${formView.id}/placeBid"
							modelAttribute="formBid">
							<td><form:input path="priceBid" type="text" placeholder=""
									class="bidClass" autocomplete="off" value="" /> <form:errors
									path="priceBid" cssClass="red-text" /></td>
							<td><a class="waves-effect waves-light btn bid-botton"
								type="submit">Place bid</a></td>
						</form:form>
					</tr>
					<tr>
						<td>Leader: &#8194<i>Unknown</i></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="ct-three">
			<table class="striped additional-inf">
				<tbody>
					<tr>
						<td>Category:</td>
						<td><q><c:out value="${formView.categoryName}" /></q></td>
					</tr>
					<tr>
						<td>Country of Manufacture:</td>
						<td><q><c:out value="${formView.countryOriginName}" /></q></td>
					</tr>
					<tr>
						<td>Composition:</td>
						<td><q><c:out value="${formView.compositionName}" /></q></td>
					</tr>
					<tr>
						<td>Shipping:</td>
						<td><q><c:forEach var="shippingMethodsNames"
									items="${formView.shippingMethodsNames}">
								-&nbsp;${shippingMethodsNames}, 	
							</c:forEach></q></td>
					</tr>
					<tr>
						<td>Payment:</td>
						<td><q><c:forEach var="paymentMethodsNames"
									items="${formView.paymentMethodsNames}">
								-&nbsp;${paymentMethodsNames},
							</c:forEach></q></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="column-1">
		<div class="photo-area">
			<img class="main-item-image"
				src="${filesUrl}/image?uuid=${formView.image}" />
		</div>
	</div>
	<div class="row-2">
		<div class="com-row-2">
			<b>Description: &#8194</b><q><c:out value="${formView.text}" /></q>
		</div>
	</div>
	<div class="column-3">
		<div class="half-area">
			<ul class="information-list">
				<li><b>Seller information</b></li>
				<li class="divider" tabindex="-1"></li>
				<li class="seller-name"><c:out value="${formView.sellerEmail}" /></li>
				<li><c:out
						value="${userAccountData.personalData.city}, ${userAccountData.personalData.country}"></c:out></li>
				<li class="divider" tabindex="-1"></li>
				<li><a
					href="${pagesFeedback}/userFeedback/${formView.sellerId}">Feedback</a></li>
				<li><a href="${pagesItem}/userItems/${formView.sellerId}">See
						other items</a></li>

				<c:if test="${formView.sellerId != showSomeElements}">
					<sec:authorize access="!isAnonymous()">
						<li><a href="${pagesMessage}/${formView.id}/add">Contact
								seller</a></li>
						<li class="divider" tabindex="-1"></li>
						<li class="add-watch-list"><a
							href="${pagesUserAccount}/${formView.id}/watchList"><i
								class="material-icons">library_books</i>Add to Watch List</a></li>
					</sec:authorize>
				</c:if>
			</ul>

		</div>
	</div>
</div>



