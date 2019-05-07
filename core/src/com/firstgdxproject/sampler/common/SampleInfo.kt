package com.firstgdxproject.sampler.common

class SampleInfo(val clazz: Class<out SampleBase>) {
    val name: String = clazz.simpleName
}

inline fun <reified T: SampleBase> sampleInfo() : SampleInfo = SampleInfo(T::class.java)
