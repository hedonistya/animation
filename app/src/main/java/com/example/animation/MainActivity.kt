package com.example.animation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Geocoder
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityCompat
import com.example.animation.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private lateinit var anim: Animation

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnCamera.setOnClickListener {
      val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
      startActivityForResult(takePicture, 100)
    }

    binding.btnDelete.setOnClickListener {
      anim = AnimationUtils.loadAnimation(this, R.anim.card_anim)
      binding.cvContent.startAnimation(anim)
      binding.ivPhoto.setImageDrawable(null)
      binding.tvTime.text = null
    }

    binding.btnAnimation.setOnClickListener {
      val animationActivity = Intent(this, AnimationActivity::class.java)
      startActivity(animationActivity)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == 100 && resultCode == RESULT_OK) {
      val imageBitmap = data?.extras?.get("data") as Bitmap

      binding.ivPhoto.setImageBitmap(imageBitmap)
      binding.tvTime.text = GetTime()
    } else {
      super.onActivityResult(requestCode, resultCode, data)
    }
  }

  private fun GetTime(): String? {
    val current = LocalDateTime.now()

    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val formatted = current.format(formatter)

    return formatted
  }
}