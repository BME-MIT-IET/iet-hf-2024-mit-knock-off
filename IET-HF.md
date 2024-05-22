# A Drukmákori sivatag projekt

## 1. A projekt célja

A projekt célja az Integrációs és Ellenőrzési Technika című (BMEVIMIAC04) tárgy keretein belül tanult tecknikák gyakorlati alkalmazása egy valós projekten. A választott alkalmazás az előző évben, Szoftver Projekt Laboratórium tantárgy során alkotott  Java nyelven írt, Drukmákóri sivatag című játék.


## 2. Az eredeti projekt  leírása

A drukmákori sivatagon át csőrendszer szállítja a vizet a forrásokból a
sivatagon túl lévő ciszternákba. A csőrendszer elágazás nélküli
csövekből és a csövekhez csatlakozó elemekből (forrás, ciszterna, pumpa) áll. Egy pumpa több  csövet is
összeköthet, és minden pumpán külön-külön állítható, hogy éppen melyik belekötött csőből
melyik másik csőbe pumpáljon, azonban egyszerre csak egy bemenete és egy kimenete lehet. A pumpák véletlen időközönként el tudnak
romlani, ilyenkor megszűnik az adott pumpánál a vízáramlás. A pumpák mindegyike
rendelkezik egy víztartállyal, amit a víz átemelése közben használ átmeneti tárolóként.

A csőhálózatot a szerelők tartják karban. Ők javítják meg és állítják át
a pumpákat, hogy mindig a lehető legtöbb víz tudjon áthaladni a hálózaton, és ha egy cső
kilyukad, az ő dolguk a cső megfoltozása is. A kilyukadt csövekből a víz kifolyik. A szerelők dolga a ciszternáknál lévő szabad
csövekkel a hálózat kapacitásának növelése. A csőhálózat bővíthető, változtatható. A csövek felvehetőek és lerakhatóak máshol. A szerelők a ciszternáknál magukhoz tudnak
venni új csövet és pumpát is.

A hálózaton élnek a nomád szabotőrök is, akik a pumpákat tudják átállítani és a csöveket kilyukasztani.

Mivel a sivatag veszélyes hely, a szerelők és a szabotőrök csak a csőhálózaton haladhatnak. A
pumpáknál kikerülhetik egymást, de a csöveken már nem tudnak elmenni egymás mellett, egy
csövön egyszerre csak egy ember állhat.

A játékot a két csapat legalább 2-2 játékossal játssza. A szabotőrök dolga, hogy minél több víz
folyjon el a lyukakon, a szerelők pedig azon dolgoznak, hogy minél több víz jusson a
ciszternákba. Az a csapat nyer, amelyik a játék végére több vizet szerez.

Egy körben felváltva jönnek a szerelők és szabotőrök. Minden játékosnak 3 akciója van
összesen lépéssel együtt a körében. Ha egy pumpa el van romolva, akkor nem lehet az állásán változtatni. Egy körben egy
egységnyi, azaz egy csőnyi víz folyik tovább a kör végén. Ha a kör végén van víz
kilyukasztott, vagy nem csatlakoztatott csőben, akkor az kifolyik.


## 3. Az alkalmazás futtatása
`javac Main.java`  
`java Main`  


## 4. Megvalósítandó feladatok

### Technológia fókusz
- Build keretrendszer és CI beüzemelése (Maven, Github Action)
- Statikus kódelemzés beüzemelése (SonalCloud hibák javítása)
### Termék/felhasználó fókusz
- BDD tesztek készítése (Cucumber)
- Manuális tesztek megtervezése, végrehajtása és dokumentálása
