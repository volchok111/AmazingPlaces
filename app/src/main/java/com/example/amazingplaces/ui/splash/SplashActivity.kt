package com.example.amazingplaces.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.amazingplaces.MainActivity
import com.example.amazingplaces.R

class SplashActivity : AppCompatActivity(), Animator.AnimatorListener {


    /**
     * A function that is called when the activity is created.
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state. If
     * the activity has never existed before, the value of the Bundle object is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        (findViewById<LottieAnimationView>(R.id.travelAnimation)).addAnimatorListener(this)
    }

    /**
     * It takes the user back to the main activity.
     */
    private fun goHome(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * A function that is called when the animation starts.
     *
     * @param animation The animation that is being started.
     */
    override fun onAnimationStart(animation: Animator?) {

    }

    /**
     * A function that is called when the animation ends.
     *
     * @param animation The animation that ended.
     */
    override fun onAnimationEnd(animation: Animator?) {
        goHome()
    }

    /**
     * `onAnimationCancel` is called when the animation is cancelled
     *
     * @param animation The animation that was started or ended.
     */
    override fun onAnimationCancel(animation: Animator?) {

    }

    /**
     * This function is called when the animation is repeated
     *
     * @param animation The animation which was repeated.
     */
    override fun onAnimationRepeat(animation: Animator?) {

    }
}