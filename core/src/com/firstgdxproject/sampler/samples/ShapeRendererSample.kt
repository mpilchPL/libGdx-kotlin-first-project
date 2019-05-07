package com.firstgdxproject.sampler.samples

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.firstgdxproject.sampler.common.SampleBase
import com.firstgdxproject.sampler.utils.clearScreen
import com.firstgdxproject.sampler.utils.drawGrid
import com.firstgdxproject.sampler.utils.logger

class ShapeRendererSample : SampleBase() {
    companion object {
        @JvmStatic
        private val log = logger<ShapeRendererSample>()
        private val WORLD_WIDTH = 40f
        private val WORLD_HEIGHT = 20f
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var renderer: ShapeRenderer

    private var drawGrid = true
    private var drawCircles = true
    private var drawRectangles = true
    private var drawPoints = true


    override fun create() {
        camera = OrthographicCamera()
        viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
        renderer = ShapeRenderer()

        Gdx.input.inputProcessor = this
    }

    override fun render() {
        clearScreen()

        renderer.projectionMatrix = camera.combined

        if (drawGrid)
            viewport.drawGrid(renderer, 1)

        if(drawCircles)
            drawCircles()

        if(drawPoints)
            drawPoint()

        if(drawRectangles)
            drawRectangle()

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width,height)
    }

    override fun keyDown(keycode: Int): Boolean {
        when(keycode){
            Input.Keys.G -> drawGrid = !drawGrid
            Input.Keys.C -> drawCircles = !drawCircles
            Input.Keys.R -> drawRectangles = !drawRectangles
            Input.Keys.P -> drawPoints = !drawPoints
        }
        return true
    }

    override fun dispose() {
        renderer.dispose()
    }


    private fun drawGrid() {
        renderer.begin(ShapeRenderer.ShapeType.Line)

        renderer.color = Color.WHITE

        val worldWidth = WORLD_WIDTH.toInt()
        val worldHeight = WORLD_HEIGHT.toInt()

        for (y in -worldHeight until worldHeight) {
            renderer.line(-worldWidth.toFloat(), y.toFloat(), worldWidth.toFloat(), y.toFloat())
        }

        for (x in -worldWidth until worldHeight) {
            renderer.line(x.toFloat(), -worldHeight.toFloat(), x.toFloat(), worldHeight.toFloat())
        }

        renderer.color = Color.RED
        renderer.line(-worldWidth.toFloat(), 0f, worldWidth.toFloat(), 0f)
        renderer.line(0f, -worldHeight.toFloat(), 0f, worldHeight.toFloat())

        renderer.end()
    }

    private fun drawCircles() {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.color = Color.GREEN

        renderer.circle(2f,2f,2f, 30)
        renderer.circle(-5f,-5f, 1f)
        renderer.end()
    }

    private fun drawRectangle() {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.color = Color.WHITE
        renderer.rect(4f,-4f, 2f,2f)

        renderer.end()
    }

    private fun drawPoint() {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.color = Color.YELLOW
        renderer.point(15.5f,8.5f, 0f)

        renderer.end()

        renderer.begin(ShapeRenderer.ShapeType.Line)
        renderer.x(5f,-3f, 1f)
        renderer.end()
    }
}