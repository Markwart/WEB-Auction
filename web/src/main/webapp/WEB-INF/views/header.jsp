<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${contextPath}">home</a></li>
				<li><a href="${pagesCondition}">Admin Communication</a></li>
				<li><a href="${pagesCondition}">Auction Rules</a></li>
				<li><a href="${pagesCondition}">Auction Duration</a></li>
				<li><a href="${pagesCondition}">Bids</a></li>
				<li><a href="${pagesCondition}">Deferred Bids</a></li>
				<li><a href="${pagesCategory}">Category</a></li>
				<li><a href="${pagesComposition}">Composition</a></li>
				<li><a href="${pagesCondition}">Condition</a></li>
				<%-- <li><a href="${pagesCountruOrigin}">Origin country</a></li>
				<li><a href="${pagesCondition}">Feedback</a></li>
				<li><a href="${pagesCondition}">Items</a></li>
				<li><a href="${pagesCondition}">Messages</a></li>
				<li><a href="${pagesCondition}">Payment</a></li>
				<li><a href="${pagesCondition}">Shipping</a></li>
				<li><a href="${pagesCondition}">Step blocks</a></li>
				<li><a href="${pagesCondition}">User Accounts</a></li>
				<li><a href="${pagesCondition}">Personal Data</a></li> --%>
				
				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${contextPath}/execute_logout" title="logout"><i
						class="material-icons">arrow_forward</i></a>
				</sec:authorize>
			</ul>
		</div>
	</nav>
</header>