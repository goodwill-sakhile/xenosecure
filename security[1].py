from kivymd.app import MDApp
from touch import TouchBox
from kivymd.uix.boxlayout import MDBoxLayout
from kivy.uix.screenmanager import SlideTransition
from kivymd.uix.screen import MDScreen
from pin_space import FourDigitPinSpaceScreen
from kivy.lang import Builder
ui = Builder.load_string("""
<LoadScreen>:
    name:"load_screen"
    MDBoxLayout:
        orientation:"vertical"
        MDBoxLayout:
        MDBoxLayout:
            size_hint_y:None
            height:"100dp"
            Widget:
            MDBoxLayout:
                size_hint:None, None
                size:"100dp", "100dp"
                padding:25, 25, 25, 25
                radius:[20, 20, 20, 20]
                md_bg_color:[0, 0/float(255), 0/float(255), 1]
                Image:
                    id:gif
                    source:"load.gif"
                    center:self.parent.center
                    size:50, 50
                    allow_stretch:True
                    anim_delay:-1
                    anim_loop:10000000000000000000000000
                    #_coreimage:anim_reset(True)
                    anim_delay:0.01
            Widget:
        MDBoxLayout:
<SecurityScreen>:
    name:"security_screen"
    id:security_screen
    MDBoxLayout:
        orientation:"vertical"
        md_bg_color:[0, 0, 0, 1]
        MDBoxLayout:
            size_hint_y:None
            height:"100dp"
            size_hint_y:None
            height:"100dp"
            padding:"10dp", "0dp"
            spacing:5
            MDLabel:
                text:"Xenosecure"
                text_size:self.size
                halign:"left"
                font_size:"25dp"
                valign:"middle"
                font_name:"Amsterdam-ZVGqm"
                color:[1, 1, 1, 1]
        MDBoxLayout:
            radius:[20, 20, 0, 0]
            md_bg_color:[25/float(255), 25/float(255), 112/float(255), 1]
            ScreenManager:
                root:security_screen
                id:screen_manager
                MDScreen:
                    name:"set_passsword_screen"
                    MDBoxLayout:
                        orientation:"vertical"
                        id:body
                        MDBoxLayout:
                            size_hint_y:None
                            height:"50dp"
                            MDLabel:
                                text:"Encrypt files as private"
                                text_size:self.size
                                halign:"center"
                                font_size:"18dp"
                                valign:"middle"
                                bold:True
                                color:[1, 1, 1, 1]
                        Widget:
                        IconSpaceBox:
                            size_hint_y:None
                            height:"200dp"
                            Widget:
                            MDIconButton:
                                size_hint:None, None
                                size:"200dp", "200dp"
                                user_font_size:"150dp"
                                icon:"key-chain-variant"
                                pos_hint:{"center_x":.5}
                            Widget:
                        MDBoxLayout:
                            size_hint_y:None
                            height:"50dp"
                            Widget:
                            SetPasswordButtonBox:
                                root:security_screen
                                radius:[40, 40, 40, 40]
                                size_hint_x:None
                                width:"200dp"
                                md_bg_color:[0, 154/float(255), 255/float(255), 1]
                                MDLabel:
                                    text:"Set password"
                                    text_size:self.size
                                    halign:"center"
                                    valign:"middle"
                                    color:[1, 1, 1, 1]
                            Widget:
                        Widget:
                FourDigitPinSpaceScreen:
                    body:body
                    id:four_digit_pin_space_screen
                    FloatLayout:
                        size_hint:None, None
                        size:body.size
                        pos:body.pos
                        MDBoxLayout:
                            pos:body.pos
                            ScreenManager:
                                id:top_screen_manager
                                MDScreen:
                                    name:"empty_screen"
                                LoadScreen:
""")
class LoadScreen(MDScreen):
    pass
class IconSpaceBox(TouchBox):
    def respondToTouch(self):
        pass
class SetPasswordButtonBox(TouchBox):
    #respond button box that sends you to the screen to set your new password
    #that screen is named pin_space_screen
    def respondToTouch(self):
        self.root.ids.four_digit_pin_space_screen.changePinSpaceTopic("Create a 4-digit passcode")
        self.root.ids.screen_manager.transition = SlideTransition(direction = "left")
        self.root.ids.screen_manager.current = "pin_space_screen"
class SecurityScreen(MDScreen):
    pass
class Test(MDA