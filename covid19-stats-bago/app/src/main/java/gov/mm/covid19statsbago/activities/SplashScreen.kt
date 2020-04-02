package gov.mm.covid19statsbago.activities;

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import gov.mm.covid19statsbago.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlin.random.Random


/**
 * Created by mk on 4/1/2020.
 */

class SplashScreen : AppCompatActivity() {

    private val upToDown: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.up_to_down
        )
    }
    private val downToUp: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.down_to_up
        )
    }
    private val rotate: RotateAnimation by lazy {
        RotateAnimation(
            0f,
            720f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 2000
            interpolator = LinearInterpolator()
        }
    }

    private val infoList by lazy {
        arrayOf(
            getString(R.string.splash_label_one),
            getString(R.string.splash_label_two),
            getString(R.string.splash_label_three),
            getString(R.string.splash_label_four)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashImage.animation = downToUp
        splashImage.startAnimation(rotate)

        splashText.apply {
            animation = upToDown
            text = infoList[Random.nextInt(3) + 1]
        }

        Handler().postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },
            3000
        )
    }
}

