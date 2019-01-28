<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header">
<c:choose>
	<c:when test="${privateList}"><mytaglib:i18n key="section.my-bid"></mytaglib:i18n></c:when>
	<c:otherwise><mytaglib:i18n key="section.bid"></mytaglib:i18n></c:otherwise>
</c:choose>
</h4>

<table class="bordered highlight">
    <tbody>
        <tr>
         <sec:authorize access="hasRole('user')">
            <th><mytaglib:i18n key="table.column.item-name"></mytaglib:i18n></th>
            <th><mytaglib:i18n key="table.column.bid"></mytaglib:i18n></th>
            <th><mytaglib:i18n key="table.column.created"></mytaglib:i18n></th>
         </sec:authorize>
         
            <sec:authorize access="hasAnyRole('admin', 'moderator')">
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="item_id"><mytaglib:i18n key="table.column.item"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="price_bid"><mytaglib:i18n key="table.column.bid"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="created"><mytaglib:i18n key="table.column.created"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="updated"><mytaglib:i18n key="table.column.updated"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="user_bid_id"><mytaglib:i18n key="table.column.user-account"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="status_bid"><mytaglib:i18n key="table.column.status"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesBid}" column="id"><mytaglib:i18n key="table.column.id"></mytaglib:i18n></mytaglib:sort-link></th>
            </sec:authorize>
            <th></th>
        </tr>
        <c:forEach var="bid" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><a href="${pagesItem}/${bid.itemId}">${bid.itemName}</a></td>
                <td>US $<fmt:formatNumber value="${bid.priceBid}" minFractionDigits="2" maxFractionDigits="2" /></td>
                <td><fmt:formatDate pattern="dd-MMM-yyyy HH:mm" value="${bid.created}" /></td>
                
                <sec:authorize access="hasAnyRole('admin', 'moderator')">
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${bid.updated}" /></td>
                <td><c:out value="${bid.userBidEmail}" /></td>
                <td><c:out value="${bid.statusBid}" /></td>
                <td><c:out value="${bid.id}" /></td>
                </sec:authorize>
                
                <td class="right"><a class="btn-floating color-icon" href="${pagesBid}/${bid.id}"><i class="material-icons">pageview</i></a> 
                
                <sec:authorize access="hasRole('admin')">
                <a class="btn-floating color-icon" href="${pagesBid}/${bid.id}/edit"><i class="material-icons">edit</i></a>
                <a class="btn-floating color-icon-delete" href="${pagesBid}/${bid.id}/delete"><i class="material-icons">delete_forever</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
<a class="waves-effect waves-light btn right" href="${pagesBid}/add"><i class="material-icons">add</i></a>
</sec:authorize>