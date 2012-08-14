<%@ page contentType="text/css;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://www.zkoss.org/dsp/web/core"  %>

<c:include page="common.css.dsp" />

/* iPad Landscape Orientation */
@media screen and (orientation: landscape) {
	.newsfeedPanel .z-html.post p {
		max-width: 720px;
	}
	
	.contentPanel .pageTitle {
		width: 550px;
	}
	
	.z-popup.feedback .z-html p {
		max-width: 250px;
		font-size: 14px;
	}

	.z-textbox.commentInput {
		width: 220px;
	}
}

/* iPad Portrait Orientation */
@media screen and (orientation: portrait) {
	.newsfeedPanel .z-html.post p {
		max-width: 650px;
	}
	
	.contentPanel .pageTitle {
		width: 300px;
	}
	
	.z-popup.feedback .z-html p {
		max-width: 180px;
		font-size: 14px;
	}

	.z-textbox.commentInput {
		width: 150px;
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
	color: ${test};
}

.newsfeedPanel .z-center-header,
.newsfeedPanel .z-toolbar {
	padding: 2px;
}
/**************************************/