package zukkey.sharedelementtransition

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.graphics.Outline
import android.graphics.Rect
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.transition.Transition
import android.transition.TransitionValues
import android.view.View
import android.view.ViewGroup
import android.view.View.MeasureSpec.makeMeasureSpec
import android.view.ViewAnimationUtils
import android.view.ViewOutlineProvider
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator


class CustomTransition: Transition() {
    private var circularReveal: Animator? = null

    companion object {
        const val TRANSITION_VALUES = "transition:values"
    }

    override fun captureStartValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }

    override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator {
        val startBounds = startValues?.values?.get(TRANSITION_VALUES) as Rect
        val endBounds = endValues?.values?.get(TRANSITION_VALUES) as Rect
        val roundImageView = endValues.view
        val fromRoundImageView = endBounds.width() > endBounds.height()
        val squreImageBounds = if (fromRoundImageView) endBounds else startBounds
        val roundImageBounds = if (fromRoundImageView) startBounds else endBounds
        val fastOutInSlowInInterpolator = FastOutSlowInInterpolator()
        val halfDuration = duration / 2
        val twoThirdsDuration = duration * 2 / 3
        if (!fromRoundImageView) {
            roundImageView.measure(
                    makeMeasureSpec(startBounds.width(), View.MeasureSpec.EXACTLY),
                    makeMeasureSpec(startBounds.height(), View.MeasureSpec.EXACTLY)
            )
            roundImageView.layout(
                    startBounds.left,
                    startBounds.top,
                    startBounds.right,
                    startBounds.bottom
            )
        }

        val transitionX = startBounds.centerX() - endBounds.centerX()
        val transitionY = startBounds.centerY() - endBounds.centerY()

        if (fromRoundImageView) {
            roundImageView.translationX = transitionX.toFloat()
            roundImageView.translationY = transitionY.toFloat()
        }


        if (fromRoundImageView) {
            circularReveal = ViewAnimationUtils.createCircularReveal(
                    roundImageView,
                    roundImageView.width / 2,
                    roundImageView.height / 2,
                    (startBounds.width() / 2).toFloat(),
                    Math.hypot((endBounds.width() / 2).toDouble(), (endBounds.height() / 2).toDouble()
                    ).toFloat())

        } else {
            circularReveal = ViewAnimationUtils.createCircularReveal(
                    roundImageView,
                    roundImageView.width / 2,
                    roundImageView.height / 2,
                    Math.hypot((endBounds.width() / 2).toDouble(), (endBounds.height() / 2).toDouble()
                    ).toFloat(),
                    (endBounds.width() / 2).toFloat())
            circularReveal?.interpolator = LinearInterpolator()
            circularReveal?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    roundImageView.outlineProvider = object : ViewOutlineProvider() {
                        override fun getOutline(view: View?, outline: Outline?) {
                            if (view == null || outline == null) return
                            val left = (view.width - roundImageBounds.width()) / 2
                            val top = (view.height - roundImageBounds.height()) / 2
                            outline.setOval(left, top, left + roundImageBounds.width(), top + roundImageBounds.height())
                            view.clipToOutline = true
                        }
                    }
                }
            })
            circularReveal?.duration = duration
        }

        val transition = AnimatorSet()
        transition.playTogether(circularReveal)
        return transition
    }

    private fun captureValues(transitionValues: TransitionValues?) {
        transitionValues?.view?.let { view ->
            if (view.width <= 0 || view.height <= 0) {
                return@let
            }
            transitionValues.values?.put(TRANSITION_VALUES, transitionValues)
        }
    }
}