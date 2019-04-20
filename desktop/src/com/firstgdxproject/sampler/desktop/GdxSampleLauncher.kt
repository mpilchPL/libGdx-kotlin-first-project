package com.firstgdxproject.sampler.desktop

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.badlogic.gdx.utils.reflect.ClassReflection
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

class GdxSampleLauncher(): JFrame() {
    private val windowSize = Dimension(1280,720)
    private var lwjglAWTCanvas: LwjglAWTCanvas? = null

    init {
        title = GdxSampleLauncher::class.java.simpleName
        minimumSize = windowSize
        size = windowSize
        isResizable = false
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        launchSample("com.firstgdxproject.sampler.InputPollingSample")

        pack()
        isVisible = true
    }

    private fun launchSample(name: String) {
        lwjglAWTCanvas?.stop()

        if (lwjglAWTCanvas != null) {
            contentPane.remove(lwjglAWTCanvas?.canvas)
        }
        val sampleClass = ClassReflection.forName(name)

        val sample = ClassReflection.newInstance(sampleClass) as ApplicationListener

        lwjglAWTCanvas = LwjglAWTCanvas(sample)
        lwjglAWTCanvas?.canvas?.size = windowSize
        contentPane.add(lwjglAWTCanvas?.canvas, BorderLayout.CENTER)
    }
}


fun main(args: Array<String>) {
    SwingUtilities.invokeLater { GdxSampleLauncher() }
}