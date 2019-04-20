package com.firstgdxproject.sampler.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.firstgdxproject.sampler.InputListenerSample

fun main(args: Array<String>) {
    var config = LwjglApplicationConfiguration()
    config.width = 1366
    config.height =768
    LwjglApplication(InputListenerSample(), config)
}