<%@ page contentType="text/html;charset=windows-1250"
	isELIgnored="false" pageEncoding="Windows-1250"%>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1250" />
<meta http-equiv="Content-Language" content="pl" />
<title>Rest client diagnostic page</title>


<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
 
<style>
.naglowek_strony {
	font-size: 20px;
	color: #5D5D5D;
	font-weight: bold;
	padding-left: 100px;
}

.naglowek_danych {
	padding-top: 20px;
	font-size: 15px;
	color: #5D5D5D;
	padding-left: 150px;
	padding-top: 40px;
}

.tresc_podsumowania {
	padding-top: 0px;
	font-size: 12px;
	color: #5D5D5D;
}
</style>

</head>


</head>
<body style="font-family: Trebuchet MS, Comic Sans MS, Verdana, Arial"
	bgcolor="white">
 
	<table width=100%>
		<tr>
			<td style="border-bottom: 1px solid #00AA90;">
				<div
					style="margin-left: 10px; margin-bottom: 10px; margin-top: 10px;">
					<!-- logo -->
				</div>
			</td>
		</tr>
		<tr>
			<td>

				<div class="naglowek_strony">Diagnostyka dostêpu do serwisu po
					stronie klienta</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="naglowek_danych">Informacje diagnostyczne:</div>

				<div class="tresc_podsumowania">
					Wywo³anie metody testuj¹cej:<br> ${ message }
				</div>
				<div class="tresc_podsumowania">
					GetForObject z parametrem 667 w url
					(...8080/sapp/rest/getMessage?reqMsg=667):<br> ${ messagePostParam }
				</div>
				<div class="tresc_podsumowania">
					PostForObject z Stringiem z RequestBody: <br> ${ messagePostBody }
				</div>
			</td>
		</tr>
</body>
</html>