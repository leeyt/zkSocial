<%@ page contentType="text/css;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://www.zkoss.org/dsp/web/core"  %>

<c:include page="common.css.dsp" />

.z-image.icon {
	width: 32px;
	height: 32px;
}

.touchable {
  	cursor: pointer;
}

.z-notification-info .z-notification-cnt {
	padding: 17px;
}

.z-west-header, .z-center-header, .z-east-header
.like-area {
	height: 48px;
}

.menuPanel .z-west-body {
	background: #112f37;
}

.menuPanel .z-west-capcnt {
	margin: 10px 10px;
}

.contentPanel .z-center-capcnt {
	margin: 10px 10px;
}

.like-area tr {
	vertical-align: middle;
}

.comment .z-center-header {
	height: 40px;	
}

.comment .z-center-body {
	background-color: #ffffff;
}

/* iPad Landscape Orientation */
@media screen and (orientation: landscape) {
}

/* iPad Portrait Orientation */
@media screen and (orientation: portrait) {
}

@media screen and (max-width: 480px) {
	.z-notification .z-notification-cl,
	.z-notification .z-notification-cnt {
		width: 100px;
		height: 50px;
	}
	
	.z-popup.feedback .z-html p {
		font-size: 10px;
	}
}

@media screen and (max-width: 320px) {
	.menuPanel .z-borderlayout-icon {
		display: inline;
	}
	
	.menuPanel .z-west-header .z-button {
		display: none;
	}
}

/**************************************/
/* Override default values for Tablet */
.z-caption .z-toolbar a, 
.z-caption .z-toolbar a:visited,
.z-caption .z-toolbar a:hover {
	color: black;
}

.z-button:active,
.z-button-os:active {
	background: #003744;
}

.z-button:focus,
.z-button:hover,
.z-button-os:focus,
.z-button-os:hover {
	background: #008CB7;
}

.newsfeedPanel .z-center-header,
.newsfeedPanel .z-toolbar {
	padding: 2px;
}
/**************************************/