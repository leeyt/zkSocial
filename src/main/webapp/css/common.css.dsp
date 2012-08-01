<%@ taglib prefix="t" uri="http://www.zkoss.org/dsp/web/theme" %>

.z-page {
  font-family: 'Lucida Grande';
}

/* Customize Search Textbox */
.search {
	background-image: url('../images/icons/spyglass.png');
	background-repeat: no-repeat;
	background-position: 5px 2px;
	padding-left: 20px;
	line-height: 18px;
	
	${t:borderRadius('20px')}
}

.menuPanel {
	line-height: 18px;
	font-size: 16px;
}

/* Hide collapse icon */
.menuPanel .z-borderlayout-icon {
	display: none;
}

.menuPanel .z-west-header {
	line-height: 24px;
	
	border-top: 1px solid #5F6673;
	border-bottom: 1px solid #161B25;
	
	${t:gradient('ver', '#002B37; #004D63')}
}

.menuPanel .z-listitem {
	line-height: 24px;
}

.menuPanel .z-listcell-cnt {
	color: #C4CCDA;
	text-shadow: 0 1px 0 rgba(0, 0, 0, .6);
	vertical-align: middle;
}

.menuPanel .z-listhead.menu-category {
	text-shadow: 0 1px 0 rgba(255, 255, 255, .6);
	vertical-align: middle;
	
	${t:gradient('ver', '#434B5C; #394152')}
	${t:boxShadow('inset 0 1px 0 rgba(255, 255, 255, .08)')}
}

.menuPanel .z-listheader-cnt {
	color: #7A8292;
}

.menuPanel .z-listcell.menuitem,
.menuPanel .z-listcell.contact {
	margin-top: 0px;
	margin-bottom: 0px;

	background-color: #002B37;
	border-bottom: 1px solid #242A37;
	
	${t:boxShadow('inset 0 1px 0 rgba(255,255,255,.8)')}
}

.menuPanel    .z-listcell.menuitem .z-image.mi-icon,
.contentPanel .z-listcell.contact  .z-image.contact-picture {
	display: inline-block;
	margin-left: 2px;
}

.z-label.mi-title,
.z-label.contact-name {
	display: inline-block;
	width: 115px;
	padding: 3px 0 0px 8px;
	vertical-align: middle;
	text-overflow: ellipsis;
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
	${t:borderRadius('2px')}
	${t:boxShadow('0 1px 2px rgba(0, 0, 0, .4)')}
}

.contentPanel .z-center-header {
	vertical-align: center;
 	${t:gradient('ver', '#009EC2; #0079A7')}
}

.contentPanel .z-center-header .z-image:not(.button):active {
	-webkit-filter: invert(1);
}

.contentPanel .pageTitle {
	display: table-cell;
	width: 940px;
	text-align: center;
	vertical-align: middle;
	color: white; 
	font-size: 14px
	font-weight: bold;
	float: left;
}

.contentPanel .z-listcell.contact  .z-label.contact-status {
	display: inline-block;
	padding-right: 6px;
}

.newsfeedPanel .z-center-header {
    background: #EDEFF3;
	border-top: 1px solid #D0D1D7;
}

.newsfeedPanel .z-toolbarbutton-cnt {
	text-shadow: none;
}

.touchable {
  cursor: pointer;
}

.contact-picture,
.mi-icon {
	width: 24px;
	height: 24px;
}

.newsfeedPanel .z-html.post p {
	max-width: 1050px;
}

.like-area {
	margin-top: 10px;
	position: relative;
	padding: 2px 5px;
	background: #DBDDE0;
	border-radius: 2px;
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
  background-color: #00B8DF;
}

.button:active {
  background-color: #005F77;
}

.z-caption button,
.z-button-os {
	font-size: 14px;
	background: #008CB7;
	color: white;
	border: none;
	border-radius: 2px;
	box-shadow: inset rgba(120,120,120,0.6) 0 0 2px 10px;
	padding: 3px 10px;
	border: black ridge 1px;
}

.z-caption button:active {
	box-shadow: inset rgba(0,0,0,0.6) 0 0 2px 10px;
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
	background: #D8D8D8
	${t:gradient('ver', '#D8D8D8; #FFFFFF')}
}


