<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>테스트</title>
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<script type="text/JavaScript">
	// 웹뷰에서 네이티브 메소드를 호출하여 값을 받아옴
	function webViewToApp() {
		alert(WebViewCallbackInterface.webViewToApp(1, 2));
	}

	// 웹뷰에서 접근 허용되지 않은 네이티브 메소드를 호출
	function appToWebViewNative() {
		alert(WebViewCallbackInterface.appToWebViewNative());
	}

	// 네이티브에서 자바 스크립트를 호출
	function executeFunction(value) {
		alert(value);
		return '네이티브로 반환';
	}
	</script>
</head>
<body>
	<div id="text"></div>
	<hr/>
	<h2>JavaScript - Android WebView Callback Interface Sample</h2>
	<hr/>
	<input type="button" style="HEIGHT: 60pt" value="1 + 2 앱 호출하여 계산하기" onclick="webViewToApp()"/>
	<br>
	<br>
	<input type="button" style="HEIGHT: 60pt" value="접근불가 메소드 호출" onclick="appToWebViewNative()"/>
</body>
</html>