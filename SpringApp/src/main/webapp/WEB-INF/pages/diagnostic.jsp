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

				<div class="naglowek_strony">Diagnostyka dostêpu do serwisu po
					stronie klienta</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="naglowek_danych">Us³ugi</div>
				<div class="tresc_podsumowania">
					1. Serwer REST WS Spring
					udostêpniaj¹cy metody testuj¹ce zarówno zabezpieczone spring
					security i OAUTH1 jak i dostêpne ogólnie <br>2. Klient REST WS
					Spring odwo³uj¹cy siê do ogólnodostêpnych metod <br>3. Klient
					REST WS Jersey komunikuj¹cy siê z uzyciem spring security i OAUTH1
				</div>
				<div class="naglowek_danych">Podzia³ ruchu</div>
				<div class="tresc_podsumowania">
					web.xml jest tu ciekawy. Po pierwsze jest filtr na /api/secure -
					strefê prywatn¹ do której maj¹ dostêp tylko serwisy z
					uwierzytelnianiem, <br> ruch w /api/private zarezerwowany i
					obs³ugiwany jest przez klienta jersey (klient ten komunikuje siê ze
					stref¹ prywarn¹). <br>Ca³y pozosta³y rucj po / jest sterowany
					juz standardowo przez springa.
				</div>
				<div class="naglowek_danych">Kontrolery</div>
				<div class="tresc_podsumowania">
					Kontrolerów jest tez kilka. Dla serwera s¹ to
					SecureServerController oraz ServerController - obs³uguj¹ce
					szyfrowane oraz nieszyfrowane zadania. <br> PublicController -
					client publicznych zada, SecureController - client do strefy
					prywatnej, WebControler - obs³uguje tê stronê ;)
				</div>
				<div class="naglowek_danych">Testy - api publiczne</div>
				<div class="tresc_podsumowania">
					Wszystkie url'e mozna spawdzic wprost w przegladarce (po
					zainstalowaniu wtyczki do jsona ³adnie prezentuj¹ siê obiekty) <br>
					Testy metod ogólnodostêpnych: <br>/api/public/isAlive <br>
					Wynik z klienta Spring REST: ${ message } <br> <br>/api/public/getMessageGet?reqMsg=667
					(GetForObject z parametrami w url'u) <br> Wynik z klienta
					Spring REST: ${ messagePostParam } <br> <br>/api/public/getMessagePost
					(PostForObject z parametrami w body) <br> Wynik z klienta
					Spring REST: ${ messagePostBody }
				</div>
				<div class="naglowek_danych">Testy - api prywatne</div>
				<div class="tresc_podsumowania">
					Strefê prywatn¹ wywo³ujemy urlami: /api/private/check/ <br>
					<br> i ciekawostka :P (a w zasadzie cel):
					api/private/check/znanylekarzpl <br>tutaj mamy dostêp do
					testowego api serwisu znanylekarz.pl

				</div>
			</td>
		</tr>
</body>
</html>