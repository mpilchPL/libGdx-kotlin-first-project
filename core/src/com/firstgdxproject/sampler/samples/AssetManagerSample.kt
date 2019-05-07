package com.firstgdxproject.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Logger
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.firstgdxproject.sampler.common.SampleBase
import com.firstgdxproject.sampler.utils.clearScreen
import com.firstgdxproject.sampler.utils.logger
import com.firstgdxproject.sampler.utils.use

class AssetManagerSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<AssetManagerSample>()

        private const val BACKGROUND_BLUE = "raw/background-blue.png"
        private const val CIRCLE_GREEN = "raw/circle-green.png"
        private const val CIRCLE_RED = "raw/circle-red.png"

        private const val FONT = "fonts/oswald-32.fnt"
    }

    private lateinit var assetManager: AssetManager
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch

    private lateinit var backgroundBlue: Texture
    private lateinit var circleRed: Texture
    private lateinit var circleGreen: Texture
    private lateinit var font: BitmapFont


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        assetManager = AssetManager()
        assetManager.logger.level = Logger.DEBUG

        camera = OrthographicCamera()
        viewport = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()

        assetManager.load(BACKGROUND_BLUE, Texture::class.java)
        assetManager.load(CIRCLE_GREEN, Texture::class.java)
        assetManager.load(CIRCLE_RED, Texture::class.java)
        assetManager.load(FONT, BitmapFont::class.java)

        assetManager.finishLoading()

        backgroundBlue = assetManager[BACKGROUND_BLUE]
        circleGreen = assetManager[CIRCLE_GREEN]
        circleRed = assetManager[CIRCLE_RED]
        font = assetManager[FONT]
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        batch.dispose()
        assetManager.dispose()
    }

    override fun render() {
        clearScreen()
        viewport.apply()
        batch.projectionMatrix = camera.combined

        batch.use {
            batch.draw(backgroundBlue, 0f,0f)
            batch.draw(circleGreen, 50f, 50f)
            batch.draw(circleRed, 200f, 200f)

            font.draw(batch, "asset manager sample", 500f, 50f)
        }
    }
}