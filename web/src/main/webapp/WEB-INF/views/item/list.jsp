<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h4 class="header offset-class">Items</h4>

 <div class="row">
    <div class="col s12 m10">
        <div class="card-panel blue lighten-5">
            <div class="row">
                <form:form method="POST" action="${pagesItem}" modelAttribute="searchFormItem">
                    <div class="input-field col s4">
                        <form:input path="name" type="text" />
                        <label for="name">name</label>
                    </div>
                    <div class="col s4">
                        <button class="btn waves-effect waves-light right" type="submit">search</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div> 

<table class="bordered highlight offset-class">
    <tbody class="item-table">
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="name">name</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="year">year</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="image">image</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="starting_price">starting price</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="auction_end">auction end</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="text">description</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="category_id">category</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="condition_id">condition</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="composition_id">composition</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="country_origin_id">country origin</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="seller_id">seller</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="status_auction">auction status</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesItem}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
            <tr class="font">
                <td><c:out value="${item.id}" /></td>
                <td><c:out value="${item.name}" /></td>
                <td><c:out value="${item.year}" /></td>
                <td><c:out value="${item.image}" /></td>
                <td><c:out value="${item.startingPrice}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.auctionEnd}" /></td>
                <td><c:out value="${item.text}" /></td>
                <td><c:out value="${item.categoryName}" /></td>
                <td><c:out value="${item.conditionName}" /></td>
                <td><c:out value="${item.compositionName}" /></td>
                <td><c:out value="${item.countryOriginName}" /></td>
                <td><c:out value="${item.sellerEmail}" /></td>
                <td><c:out value="${item.statusAuction}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.created}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesItem}/${item.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesItem}/${item.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesItem}/${item.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesItem}/add"><i class="material-icons">add</i></a>
