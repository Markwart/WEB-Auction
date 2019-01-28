<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h4 class="header offset-class">
	<c:choose>
		<c:when test="${watchList}">
			<mytaglib:i18n key="section.watch-list" />
		</c:when>
		<c:when test="${privateList}">
			<mytaglib:i18n key="section.my-item" />
		</c:when>
		<c:otherwise>
			<mytaglib:i18n key="section.item"></mytaglib:i18n>
		</c:otherwise>
	</c:choose>
</h4>

<c:if test="${privateList eq 'false'}">
	<div class="row">
		<nav class="search-from-db">
			<div class="nav-wrapper">
				<form:form method="POST" action="${pagesItem}"
					modelAttribute="searchFormModel">
					<div class="input-field">
						<form:input path="name" type="search" id="search-item" />
						<form:errors path="name" cssClass="red-text" />
						<label class="label-icon" for="search"> <i
							class="material-icons">search</i></label>
						<button class="button-reset" type="button" onclick="form.reset()">
							<i class="material-icons">close</i>
						</button>
					</div>
				</form:form>
			</div>
		</nav>
	</div>
</c:if>


<c:if test="${privateList eq 'false'}">
	<div class="sorting">
		<div class="right">
			<a class="dropdown-trigger btn" data-target="dropdown4"><mytaglib:i18n
					key="sort"></mytaglib:i18n><i class="material-icons right">arrow_drop_down</i></a>
		</div>

		<!-- Dropdown Structure -->
		<ul id="dropdown4" class="dropdown-content sorting-dropdown">
			<li><mytaglib:sort-link pageUrl="${pagesItem}" column="name">
					<mytaglib:i18n key="table.column.name"></mytaglib:i18n>
				</mytaglib:sort-link></li>
			<li class="divider" tabindex="-1"></li>
			<li><mytaglib:sort-link pageUrl="${pagesItem}"
					column="starting_price">
					<mytaglib:i18n key="table.column.starting-price"></mytaglib:i18n>
				</mytaglib:sort-link></li>
			<li class="divider" tabindex="-1"></li>
			<li><mytaglib:sort-link pageUrl="${pagesItem}"
					column="auction_end">
					<mytaglib:i18n key="table.column.ends"></mytaglib:i18n>
				</mytaglib:sort-link></li>
			<li class="divider" tabindex="-1"></li>
			<li><mytaglib:sort-link pageUrl="${pagesItem}"
					column="seller_id">
					<mytaglib:i18n key="table.column.seller"></mytaglib:i18n>
				</mytaglib:sort-link></li>
			<li class="divider" tabindex="-1"></li>
			<li><mytaglib:sort-link pageUrl="${pagesItem}" column="created">
					<mytaglib:i18n key="table.column.created-2"></mytaglib:i18n>
				</mytaglib:sort-link></li>
		</ul>
	</div>
</c:if>


<div class="container-items">
	<c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
		<div class="browse">
			<div class="browsetext-top">
				<div class="right">
					<span><b><mytaglib:i18n key="table.column.ends" />: <fmt:formatDate
								pattern="dd-MMM-yyyy HH:mm" value="${item.auctionEnd}" /></b></span>
				</div>
			</div>
			<div class="browseimg">
				<a href="${pagesItem}/${item.id}"><img
					src="${filesUrl}/image?uuid=${item.image}"></a>
			</div>
			<div class="browsetext">
				<span><a href="${pagesItem}/${item.id}">${item.name}</a></span>
				<div class="bottom">
					<c:if test="${item.sellerId != loggedUser.id}">
						<div class="left">
							<sec:authorize access="!isAnonymous()">
								<c:if test="${not fn:contains(loggedUser.itemsIds, item.id)}">
									<a href="${pagesUserAccount}/${item.id}/addWatchList/main"><input
										type="button"
										value="<mytaglib:i18n
			key="add-watch-list" />"></a>
								</c:if>
								<c:if test="${fn:contains(loggedUser.itemsIds, item.id)}">
									<a href="${pagesUserAccount}/${item.id}/removeWatchList/main"><input
										type="button"
										value="<mytaglib:i18n
			key="remove-watch-list" />"></a>
								</c:if>
							</sec:authorize>
						</div>
					</c:if>
					<div class="right">
						<b><mytaglib:i18n key="table.column.starting-price" />: US $<fmt:formatNumber
								value="${item.startingPrice}" minFractionDigits="2"
								maxFractionDigits="2" /></b>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<div class="padding">
	<jspFragments:paging />
</div>

<c:if test="${privateList}">
	<a class="waves-effect waves-light btn right" href="${pagesItem}/add"><mytaglib:i18n
			key="place-item" /></a>
</c:if>


