package zukkey.sharedelementtransition

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.transition.TransitionSet
import zukkey.sharedelementtransition.databinding.ActivityNextBinding

/**
 * [WIP]
 * Write Overview here.
 */
class NextActivity: AppCompatActivity() {

  private val binding by lazy {
    DataBindingUtil.setContentView<ActivityNextBinding>(this, R.layout.activity_next)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_next)
    setTransition()
  }

  private fun setTransition() {
    val inSet = TransitionSet()
    val inflater = TransitionInflater.from(this)
    val transition = inflater.inflateTransition(R.transition.enter_transition)

    inSet.apply {
      addTransition(transition)
      duration = 380
    }

    val outSet = TransitionSet().apply {
      addTransition(transition)
      duration = 380
    }

    window.sharedElementEnterTransition = inSet
    window.sharedElementExitTransition = outSet
    window.sharedElementReturnTransition = outSet
  }
//
//  fun reveal() {
//    val center = binding.image.getCenter()
//    ViewAnimationUtils.createCircularReveal(
//            binding.image,
//            center.first,
//            center.second,
//            0f,
//            Math.hypot(center.first.toDouble(), center.second.toDouble()).toFloat()
//    ).apply {
//      duration = 400
//      start()
//    }
//  }
//
//  fun hideView() {
//    val center = binding.image.getCenter()
//    ViewAnimationUtils.createCircularReveal(
//            binding.image,
//            center.first,
//            center.second,
//            binding.image.width.toFloat(),
//            0f
//    ).apply {
//      duration = 400
//      start()
//    }
//  }
//
////  override fun onBackPressed() {
////    super.onBackPressed()
////    hideView()
////  }
//
//  fun View.getCenter(): Pair<Int, Int> {
//    val cx = this.width / 2
//    val cy = this.height / 2
//    return Pair(cx, cy)
//  }
}