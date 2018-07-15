package zukkey.sharedelementtransition

import android.animation.Animator
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionValues
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView


class CustomTransition: Transition() {
    private var circularReveal: Animator? = null

    companion object {
        /**
         * 詳細は下記リンク
         * https://developer.android.com/training/transitions/custom-transitions
         * キーは下記のようにした方がいいらしい
         * package_name:transition_name:property_name
         */
        const val PROPNAME_IMAGE = "zukkey.sharedelementtransition:transition:imageView"
    }

    override fun captureStartValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }

    override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator {
        val startBounds = startValues?.values?.get(PROPNAME_IMAGE) as ImageView
        val endBounds = endValues?.values?.get(PROPNAME_IMAGE) as ImageView
        if (startBounds != endBounds) {

        }
//        ViewAnimationUtils.createCircularReveal()
        return super.createAnimator(sceneRoot, startValues, endValues)

    }

    private fun captureValues(transitionValues: TransitionValues?) {
        transitionValues?.view?.let { view ->
            if (view.width <= 0 || view.height <= 0) {
                return@let
            }
            transitionValues.values?.put(PROPNAME_IMAGE, transitionValues)
        }
    }

    fun addExtraProperties(imageView: ImageView, extra:Bundle) {

    }
}