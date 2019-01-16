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

<div class="row">
	<nav class="search-from-db">
		<div class="nav-wrapper">
			<form:form method="POST" action="${pagesItem}"
				modelAttribute="searchFormModel">
				<div class="input-field">
					<form:input path="name" type="search" />
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

<table class="bordered highlight offset-class">
	<tbody class="item-table">
		<tr class="font-my-set">
			<th><mytaglib:sort-link pageUrl="${pagesItem}" column="image"><mytaglib:i18n key="table.column.image"></mytaglib:i18n></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesItem}" column="name">
					<mytaglib:i18n key="table.column.name"></mytaglib:i18n>
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesItem}"
					column="starting_price">
					<mytaglib:i18n key="table.column.price"></mytaglib:i18n>
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesItem}"
					column="auction_end">
					<mytaglib:i18n key="table.column.ends"></mytaglib:i18n>
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesItem}"
					column="seller_id">
					<mytaglib:i18n key="table.column.seller"></mytaglib:i18n>
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesItem}"
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
			    <td class="image-column-set"><img src="${filesUrl}/image?uuid=${item.image}"/> </td>
				<td><c:out value="${item.name}" /></td>
				
				<td><c:out value="${item.startingPrice}" /></td>
				
				<td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss a" value="${item.auctionEnd}" /></td>
				<td><c:out value="${item.sellerEmail}" /></td>
				<td><c:out value="${item.statusAuction}" /></td>

				<sec:authorize access="hasAnyRole('admin', 'moderator')">
					<td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.created}" /></td>
					<td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.updated}" /></td>
					<td><c:out value="${item.id}" /></td>
				</sec:authorize>

				<td class="center info-edit-delete">
				<a class="btn-floating" href="${pagesItem}/${item.id}"><i class="material-icons">info</i></a>
				
				<sec:authorize access="!isAnonymous()">
				<a class="btn-floating" href="${pagesItem}/${item.id}/edit"><i class="material-icons">edit</i></a>
				<a class="btn-floating red" href="${pagesItem}/${item.id}/delete"><i class="material-icons">delete</i></a>
				</sec:authorize></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<jspFragments:paging />

<c:if test="${showAddButton}">
<a class="waves-effect waves-light btn right" href="${pagesItem}/add"><i class="material-icons">add</i></a>
</c:if>





