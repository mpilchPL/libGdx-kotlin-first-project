package com.firstgdxproject.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Logger
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.firstgdxproject.sampler.common.SampleBase
import com.firstgdxproject.sampler.utils.clearScreen
import com.firstgdxproject.sampler.utils.logger
import com.firstgdxproject.sampler.utils.toInternalFile
import com.firstgdxproject.sampler.utils.use

class ImageTextButtonSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<ImageTextButtonSample>()

        private const val BUTTON = "button"
        private const val BUTTON_SELECTED = "buttonSelected"

        private const val FONT = "fonts/oswald-32.fnt"
        private const val ATLAS = "images/atlasSample.atlas"
        private const val SKIN = "ui/myskin.json"
        private const val SKIN_ATLAS = "ui/myskin.atlas"
        private const val WORLD_WIDTH = 1080f
        private const val WORLD_HEIGHT = 720f
    }

    private lateinit var assetManager: AssetManager
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var stage: Stage

    private lateinit var mmAtlas: TextureAtlas
    private lateinit var buttonOff: TextureRegion
    private lateinit var font: BitmapFont
    private lateinit var skin: Skin

    private lateinit var button: ImageTextButton
    private lateinit var style: ImageTextButton.ImageTextButtonStyle


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        assetManager = AssetManager()
        assetManager.logger.level = Logger.DEBUG

        camera = OrthographicCamera()
        viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
        batch = SpriteBatch()
        stage = Stage(viewport)
        Gdx.input.inputProcessor = stage


        assetManager.load(ATLAS, TextureAtlas::class.java)
        assetManager.load(FONT, BitmapFont::class.java)
        assetManager.load(SKIN_ATLAS, TextureAtlas::class.java)
        assetManager.finishLoading()


        font = assetManager[FONT]
        mmAtlas = assetManager[SKIN_ATLAS]

        // =========== buttons  & skin ===========

        skin = Skin(SKIN.toInternalFile())
        skin.addRegions(mmAtlas)
        style = ImageTextButton.ImageTextButtonStyle(skin.get("default", TextButton.TextButtonStyle::class.java))

        button = ImageTextButton("asdasd", style)
        button.setPosition(200f,200f)

        button.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                println("aasas")
            }
        })
        stage.addActor(button)


    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        batch.dispose()
        assetManager.dispose()
        skin.dispose()
    }

    override fun render() {
        clearScreen()
        viewport.apply()
        batch.projectionMatrix = camera.combined

        stage.act()
        stage.draw()
        batch.use {

        }
    }


}