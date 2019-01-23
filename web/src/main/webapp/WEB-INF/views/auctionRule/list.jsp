<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header"><mytaglib:i18n key="section.rule"></mytaglib:i18n></h4>
<table class="bordered highlight">
    <tbody>
        <tr class="font-my-set">
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="index"><mytaglib:i18n key="table.column.index"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="theme"><mytaglib:i18n key="table.column.theme"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="text"><mytaglib:i18n key="table.column.text"/></mytaglib:sort-link></th>
            
            <sec:authorize access="hasAnyRole('admin', 'moderator')">
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="id"><mytaglib:i18n key="table.column.id"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="created"><mytaglib:i18n key="table.column.created"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAuctionRule}" column="updated"><mytaglib:i18n key="table.column.updated"/></mytaglib:sort-link></th>
            </sec:authorize>
            <th></th>
        </tr>
        <c:forEach var="auctionRule" items="${gridItems}" varStatus="loopCounter">
            <tr class="font-my-set">
                <td><c:out value="${auctionRule.index}" /></td>
                <td><c:out value="${auctionRule.theme}" /></td>
                <td><c:out value="${auctionRule.text}" /></td>
                
                <sec:authorize access="hasAnyRole('admin', 'moderator')">
                <td><c:out value="${auctionRule.id}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${auctionRule.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${auctionRule.updated}" /></td>
                </sec:authorize>
                
                <td class="right"><a class="btn-floating color-icon" href="${pagesAuctionRule}/${auctionRule.id}"><i class="material-icons">pageview</i></a>
                
                <sec:authorize access="hasRole('admin')">  
                <a class="btn-floating color-icon" href="${pagesAuctionRule}/${auctionRule.id}/edit"><i class="material-icons">edit</i></a> 
                <a class="btn-floating color-icon-delete" href="${pagesAuctionRule}/${auctionRule.id}/delete"><i class="material-icons">delete_forever</i></a>
                </sec:authorize> </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
   <a class="waves-effect waves-light btn right" href="${pagesAuctionRule}/add"><i class="material-icons">add</i></a>
</sec:authorize>