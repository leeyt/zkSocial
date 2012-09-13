<%@ taglib prefix="c" uri="http://www.zkoss.org/dsp/web/core" %>
<%@ taglib prefix="t" uri="http://www.zkoss.org/dsp/web/theme" %>

/* ----------------------------------------------------------------------- */
/* Entire Page                                                             */
/* ----------------------------------------------------------------------- */
body {
	padding-left: 0;
	padding-right: 0;
}

.z-page {
  font-family: 'Helvetica';
  background: #F8F8F8;
}

.z-west, .z-center {
	border: none;
}

.z-borderlayout {
	background: none;
}

.z-image.icon {
	margin-left: 15px;
	margin-right: 15px;
}

div.z-listbox,
div.z-listbox-header th.z-listheader,
td.z-listcell {
	border: none;
}

/* ----------------------------------------------------------------------- */
/* Customize Notification                                                  */
/* ----------------------------------------------------------------------- */
.z-notification-info {
	text-align: center;
	vertical-align: middle;
}

.z-notification-info .z-notification-cl {
	background: #ededed;
	color: black;
}

.z-notification-info .z-notification-cnt {
	position: relative;
	left: -15px;
	text-align: center;
}

.z-notification-info .z-notification-pointer-l {
	border-right-color: #ededed;
}

.z-notification-info .z-notification-pointer-r {
	border-left-color: #ededed;
}

.z-notification-info .z-notification-pointer-u {
	border-bottom-color: #ededed;
}

.z-notification-info .z-notification-pointer-d {
	border-top-color: #ededed;
}

/* ----------------------------------------------------------------------- */
/* Menu Panel Sidebar                                                      */
/* ----------------------------------------------------------------------- */
.menuPanel {
	line-height: 18px;
	font-size: 16px;
}

/* Hide collapse icon */
.menuPanel .z-borderlayout-icon {
	display: none;
}

.menuPanel .z-west-header {
	padding: 0px;
	border-bottom: none;
	
	line-height: 24px;
	
	${t:gradient('ver', '#02B0E5; #007599')}
	
	<c:if test="${zk.ie == 9}">
	background-image:url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIHZpZXdCb3g9IjAgMCAxIDEiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPgo8bGluZWFyR3JhZGllbnQgaWQ9Imc1NjIiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4MT0iMCUiIHkxPSIwJSIgeDI9IjAlIiB5Mj0iMTAwJSI+CjxzdG9wIHN0b3AtY29sb3I9IiMwMkIwRTUiIG9mZnNldD0iMCIvPjxzdG9wIHN0b3AtY29sb3I9IiMwMDc1OTkiIG9mZnNldD0iMSIvPgo8L2xpbmVhckdyYWRpZW50Pgo8cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMSIgaGVpZ2h0PSIxIiBmaWxsPSJ1cmwoI2c1NjIpIiAvPgo8L3N2Zz4=);
	filter: none;
	</c:if>
	
	<c:if test="${zk.ie == 8}">
	background: #007599;
	</c:if>
}

.menuPanel .z-west-body {
	background: #002B37;
}

/* Left-align back-button */
.menuPanel .z-caption td {
	text-align: left;
}

.menuPanel .z-listitem {
	line-height: 24px;
}

.menuPanel .z-listheader-cnt {
	color: #7A8292;
}

.menuPanel .z-listcell-cnt {
	color: #C4CCDA;
	text-shadow: 0 1px 0 rgba(0, 0, 0, .6);
	vertical-align: middle;
}

.menuPanel .z-listhead.menu-category {
	text-shadow: 1px 1px 1px black;
	vertical-align: middle;
	height: 38px;
	
	${t:gradient('ver', '#434B5C; #394152')}

	<c:if test="${zk.ie == 9}">
	background-image:url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIHZpZXdCb3g9IjAgMCAxIDEiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPgo8bGluZWFyR3JhZGllbnQgaWQ9Imc4ODQiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4MT0iMCUiIHkxPSIwJSIgeDI9IjAlIiB5Mj0iMTAwJSI+CjxzdG9wIHN0b3AtY29sb3I9IiM0MzRCNUMiIG9mZnNldD0iMCIvPjxzdG9wIHN0b3AtY29sb3I9IiMzOTQxNTIiIG9mZnNldD0iMSIvPgo8L2xpbmVhckdyYWRpZW50Pgo8cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMSIgaGVpZ2h0PSIxIiBmaWxsPSJ1cmwoI2c4ODQpIiAvPgo8L3N2Zz4=);
	filter: none;
	</c:if>

	<c:if test="${zk.ie == 8}">
	background: #434B5C;
	</c:if>
	
	${t:boxShadow('inset 0 1px 0 rgba(0, 0, 0, .08)')}
}


.menuPanel .menuitem,
.menuPanel .contact {
	margin-top: 0px;
	margin-bottom: 0px;

	background-color: #002B37;
	border-bottom: 1px solid #242A37;
	
	${t:boxShadow('inset 0 1px 0 rgba(0,0,0,.8)')}
}

.menuPanel    .z-listcell.menuitem .z-image.mi-icon,
.contentPanel .z-listcell.contact  .z-image.contact-picture {
	display: inline-block;
	margin-left: 2px;
}

.z-label.mi-title,
.z-label.contact-name {
	display: inline-block;
	padding: 3px 0 0px 8px;
	vertical-align: middle;
	text-overflow: ellipsis;
}

.z-label.mi-title {
	width: 240px;
}

.z-label.contact-name {
	width: 170px;
}

.menuPanel .z-label {
	font-weight: bold;
}

.menuPanel .menuitem .mi-count {
	border-top: 1px solid #505A63;
	color: #BFC5D2;
	padding: 1px 3px 3px 4px;
	min-width: 11px;
	text-align: center;
	
	${t:gradient('ver', '#3E485E; #323A4D')}
	
	<c:if test="${zk.ie == 9}">
	background-image:url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIHZpZXdCb3g9IjAgMCAxIDEiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPgo8bGluZWFyR3JhZGllbnQgaWQ9ImcxNzgiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4MT0iMCUiIHkxPSIwJSIgeDI9IjAlIiB5Mj0iMTAwJSI+CjxzdG9wIHN0b3AtY29sb3I9IiMzRTQ4NUUiIG9mZnNldD0iMCIvPjxzdG9wIHN0b3AtY29sb3I9IiMzMjNBNEQiIG9mZnNldD0iMSIvPgo8L2xpbmVhckdyYWRpZW50Pgo8cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMSIgaGVpZ2h0PSIxIiBmaWxsPSJ1cmwoI2cxNzgpIiAvPgo8L3N2Zz4=);
	filter: none;
	</c:if>
	
	<c:if test="${zk.ie == 8}">
	background: #3E485E;
	</c:if>
	
	${t:borderRadius('2px')}
	${t:boxShadow('0 1px 2px rgba(0, 0, 0, .4)')}
}

.contentPanel {
	background: #C4CDE0;
}

.contentPanel .z-center-header {
	padding: 0;
	border-bottom: none;
	
 	${t:gradient('ver', '#02B0E5; #007599')}
 	
 	<c:if test="${zk.ie == 9}">
 	background-image:url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIHZpZXdCb3g9IjAgMCAxIDEiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPgo8bGluZWFyR3JhZGllbnQgaWQ9Imc4OTkiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4MT0iMCUiIHkxPSIwJSIgeDI9IjAlIiB5Mj0iMTAwJSI+CjxzdG9wIHN0b3AtY29sb3I9IiMwMDlFQzIiIG9mZnNldD0iMCIvPjxzdG9wIHN0b3AtY29sb3I9IiMwMDc5QTciIG9mZnNldD0iMSIvPgo8L2xpbmVhckdyYWRpZW50Pgo8cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMSIgaGVpZ2h0PSIxIiBmaWxsPSJ1cmwoI2c4OTkpIiAvPgo8L3N2Zz4=);
 	filter: none;
 	</c:if>
 	
 	<c:if test="${zk.ie == 8}">
 	background: #009EC2;
 	</c:if>
}

.contentPanel .z-center-header .z-image:active {
	-webkit-filter: invert(1);
	-moz-filter: invert(1);
	filter: invert(1);
}

.contentPanel .z-center-header button.z-button-os {
	border: none;
}

.contentPanel .z-listcell.contact  .z-label.contact-status {
	display: inline-block;
	padding-right: 6px;
}

.newsfeedPanel .z-center-header {
    background: #E1E1E1;
}

.newsfeedPanel .z-caption-l {
	display: none;
}

.newsfeedPanel .toolbar table {
	width: 100%;
}

.newsfeedPanel .z-center-body {
	background: #F8F8F8;
}

.newsfeedPanel .z-toolbarbutton-cnt {
	text-shadow: none;
}

.post {
	background: #ffffff;
	border-radius: 5px;
	margin: 20px 20px 0px 20px;
}

.newsfeedPanel .z-vlayout-inner:last-child .post {
	margin-bottom: 20px;
}

.z-image.post-avatar {
	width: 34px;
	height: 34px;
}

.z-label.post-author {
	color: #576B95;
	font-weight: bold;
	line-height: 17px;
	font-size: 13px;
}

.z-label.post-time {
	color: #BBB;
	font-size: 12px;
	line-height: 15px;
}

.z-html.post-article {
	display: inline;
	font-size: 14px;
	width: 100%;
}

.post .z-html.post-article iframe {
	width: 250px;
	height: 200px;
}

.post .z-html.post-article img {
	width: 80%;
}

.feedback .z-center-body {
	background: #FFFFFF;
}

.feedback .z-button-os {
	background: blue;
}

.comment .z-html.post-article {
	display: inline-block;
	font-size: 12px;
	width: 85%;
}

.comment .z-html.post-article iframe {
	width: 250px;
	height: 200px;
}

.comment .z-html.post-article img {
	width: 80%;
}

.contact-picture,
.mi-icon {
	width: 24px;
	height: 24px;
}

.like-area {
	margin-top: 10px;
	position: relative;
	padding: 2px 5px;
	background: #DBDDE0;
	border-radius: 2px;
	vertical-align: middle;
}

.like-area::before {
	content: '';
	position: absolute;
	width: 0px;
	height: 0px;
	top: -13px;
	left: 20px;
	border-top: 7px solid transparent;
	border-left: 7px solid transparent;
	border-right: 7px solid transparent;
	border-bottom: 7px solid #DBDDE0;
}

.button { 
  font-size: 12px;
  color: white;
  text-shadow: 0 -1px 0 rgba(0, 0, 0, .35);
  border: 1px black solid;
  border-radius: 2px;
  padding: 2px;
  background-color: transparent;
}

.button:active {
  background-color: #005F77;
}

.z-caption button,

.z-button-os {
	font-size: 14px;
	background: transparent;
	color: white;
	border: none;
	border-radius: 2px;
	padding: 1px 10px;
	border: black ridge 1px;
}

.z-south button {
	color: white;
	background: #018FB9;
}

.z-caption button:active {
	background: #003744;
}

.left { 
	float: left;
}

.right { 
	float: right; 
} 

.textCenter {
	text-align: center; 
}

.modal {
	width: 100%;
	height: 100%;
	position: absolute;	
	top: 0px;
	left: 0;
	filter: alpha(opacity=70);
	opacity: .7;
	zoom: 1;
	background: #E0E1E3;
	z-index: 1800;
	cursor: pointer;
}

.z-popup.feedback { 
	width: 210px;
	
	${t:boxShadow('0 0 10px #ACACAC')}
	${t:borderRadius('8px')}
}

.z-popup.feedback .z-popup-cnt {
	padding: 0px;
}

.z-popup.feedback .z-center-header {
	background: #008AB4;
}

.z-popup.feedback .z-center-body {
	${t:gradient('ver', '#D8D8D8; #FFFFFF')}
	
	<c:if test="${zk.ie == 9}">
	background-image:url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIHZpZXdCb3g9IjAgMCAxIDEiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPgo8bGluZWFyR3JhZGllbnQgaWQ9Imc1IiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgo8c3RvcCBzdG9wLWNvbG9yPSIjRDhEOEQ4IiBvZmZzZXQ9IjAiLz48c3RvcCBzdG9wLWNvbG9yPSIjRkZGRkZGIiBvZmZzZXQ9IjEiLz4KPC9saW5lYXJHcmFkaWVudD4KPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNnNSkiIC8+Cjwvc3ZnPg==);
	filter: none;
	</c:if>
	
	<c:if test="${zk.ie == 8}">
	background: #D8D8D8;
	</c:if>
}

.z-textbox.commentInput {
	width: 180px;
}

.post {
	padding: 10px 10px;
}

.comment .z-center-body > .z-vlayout {
	padding: 5px 5px;
}

.z-image.post-avatar {
	margin-right: 10px;
	width: 50px;
	height: 50px;
}

.valign-middle {
	vertical-align: center;
}