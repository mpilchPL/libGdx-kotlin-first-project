package com.firstgdxproject.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Pool
import com.firstgdxproject.sampler.common.SampleBase
import com.firstgdxproject.sampler.utils.GdxArray
import com.firstgdxproject.sampler.utils.logger


class PoolingSample : SampleBase() {


    companion object {
        @JvmStatic
        private val log = logger<PoolingSample>()

        const val SPAWN_INTERVAL = 1f
        const val ALIVE_INTERVAL = 3f
    }

    private val bullets = GdxArray<Bullet>()
    private var timer = 0f

    private val bulletPool = object : Pool<Bullet>(10) {
        override fun newObject(): Bullet = Bullet()
        override fun free(bullet: Bullet?) {
            log.debug("before free bullets: $bullet, free: $free")
            super.free(bullet)
            log.debug("after free bullets: $bullet, free: $free")
        }

        override fun obtain(): Bullet {
            log.debug("before obtain free: $free")
            val ret = super.obtain()
            log.debug("after obtain free: $free")
            return ret
        }

        override fun reset(bullet: Bullet?) {
            log.debug("reseting object: $bullet")
            super.reset(bullet)
        }
    }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
    }

    override fun render() {
        val delta = Gdx.graphics.deltaTime
        timer += delta

        if (timer > SPAWN_INTERVAL) {
            timer = 0f
            val bullet = bulletPool.obtain()
            bullets.add(bullet)
            log.debug("after obtain free: ${bullets.size}")
        }

        for (bullet in bullets) {
            bullet.update(delta)
            if (!bullet.alive) {
                bullets.removeValue(bullet, true)
                bulletPool.free(bullet)
                log.debug("alive bullets: ${bullets.size}")
            }
        }
    }

    override fun dispose() {
        bulletPool.freeAll(bullets)
        bulletPool.clear()
        bullets.clear()
    }



}



class Bullet : Pool.Poolable {
    var alive = true
    var timer = 0f

    override fun reset() {
        alive = true
        timer = 0f
    }

    fun update(delta: Float) {
        timer += delta

        if (alive && timer > PoolingSample.ALIVE_INTERVAL) {
            alive = false
        }
    }
}