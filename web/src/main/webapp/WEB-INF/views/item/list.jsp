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

<!-- [A-Za-zА-Яа-яЁё_]{1,}[a-zA-Z-А-Яа-яЁё0-9\s]+$ -->

<div class="sorting">
	<div class="right">
		<a class="dropdown-trigger btn" data-target="dropdown4"><mytaglib:i18n key="sort"></mytaglib:i18n><i
			class="material-icons right">arrow_drop_down</i></a>
	</div>

	<!-- Dropdown Structure -->
	<ul id="dropdown4" class="dropdown-content sorting-dropdown">
		<li><mytaglib:sort-link pageUrl="${pagesItem}" column="name">
				<mytaglib:i18n key="table.column.name"></mytaglib:i18n>
			</mytaglib:sort-link></li>
		<li class="divider" tabindex="-1"></li>
		<li><mytaglib:sort-link pageUrl="${pagesItem}"
				column="starting_price">
				<mytaglib:i18n key="table.column.price"></mytaglib:i18n>
			</mytaglib:sort-link></li>
		<li class="divider" tabindex="-1"></li>
		<li><mytaglib:sort-link pageUrl="${pagesItem}"
				column="auction_end">
				<mytaglib:i18n key="table.column.ends"></mytaglib:i18n>
			</mytaglib:sort-link></li>
		<li class="divider" tabindex="-1"></li>
		<li><mytaglib:sort-link pageUrl="${pagesItem}" column="seller_id">
				<mytaglib:i18n key="table.column.seller"></mytaglib:i18n>
			</mytaglib:sort-link></li>
		<li class="divider" tabindex="-1"></li>
		<li><mytaglib:sort-link pageUrl="${pagesItem}" column="created"><mytaglib:i18n key="table.column.created-2"></mytaglib:i18n></mytaglib:sort-link></li>
	</ul>
</div>

<div class="container-items">
	<c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
		<div class="browse">
			<div class="browsetext-top">
				<div class="right">
					<span><b>Starting Price: US $${item.startingPrice}</b></span>
				</div>
			</div>
			<div class="browseimg">
				<a href="${pagesItem}/${item.id}"><img
					src="${filesUrl}/image?uuid=${item.image}"></a>
			</div>
			<div class="browsetext">
				<span><a href="${pagesItem}/${item.id}">${item.name}</a></span>
				<div class="bottom">
					<div class="left">
						<a class="watchlot" href="#!"><input type="button"
							class="button gray" value="Add to Watch List"></a>
					</div>
					<div class="right">
						<b>Current Price: US $${item.startingPrice}</b>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>


<div class="padding">
	<jspFragments:paging />
</div>

<c:if test="${showSomeElements}">
	<a class="waves-effect waves-light btn right" href="${pagesItem}/add">Place
		a new Item</a>
</c:if>



