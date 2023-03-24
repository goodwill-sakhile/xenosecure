from kivymd.app import MDApp
from kivymd.uix.screen import MDScreen
from kivymd.uix.boxlayout import MDBoxLayout
from kivy.uix.screenmanager import SlideTransition
from kivy.lang import Builder
from touch import TouchBox
from pin_space import FourDigitPinSpaceScreen
ui = Builder.load_string("""
<SettingsScreen>:
    id:settings_screen
    name:"settings_screen"
    MDBoxLayout:
        orientation:"vertical"
        md_bg_color:[0, 0, 0, 1]
        MDBoxLayout:
            size_hint_y:None
            height:"80dp"
            MDIconButton:
                size_hint:None, None
                size:"50dp", "50dp"
                user_font_size:"30dp"
                pos_hint:{"center_y":.5}
                icon:"arrow-left-thick"
                theme_text_color:"Custom"
                text_color:[1, 1, 1, 1]
                on_release:root.goBackToHomeScreen()
            MDLabel:
                text:"Settings"
                font_size:"23dp"
                text_size:self.size
                halign:"left"
                valign:"middle"
                color:[1, 1, 1, 1]
            MDIconButton:
                id:change_pin_screen_button
                icon:"key-chain-variant"
                size_hint:None, None
                size:"50dp", "50dp"
                user_font_size:"30dp"
                pos_hint:{"center_y":.5}
                theme_text_color:"Custom"
                text_color:[0, 154/float(255), 255/float(255), 1]
                on_release:root.goToChangePinScreen(self)
            MDIconButton:
                id:about_screen_button
                icon:"information"
                size_hint:None, None
                size:"50dp", "50dp"
                user_font_size:"30dp"
                pos_hint:{"center_y":.5}
                theme_text_color:"Custom"
                text_color:[1, 1, 1, 1]
                on_release:root.goToAboutScreen(self)
        MDBoxLayout:
            radius:[20, 20, 0, 0]
            md_bg_color:[25/float(255), 25/float(255), 112/float(255), 1]
            orientation:"vertical"
            FloatLayout:
                size_hint:None, None
                size:self.parent.size
                pos:self.parent.pos
                MDBoxLayout:
                    pos:self.parent.pos
                    ScreenManager:
                        id:settings_body_screen_manager
                        FourDigitPinSpaceScreen:
                            id:four_digit_pin_space_screen
                        Screen:
                            name:"about_screen"
                            MDBoxLayout:
                                orientation:"vertical"
                                MDBoxLayout:
                                    size_hint_y:None
                                    height:"60dp"
                                    padding:5
                                    MDLabel:
                                        text:"About"
                                        text_size:self.size
                                        halign:"center"
                                        valign:"middle"
                                        color:[1, 1, 1, 1]
                                MDBoxLayout:
                                    orientation:"vertical"
                                    MDBoxLayout:
                                        size_hint_y:None
                                        height:"70dp"
                                        Widget:
                                        MDIconButton:
                                            size_hint:None, None
                                            size:"70dp", "70dp"
                                            icon:"information"
                                            theme_text_color:"Custom"
                                            text_color:[1, 1, 1, 1]
                                            user_font_size:"60dp"
                                            pos_hint:{"center_x":.5, "center_y":.5}
                                        Widget:
                                    MDBoxLayout:
                                        size_hint_y:None
                                        height:about_label.height
                                        Label:
                                            text:"Xenosecure version 1.0.0 for android is a security application that encrypt different media files"
                                            id:about_label
                                            text_size:self.width, None
                                            size_hint:1, None
                                            halign:"center"
                                            valign:"middle"
                                            height:self.texture_size[1]
                                            color:[1, 1, 1, 1]
                                    MDBoxLayout:
                                    MDBoxLayout:
                                        size_hint_y:None
                                        height:"30dp"
                                        MDLabel:
                                            color:[120/float(255), 120/float(255), 120/float(255), 1]
                                            text:"Copyright@2022 Xenosecure all right reserved"
                                            text_size:self.size
                                            halign:"center"
                                            valign:"middle"
""")
class ChangePinButtonBox(TouchBox):
    def respondToTouch(self):
        self.parent.root.ids.about_button_box.md_bg_color = [0, 154/float(255), 255/float(255), 1]
        self.parent.root.ids.change_pin_button_box.md_bg_color = [55/float(255), 55/float(255), 142/float(255), 1]
class AboutButtonBox(TouchBox):
    def respondToTouch(self):
        self.parent.root.ids.change_pin_button_box.md_bg_color = [0, 154/float(255), 255/float(255), 1]
        self.parent.root.ids.about_button_box.md_bg_color = [55/float(255), 55/float(255), 142/float(255), 1]
class SettingsScreen(MDScreen):
    def goBackToHomeScreen(self):
        self.parent.transition = SlideTransition(direction = "right")
        self.parent.current = "home_screen"
    def goToChangePinScreen(self, button):
        self.ids.about_screen_button.text_color = [1, 1, 1, 1]
        button.text_color = [0, 154/float(255), 255/float(255), 1]
        self.ids.settings_body_screen_manager.transition = SlideTransition(direction = "right")
        self.ids.settings_body_screen_manager.current = "pin_space_screen"
    def goToAboutScreen(self, button):
        self.ids.change_pin_screen_button.text_color = [1, 1, 1, 1]
        button.text_color = [0, 154/float(255), 255/float(255), 1]
        self.ids.settings_body_screen_manager.transition = SlideTransition(direction = "left")
        self.ids.settings_body_screen_manager.current = "about_screen"
class TestApp(MDApp):
    def build(self):
        root = SettingsScreen()
        return root
if __name__ == "__main__":
    TestApp().run()