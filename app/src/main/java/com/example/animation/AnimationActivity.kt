package com.example.animation

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animation.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
  private lateinit var binding: ActivityAnimationBinding
  private lateinit var anim: AnimationDrawable

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAnimationBinding.inflate(layoutInflater)
    setContentView(binding.root)

    (binding.ivAnimation.background as AnimationDrawable).start()

    binding.btnBack.setOnClickListener {
      val mainActivity = Intent(this, MainActivity::class.java)
      (binding.ivAnimation.background as AnimationDrawable).stop()
      startActivity(mainActivity)
    }
  }
}