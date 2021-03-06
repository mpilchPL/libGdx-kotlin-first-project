package com.firstgdxproject.sampler.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

@JvmOverloads
fun clearScreen(color: Color = Color.BLACK) = clearScreen(color.r, color.g, color.b, color.a)


fun clearScreen(red: Float, green: Float, blue: Float, alpha: Float) {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
}

inline fun Batch.use (function: () -> Unit) {
    begin()
    function()
    end()
}

inline fun ShapeRenderer.use (function: () -> Unit) {
    begin(ShapeRenderer.ShapeType.Line)
    function()
    end()
}