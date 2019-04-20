package com.firstgdxproject.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.firstgdxproject.sampler.common.SampleBase
import com.firstgdxproject.sampler.utils.logger

class ApplicationListenerSample : SampleBase() {

    companion object {
        @JvmStatic
        //private val log = Logger(ApplicationListenerSample::class.java.simpleName, Logger.DEBUG)
        //private val log = logger(ApplicationListenerSample::class.java)
        private val log = logger<ApplicationListenerSample>()
    }

    private var renderInterrupted = true


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("create()")
    }

    override fun resize(width: Int, height: Int) {
        log.debug("resize()")
    }

    override fun render() {
        if(renderInterrupted){
            log.debug("render()")
            renderInterrupted = false
        }
    }

    override fun pause() {
        log.debug("pause()")
        renderInterrupted = true
    }

    override fun resume() {
        log.debug("resume()")
        renderInterrupted = true
    }

    override fun dispose() {
        log.debug("dispose()")
    }
}