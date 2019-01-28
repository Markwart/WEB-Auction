<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header"><mytaglib:i18n key="section.message"></mytaglib:i18n></h4>
<table class="bordered highlight">
    <tbody>
        <tr class="font-my-set">
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="item_id"><mytaglib:i18n key="table.column.item-name"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="theme"><mytaglib:i18n key="table.column.theme"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="user_from_id"><mytaglib:i18n key="table.column.from"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="created"><mytaglib:i18n key="table.column.created"></mytaglib:i18n></mytaglib:sort-link></th>
            
            <sec:authorize access="hasAnyRole('admin', 'moderator')">
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="updated"><mytaglib:i18n key="table.column.updated" /></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="user_whom_id"><mytaglib:i18n key="table.column.seller" /></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
            </sec:authorize>
            <th></th>
        </tr>
        <c:forEach var="message" items="${gridItems}" varStatus="loopCounter">
            <tr class="font-my-set">
                <td><c:out value="${message.itemName}" /></td>
                <td><c:out value="${message.theme}" /></td>
                <td><c:out value="${message.userFromEmail}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${message.created}" /></td>
                
                <sec:authorize access="hasAnyRole('admin', 'moderator')">
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${message.updated}" /></td>
                <td><c:out value="${message.userWhomEmail}" /></td>
                <td><c:out value="${message.id}" /></td>
                </sec:authorize>
                
                <td class="right"><a class="btn-floating color-icon" href="${pagesMessage}/${message.id}"><i class="material-icons">pageview</i></a> 
                
                <sec:authorize access="hasRole('admin')">
                <a class="btn-floating color-icon" href="${pagesMessage}/${message.id}/edit"><i class="material-icons">edit</i></a> 
                <a class="btn-floating color-icon-delete" href="${pagesMessage}/${message.id}/delete"><i class="material-icons">delete_forever</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
<a class="waves-effect waves-light btn right" href="${pagesMessage}/add"><i class="material-icons">add</i></a>
</sec:authorize>