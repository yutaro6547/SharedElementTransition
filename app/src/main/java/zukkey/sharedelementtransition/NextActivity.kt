package zukkey.sharedelementtransition

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import zukkey.sharedelementtransition.databinding.ActivityNextBinding

/**
 * [WIP]
 * Write Overview here.
 */
class NextActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_next)
    DataBindingUtil.setContentView<ActivityNextBinding>(this, R.layout.activity_next)
  }
}