<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<nav>
		<div class="nav-wrapper container">

			<!-- Dropdown Structure -->
			<sec:authorize access="hasAnyRole('admin', 'moderator')">
				<ul id="dropdown1" class="dropdown-content">
					<li><a href="${pagesCategory}">Category</a></li>
					<li><a href="${pagesComposition}">Composition</a></li>
					<li><a href="${pagesCondition}">Condition</a></li>
					<li><a href="${pagesCountryOrigin}">Origin country</a></li>
					<li><a href="${pagesPaymentMethod}">Payment</a></li>
					<li><a href="${pagesShippingMethod}">Shipping</a></li>
					<li><a href="${pagesStepBlock}">Step blocks</a></li>
					<li><a href="${pagesAuctionDuration}">Auction duration</a></li>
					<!-- <li class="divider"></li> -->
				</ul>
				<ul id="dropdown2" class="dropdown-content">
					<li><a href="${pagesUserAccount}">User accounts</a></li>
					<li><a href="${pagesAdminCommunication}">Admin
							communication</a></li>
					<li><a href="${pagesMessage}">Messages</a></li>
					<li><a href="${pagesBid}">Bids</a></li>
					<li><a href="${pagesDeferredBid}">Deferred bids</a></li>
					<!-- <li class="divider"></li> -->
				</ul>
			</sec:authorize>
			<nav>
				<div class="nav-wrapper">
					<a href="#!" class="brand-logo">Auction</a>
					<ul class="right hide-on-med-and-down">
						<li><a href="${contextPath}">home</a></li>
						<li><a href="${pagesAuctionRule}">Auction rules</a></li>
						<li><a href="${pagesFeedback}">Feedback</a></li>
						<li><a href="${pagesItem}">Items</a></li>

						<!-- Dropdown Trigger -->
						<sec:authorize access="hasAnyRole('admin', 'moderator')">
							<li><a class="dropdown-trigger" href="#!"
								data-target="dropdown1">Working settings<i
									class="material-icons right">arrow_drop_down</i></a></li>
							<li><a class="dropdown-trigger" href="#!"
								data-target="dropdown2">Manage users<i
									class="material-icons right">arrow_drop_down</i></a></li>
						</sec:authorize>
					</ul>
				</div>
			</nav>
		</div>

		<div class="current-user">
			<sec:authentication property="name" />
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

			<sec:authorize access="isAnonymous()">
				<a class="right" href="${contextPath}/login" title="login"><i
					class="material-icons">open_in_browser</i></a>
			</sec:authorize>

		</div>

	</nav>
</header>