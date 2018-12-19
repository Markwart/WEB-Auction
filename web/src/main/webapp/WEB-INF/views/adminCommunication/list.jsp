<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Admin communication</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesAdminCommunication}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAdminCommunication}" column="theme">theme</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAdminCommunication}" column="text">content</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAdminCommunication}" column="user_from_id">user account</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAdminCommunication}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesAdminCommunication}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="adminCommunication" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${adminCommunication.id}" /></td>
                <td><c:out value="${adminCommunication.theme}" /></td>
                <td><c:out value="${adminCommunication.text}" /></td>
                <td><c:out value="${adminCommunication.userAccountEmail}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${adminCommunication.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${adminCommunication.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesAdminCommunication}/${adminCommunication.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesAdminCommunication}/${adminCommunication.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesAdminCommunication}/${adminCommunication.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesAdminCommunication}/add"><i class="material-icons">add</i></a>
