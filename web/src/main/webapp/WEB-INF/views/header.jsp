<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<nav>
		<div class="row"> <!-- nav-wrapper container -->
			<ul class="left hide-on-med-and-down">
				<li><a href="${contextPath}">home</a></li>
				<li><a href="${pagesCategory}">Category</a></li>
				<li><a href="${pagesComposition}">Composition</a></li>
				<li><a href="${pagesCondition}">Condition</a></li>
				<li><a href="${pagesCountryOrigin}">Origin country</a></li>
				<li><a href="${pagesPaymentMethod}">Payment</a></li>
				<li><a href="${pagesShippingMethod}">Shipping</a></li>
				<li><a href="${pagesAuctionRule}">Auction rules</a></li>
				<li><a href="${pagesStepBlock}">Step blocks</a></li>
				<li><a href="${pagesAdminCommunication}">Admin communication</a></li>
				<li><a href="${pagesAuctionDuration}">Auction duration</a></li>
				<li><a href="${pagesBid}">Bids</a></li>
				<li><a href="${pagesDeferredBid}">Deferred bids</a></li>
				<li><a href="${pagesFeedback}">Feedback</a></li>
				<li><a href="${pagesItem}">Items</a></li>
				<li><a href="${pagesMessage}">Messages</a></li>
				<li><a href="${pagesUserAccount}">User accounts</a></li>

				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${contextPath}/execute_logout"
						title="logout"><i class="material-icons">arrow_forward</i></a>
				</sec:authorize>
			</ul>
		</div>
	</nav>
</header>