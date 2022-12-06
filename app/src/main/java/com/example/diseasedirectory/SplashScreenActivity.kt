package com.example.diseasedirectory


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet.Motion
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        motionLayout.addTransitionListener(object : MotionLayout.TransitionListener{
             override fun onTransitionStarted(
                 motionLayout: MotionLayout?,
                 startId: Int,
                 endId: Int
             ) {
             }

             override fun onTransitionChange(
                 motionLayout: MotionLayout?,
                 startId: Int,
                 endId: Int,
                 progress: Float
             ) {
             }

             override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                 startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                 finish()
             }

             override fun onTransitionTrigger(
                 motionLayout: MotionLayout?,
                 triggerId: Int,
                 positive: Boolean,
                 progress: Float
             ) {
             }
        })
    }
}