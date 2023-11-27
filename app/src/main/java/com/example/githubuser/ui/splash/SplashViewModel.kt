package com.example.githubuser.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.example.githubuser.data.datastore.SettingsPreferences
import kotlinx.coroutines.Dispatchers

class SplashViewModel(application: Application): AndroidViewModel(application) {
    private val settingsPreferences: SettingsPreferences

    init {
        settingsPreferences = SettingsPreferences.getInstance(application)
    }

    fun getThemeSetting() = settingsPreferences.getThemeSetting().asLiveData(Dispatchers.IO)

}