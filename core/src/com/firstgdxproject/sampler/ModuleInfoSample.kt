package com.firstgdxproject.sampler

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.firstgdxproject.sampler.utils.logger

class ModuleInfoSample : ApplicationAdapter() {

    companion object {
        @JvmStatic
        private val log = logger<ModuleInfoSample>()
    }


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("app: ${Gdx.app}")
        log.debug("audio: ${Gdx.audio}")
        log.debug("input: ${Gdx.input}")
        log.debug("files: ${Gdx.files}")
        log.debug("net: ${Gdx.net}")
        log.debug("graphics: ${Gdx.graphics}")

    }
}