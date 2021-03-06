<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true" /></title>
	<link href="./css/bootstrap.css" rel="stylesheet">
	<link href="./css/bootstrap-responsive.css" rel="stylesheet">
	
	<script type="text/javascript" src="./js/jquery.js"></script>  
	<script type="text/javascript" src="./js/bootstrap.js"></script>  
    <script type="text/javascript" src="./js/bootstrap-collapse.js"></script>  
	<script type="text/javascript" src="./js/bootstrap-carousel.js"></script>
	<script type="text/javascript" src="./js/bootstrap-dropdown.js"></script> 

</head>
<body>

<script src="./js/bootstrap-tooltip.js"></script>  
<script src="./js/bootstrap-popover.js"></script>
<script src="./js/jquery.js"></script>  
<script src="./js/jquery.min.js"></script>    

    <table border="0" cellpadding="1" cellspacing="1" align="center">
        <tr>
            <td height="30"><tiles:insertAttribute name="header" /></td>
        </tr>
        <tr>
            <td height="15"><tiles:insertAttribute name="menu" /></td>
       </tr>
       <tr>
            <td width="350"><tiles:insertAttribute name="info" /></td>
        </tr>
       <tr>
            <td width="350"><tiles:insertAttribute name="search" /></td>
        </tr>
       <tr>
            <td width="350"><tiles:insertAttribute name="result" /></td>
        </tr>
        <tr>
            <td height="30" ><tiles:insertAttribute name="footer" /></td>
        </tr>
    </table>
</body>
</html>
