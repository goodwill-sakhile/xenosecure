from kivymd.uix.screen import MDScreen
from kivy.uix.screenmanager import SlideTransition
from kivymd.uix.boxlayout import MDBoxLayout
from kivymd.uix.dialog import MDDialog
from kivymd.uix.button import MDFillRoundFlatButton
from kivy.lang import Builder
import _thread as thread
ui = Builder.load_string("""
<ViewMediaFileScreen>:
    id:media_file_screen_object
    name:"view_media_file_screen"
    MDBoxLayout:
        md_bg_color:[0, 0, 0, 1]
        orientation:"vertical"
        MDBoxLayout:
            size_hint_y:None
            height:"80dp"
            MDIconButton:
                size_hint:None, None
                size:"40dp", "40dp"
                user_font_size:"30dp"
                icon:"arrow-left-thick"
                theme_text_color:"Custom"
                text_color:[220/float(255), 220/float(255), 220/float(255), 1]
                pos_hint:{"center_x":.5, "center_y":.5}
                on_release:root.goBackToHome()
            MDLabel:
                text:"View Media File"
                font_size:"23dp"
                text_size:self.size
                halign:"center"
                valign:"middle"
                color:[1, 1, 1, 1]
            BoxLayout:
                size_hint_x:None
                width:"40dp"
        MDBoxLayout:
            radius:[40, 40, 0, 0]
            md_bg_color:[25/float(255), 25/float(255), 112/float(255), 1]
            orientation:"vertical"
            MDBoxLayout:
                padding:"5dp", "10dp", "5dp", "0dp"
                FitImage:
                    radius:[20, 20, 20, 20]
                    id:current_image
                    source:"images.jpeg"
            MDBoxLayout:
                size_hint_y:None
                height:"70dp"
                padding:10
                EncryptFileButtonBox:
                    root:media_file_screen_object
                    size_hint_y:None
                    height:"50dp"
                    radius:[40, 40, 40, 40]
                    md_bg_color:[0/float(255), 154/float(255), 255/float(255), 1]
                    MDLabel:
                        text:"encrypt file"
                        text_size:self.size
                        halign:"center"
                        valign:"middle"
                        color:[220/float(255), 220/float(255), 220/float(255), 1]
""")
class TouchBox(MDBoxLayout):
    def on_touch_down(self, touch):
        x_size = self.pos[0] + self.size[0]
        y_size = self.pos[1] + self.size[1]
        if ((touch.x > self.pos[0] and touch.x < x_size) and (touch.y > self.pos[1] and touch.y < y_size)):
            self.respondToTouch()
    def respondToTouch(self):
        pass
class ViewMediaFileScreen(MDScreen):
    #main media file screen
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.current_image_tile = None
        self.encrypting_files_list = []
        self.encrypting = False
    def goBackToHome(self):
        #return to home_screen
        self.parent.transition = SlideTransition(direction = "right")
        self.parent.current = "home_screen"
class EncryptFileButtonBox(TouchBox):
    def callDialog(self, text):
        button = MDFillRoundFlatButton(text = "Ok")
        self.dialog = MDDialog(title = "Encryption error", text = text, buttons = [button])
        button.bind(on_press = self.close)
        self.dialog.open()
    def close(self, button):
        self.dialog.dismiss()
    def respondToTouch(self):
        if not self.root.encrypting:
            self.root.encrypting = True
            self.root.current_image_tile.ids.encryption_progress.md_bg_color = [20/float(255), 20/float(255), 20/float(255), 1]
            self.root.encrypting_files_list.append(self.root.current_image_tile)
            self.root.parent.transition = SlideTransition(direction = "right")
            self.root.parent.current = "home_screen"
            thread.start_new_thread(self.root.current_image_tile.encryptFile, ())
        else:
            self.callDialog("Another file is still encrypting")
class ViewVideoFileScreen(MDScreen):
    pass
class ViewImageFileScreen(MDScreen):
    pass