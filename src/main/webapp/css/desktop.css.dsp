<%@ page contentType="text/css;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://www.zkoss.org/dsp/web/core"  %>
<%@ taglib prefix="t" uri="http://www.zkoss.org/dsp/web/theme" %>

<c:include page="common.css.dsp" />

.z-page {
}

.z-page::before {
	content: '';
	width: 100%;
	border-bottom: solid 38px #018DB7;
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

.menuPanel .z-west-header {
	background: #018DB7;
}

.menuPanel .z-west-body {
	background: #112f37;
}

.menuPanel .z-west-capcnt {
	margin: 8px 5px;
}

.contentPanel > .z-center-header {
	background: #018DB7;
}

.contentPanel .z-center-capcnt {
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
