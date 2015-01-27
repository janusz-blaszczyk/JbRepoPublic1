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
	padding-left: 200px;
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

				<div class="naglowek_strony">Diagnostyka dost�pu do serwisu po
					stronie klienta</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="naglowek_danych">Us�ugi</div>
				<div class="tresc_podsumowania">
					1. Serwer REST WS Spring
					udost�pniaj�cy metody testuj�ce zar�wno zabezpieczone spring
					security i OAUTH1 jak i dost�pne og�lnie <br>2. Klient REST WS
					Spring odwo�uj�cy si� do og�lnodost�pnych metod <br>3. Klient
					REST WS Jersey komunikuj�cy si� z uzyciem spring security i OAUTH1
				</div>
				<div class="naglowek_danych">Podzia� ruchu</div>
				<div class="tresc_podsumowania">
					web.xml jest tu ciekawy. Po pierwsze jest filtr na /api/secure -
					stref� prywatn� do kt�rej maj� dost�p tylko serwisy z
					uwierzytelnianiem, <br> ruch w /api/private zarezerwowany i
					obs�ugiwany jest przez klienta jersey (klient ten komunikuje si� ze
					stref� prywarn�). <br>Ca�y pozosta�y rucj po / jest sterowany
					juz standardowo przez springa.
				</div>
				<div class="naglowek_danych">Kontrolery</div>
				<div class="tresc_podsumowania">
					Kontroler�w jest tez kilka. Dla serwera s� to
					SecureServerController oraz ServerController - obs�uguj�ce
					szyfrowane oraz nieszyfrowane zadania. <br> PublicController -
					client publicznych zada, SecureController - client do strefy
					prywatnej, WebControler - obs�uguje t� stron� ;)
				</div>
				<div class="naglowek_danych">Testy - api publiczne</div>
				<div class="tresc_podsumowania">
					Wszystkie url'e mozna spawdzic wprost w przegladarce (po
					zainstalowaniu wtyczki do jsona �adnie prezentuj� si� obiekty) <br>
					Testy metod og�lnodost�pnych: <br>/api/public/isAlive <br>
					Wynik z klienta Spring REST: ${ message } <br> <br>/api/public/getMessageGet?reqMsg=667
					(GetForObject z parametrami w url'u) <br> Wynik z klienta
					Spring REST: ${ messagePostParam } <br> <br>/api/public/getMessagePost
					(PostForObject z parametrami w body) <br> Wynik z klienta
					Spring REST: ${ messagePostBody }
				</div>
				<div class="naglowek_danych">Testy - api prywatne</div>
				<div class="tresc_podsumowania">
					Stref� prywatn� wywo�ujemy urlami: /api/private/check/ <br>
					<br> i ciekawostka :P (a w zasadzie cel):
					api/private/check/znanylekarzpl <br>tutaj mamy dost�p do
					testowego api serwisu znanylekarz.pl

				</div>
			</td>
		</tr>
</body>
</html>