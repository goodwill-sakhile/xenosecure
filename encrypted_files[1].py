from kivymd.uix.boxlayout import MDBoxLayout
from kivy.uix.screenmanager import SlideTransition, NoTransition
from kivymd.uix.screen import MDScreen
from kivymd.uix.gridlayout import MDGridLayout
from kivy.lang import Builder
from touch import TouchBox
from kivy.clock import Clock
import os
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
<SecureMediaFileBar>:
    id:media_file_bar
    size_hint_y:None
    height:"50dp"
    padding:10, 0, 10, 0
    KeyIconBox:
        root:media_file_bar
        size_hint:None, None
        size:"50dp", "50dp"
        MDIcon:
            id:media_icon_object
            user_font_size:"30dp"
            icon:"lock"
            theme_text_color:"Custom"
            text_color:[190/float(255), 190/float(255), 190/float(255), 1]
            pos_hint:{"center_x":.5, "center_y":.5}
    MDLabel:
        id:file_name
        shorten:True
        text:"file_name"
        text_size:self.size
        valign:"middle"
        halign:"left"
        color:[1, 1, 1, 1]
    DecryptButtonBox:
        size_hint:None, None
        size:"100dp", "40dp"
        radius:[30, 30, 30, 30]
        md_bg_color:[35/float(255), 35/float(255), 122/float(255), 1]
        MDLabel:
            id:decrypt_button_label
            text:"Decrypt file"
            color:[190/float(255), 190/float(255), 190/float(255), 1]
            text_size:self.size
            halign:"center"
            valign:"middle"
<EncryptedFilesScreen>:
    name:"encrypted_files_screen"
    id:encrypted_files
    MDBoxLayout:
        orientation:"vertical"
        md_bg_color:[0, 0, 0, 1]
        orientation:"vertical"
        MDBoxLayout:
            size_hint_y:None
            height:"80dp"
            MDIconButton:
                size_hint:None, None
                size:"40dp", "40dp"
                user_font_size:"30dp"
                theme_text_color:"Custom"
                text_color:[1, 1, 1, 1]
                pos_hint:{"center_x":.5, "center_y":.5}
                icon:"arrow-left-thick"
                on_release:root.goBackToHomeScreen()
            MDLabel:
                text:"Encrypted Flles"
                font_size:"23dp"
                text_size:self.size
                halign:"center"
                valign:"middle"
                color:[1, 1, 1, 1]
            BoxLayout:
                size_hint_x:None
                width:"40dp"
        MDBoxLayout:
            orientation:"vertical"
            radius:[40, 40, 0, 0]
            md_bg_color:[25/float(255), 25/float(255), 112/float(255), 1]
            orientation:"vertical"
            MDBoxLayout:
                FloatLayout:
                    pos:self.parent.pos
                    size:self.parent.size
                    MDBoxLayout:
                        pos:self.parent.pos
                        ScreenManager:
                            id:body_screen_manager
                            MDScreen:
                                name:"screen_one"
                                MDBoxLayout:
                                    orientation:"vertical"
                                    Widget:
                                    MDBoxLayout:
                                        size_hint_y:None
                                        height:"60dp"
                                        padding:"0dp", "5dp"
                                        Widget:
                                        LoadFilesButtonBox:
                                            root:encrypted_files
                                            size_hint_x:None
                                            width:"200dp"
                                            radius:[40, 40, 40, 40]
                                            md_bg_color:[0/float(255), 154/float(255), 255/float(255), 1]
                                            MDLabel:
                                                text:"Load encrypted files"
                                                text_size:self.size
                                                halign:"center"
                                                valign:"middle"
                                                color:[1, 1, 1, 1]
                                        Widget:
                                    Widget:
                            MDScreen:
                                name:"encrypted_videos"
                                MDBoxLayout:
                                    padding:10, 10, 10, 10
                                    orientation:"vertical"
                                    MDBoxLayout:
                                        size_hint_y:None
                                        height:"60dp"
                                        md_bg_color:[220/float(255), 220/float(255), 220/float(255), 1]
                                        radius:[40, 40, 40, 40]
                                        MDLabel:
                                            text:"Encrypted videos"
                                            font_size:"20dp"
                                            text_size:self.size
                                            halign:"center"
                                            valign:"middle"
                                    MDBoxLayout:
                                        ScrollView:
                                            size_hint:None, None
                                            size:self.parent.size
                                            pos:self.parent.pos
                                            EncryptedVideosList:
                                                id:videos_files_layout
                            MDScreen:
                                name:"encrypted_music"
                                MDBoxLayout:
                                    padding:10, 10, 10, 10
                                    orientation:"vertical"
                                    MDBoxLayout:
                                        size_hint_y:None
                                        height:"60dp"
                                        md_bg_color:[220/float(255), 220/float(255), 220/float(255), 1]
                                        radius:[40, 40, 40, 40]
                                        MDLabel:
                                            text:"Encrypted videos"
                                            font_size:"20dp"
                                            text_size:self.size
                                            halign:"center"
                                            valign:"middle"
                                    MDBoxLayout:
                                        ScrollView:
                                            size_hint:None, None
                                            size:self.parent.size
                                            pos:self.parent.pos
                                            EncryptedMusicList:
                                                id:music_files_layout
                            MDScreen:
                                name:"encrypted_photos"
                                MDBoxLayout:
                                    padding:10, 10, 10, 10
                                    orientation:"vertical"
                                    MDBoxLayout:
                                        size_hint_y:None
                                        height:"60dp"
                                        md_bg_color:[220/float(255), 220/float(255), 220/float(255), 1]
                                        radius:[40, 40, 40, 40]
                                        MDLabel:
                                            text:"Encrypted videos"
                                            font_size:"20dp"
                                            text_size:self.size
                                            halign:"center"
                                            valign:"middle"
                                    MDBoxLayout:
                                        ScrollView:
                                            size_hint:None, None
                                            size:self.parent.size
                                            pos:self.parent.pos
                                            EncryptedImagesList:
                                                id:images_files_layout
                    MDBoxLayout:
                        pos:self.parent.pos
                        ScreenManager:
                            id:load_space
                            MDScreen:
                                name:"empty_screen"
                            LoadScreen:
            MDBoxLayout:
                root:encrypted_files
                size_hint_y:None
                height:"60dp"
                padding:10
                spacing:10
                EncryptedVideosButton:
                    id:encrypted_videos
                    radius:[40, 40, 40, 40]
                    md_bg_color:[55/float(255), 55/float(255), 142/float(255), 1]
                    Widget:
                    MDIconButton:
                        size_hint:None, None
                        size:"50dp", "50dp"
                        icon:"play-circle"
                        user_font_size:"30dp"
                        pos_hint:{"center_x":.5, "center_y":.5}
                    Widget:
                EncryptedMusicButton:
                    id:encrypted_music
                    radius:[40, 40, 40, 40]
                    md_bg_color:[55/float(255), 55/float(255), 142/float(255), 1]
                    Widget:
                    MDIconButton:
                        size_hint:None, None
                        size:"50dp", "50dp"
                        icon:"music-circle"
                        user_font_size:"30dp"
                        pos_hint:{"center_x":.5, "center_y":.5}
                    Widget:
                EncryptedImagesButton:
                    id:encrypted_images
                    radius:[40, 40, 40, 40]
                    md_bg_color:[55/float(255), 55/float(255), 142/float(255), 1]
                    Widget:
                    MDIconButton:
                        size_hint:None, None
                        size:"50dp", "50dp"
                        icon:"image"
                        user_font_size:"30dp"
                        pos_hint:{"center_x":.5, "center_y":.5}
                    Widget:
""")
class LoadScreen(MDScreen):
    pass
class SecureMediaFileBar(MDBoxLayout):
    pass
class KeyIconBox(TouchBox):
    pass
class DecryptButtonBox(TouchBox):
    pass
class LoadFilesButtonBox(TouchBox):
    def respondToTouch(self):
        self.root.ids.load_space.transition = SlideTransition(direction = "left")
        self.root.ids.load_space.current = "load_screen"
        Clock.schedule_once(self.root.loadEncryptedFiles, 0)
class EncryptedVideosList(MDGridLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.files = []
        self.cols = 1
        self.padding = ("10dp", "10dp")
        self.size_hint_y = None
        self.bind(minimum_height = self.setter("height"))
        for i in self.files:
            bar = SecureMediaFileBar()
            self.add_widget(bar)
class EncryptedMusicList(MDGridLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.files = []
        self.cols = 1
        self.padding = ("10dp", "10dp")
        self.size_hint_y = None
        self.bind(minimum_height = self.setter("height"))
        for i in self.files:
            bar = SecureMediaFileBar()
            self.add_widget(bar)
class EncryptedImagesList(MDGridLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.files = []
        self.cols = 1
        self.padding = ("10dp", "10dp")
        self.size_hint_y = None
        self.bind(minimum_height = self.setter("height"))
        for i in self.files:
            bar = SecureMediaFileBar()
            self.add_widget(bar)
class EncryptedVideosButton(TouchBox):
    def respondToTouch(self):
        #move to encrypted videos screen
        if self.parent.root.files_loaded:
            self.parent.root.turnAllButtonsOf()
            self.parent.root.ids.encrypted_videos.md_bg_color = [0, 154/float(255), 255/float(255), 1]
            self.parent.root.transition = NoTransition()
            self.parent.root.current = "encrypted_videos"
class EncryptedMusicButton(TouchBox):
    #move to encrypted music screen
    def respondToTouch(self):
        if self.parent.root.files_loaded:
            self.parent.root.turnAllButtonsOf()
            self.parent.root.ids.encrypted_music.md_bg_color = [0, 154/float(255), 255/float(255), 1]
            self.parent.root.transition = NoTransition()
            self.parent.root.current = "encrypted_music"
class EncryptedImagesButton(TouchBox):
    def respondToTouch(self):
        #move to encrypted photos screen
        if self.parent.root.files_loaded:
            self.parent.root.turnAllButtonsOf()
            self.parent.root.ids.encrypted_images.md_bg_color = [0, 154/float(255), 255/float(255), 1]
            self.parent.root.transition = NoTransition()
            self.parent.root.current = "encrypted_photos"
class EncryptedFilesScreen(MDScreen):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.files_loaded = False
    def turnAllButtonsOf(self):
        #change buttons color to white
        self.ids.encrypted_videos.md_bg_color = [55/float(255), 55/float(255), 142/float(255), 1]
        self.ids.encrypted_music.md_bg_color = [55/float(255), 55/float(255), 142/float(255), 1]
        self.ids.encrypted_images.md_bg_color = [55/float(255), 55/float(255), 142/float(255), 1]
    def getEncryptedImages(self, dirname, filename):
        #load encrypted image files
        if filename.endswith(".py"):
            path = os.path.join(dirname, filename)
            self.ids.images_files_layout.files.append(path)
    def getEncryptedAudios(self, dirname, filename):
        #load encrypted audio files 
        if filename.endswith(".py"):
            path = os.path.join(dirname, filename)
            self.ids.music_files_layout.files.append(path)
    def getEncryptedVideos(self, dirname, filename):
        #load encrypted video files
        if filename.endswith(".mp4"):
            path = os.path.join(dirname, filename)
            self.ids.videos_files_layout.files.append(path)
    def loadEncryptedFiles(self, seconds):
        #load all encrypted files collectively
        for (dirname, dirsher, fileshere) in os.walk("/storage"):
            for filename in fileshere:
                self.getEncryptedVideos(dirname, filename)
                self.getEncryptedAudios(dirname, filename)
                self.getEncryptedImages(dirname, filename)
        self.ids.load_space.transition = SlideTransition(direction = "left")
        self.ids.load_space.current = "empty_screen"
        self.ids.body_screen_manager.transition = SlideTransition(direction = "left")
        self.ids.body_screen_manager.current = "encrypted_videos"
    def goBackToHomeScreen(self):
        #reroute to home screen
        self.parent.transition = SlideTransition(direction = "right")
        self.parent.current = "home_screen"