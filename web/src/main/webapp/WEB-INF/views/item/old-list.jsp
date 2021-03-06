<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header offset-class">
	<mytaglib:i18n key="section.item"></mytaglib:i18n>
</h4>

<c:if test="${showSomeElements eq 'false'}">
	<div class="row">
		<nav class="search-from-db">
			<div class="nav-wrapper">
				<form:form method="POST" action="${pagesItem}"
					modelAttribute="searchFormModel">
					<div class="input-field">
						<form:input path="name" type="search" id="search-item" />
						<form:errors path="name" cssClass="red-text"/>
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

<!-- [A-Za-zА-Яа-яЁё_]{1,}[a-zA-Z-А-Яа-яЁё0-9\s]+$ -->

<table class="bordered highlight offset-class">
	<tbody class="item-table">
		<tr class="font-my-set">
			<th class="center"><mytaglib:sort-link pageUrl="${pagesItem}"
					column="image">
					<mytaglib:i18n key="table.column.image"></mytaglib:i18n>
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesItem}" column="name">
					<mytaglib:i18n key="table.column.name"></mytaglib:i18n>
				</mytaglib:sort-link></th>
			<th class="center"><mytaglib:sort-link pageUrl="${pagesItem}"
					column="starting_price">
					<mytaglib:i18n key="table.column.price"></mytaglib:i18n>
				</mytaglib:sort-link></th>
			<th class="center"><mytaglib:sort-link pageUrl="${pagesItem}"
					column="auction_end">
					<mytaglib:i18n key="table.column.ends"></mytaglib:i18n>
				</mytaglib:sort-link></th>
			<th class="center"><mytaglib:sort-link pageUrl="${pagesItem}"
					column="seller_id">
					<mytaglib:i18n key="table.column.seller"></mytaglib:i18n>
				</mytaglib:sort-link></th>
			<th class="center"><mytaglib:sort-link pageUrl="${pagesItem}"
					column="status_auction">
					<mytaglib:i18n key="table.column.status"></mytaglib:i18n>
				</mytaglib:sort-link></th>

			<sec:authorize access="hasAnyRole('admin', 'moderator')">
				<th><mytaglib:sort-link pageUrl="${pagesItem}" column="created">created</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pagesItem}" column="updated">updated</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pagesItem}" column="id">id</mytaglib:sort-link></th>
			</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
			<tr class="font-my-set">
				<td class="center image-column-set"><img
					class="small-item-image" src="${filesUrl}/image?uuid=${item.image}" />
				</td>
				<td><a href="${pagesItem}/${item.id}"><c:out
							value="${item.name}" /></a></td>

				<td class="center">US $<c:out value="${item.startingPrice}" /></td>

				<td class="center no-transfer"><fmt:formatDate
						pattern="dd/MM/yyyy HH:mm" value="${item.auctionEnd}" /></td>
				<td class="center"><c:out value="${item.sellerEmail}" /></td>
				<td class="center"><c:out value="${item.statusAuction}" /></td>

				<sec:authorize access="hasAnyRole('admin', 'moderator')">
					<td class="no-transfer"><fmt:formatDate pattern="dd/MM/yyyy"
							value="${item.created}" /></td>
					<td class="no-transfer"><fmt:formatDate pattern="dd/MM/yyyy"
							value="${item.updated}" /></td>
					<td><c:out value="${item.id}" /></td>
				</sec:authorize>


				<td class="center info-edit-delete"><a
					class="btn-floating color-icon" href="${pagesItem}/${item.id}"><i
						class="material-icons" title="Page view">pageview</i></a> <sec:authorize
						access="!isAnonymous()">
						<c:if test="${showSomeElements}">
							<a class="btn-floating color-icon"
								href="${pagesItem}/${item.id}/edit"><i
								class="material-icons" title="Edit">edit</i></a>
							<a class="btn-floating color-icon-delete"
								href="${pagesItem}/${item.id}/delete"><i
								class="material-icons" title="Delete">delete_forever</i></a>
						</c:if>
					</sec:authorize></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<jspFragments:paging />

<c:if test="${showSomeElements}">
	<a class="waves-effect waves-light btn right" href="${pagesItem}/add">Place
		a new Item</a>
</c:if>



