package com.firstgdxproject.sampler.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.firstgdxproject.sampler.common.SampleFactory
import com.firstgdxproject.sampler.common.SampleInfos
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class GdxSampleLauncher() : JFrame() {
    private val windowWidth = 1280
    private val windowHeight = 720
    private val windowSize = Dimension(windowWidth, windowHeight)
    private val cellWidth = 200
    private val canvasWidth = windowWidth - cellWidth

    private var lwjglAWTCanvas: LwjglAWTCanvas? = null
    private lateinit var sampleList: JList<String>

    init {
        title = GdxSampleLauncher::class.java.simpleName
        minimumSize = windowSize
        size = windowSize
        //isResizable = false
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        createControlPanel()

        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                lwjglAWTCanvas?.stop()
            }
        })


        pack()
        isVisible = true
    }

    private fun launchSample(name: String?) {
        lwjglAWTCanvas?.stop()

        if (lwjglAWTCanvas != null) {
            contentPane.remove(lwjglAWTCanvas?.canvas)
        }

        if (!name.isNullOrBlank()){
            val sample = SampleFactory.newSample(name!!)
            lwjglAWTCanvas = LwjglAWTCanvas(sample)
            lwjglAWTCanvas?.canvas?.size = Dimension(canvasWidth, windowHeight)
            contentPane.add(lwjglAWTCanvas?.canvas, BorderLayout.CENTER)
        }


        pack()
    }

    private fun createControlPanel() {
        val controlPanel = JPanel(GridBagLayout())
        val c = GridBagConstraints()
        c.apply {
            gridx = 0
            gridy = 0
            fill = GridBagConstraints.VERTICAL
            weighty = 1.0
        }
        sampleList = JList(SampleInfos.getSampleNames())
        sampleList.fixedCellWidth = cellWidth
        sampleList.selectionMode = ListSelectionModel.SINGLE_SELECTION

        sampleList.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                if (e?.clickCount == 2) {
                    launchSelectedSample()
                }
            }

        })

        val scrollPane = JScrollPane(sampleList)
        controlPanel.add(scrollPane, c)
        contentPane.add(controlPanel, BorderLayout.WEST)

        c.apply {
            gridx = 0
            gridy = 1
            fill = GridBagConstraints.HORIZONTAL
            weighty = 0.0
        }

        val launchButton = JButton("Launch Sampler")

        launchButton.addActionListener { launchSelectedSample() }
        controlPanel.add(launchButton, c)

    }

    private fun launchSelectedSample() {
        val sampleName : String? = sampleList.selectedValue
        if (sampleName.isNullOrBlank()){
            println("sample name is null or blank")
            return
        }
        launchSample(sampleName)

    }
}


fun main(args: Array<String>) {
    SwingUtilities.invokeLater { GdxSampleLauncher() }
}