package com.firstgdxproject.sampler

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.firstgdxproject.sampler.utils.logger

class InputPollingSample : ApplicationAdapter() {

    companion object {
        @JvmStatic
        private val log = logger<InputPollingSample>()
    }
    lateinit var camera: OrthographicCamera
    lateinit var viewport: Viewport
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("create")

        camera = OrthographicCamera()
        viewport = FitViewport(1366f,768f, camera)
        batch = SpriteBatch()
        font = BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"))

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width,height,true)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

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

    private fun draw() {
        val mouseX = Gdx.input.x
        val mouseY = Gdx.input.y

        //mouse
        val leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT)
        val rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT)
        val leftPressedString = if (leftPressed) "Left pressed" else "Left not pressed"
        val rightPressedString = if (rightPressed) "Right pressed" else "Right not pressed"

        font.draw(batch, "MouseX = $mouseX, MouseY = $mouseY", 20f, 768f - 20f)
        font.draw(batch, leftPressedString, 20f,768f - 60f)
        font.draw(batch, rightPressedString, 20f,768f - 100f)

        //keys
        var keyColors = hashMapOf("A" to Color.WHITE, "W" to Color.WHITE, "S" to Color.WHITE, "D" to Color.WHITE)


        val aPressed = Gdx.input.isKeyPressed(Input.Keys.A)
        val wPressed = Gdx.input.isKeyPressed(Input.Keys.W)
        val sPressed = Gdx.input.isKeyPressed(Input.Keys.S)
        val dPressed = Gdx.input.isKeyPressed(Input.Keys.D)
        if (aPressed) keyColors["A"] = Color.RED
        if (wPressed) keyColors["W"] = Color.RED
        if (dPressed) keyColors["D"] = Color.RED
        if (sPressed) keyColors["S"] = Color.RED
        var i = 1
        for (keys in keyColors.keys) {
            font.setColor(keyColors[keys])
            font.draw(batch, keys, 460f + 40*i,768f - 140f)
            i++
        }







    }
}