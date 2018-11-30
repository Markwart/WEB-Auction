<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Composition</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesComposition}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesComposition}" column="name">name</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesComposition}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesComposition}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="composition" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${composition.id}" /></td>
                <td><c:out value="${composition.name}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${composition.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${composition.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesComposition}/${composition.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesComposition}/${composition.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesComposition}/${composition.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesComposition}/add"><i class="material-icons">add</i></a>
