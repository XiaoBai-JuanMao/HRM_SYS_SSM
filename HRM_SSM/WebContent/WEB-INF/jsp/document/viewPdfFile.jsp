<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script src="${ctx }/js/jquery.media.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function () {
		$('#handout_wrap_inner').media({
 	        width: '100%',
 	        autoplay: true,
 	        src:"${ctx }"+"${filePath }",
	    });
	})
</script>
<head>
<meta charset="UTF-8">
<title>预览页面</title>
</head>
<body>
	<div id="handout_wrap_inner"></div>
</body>
</html>