<%@ page contentType="text/css;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://www.zkoss.org/dsp/web/core"  %>
<%@ taglib prefix="t" uri="http://www.zkoss.org/dsp/web/theme" %>

<c:include page="common.css.dsp" />

.z-page::before {
	content: '';
	width: 100%;
	height: 38px;
	
	${t:gradient('ver', '#02B0E5 0%; #007599 100%')}
	
	<c:if test="${zk.ie == 9}">
	background-image:url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIHZpZXdCb3g9IjAgMCAxIDEiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPgo8bGluZWFyR3JhZGllbnQgaWQ9Imc1NjIiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4MT0iMCUiIHkxPSIwJSIgeDI9IjAlIiB5Mj0iMTAwJSI+CjxzdG9wIHN0b3AtY29sb3I9IiMwMkIwRTUiIG9mZnNldD0iMCIvPjxzdG9wIHN0b3AtY29sb3I9IiMwMDc1OTkiIG9mZnNldD0iMSIvPgo8L2xpbmVhckdyYWRpZW50Pgo8cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMSIgaGVpZ2h0PSIxIiBmaWxsPSJ1cmwoI2c1NjIpIiAvPgo8L3N2Zz4=);
	filter: none;
	</c:if>
	
	<c:if test="${zk.ie == 8}">
	background: #007599;
	</c:if>
	
	position: absolute;
	top: 0px;
}

.z-image.icon {
	width: 24px;
	height: 24px;
}

.z-image.icon:hover {
	-webkit-filter: invert(100%);
}

.menuPanel .z-west-body {
	background: #112f37;
}

.menuPanel .z-west-caption {
	margin: 8px 5px;
}

.contentPanel .z-center-caption {
	margin: 6px 5px;
}

.z-west-header, .z-center-header, .z-east-header,
.like-area {
	height: 38px;
}

.z-window-embedded {
	margin: 0px auto;
	height: 100%;
}

.like-area > table {
	margin-top: 9px;
}
