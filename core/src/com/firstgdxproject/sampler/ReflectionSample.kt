package com.firstgdxproject.sampler

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.reflect.ClassReflection
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.firstgdxproject.sampler.utils.clearScreen
import com.firstgdxproject.sampler.utils.logger
import com.firstgdxproject.sampler.utils.toInternalFile

class ReflectionSample : ApplicationAdapter() {

    companion object {
        @JvmStatic
        private val log = logger<ReflectionSample>()
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
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile())

        debugReflection<ReflectionSample>()
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

    private fun draw() {
        val mouseX = Gdx.input.x
        val mouseY = Gdx.input.y

        //mouse
        val leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT)
        val rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT)
        val leftPressedString = if (leftPressed) "Left pressed" else "Left not pressed"
        val rightPressedString = if (rightPressed) "Right pressed" else "Right not pressed"

        font.draw(batch, "MouseX = $mouseX, MouseY = $mouseY", 20f, 768f - 20f)
        font.draw(batch, leftPressedString, 20f, 768f - 60f)
        font.draw(batch, rightPressedString, 20f, 768f - 100f)


    }

    private inline fun <reified T: Any> debugReflection(){
        val fields = ClassReflection.getDeclaredFields(T::class.java)
        val methods = ClassReflection.getDeclaredMethods(T::class.java)
        log.debug("reflecting class: ${T::class.java.simpleName}")
        log.debug("field count: ${fields.size}")

        for (field in fields){
            log.debug("name: ${field.name}, type: ${field.type}")
        }

        methods.forEach {
            log.debug("name: ${it.name}, parameterCount: ${it.parameterTypes.size}")
        }
    }
}