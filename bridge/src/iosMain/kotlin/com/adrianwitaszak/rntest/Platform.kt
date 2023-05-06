package com.adrianwitaszak.rntest

import platform.UIKit.UIDevice
//import cocoapods.react-native.*

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
