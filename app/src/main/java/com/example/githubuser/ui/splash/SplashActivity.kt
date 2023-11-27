package com.example.githubuser.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.ActivitySplashBinding
import com.example.githubuser.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        supportActionBar?.hide()

        Glide.with(this)
            .load(R.drawable.octocat)
            .circleCrop()
            .into(splashBinding.ivMark)

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getThemeSetting().observe(this@SplashActivity, {isDarkModeActive ->
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                if (!isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            })
            finish()
        }, 3000)
    }
}
