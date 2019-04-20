package com.firstgdxproject.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.firstgdxproject.sampler.common.SampleBase
import com.firstgdxproject.sampler.utils.clearScreen
import com.firstgdxproject.sampler.utils.logger
import com.firstgdxproject.sampler.utils.toInternalFile
import com.firstgdxproject.sampler.utils.use

class SpriteBatchSample : SampleBase() {

    companion object {
        @JvmStatic
        private val log = logger<SpriteBatchSample>()
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        log.debug("create")

        camera = OrthographicCamera()
        viewport = FitViewport(10.8f, 7.2f, camera)
        batch = SpriteBatch()
        texture = Texture("raw/character.png".toInternalFile())

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        clearScreen()

        batch.projectionMatrix = camera.combined

        /*batch.begin()
        draw()
        batch.end()*/
        batch.use { draw() }
        batch.use { drawSec() }

    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }

    private fun drawSec() {
        val width = 1f
        val height = 1f
        batch.draw(texture,
                1f, 1f,
                width / 2f, height / 2f,
                width, height,
                1f, 1f,
                0f,
                0, 0,
                texture.width, texture.height,
                false, false)
    }

    private fun draw() {

        val width = 1f
        val height = 1f

        val oldColor = Color(batch.color)
        batch.color = Color.GREEN

        batch.draw(texture, 4f, 2f, width / 2f, height / 2f,
                width, height,
                2f, 2f,
                0f, 0, 0,
                texture.width, texture.height,
                false, false)

        batch.color = oldColor

        batch.draw(texture, 8f, 2f, width / 2f, height / 2f,
                width, height,
                1f, 1f,
                0f, 0, 0,
                texture.width, texture.height,
                false, false)
    }
}