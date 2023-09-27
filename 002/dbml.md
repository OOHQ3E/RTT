# Relációs adatbázis tervezés

 Egy adatbázis megtervezése, létrehozása igen összetett feladat, és igényel némi kreativitást. Az adatbázisokat úgy kell megtervezni, hogy eleget tegyenek bizonyos kritériumnak, például, hogy minimális legyen bennük az adatredundancia, teljesüljenek a különböző adatfüggetlenségek, stb.

 Ezek létrehozására a [dbdiagram.io](https://dbdiagram.io/)-t fogjuk használni. A DBML dokumentációját [ezen a linken](https://dbml.dbdiagram.io/docs/), a dbdiagram.io dokumentációját pedig [ezen a linken](https://dbdiagram.io/docs) lehet megtalálni.

## Adatbázis normálformák

- **1 NF**
  - Oszlopok nevei egyediek
  - Elemei azonos típusúak
  - Nincs két azonos sor
  - Minden tábla tartalmaz elsődleges kulcsot
  - Oszlopok-sorok felcserélhetőek a lekérdezés során/sorrend nem számít
  - Minden oszlop-sor metszetében elemi értékek találhatóak

- **2 NF**
  - 1NF teljesül
  - Minden másodlagos (nem kulcs) attribútum teljesen függ a kulcstól

- **3 NF**
  - 2NF teljesül
  - Nincs tranzitív függőség

---

### 1. Feladat

Tervezzünk programot, mely a CD lemezeinkről az alábbi adatokat tárolja:

- Lemez előadója és címe
- A rajta levő számok és sorrendjük, valamint a zenekar alapítási éve.

 Egy rekord a normalizálás előtt az alábbi felépítésű:

![image](https://github.com/OOHQ3E/RTT_guide/blob/master/002/img_src/01.png)

```dbml
Table compactdiscs {
  id integer [pk]
  album text
  founded date
  tracks text
}
```

**Ez nincs 1 NF-ben, mert** egyes mezők tovább oszthatók az alábbiak szerint:

- album + előadó szétszedése, így azok atomi adatok lesznek
- Nem tartalmazhatnak felsorolást (tracks), ezért ezt is szétszedjük és újabb sorokat készítünk belőlük.

Az 1-es normálformához ezeket megszüntetjük, és új oszlopokat vezetünk be: a band-et és track-et. Sajnos így már a compactdisks.id nem kulcs többé, mert több sorban is azonos az értéke, ezért összetett kulcs lesz: (compactdiscs.id, track)

![image](https://github.com/OOHQ3E/RTT_guide/blob/master/002/img_src/02.png)

```dbml
Table compactdisks {
  id integer [pk]
  band text
  album text
  founded date
  tracknumber integer [pk]
  title text
}
```

Ez nincs 2NF-ben, mert az album címe csak az egyik kulcstól függ, a másiktól nem. Egy adatbázis második normálformába kerül, ha *Első normálformában van,* Eltávolítjuk az egyes táblák esetén azokat az adatcsoportokat, melyek több sort érintenek, ezeket új táblákba helyezzük, majd a régi és az új táblák közt kulcsokon alapuló kapcsolatokat hozunk létre.

Mivel a zenekarok esetén az albumok ismétlődnek, ezért kiemeljük azokat:

![image](https://github.com/OOHQ3E/RTT_guide/blob/master/002/img_src/03.png)

```dbml
Table compactdisks {
  id integer [pk]
  band text
  album text
  founded date
}

Table tracks {
  id integer [ref: < compactdisks.id]
  tracknumber integer
  title text
}
```

Ez nincs 3NF-ben, mert a compactdisks táblában a compactdisks.id-től függ az album, attól pedig az előadó, ezért trazitív függőség áll fenn. Oldjuk fel ezt! (Azon el lehet gondolkodni, hogy egy lemez csak egy előadós-e, de ettől most eltekintünk.)

![image](https://github.com/OOHQ3E/RTT_guide/blob/master/002/img_src/04.png)

```dbml
Table compactdisks {
  id integer [pk]
  album text
  band_id integer [ref: > band.id]
}

Table band {
  id integer [pk]
  band text
  founded date
}

Table tracks {
  cd_id integer [ref: < compactdisks.id]
  tracknumber integer
  title text
}
```

**Ez már 3NF-ben van, mert nincs tranzitív függőség.**

---

### 2. feladat

Egy egyetemi rendszer, amiben az alábbiak találhatóak:

- tanuló
  - szakja
  - neve
  - évfolyama
  - jegyei
  - kurzusai
  - szakja
  - elérhetősége (pl: telefonszáma)
- szak
  - ügyintéző

**Lehetséges megoldás**:

![image](https://github.com/OOHQ3E/RTT_guide/blob/master/002/img_src/05.png)

```dbml
Table tanulok {
  id integer [pk]
  szak_id integer [ref: <> szakok.id]
  nev text
  evfolyam integer
}

Table szakok {
  id integer [pk]
  szaknev text
}

Table telefon {
  id integer [pk]
  telefonszam text
  tanulo_id integer
}
ref: telefon.tanulo_id - tanulok.id [delete: cascade]

Table ugyintezok {
  id integer [pk]
  nev text
  szak_id integer [ref: > szakok.id]
}

Table kurzusok {
  id integer [pk]
  kurzus text
}

Table jegyek {
  tanulo_id integer [ ref: > tanulok.id ]
  kurzus_id integer [ ref: > kurzusok.id ]
  jegy integer [ default: null]
}
```

---

### 3.feladat

Egy film adatbázisban a következő információkat tároljuk:

- film cím
- rendező
- főszereplők
- film kategória (pl. akció, romantikus stb.)
- kiadási év
- film leírás

**Lehetséges megoldás**:

![image](https://github.com/OOHQ3E/RTT_guide/blob/master/002/img_src/06.png)

```dbml
Table Filmek {
  id int [pk, increment]
  cim string
  kategoria text
  kiadasi_ev int
  leiras text
}

Table RendezoFilmek {
  rendezo_id int [ref: < Rendezok.id]
  film_id int [ref: < Filmek.id]
}

Table SzereplokFilmek {
  szereplo_id int [ref: < Szereplok.id]
  film_id int [ref: < Filmek.id]
}

Table Rendezok {
  id int [pk, increment]
  nev text
}

Table Szereplok {
  id int [pk, increment]
  nev text
}
```

---

### 4.feladat

Egy online fórumhoz tároljuk a felhasználók adatait, posztokat és a hozzászólásokat.
Minden felhasználóhoz tartozik:

- felhasználónév
- e-mail cím
- jelszó
  
Minden poszthoz tartozik:

- cim
- tartalom
- időbélyeg
- felhasználó, aki írta

Minden poszthoz hozzá lehet szólni, aminek van:

- tartalma
- időbélyeg
- felhasználó, aki írta

**Lehetséges megoldás**:

![image](https://github.com/OOHQ3E/RTT_guide/blob/master/002/img_src/07.png)

```dbml
Table Felhasznalok {
  id int [pk]
  felhasznalonev varchar
  email email
  jelszo md5
}

Table Posztok {
  id int [pk]
  cim text
  tartalom text
  idobelyeg datetime
  felhasznalo_id int [ref: > Felhasznalok.id]
}

Table Kommentek {
  id int [pk]
  tartalom text
  idobelyeg datetime
  felhasznalo_id int [ref: > Felhasznalok.id]
  poszt_id int [ref: > Posztok.id]
}
```

---

### 5.feladat

Egy webáruházban termékek adatait tároljuk. Minden terméknek van:

- cikkszáma
- neve
- leírása
- ára
- készleten lévő darabszáma

Termékeket lehet rendelni, ezeknek az adatait is tároljuk:

- rendelés dátuma
- rendelt termékek

**Lehetséges megoldás**:

![image](https://github.com/OOHQ3E/RTT_guide/blob/master/002/img_src/08.png)

```dbml
Table Termek {
  id int [pk]
  nev text
  leiras text
  ar int
  keszlet int
}

Table Ugyfel {
  id int [pk]
  name text
  email text
}

Table Rendeles {
  id int [pk]
  ugyfel_id int [ref: > Ugyfel.id]
  datum date
}

Table RendeltTermekek {
  id int [pk]
  rendeles_id int [ref: > Rendeles.id]
  termek_id int [ref: > Termek.id]
  mennyiseg int
}
```

---

### Egyéb

Adatbázis tervet a dbdiagram.io-n ki lehet exportálni képbe, pdf-be, és különböző adatbázis formára, (PostgreSQL, és mysql) és szintén lehet létező adatbázist is importálni mint DBML ábraként.

Akik a szakdolgozatukban Laravel-t használnak annak tudom ajánlani esetleg a [Laravel Schema Designer-t](http://www.laravelsd.com/), ahol lehet készíteni adatbázis tervet, ami alapján le tudja generálni nekünk a:

- Controller-t
- Model-t
- Migration-t
