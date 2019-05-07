package com.firstgdxproject.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Logger
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.firstgdxproject.sampler.common.SampleBase
import com.firstgdxproject.sampler.utils.clearScreen
import com.firstgdxproject.sampler.utils.logger
import com.firstgdxproject.sampler.utils.use

class TextureAtlasSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<TextureAtlasSample>()

        private const val BACKGROUND_BLUE = "background-blue"
        private const val CIRCLE_GREEN = "circle-green"
        private const val CIRCLE_RED = "circle-red"

        private const val FONT = "fonts/oswald-32.fnt"
        private const val ATLAS = "images/atlasSample.atlas"
    }

    private lateinit var assetManager: AssetManager
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch

    private lateinit var backgroundBlue: TextureRegion
    private lateinit var circleRed: TextureRegion
    private lateinit var circleGreen: TextureRegion
    private lateinit var font: BitmapFont


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        assetManager = AssetManager()
        assetManager.logger.level = Logger.DEBUG

        camera = OrthographicCamera()
        viewport = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()

        assetManager.load(ATLAS, TextureAtlas::class.java)
        assetManager.load(FONT, BitmapFont::class.java)

        assetManager.finishLoading()


        val atlas: TextureAtlas = assetManager[ATLAS]
        backgroundBlue = atlas.findRegion(BACKGROUND_BLUE)
        circleGreen = atlas.findRegion(CIRCLE_GREEN)
        circleRed = atlas.findRegion(CIRCLE_RED)
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

            font.draw(batch, "Texture Atlas sample", 500f, 50f)
        }
    }


}