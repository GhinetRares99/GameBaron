# GameBaron
Aplicatie destinata vanzarii jocurilor video.

Backend-ul aplicatiei are 6 entitati: User, Badge, Possession, Game, Tool, Achievement.

User: utilizatorul aplicatiei. Atribute: UserName, Password, FirstName, LastName, Email, Phone, WalletMoney, Country, City, StreetName.

Badge: se acorda utilizatorului atunci cand indeplineste o conditie (ex: a cumparat primul joc). Atribute: BadgeName, BadgeDescription, user (utilizatorul de care apartine).

Possession: un tabel de legatura intre entitatile Game si User, care descrie cine ce joc detine. Atribute: user si game.

Game: descrie un joc. Atribute: GameName, GameGenre, GameCreator, GamePublisher, GameDescription, GamePrice.

Tool: o aplicatie prin care se creeaza custom content pentru jocul respectiv. Atribute: ToolName, ToolDescription, ToolVersion, game.

Achievement: un trofeu/realizare asociata jocului. Atribute: AchievementName, AchievementDescription, AchievementRarity, game.

Au fost aplicate constrangeri de tip CASCADE.REMOVE: daca entitatea parinte (User/Game) este stearsa, atunci sunt sterse si entitatile copil.

Fiecare entitate are asociat un controller, un serviciu si un repository. Cererile GET, POST, PUT si DELETE din controller apeleaza metodele din cadrul serviciului, fiind posibila manipularea datelor. Vor fi aruncate exceptii in cazul in care informatiile pe care dorim sa le accesam nu exista in baza de date.

Au fost realizate teste unitare pentru a verifica corectitudinea codului scris. Coverage: 72% pe metoda si 80% pe linie.
