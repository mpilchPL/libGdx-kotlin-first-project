package com.firstgdxproject.sampler.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.firstgdxproject.sampler.ModuleInfoSample

fun main(args: Array<String>) {
    LwjglApplication(ModuleInfoSample(), LwjglApplicationConfiguration())
}