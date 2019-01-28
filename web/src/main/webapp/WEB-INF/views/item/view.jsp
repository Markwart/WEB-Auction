<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<div class="grid-container">
	<div class="column-2">
		<div class="ct-one">
			<ul class="information-list">
				<li class="item-name">${formView.name}</li>
				<li class="divider" tabindex="-1"></li>
				<li><mytaglib:i18n key="section.condition" />: &#8194<q>${formView.conditionName}</q></li>
				<li><mytaglib:i18n key="table.column.starting-price" />: <b><i>&#8194
							US $<fmt:formatNumber value="${formView.startingPrice}"
								minFractionDigits="2" maxFractionDigits="2" />
					</i></b></li>
				<li><mytaglib:i18n key="auction-ends" />: &#8194<q><fmt:formatDate
							pattern="dd MMMM yyyy HH:mm:ss" value="${formView.auctionEnd}" /></q></li>
				<li><mytaglib:i18n key="time-left" />: &#8194<b><i><span
							id="demo"></span></i></b></li>
			</ul>
		</div>
		<div class="ct-two">
			<table class="striped additional-inf">
				<tbody>
					<tr>
						<td><mytaglib:i18n key="current-price" />: &#8194<b><i>US
									$<c:if test="${latestBid[0].priceBid != null}">
										<fmt:formatNumber value="${latestBid[0].priceBid}"
											minFractionDigits="2" maxFractionDigits="2" />
									</c:if> <c:if test="${latestBid[0].priceBid == null}">
										<fmt:formatNumber value="${formView.startingPrice}"
											minFractionDigits="2" maxFractionDigits="2" />
									</c:if>
							</i></b></td>
						<td><span class="count-bids">(${formView.totalCountBids}
								<mytaglib:i18n key="count-bids" />)
						</span></td>
					</tr>
					<sec:authorize access="!isAnonymous()">
						<c:if test="${formView.sellerId != loggedUser.id}">
							<tr>
								<form:form method="POST"
									action="${pagesBid}/${formView.id}/placeBid"
									modelAttribute="formBid">
									<td><form:input path="priceBid" type="text"
											class="bidClass" autocomplete="off" value="" /> <form:errors
											path="priceBid" cssClass="red-text" /></td>
									<td><button
											class="waves-effect waves-light btn bid-botton" type="submit">
											<mytaglib:i18n key="place-bid" />
										</button></td>
								</form:form>
							</tr>
						</c:if>
					</sec:authorize>
					<tr>
						<td><mytaglib:i18n key="leader" />: &#8194<i><c:if
									test="${latestBid[0].userBid != null}">${latestBid[0].userBid.email}</c:if>
								<c:if test="${latestBid[0].userBid == null}">
									<mytaglib:i18n key="none" />
								</c:if></i></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="ct-three">
			<table class="striped additional-inf">
				<tbody>
					<tr>
						<td><mytaglib:i18n key="section.category" />:</td>
						<td><q>${formView.categoryName}</q></td>
					</tr>
					<tr>
						<td><mytaglib:i18n key="country-manufacture" />:</td>
						<td><q>${formView.countryOriginName}</q></td>
					</tr>
					<tr>
						<td><mytaglib:i18n key="section.composition" />:</td>
						<td><q>${formView.compositionName}</q></td>
					</tr>
					<tr>
						<td><mytaglib:i18n key="section.shipping" />:</td>
						<td><q><c:forEach var="shippingMethodsNames"
									items="${formView.shippingMethodsNames}">
								-&nbsp;${shippingMethodsNames} 	
							</c:forEach></q></td>
					</tr>
					<tr>
						<td><mytaglib:i18n key="section.payment" />:</td>
						<td><q><c:forEach var="paymentMethodsNames"
									items="${formView.paymentMethodsNames}">
								-&nbsp;${paymentMethodsNames}
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
			<b><mytaglib:i18n key="table.column.text" />: &#8194</b><q>${formView.text}</q>
		</div>
	</div>
	<div class="column-3">
		<div class="half-area">
			<ul class="information-list">
				<li><b><mytaglib:i18n key="seller-info" /></b></li>
				<li class="divider" tabindex="-1"></li>
				<li class="seller-name">${formView.sellerEmail}</li>
				<li class="seller-name">${userAccountData.personalData.city},
					${userAccountData.personalData.country}</li>
				<li class="divider" tabindex="-1"></li>
				<li><a
					href="${pagesFeedback}/userFeedback/${formView.sellerId}"><i
						class="material-icons">feedback</i> <mytaglib:i18n
							key="section.feedback" /></a></li>
				<li><a href="${pagesItem}/userItems/${formView.sellerId}"><i
						class="material-icons">storage</i> <mytaglib:i18n
							key="see-other-items" /></a></li>


				<c:if test="${formView.sellerId != loggedUser.id}">
					<sec:authorize access="!isAnonymous()">
						<li><a href="${pagesMessage}/${formView.id}/add"><i
								class="material-icons">mail_outline</i> <mytaglib:i18n
									key="contact-seller" /></a></li>
						<li class="divider" tabindex="-1"></li>

						<%-- <spring:eval var="containsValue"
							expression="loggedUser.itemsIds.contains(formView.id)" />
						<c:if test="${containsValue}"> --%>

						<c:if test="${not fn:contains(loggedUser.itemsIds, formView.id)}">
							<li><a
								href="${pagesUserAccount}/${formView.id}/addWatchList"><i
									class="material-icons">library_books</i> <mytaglib:i18n
										key="add/watch-list" /></a></li>
						</c:if>
						<c:if test="${fn:contains(loggedUser.itemsIds, formView.id)}">
							<li><a
								href="${pagesUserAccount}/${formView.id}/removeWatchList"><i
									class="material-icons">library_books</i> <mytaglib:i18n
										key="remove/watch-list" /></a></li>
						</c:if>

					</sec:authorize>
				</c:if>
				<c:if test="${formView.sellerId == loggedUser.id}">
					<li><a href="#!<%-- ${pagesItem}/${formView.id}/edit --%>"><i
							class="material-icons">edit</i> <mytaglib:i18n key="edit" /></a></li>
				</c:if>
				<c:if test="${formView.sellerId != loggedUser.id}">
					<li><a href="${pagesFeedback}/add"><i
							class="material-icons">feedback</i> <mytaglib:i18n
								key="leave-feedback" /></a></li>
				</c:if>
			</ul>

		</div>
	</div>
</div>


<script>
	// Set the date we're counting down to
	var countDownDate = new Date("${formView.auctionEnd}").getTime();

	// Update the count down every 1 second
	var x = setInterval(function() {

		// Get todays date and time
		var now = new Date().getTime();

		// Find the distance between now and the count down date
		var distance = countDownDate - now;

		// Time calculations for days, hours, minutes and seconds
		var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
				/ (1000 * 60 * 60));
		var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((distance % (1000 * 60)) / 1000);

		// Display the result in the element with id="demo"
		document.getElementById("demo").innerHTML = days + " days " + hours
				+ " hours " + minutes + " min " + seconds + " s ";

		// If the count down is finished, write some text 
		if (distance < 0) {
			clearInterval(x);
			document.getElementById("demo").innerHTML = "EXPIRED";
		}
	}, 1000);
</script>



