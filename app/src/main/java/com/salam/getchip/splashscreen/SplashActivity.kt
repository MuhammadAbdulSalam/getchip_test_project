package com.salam.getchip.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.salam.getchip.MainActivity
import com.salam.getchip.R

/**
 * @author: Muhammad Abdul Salam
 * Shiba Inu to start app with
 * For Good Luck
 */

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val ivLogo: ImageView = findViewById<View>(R.id.iv_splash_logo) as ImageView
        val ivText: ImageView = findViewById<View>(R.id.iv_text) as ImageView

        val animZoomIn: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_in_animation)
        val animFadeIn: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in_animation)

        ivLogo.startAnimation(animZoomIn)
        ivText.startAnimation(animFadeIn)

        animFadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
    }
}