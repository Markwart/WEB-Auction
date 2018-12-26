<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Deferred bid</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesDeferredBid}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesDeferredBid}" column="item_id">item</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesDeferredBid}" column="price_bid">price</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesDeferredBid}" column="user_bid_id">user account bid</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesDeferredBid}" column="status_bid">status</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesDeferredBid}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesDeferredBid}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="deferredBid" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${deferredBid.id}" /></td>
                <td><c:out value="${deferredBid.itemName}" /></td>
                <td><c:out value="${deferredBid.priceBid}" /></td>
                <td><c:out value="${deferredBid.userBidEmail}" /></td>
                <td><c:out value="${deferredBid.statusBid}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${deferredBid.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${deferredBid.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesDeferredBid}/${deferredBid.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesDeferredBid}/${deferredBid.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesDeferredBid}/${deferredBid.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesDeferredBid}/add"><i class="material-icons">add</i></a>
