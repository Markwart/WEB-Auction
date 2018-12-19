<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Bid</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="item_id">item</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="price_bid">price</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="user_bid_id">user account bid</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="status_bid">status</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="bid" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${bid.id}" /></td>
                <td><c:out value="${bid.itemName}" /></td>
                <td><c:out value="${bid.priceBid}" /></td>
                <td><c:out value="${bid.userAccountEmail}" /></td>
                <td><c:out value="${bid.statusBid}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${bid.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${bid.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesBid}/${bid.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesBid}/${bid.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesBid}/${bid.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesBid}/add"><i class="material-icons">add</i></a>
