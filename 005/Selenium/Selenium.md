#Selenium web tesztelés

## ~~Chrome drive letöltése és telepítése~~

~~<https://sites.google.com/chromium.org/driver/downloads>-~~

* ~~Chrome-ot igényel, aminek a verzióját az alábbiak alapján találhatod meg:~~
  
~~![Chrome](https://github.com/OOHQ3E/RTT_guide/blob/master/005/Selenium/chrome%20version.bmp)~~

~~Mivel nekem 118-as verzó van jelenleg, ami nincs a fenti oldal listájában, ezért az alábbi oldalról lehet ezt letölteni: <https://googlechromelabs.github.io/chrome-for-testing/#stable>~~

~~Ezután az alábbi oldal követése, hogy hogyan is setupoljuk a Chrome Web Test-et: <https://sites.google.com/chromium.org/driver/getting-started>~~

Mivel a Selenium 4.x-es verzió automatikusan elvégzi ezeket, ezért nem szükséges a fentiekkel bajlódnunk :)

## SeleniumIDE

Az alábbi oldalról le kell tölteni a SeleniumIDE driver-t: <https://www.selenium.dev/selenium-ide/>

SeleniumIDE-vel lehetőségünk van webes tevékenységek felvételére is, (oldal megtekintés, klikkelés, írás, navigálás, stb...) amit a későbbiekben vissza tudunk játszani, mint automatizált mozdulatok sorozata.

Szintén lehetőség van, hogy különböző utasításokat is megadjunk, és saját mozdulatokat/tevékenységeket is definiálni tudunk.

Az alábbi oldalon megtalálható a SeleniumIDE dokumentációja, ahol további műveleteket megtekinthet: <https://www.selenium.dev/selenium-ide/docs/en/introduction/getting-started>

## Selenium Python-ban

* ```pip install selenium```  -> install selenium -> import pip, ezeket a PyCharm (és szerintem a VsCode is) elintézi

További teendők pedig a python file-on belül megtalálhatóak :)
