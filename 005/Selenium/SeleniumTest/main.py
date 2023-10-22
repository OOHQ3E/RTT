import unittest
from selenium import webdriver
import page

class Testing(unittest.TestCase):
    def setUp(self):
        print("setting up")
        self.driver = webdriver.Chrome()
        self.driver.get("https://www.saucedemo.com/")

    def test_title(self):
        mainPage = page.MainPage(self.driver)
        assert mainPage.is_title_matching()

    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    unittest.main()
