<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Message</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="item_id">item</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="theme">theme</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="text">content</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="user_from_id">from</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="user_whom_id">whom</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesMessage}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="message" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${message.id}" /></td>
                <td><c:out value="${message.itemName}" /></td>
                <td><c:out value="${message.theme}" /></td>
                <td><c:out value="${message.text}" /></td>
                <td><c:out value="${message.userAccountFromEmail}" /></td>
                <td><c:out value="${message.userAccountWhomEmail}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${message.created}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${message.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesMessage}/${message.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesMessage}/${message.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesMessage}/${message.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesMessage}/add"><i class="material-icons">add</i></a>
