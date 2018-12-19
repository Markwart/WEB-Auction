<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header offset-class">Item</h4>
<table class="bordered highlight offset-class">
    <tbody class="item-table">
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="name">name</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="year">year</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="image">image</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="starting_price">starting price</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="auction_end">auction end</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="text">description</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="category_id">category</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="condition_id">condition</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="composition_id">composition</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="country_origin">country origin</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="seller_id">seller</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="status_auction">auction status</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
            <tr class="font">
                <td><c:out value="${item.id}" /></td>
                <td><c:out value="${item.name}" /></td>
                <td><c:out value="${item.year}" /></td>
                <td><c:out value="${item.image}" /></td>
                <td><c:out value="${item.startingPrice}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.auctionEnd}" /></td>
                <td><c:out value="${item.text}" /></td>
                <td><c:out value="${item.categoryId}" /></td>
                <td><c:out value="${item.conditionId}" /></td>
                <td><c:out value="${item.compositionId}" /></td>
                <td><c:out value="${item.countryOriginId}" /></td>
                <td><c:out value="${item.sellerEmail}" /></td>
                <td><c:out value="${item.statusAuction}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.created}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesItem}/${item.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesItem}/${item.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesItem}/${item.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesItem}/add"><i class="material-icons">add</i></a>
