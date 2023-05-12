from kivymd.app import MDApp
from kivy.uix.screenmanager import ScreenManager
from kivy.uix.screenmanager import NoTransition
from kivy.lang import Builder
from security import SecurityScreen
from home import HomeScreen
from encrypted_files import EncryptedFilesScreen
from view_media_file import ViewMediaFileScreen
from settings import SettingsScreen
import pickle
from kivy.clock import Clock
import random
ui = Builder.load_string("""
<MainScreenManager>:
    SecurityScreen:
        id:security_screen
    HomeScreen:
        id:home_screen
    EncryptedFilesScreen:
    ViewMediaFileScreen:
        id:view_media_files_screen
    SettingsScreen:
        id:settings_screen
""")
class MainScreenManager(ScreenManager):
    #parent container of all app screens
    pass
class Xenosecure(MDApp):
    #main app loop object
    def storeKey(self):
        #string key
        try:
            file_object = open("dbase.db", "rb")
        except:
            file_object = open("dbase.db", "wb")
            key = random.choice(list(range(1, 256)))
            pickle.dump({"key":key}, file_object)
    def build(self):
        self.storeKey()
        root = MainScreenManager()
        file_object = open("dbase.db", "rb")
        table = pickle.load(file_object)
        file_object.close()
        try:
            pin = table["pin"]
            root.ids.security_screen.ids.four_digit_pin_space_screen.stage = 1
            root.ids.security_screen.ids.screen_manager.transition = NoTransition()
            root.ids.security_screen.ids.screen_manager.current = "pin_space_screen"
            print("try done")
        except:
            pass
        return root
if __name__ == "__main__":
    Xenosecure().run()