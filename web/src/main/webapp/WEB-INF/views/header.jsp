<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<nav>
		<div class="nav-wrapper container">
			<!-- row -->
			<ul class="menu-position-center">
				<li><a href="${contextPath}">home</a></li>
				<li><a href="${pagesCategory}">Category</a></li>
				<li><a href="${pagesComposition}">Composition</a></li>
				<li><a href="${pagesCondition}">Condition</a></li>
				<li><a href="${pagesCountryOrigin}">Origin country</a></li>
				<li><a href="${pagesPaymentMethod}">Payment</a></li>
				<li><a href="${pagesShippingMethod}">Shipping</a></li>
				<li><a href="${pagesAuctionRule}">Auction rules</a></li>
				<li><a href="${pagesStepBlock}">Step blocks</a></li>
				<li><a href="${pagesAdminCommunication}">Admin
						communication</a></li>
				<li><a href="${pagesAuctionDuration}">Auction duration</a></li>
				<li><a href="${pagesBid}">Bids</a></li>
				<li><a href="${pagesDeferredBid}">Deferred bids</a></li>
				<li><a href="${pagesFeedback}">Feedback</a></li>
				<li><a href="${pagesItem}">Items</a></li>
				<li><a href="${pagesMessage}">Messages</a></li>
				<li><a href="${pagesUserAccount}">User accounts</a></li>
			</ul>
		</div>

		<div class="current-user">
			<sec:authorize access="permitAll">
				<sec:authentication property="name" />
			</sec:authorize>

			<sec:authorize access="hasRole('admin')">
				<div class="right" title="admin">
					<i class="material-icons">supervisor_account</i>
				</div>
			</sec:authorize>

			<sec:authorize access="hasRole('moderator')">
				<div class="right" title="moderator">
					<i class="material-icons">supervisor_account</i>
				</div>
			</sec:authorize>

			<sec:authorize access="hasRole('user')">
				<div class="right" title="user">
					<i class="material-icons">perm_identity</i>
				</div>
			</sec:authorize>

			<sec:authorize access="!isAnonymous()">
				<a class="right" href="${contextPath}/execute_logout" title="logout"><i
					class="material-icons">exit_to_app</i></a>
			</sec:authorize>
		</div>

	</nav>
</header>