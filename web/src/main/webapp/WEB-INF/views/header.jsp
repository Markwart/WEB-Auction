<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
	
<header>
	<nav>
		<div class="nav-wrapper container">

			<!-- Dropdown Structure -->
			<sec:authorize access="hasAnyRole('admin', 'moderator')">
				<ul id="dropdown1" class="dropdown-content">
					<li><a href="${pagesCategory}"><mytaglib:i18n key="section.category"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesComposition}"><mytaglib:i18n key="section.composition"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesCondition}"><mytaglib:i18n key="section.condition"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesCountryOrigin}"><mytaglib:i18n key="section.country"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesPaymentMethod}"><mytaglib:i18n key="section.payment"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesShippingMethod}"><mytaglib:i18n key="section.shipping"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesStepBlock}"><mytaglib:i18n key="section.step-block"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesAuctionDuration}"><mytaglib:i18n key="section.duration"></mytaglib:i18n></a></li>
					<li class="divider"></li>
				</ul>
				<ul id="dropdown2" class="dropdown-content">
					<li><a href="${pagesUserAccount}"><mytaglib:i18n key="section.account"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesAdminCommunication}"><mytaglib:i18n key="section.admin-communication"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesMessage}"><mytaglib:i18n key="section.message"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesBid}"><mytaglib:i18n key="section.bid"></mytaglib:i18n></a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${pagesDeferredBid}"><mytaglib:i18n key="section.deferred-bid"></mytaglib:i18n></a></li>
					<li class="divider"></li>
				</ul>
			</sec:authorize>
			<nav>
				<div class="nav-wrapper">
					<a href="${contextPath}" class="brand-logo" title="Home page"><mytaglib:i18n key="main"></mytaglib:i18n></a>
					<ul class="right hide-on-med-and-down">
						<%-- <li><a href="${contextPath}">Home page</a></li> --%>
						<li><a href="${pagesAuctionRule}"><mytaglib:i18n key="section.rule"></mytaglib:i18n></a></li>
						<li><a href="${pagesFeedback}"><mytaglib:i18n key="section.feedback"></mytaglib:i18n></a></li>
						<li><a href="${pagesItem}"><mytaglib:i18n key="section.item"></mytaglib:i18n></a></li>
						<li><a href="#!"><mytaglib:i18n key="section.help"></mytaglib:i18n></a></li>

						<!-- Dropdown Trigger -->
						<sec:authorize access="hasAnyRole('admin', 'moderator')">
							<li><a class="dropdown-trigger" href="#!" data-target="dropdown1"><mytaglib:i18n key="section.work-set"></mytaglib:i18n><i
									class="material-icons right">arrow_drop_down</i></a></li>
							<li><a class="dropdown-trigger" href="#!" data-target="dropdown2"><mytaglib:i18n key="section.manage-users"></mytaglib:i18n><i
									class="material-icons right">arrow_drop_down</i></a></li>
						</sec:authorize>
					</ul>
				</div>
			</nav>
		</div>

		<div class="locale">
		
		    <%-- <ul class="language-select"> 
                <li class="btn active" data-lang="ru"><a href="${contextPath}?language=ru">RU</a></li> 
	            <li class="btn" data-lang="en"><a href="${contextPath}?language=en">EN</a></li>
            </ul> --%>
		
			 <ul>
				<li><a href="${contextPath}?language=ru">RU</a></li>
				<li><a href="${contextPath}?language=en">EN</a></li>
			</ul> 
		</div>

		<div class="current-user">
		    <sec:authorize access="!isAnonymous()">
			    <sec:authentication property="name" />
			</sec:authorize>

			<sec:authorize access="hasRole('admin')">
				<div class="right" title='<mytaglib:i18n key="admin"></mytaglib:i18n>'>
					<a class="dropdown-trigger btn private-menu" data-target="dropdown3"><i class="material-icons">supervisor_account</i></a>
				</div>
			</sec:authorize>

			<sec:authorize access="hasRole('moderator')">
				<div class="right" title='<mytaglib:i18n key="moderator"></mytaglib:i18n>'>
					<a class="dropdown-trigger btn private-menu" data-target="dropdown3"><i class="material-icons">supervisor_account</i></a>
				</div>
			</sec:authorize>

			<sec:authorize access="hasRole('user')">
				<div class="right" title='<mytaglib:i18n key="user"></mytaglib:i18n>'>
					<a class="dropdown-trigger btn private-menu" data-target="dropdown3"><i class="material-icons">perm_identity</i></a>
				</div>
			</sec:authorize>

			<sec:authorize access="isAnonymous()">
				<mytaglib:i18n key="guest"></mytaglib:i18n>&nbsp<a class="right" href="${contextPath}/login" title='<mytaglib:i18n key="login"></mytaglib:i18n>'><i
					class="material-icons">open_in_browser</i></a>
			</sec:authorize>
		</div>

	</nav>

	<!-- Dropdown Structure -->
	<ul id="dropdown3" class="dropdown-content private-menu-mod">
		<li><a href="${pagesItem}/private"><i class="material-icons">storage</i><mytaglib:i18n key="section.my-item"></mytaglib:i18n></a></li>
		<li class="divider" tabindex="-1"></li>
		<li><a href="#!"><i class="material-icons">library_books</i><mytaglib:i18n key="section.watch-list"></mytaglib:i18n></a></li>
		<li class="divider" tabindex="-1"></li>
		<li><a href="#!"><i class="material-icons">equalizer</i><mytaglib:i18n key="section.biddin"></mytaglib:i18n></a></li>
		<li class="divider" tabindex="-1"></li>
		<li><a href="${pagesMessage}/private"><i class="material-icons">mail_outline</i><mytaglib:i18n key="section.message"></mytaglib:i18n></a></li>
		<li class="divider" tabindex="-1"></li>
		<li><a href="${pagesFeedback}/private"><i class="material-icons">feedback</i><mytaglib:i18n key="section.feedback"></mytaglib:i18n></a></li>
		<li class="divider" tabindex="-1"></li>
		<li><a href="#!"><i class="material-icons">settings</i><mytaglib:i18n key="section.setting"></mytaglib:i18n></a></li>
		<li class="divider" tabindex="-1"></li>
		<li><a href="${contextPath}/execute_logout"><i class="material-icons">exit_to_app</i><mytaglib:i18n key="logout"></mytaglib:i18n></a></li>
	</ul>
	
	<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

	<script>
		$('.language-select').click(function() {
			$(this).toggleClass('open');
		})

		$('.language-select li')
				.click(
						function() {
							var setLang = $('.language-select')
									.data('location'), dataLangSelect = $(this)
									.data('lang')
							$('.language-select').data('location',
									dataLangSelect);
							$('.language-select li').removeClass('active');
							$(this).toggleClass('active');
						})
	</script> -->
	
</header>