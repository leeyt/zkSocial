<%@ taglib uri="http://www.zkoss.org/dsp/web/theme" prefix="t" %>

/* Customize Search Textbox */
.z-textbox.search {
	background-image: url('../images/icons/spyglass.png');
	background-repeat: no-repeat;
	background-position: 5px 0px;
	padding-left: 20px;
	
	${t:borderRadius('20px')}
}

.menuPanel {
	color: #C4CCDA;
	text-shadow: 0 1px 0 rgba(0, 0, 0, .6);
}

/* Hide collapse icon */
.menuPanel .z-borderlayout-icon {
	display: none;
}

.menuPanel .z-west-header {
	line-height: 32px;
	
	font-size: 14px;
	border-top: 1px solid #5F6673;
	border-bottom: 1px solid #161B25;
	
	${t:gradient('ver', '#002B37; #004D63')}
}

.menuPanel .z-label.function-category {
	display: block;
	width: 100%;
	border-top: none;
	border-bottom: 1px solid #242A37;
	color: #7A8292;
	padding-left: 10px;
	text-shadow: 0 1px 0 rgba(0, 0, 0, .6);
	line-height: 18px;
	vertical-align: middle;
	
	${t:gradient('ver', '#434B5C; #394152')}
	${t:boxShadow('inset 0 1px 0 rgba(255, 255, 255, .08)')}
}

.menuPanel .z-hlayout.function-item {
	margin-top: 0px;
	margin-bottom: 0px;

	background-color: #31394A;
	border-bottom: 1px solid #242A37;
	
	${t:gradient('ver', '#002B37; #004D63')}
	${t:boxShadow('inset 0 1px 0 rgba(255,255,255,.8)')}
}

.menuPanel .z-hlayout.function-item .z-image {
	display: table-cell;
	margin-left: 2px;
}

/*
.menuPanel .z-hlayout.function-item 
*/

.z-label.title {
	display: table-cell;
	height: 37px;
	width: 120px;
	padding: 3px 0 3px 8px;
	vertical-align: middle;
}

.menuPanel .z-hlayout.function-item .z-label.count {
	border-top: 1px solid #505A63;
	color: #BFC5D2;
	padding: 1px 3px 3px 4px;
	min-width: 11px;
	text-align: center;
	
	${t:gradient('ver', '#3E485E; #323A4D')}
	${t:borderRadius('2px')}
	${t:boxShadow('0 1px 2px rgba(0, 0, 0, .4)')}
}

.contentPanel .z-center-header {
	vertical-align: center;
 	${t:gradient('ver', '#009EC2; #0079A7')}
}

.contentPanel .pageTitle {
	text-align: center;
	vertical-align: middle;
	color: white; 
	font-weight: bold;
	float: left;
}

.newsfeedPanel .z-center-header {
    background: #EDEFF3;
	border-top: 1px solid #D0D1D7;
}

.z-caption .z-toolbar a, 
.z-caption .z-toolbar a:visited, 
.z-caption .z-toolbar a:hover {
	color: black;
}

.newsfeedPanel .z-toolbarbutton-cnt {
	text-shadow: none;
}

.touchable {
  cursor: pointer;
}

/* PC and Notebook */
@media screen and (min-width: 1366px) {
	.like-area {
		width: 1200px;
	}
	.menuPanel .z-hlayout.function-item .z-image {
		width: 32px;
		height: 32px;
	}
	.contentPanel .pageTitle {
		width: 956px;
	}
}


/* iPad Landscape Orientation */
@media screen and (max-width: 1024px) {
	.newsfeedPanel .z-html.post p {
		max-width: 720px;
	}
	.like-area {
		width: 648px;
	}
	.menuPanel .z-hlayout.function-item .z-image {
		width: 24px;
		height: 24px;
	}
	.contentPanel .pageTitle {
		width: 716px;
	}
	
}

/* iPad Portrait Orientation */
@media screen and (max-width: 768px) {
	.newsfeedPanel .z-html.post p {
		max-width: 450px;
	}
	.like-area {
		width: 405px;
	}
	.menuPanel .z-hlayout.function-item .z-image {
		width: 24px;
		height: 24px;
	}
	.contentPanel .pageTitle {
		width: 537px;
	}
	
}

.like-area {
	margin-top: 5px;
	position: relative;
	padding: 1px 2px;
	background: #CECECE;
	border-radius: 5px;
}

.like-area::before {
	content: '';
	position: absolute;
	width: 0px;
	height: 0px;
	top: -10px;
	left: 20px;
	border-top: 5px solid transparent;
	border-left: 5px solid transparent;
	border-right: 5px solid transparent;
	border-bottom: 5px solid #CECECE;
}


.button { 
  font-size: 12px;
  color: white;
  text-shadow: 0 -1px 0 rgba(0, 0, 0, .35);
  border: 1px black solid;
  border-radius: 2px;
  padding: 2px;
  background-color: #00B8DF;
}

.button:active {
  background-color: #005F77;
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
	filter: alpha(opacity=60);
	opacity: .6;
	zoom: 1;
	background: #E0E1E3;
	z-index: 1800;
	cursor: pointer;
}

.z-popup { 
	width: 210px;
	${t:boxShadow('0 0 10px #ACACAC')}
	${t:borderRadius('8px')}
} 
.z-popup > div {
	${t:borderRadius('8px')}
} 





























<!--
div.z-column-cnt, 
div.z-row-cnt {
	font-size: 15px; 
}
.z-label { 
	font-size: 13px; 
}

.z-hlayout-inner { 
	vertical-align: middle; 
}

.collapse_button { 
	width: 28px; 
	height: 25px; 
	padding: 2px; 
	border: 1px solid #ccc;
	border-radius: 5px; 
} 
.thread_bold {
	font-size: 14px; 
	font-weight: bold; 
}
.thread_title { 
	font-size: 15px;
	font-weight: bold; 
} 
.content_header {
	margin: 5px; 
} 
.content_author { 
	float: left; 
	font-size: 15px; 
	color: #093; 
}

.product_btn { 
	float: left;
	width: 130px; 
	height: 32px; 
	margin-left: 50px; 
} 
.product_btn > img { 
	width: 20px; 
	height: 20px; 
} 
.product_title {
	font-size: 14px; 
	font-weight: bold; 
}
.autoSkip { 
	display: block; 
	overflow: hidden; 
	text-overflow: ellipsis;
	white-space: nowrap; 
} 
.disable {
	opacity: 0.3; 
}


-->
