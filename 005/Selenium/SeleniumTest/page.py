class BasePage(object):
    def __init__(self, driver):
        self.driver = driver


class MainPage(BasePage):

    def is_title_matching(self):
        return "Swag Labs" in self.driver.title
