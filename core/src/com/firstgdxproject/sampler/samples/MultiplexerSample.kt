package com.firstgdxproject.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.InputMultiplexer
import com.firstgdxproject.sampler.common.SampleBase
import com.firstgdxproject.sampler.utils.logger

class MultiplexerSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<MultiplexerSample>()
    }


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG


        val firstProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                log.debug("first proc - keyDown, keycode: $keycode")
                return true
            }

            override fun keyUp(keycode: Int): Boolean {
                log.debug("first proc - keyUp, keycode: $keycode")
                return false
            }
        }


        val secondProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                log.debug("second proc - keyDown, keycode: $keycode")
                return true
            }

            override fun keyUp(keycode: Int): Boolean {
                log.debug("second proc - keyUp, keycode: $keycode")
                return false
            }
        }


        Gdx.input.inputProcessor = InputMultiplexer(firstProcessor, secondProcessor)
    }

}