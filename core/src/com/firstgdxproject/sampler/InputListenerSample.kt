package com.firstgdxproject.sampler

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.firstgdxproject.sampler.utils.GdxArray
import com.firstgdxproject.sampler.utils.clearScreen
import com.firstgdxproject.sampler.utils.logger
import com.firstgdxproject.sampler.utils.toInternalFile

//import com.badlogic.gdx.utils.Array as GdxArray

class InputListenerSample : ApplicationAdapter(), InputProcessor {

    companion object {
        @JvmStatic
        private val log = logger<InputListenerSample>()
    }
    lateinit var camera: OrthographicCamera
    lateinit var viewport: Viewport
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    private val maxMessageCount = 15
    private val messages = GdxArray<String>()


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("create")
        Gdx.input.inputProcessor = this

        camera = OrthographicCamera()
        viewport = FitViewport(1366f,768f, camera)
        batch = SpriteBatch()
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile())

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width,height,true)
    }

    override fun render() {
        clearScreen()

        batch.projectionMatrix = camera.combined

        batch.begin()
        font.setColor(Color.WHITE)
        draw()
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }

    private fun addMessage(message: String) {
        messages.add(message)
        if (messages.size > maxMessageCount)
            messages.removeIndex(0)

    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val message = "touchUP, screenX: $screenX, screenY: $screenY"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        val message = "mouseMoved, screenX: $screenX, screenY: $screenY"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun keyTyped(character: Char): Boolean {
        val message = "keyTyped, char: $character"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun scrolled(amount: Int): Boolean {
        val message = "Scrolled, amount: $amount"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        val message = "keyUp, keycode: $keycode"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        val message = "touchDragged, screenX: $screenX, screenY: $screenY, pointer: $pointer"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun keyDown(keycode: Int): Boolean {
        val message = "keyDown, keycode: $keycode"
        log.debug(message)
        addMessage(message)
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val message = "touchDown, screenX: $screenX, screenY: $screenY,  pointer: $pointer, button: $button"
        log.debug(message)
        addMessage(message)
        return true
    }

    private fun draw() {
        for(i in 0 until messages.size){
            font.draw(batch, messages[i], 20f, 720f - 40f*(i+1))
        }

    }



}