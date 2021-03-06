<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pagesCondition" value="${contextPath}/condition" scope="request" />
<c:set var="pagesComposition" value="${contextPath}/composition" scope="request" />
<c:set var="pagesCategory" value="${contextPath}/category" scope="request" />
<c:set var="pagesCountryOrigin" value="${contextPath}/countryOrigin" scope="request" />
<c:set var="pagesPaymentMethod" value="${contextPath}/paymentMethod" scope="request" />
<c:set var="pagesShippingMethod" value="${contextPath}/shippingMethod" scope="request" />
<c:set var="pagesAuctionRule" value="${contextPath}/auctionRule" scope="request" />
<c:set var="pagesStepBlock" value="${contextPath}/stepBlock" scope="request" />
<c:set var="pagesAuctionDuration" value="${contextPath}/auctionDuration" scope="request" />
<c:set var="pagesBid" value="${contextPath}/bid" scope="request" />
<c:set var="pagesDeferredBid" value="${contextPath}/deferredBid" scope="request" />
<c:set var="pagesFeedback" value="${contextPath}/feedback" scope="request" />
<c:set var="pagesMessage" value="${contextPath}/message" scope="request" />
<c:set var="pagesAdminCommunication" value="${contextPath}/adminCommunication" scope="request" />
<c:set var="pagesUserAccount" value="${contextPath}/userAccount" scope="request" />
<c:set var="pagesItem" value="${contextPath}/item" scope="request" />
<c:set var="filesUrl" value="${contextPath}/file" scope="request" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><tiles:insertAttribute name="title" /></title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>

<link rel="stylesheet" href="${contextPath}/resources/css/custom.css">
<script src="${contextPath}/resources/js/init-materialize-forms.js"></script>
<script src="${contextPath}/resources/js/init-menu.js"></script>
</head>
<body>
    <tiles:insertAttribute name="header" />
    <main>
    <div class="container">
    
        <tiles:insertAttribute name="body" />
    </div>
    </main>
    <tiles:insertAttribute name="footer" />
</body>
</html>