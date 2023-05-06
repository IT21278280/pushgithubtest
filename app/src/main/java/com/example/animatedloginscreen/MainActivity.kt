package com.example.animatedloginscreen
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout

class MainActivity : Activity(), View.OnTouchListener {

    private var dX = 0f
    private var dY = 0f
    private var initialTouchX = 0f
    private var initialTouchY = 0f
    private var isCardAnimating = false
    private lateinit var cardView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardView = findViewById(R.id.relativeLayout2)
        cardView.setOnTouchListener(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Save the initial touch position and the position of the view
                initialTouchX = event.rawX
                initialTouchY = event.rawY
                dX = view.x - event.rawX
                dY = view.y - event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                // Calculate the new position of the view based on the touch position and the offset
                val newX = event.rawX + dX
                val newY = event.rawY + dY

                // Calculate the amount of rotation based on the distance between the initial touch position and the current touch position
                val rotationX = -(event.rawY - initialTouchY) / 2f
                val rotationY = (event.rawX - initialTouchX) / 2f

                // Set the new position and rotation of the view
                view.animate()
                    .x(newX)
                    .y(newY)
                    .setDuration(0)
                    .start()
                view.rotationX = rotationX
                view.rotationY = rotationY

                isCardAnimating = true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                // Animate the view back to its original position and rotation
                val animX = ObjectAnimator.ofFloat(view, View.X, initialTouchX + dX)
                val animY = ObjectAnimator.ofFloat(view, View.Y, initialTouchY + dY)
                val animRotationX = ObjectAnimator.ofFloat(view, View.ROTATION_X, 0f)
                val animRotationY = ObjectAnimator.ofFloat(view, View.ROTATION_Y, 0f)

                animX.duration = 500
                animY.duration = 500
                animRotationX.duration = 500
                animRotationY.duration = 500

                animX.interpolator = AccelerateDecelerateInterpolator()
                animY.interpolator = AccelerateDecelerateInterpolator()
                animRotationX.interpolator = AccelerateDecelerateInterpolator()
                animRotationY.interpolator = AccelerateDecelerateInterpolator()

                animX.start()
                animY.start()
                animRotationX.start()
                animRotationY.start()

                isCardAnimating = false
            }
        }

        return true
    }

    override fun onBackPressed() {
        if (isCardAnimating) {
            // Block back button during card animation
            return
        }
        super.onBackPressed()
    }
}