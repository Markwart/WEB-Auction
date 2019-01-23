<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header">Auction duration</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionDuration}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionDuration}" column="day">duration (days)</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionDuration}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionDuration}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="auctionDuration" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${auctionDuration.id}" /></td>
                <td><c:out value="${auctionDuration.day}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${auctionDuration.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${auctionDuration.updated}" /></td>
                <td class="right"><a class="btn-floating color-icon" href="${pagesAuctionDuration}/${auctionDuration.id}"><i class="material-icons">pageview</i></a> 
                
                <sec:authorize access="hasRole('admin')">
                <a class="btn-floating color-icon" href="${pagesAuctionDuration}/${auctionDuration.id}/edit"><i class="material-icons">edit</i></a>
                <a class="btn-floating color-icon-delete" href="${pagesAuctionDuration}/${auctionDuration.id}/delete"><i class="material-icons">delete_forever</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
<a class="waves-effect waves-light btn right" href="${pagesAuctionDuration}/add"><i class="material-icons">add</i></a>
</sec:authorize>