package com.adrianwitaszak.rntest

import de.voize.reaktnativetoolkit.annotation.ReactNativeMethod
import de.voize.reaktnativetoolkit.annotation.ReactNativeModule

@ReactNativeModule("Greeting")
class Greeting {
    private val platform: Platform = getPlatform()

    @ReactNativeMethod
    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
