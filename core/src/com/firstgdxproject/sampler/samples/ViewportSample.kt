package com.firstgdxproject.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ArrayMap
import com.badlogic.gdx.utils.viewport.*
import com.firstgdxproject.sampler.common.SampleBase
import com.firstgdxproject.sampler.utils.clearScreen
import com.firstgdxproject.sampler.utils.logger
import com.firstgdxproject.sampler.utils.toInternalFile

class ViewportSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<ViewportSample>()

        private const val WORLD_WIDTH = 1080f
        private const val WORLD_HEIGHT = 720f
        private const val CAMERA_SPEED = 2.0f
        private const val CAMERA_ZOOM_SPEED = 2.0f
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var currentViewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture
    private lateinit var font: BitmapFont

    private val viewports = ArrayMap<String, Viewport>()
    private var currentViewportIndex = -1
    private var currentViewportName = ""


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("create")

        camera = OrthographicCamera()

        batch = SpriteBatch()
        texture = Texture("raw/level-bg.png".toInternalFile())
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile())

        createViewports()
        selectNextViewport()
        Gdx.input.inputProcessor = this
    }

    private fun createViewports() {
        viewports.put(StretchViewport::class.java.simpleName, StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera))
        viewports.put(FitViewport::class.java.simpleName, FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera))
        viewports.put(FillViewport::class.java.simpleName, FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera))
        viewports.put(ScreenViewport::class.java.simpleName, ScreenViewport(camera))
        viewports.put(ExtendViewport::class.java.simpleName, ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera))
    }

    private fun selectNextViewport() {
        currentViewportIndex = (currentViewportIndex + 1) % viewports.size

        currentViewport = viewports.getValueAt(currentViewportIndex)
//        currentViewport.update(Gdx.graphics.width, Gdx.graphics.height)
        resize(Gdx.graphics.width, Gdx.graphics.height)
        currentViewportName = viewports.getKeyAt(currentViewportIndex)
        log.debug("selected viewport: $currentViewportName")
    }

    override fun resize(width: Int, height: Int) {
        currentViewport.update(width, height, true)
    }

    override fun render() {
        clearScreen()

        batch.projectionMatrix = camera.combined

        batch.begin()
        draw()
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
        font.dispose()
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        selectNextViewport()
        return false
    }

    private fun draw() {

        batch.draw(texture,0f,0f, WORLD_WIDTH, WORLD_HEIGHT)
        font.draw(batch, currentViewportName, 50f, 100f)
    }
}