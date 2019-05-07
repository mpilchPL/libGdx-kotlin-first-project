package com.firstgdxproject.sampler.common

import com.firstgdxproject.sampler.samples.*

object SampleInfos {

    val allSamples = arrayListOf(
            SampleInfo(ApplicationListenerSample::class.java),
            SampleInfo(GdxGeneratedSample::class.java),
            SampleInfo(InputListenerSample::class.java),
            SampleInfo(InputPollingSample::class.java),
            SampleInfo(ModuleInfoSample::class.java),
            SampleInfo(MultiplexerSample::class.java),
            SampleInfo(ReflectionSample::class.java),
            SampleInfo(OrthographicCameraSample::class.java),
            SampleInfo(ViewportSample::class.java),
            SampleInfo(SpriteBatchSample::class.java),
            SampleInfo(ShapeRendererSample::class.java),
            SampleInfo(PoolingSample::class.java),
            sampleInfo<AssetManagerSample>(),
            sampleInfo<TextureAtlasSample>(),
            sampleInfo<ImageTextButtonSample>()

    )

    /*fun getSampleNames(): Array<String> {
        val names = arrayListOf<String>()
        allSamples.forEach { names.add(it.name) }
        names.sort()
        return names.toTypedArray()
    }*/

    fun getSampleNames(): Array<String> = allSamples.associateBy { it.name }.keys.toList().sorted().toTypedArray()

    fun find(name: String) = allSamples.find { it.name == name }

}