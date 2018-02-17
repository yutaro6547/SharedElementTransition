package zukkey.sharedelementtransition

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import zukkey.sharedelementtransition.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    setSupportActionBar(binding.toolbar)
    binding.fab.setOnClickListener { view ->
      val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
          this,
          binding.contentMain?.image,
          "transition:android"
      ).toBundle()
      ActivityCompat.startActivity(
          this,
          Intent(this, NextActivity::class.java),
          bundle)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }
}
