<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header">Auction rule</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="index">index</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="theme">theme</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="text">text</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="auctionRule" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${auctionRule.id}" /></td>
                <td><c:out value="${auctionRule.index}" /></td>
                <td><c:out value="${auctionRule.theme}" /></td>
                <td><c:out value="${auctionRule.text}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${auctionRule.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${auctionRule.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesAuctionRule}/${auctionRule.id}"><i class="material-icons">info</i></a>
                
                <sec:authorize access="hasRole('admin')"> 
                <a class="btn-floating" href="${pagesAuctionRule}/${auctionRule.id}/edit"><i class="material-icons">edit</i></a> 
                <a class="btn-floating red" href="${pagesAuctionRule}/${auctionRule.id}/delete"><i class="material-icons">delete</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
   <a class="waves-effect waves-light btn right" href="${pagesAuctionRule}/add"><i class="material-icons">add</i></a>
</sec:authorize>