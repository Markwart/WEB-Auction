<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header">Shipping method</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="name">name</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="delivery_time">delivery time</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="cost">cost</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="shippingMethod" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${shippingMethod.id}" /></td>
                <td><c:out value="${shippingMethod.name}" /></td>
                <td><c:out value="${shippingMethod.deliveryTime}" /></td>
                <td><c:out value="${shippingMethod.cost}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${shippingMethod.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${shippingMethod.updated}" /></td>
                <td class="right"><a class="btn-floating color-icon" href="${pagesShippingMethod}/${shippingMethod.id}"><i class="material-icons">pageview</i></a> 
                
                <sec:authorize access="hasRole('admin')">
                <a class="btn-floating color-icon" href="${pagesShippingMethod}/${shippingMethod.id}/edit"><i class="material-icons">edit</i></a> 
                <a class="btn-floating color-icon-delete" href="${pagesShippingMethod}/${shippingMethod.id}/delete"><i class="material-icons">delete_forever</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
<a class="waves-effect waves-light btn right" href="${pagesShippingMethod}/add"><i class="material-icons">add</i></a>
</sec:authorize>