package com.adrianwitaszak.rntest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform