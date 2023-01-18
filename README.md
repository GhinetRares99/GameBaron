# GameBaron
Aplicatie destinata vanzarii jocurilor video.

-------------------------------------

Backend-ul aplicatiei are 6 entitati: User, Badge, Possession, Game, Tool, Achievement.

User: utilizatorul aplicatiei. Atribute: UserName, Password, FirstName, LastName, Email, Phone, WalletMoney, Country, City, StreetName.

Badge: se acorda utilizatorului atunci cand indeplineste o conditie (ex: a cumparat primul joc). Atribute: BadgeName, BadgeDescription, user (utilizatorul de care apartine).

Possession: un tabel de legatura intre entitatile Game si User, care descrie cine ce joc detine. Atribute: user si game.

Game: descrie un joc. Atribute: GameName, GameGenre, GameCreator, GamePublisher, GameDescription, GamePrice.

Tool: o aplicatie prin care se creeaza custom content pentru jocul respectiv. Atribute: ToolName, ToolDescription, ToolVersion, game.

Achievement: un trofeu/realizare asociata jocului. Atribute: AchievementName, AchievementDescription, AchievementRarity, game.

-------------------------------------

Functionalitatile aplicatiei: 

- citirea, adaugarea, stergerea si actualizarea utilizatorilor

- citirea, adaugarea, stergerea si actualizarea jocurilor

- citirea, adaugarea, stergerea si actualizarea badge-urilor

- citirea, adaugarea, stergerea si actualizarea tool-urilor

- citirea, adaugarea, stergerea si actualizarea achievement-urilot

- citirea, adaugarea si stergerea posesiilor

Au fost aplicate constrangeri de tip CASCADE.REMOVE: daca entitatea parinte (User/Game) este stearsa, atunci sunt sterse si entitatile copil.

Fiecare inregistrare adaugata trebuie sa fie validata.

Fiecare entitate are asociat un controller, un serviciu si un repository. Cererile GET, POST, PUT si DELETE din controller apeleaza metodele din cadrul serviciului, fiind posibila manipularea datelor. Vor fi aruncate exceptii in cazul in care informatiile pe care dorim sa le accesam nu exista in baza de date.

Au fost realizate teste unitare si de integrare pentru a verifica corectitudinea codului scris. Coverage: 81% pe metoda si 84% pe linie.
