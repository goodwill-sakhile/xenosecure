from kivymd.app import MDApp
from kivymd.uix.screen import MDScreen
from kivymd.uix.gridlayout import MDGridLayout
from kivy.uix.screenmanager import SlideTransition
from touch import TouchBox
from kivy.lang import Builder
from kivymd.uix.dialog import MDDialog
from kivymd.uix.button import MDFillRoundFlatButton
import pickle
ui = Builder.load_string("""
<DigitButtonBox>:
    md_bg_color:[0, 0, 0, 1]
    radius:[10, 10, 10, 10]
    Widget:
    MDIconButton:
        id:icon_button
        size_hint:None, None
        size:"50dp", "50dp"
        icon:"numeric-1"
        theme_text_color:"Custom"
        text_color:[1, 1, 1, 1]
        pos_hint:{"center_x":.5, "center_y":.5}
    Widget:
<DigitSpace>:
    size_hint:None, None
    size:"60dp", "60dp"
    radius:[10, 10, 10, 10]
    md_bg_color:[35/float(255), 35/float(255), 122/float(255), 1]
    MDLabel:
        id:digit
        text:""
        text_size:self.size
        halign:"center"
        valign:"middle"
        color:[1, 1, 1, 1]
<FourDigitPinSpaceScreen>:
    name:"pin_space_screen"
    id:four_digit_pin_space_screen
    MDBoxLayout:
        radius:[20, 20, 0, 0]
        md_bg_color:[25/float(255), 25/float(255), 112/float(255), 1]
        orientation:"vertical"
        padding:0, 20, 0, 0
        spacing:10
        Widget:
        MDBoxLayout:
            size_hint_y:None
            height:"30dp"
            MDLabel:
                id:passcode_screen_topic
                text:"Enter 4-digit passcode"
                text_size:self.size
                halign:"center"
                valign:"middle"
                color:[1, 1, 1, 1]
        MDBoxLayout:
            id:digits_input_space
            size_hint_y:None
            height:"60dp"
            spacing:10
            Widget:
            DigitSpace:
            DigitSpace:
            DigitSpace:
            DigitSpace:
            Widget:
        MDBoxLayout:
            size_hint_y:None
            height:"300dp"
            Widget:
            MDBoxLayout:
                root:four_digit_pin_space_screen
                radius:[20, 20, 20, 20]
                size_hint_x:None
                width:"300dp"
                DigitLayout:
                    id:digit_layout
                    padding:10, 5, 10, 5
                    spacing:5
                    cols:3
                    rows:4
            Widget:
        MDBoxLayout:
            size_hint_y:None
            height:"50dp"
            padding:10, 0, 10, 10
            ContinueButtonBox:
                root:four_digit_pin_space_screen
                radius:[30, 30, 30, 30]
                md_bg_color:[0, 154/float(255), 255/float(255), 1]
                MDLabel:
                    text:"Continue"
                    text_size:self.size
                    halign:"center"
                    valign:"middle"
                    color:[1, 1, 1, 1]
""")
class DigitButtonBox(TouchBox):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.numeric = ""
        self.function = None
    def respondToTouch(self):
        try:
            int(self.numeric)
            self.function(self.numeric)
        except:
            self.function()
class DigitSpace(TouchBox):
    pass
class DigitLayout(MDGridLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.bottom_buttons = [("reload", self.reload), ("numeric-0", self.enterDigit), ("close", self.cancel)]
        self.input_indexes = 3
        for i in range(1, 13):
            button_box = DigitButtonBox()
            if i < 10:
                button_box.ids.icon_button.icon = "numeric-" + str(i)
                button_box.numeric = button_box.ids.icon_button.icon[-1]
                button_box.function = self.enterDigit
            else:
                button_box.ids.icon_button.icon = self.bottom_buttons[0][0]
                button_box.numeric = button_box.ids.icon_button.icon[-1]
                button_box.function = self.bottom_buttons[0][1]
                self.bottom_buttons.remove(self.bottom_buttons[0])
            self.add_widget(button_box)
    def enterDigit(self, numeric):
        print(numeric)
        if not self.parent.root.loading:
            if self.input_indexes >= 0:
                self.parent.root.ids.digits_input_space.children[-(5-self.input_indexes)].ids.digit.text = "*"
                self.input_indexes -= 1
                self.parent.root.four_digit_pin += numeric
    def reload(self):
        if not self.parent.root.loading:
            for i in range(4):
                self.parent.root.ids.digits_input_space.children[-(5-i)].ids.digit.text = ""
                self.parent.root.four_digit_pin = self.parent.root.four_digit_pin[:-1]
            self.input_indexes = 3
    def cancel(self):
        if not self.parent.root.loading:
            if self.input_indexes < 3:
                self.input_indexes += 1
                self.parent.root.ids.digits_input_space.children[-(5-self.input_indexes)].ids.digit.text = ""
                self.parent.root.four_digit_pin = self.parent.root.four_digit_pin[:-1]
class ContinueButtonBox(TouchBox):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.main_topic = ""
    def loadHomeScreen(self):
        print("***", self.root.parent.root.ids.top_screen_manager)
        self.root.parent.root.ids.top_screen_manager.transition = SlideTransition(direction = "left")
        self.root.parent.root.ids.top_screen_manager.current = "load_screen"
        self.root.parent.root.parent.ids.home_screen.loadFiles(self.root.parent.root.parent)
        #self.loading = False
        #self.root.parent.root.ids.top_screen_manager.transition = SlideTransition(direction = "left")
        #self.root.parent.root.ids.top_screen_manager.current = "empty_screen"
    def moveToHomeScreen(self):
        self.root.parent.root.parent.transition = SlideTransition(direction = "left")
        self.root.parent.root.parent.current = "home_screen"
    def completePinChange(self):
        if self.root.stage == 0:
            self.root.loading = True
            self.loadHomeScreen()
        else:
            self.root.ids.digit_layout.reload()
            self.root.pincode_one = ""
            self.root.pincode_two = ""
            self.main_topic = "Change 4-digit pin"
            self.root.changePinSpaceTopic(self.main_topic)
            self.callDialog("4-digit pin changed succesfully")
    def comparePasscodes(self, pin_one, pin_two):
        if pin_one == pin_two:
            file_object = open("dbase.db", "rb")
            table = pickle.load(file_object)
            file_object.close()
            table["pin"] = pin_two
            file_object = open("dbase.db", "wb")
            pickle.dump(table, file_object)
            file_object.close()
            self.completePinChange()
            #self.moveToHomeScreen()
        else:
            self.root.changePinSpaceTopic(self.main_topic)
            self.root.pincode_one = ""
            self.root.pincode_two = ""
            self.root.ids.digit_layout.reload()
    def createPinCode(self):
        if self.root.pincode_one == "":
            self.root.changePinSpaceTopic("Re-Enter 4-digit passcode")
            self.root.pincode_one = self.root.four_digit_pin
            self.root.ids.digit_layout.reload()
            self.root.four_digit_pin = ""
        else:
            self.root.pincode_two = self.root.four_digit_pin
            self.comparePasscodes(self.root.pincode_one, self.root.pincode_two)
    def callDialog(self, text):
        button = MDFillRoundFlatButton(text = "Ok")
        self.dialog = MDDialog(title = "Security code", text = text, buttons = [button])
        button.bind(on_press = self.close)
        self.dialog.open()
    def close(self, button):
        self.dialog.dismiss()
    def respondToTouch(self):
        if len(self.root.four_digit_pin) == 4: 
            if self.root.stage == 0:
                self.main_topic = "Create a 4-digit passcode"
                self.createPinCode()
            elif self.root.stage == 1:
                file_object = open("dbase.db", "rb")
                table = pickle.load(file_object)
                file_object.close()
                if table["pin"] == self.root.four_digit_pin:
                    self.root.loading = True
                    self.loadHomeScreen()
                else:
                    self.root.ids.digit_layout.reload()
            else:
                self.main_topic = "Change 4-digit pin"
                self.createPinCode()
class FourDigitPinSpaceScreen(MDScreen):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.four_digit_pin = ""
        self.pincode_one = ""
        self.pincode_two = ""
        self.stage = 0
        self.loading = False
    def changePinSpaceTopic(self, topic):
        self.ids.passcode_screen_topic.text = topic
class Test(MDApp):
    def build(self):
        root = FourDigitPinSpaceScreen()
        return root
if __name__ == "__main__":
    Test().run()