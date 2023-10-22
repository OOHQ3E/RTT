# import pip
import selenium
from selenium import webdriver
from selenium.webdriver.common.by import By

try:
    driver = webdriver.Chrome()
    driver.get("https://www.saucedemo.com/")

    username = driver.find_element(By.ID, "login_credentials").text.split('\n')[1]
    print(f"username: %a" % username)

    driver.find_element(By.ID, "user-name").send_keys(username)
    # -----
    password = driver.find_element(By.CLASS_NAME, "login_password").text.split('\n')[1]
    print(f"password: %a" % password)

    driver.find_element(By.ID, "password").send_keys(password)

    driver.find_element(By.ID, 'login-button').click()
    title = driver.find_element(By.CLASS_NAME, 'title').text
    print(f"Title is: %a" % title)

    input()  # <- az alábbi csak azért kell, hogy teszt lefutása után ne záródjon be egyből a Chrome
except:
    driver.quit()
