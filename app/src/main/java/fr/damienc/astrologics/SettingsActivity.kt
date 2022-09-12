package fr.damienc.astrologics

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.damienc.astrologics.fragments.SettingsFragment
import java.lang.NullPointerException

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.color.black))

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_settings_fl, SettingsFragment())
            .commit()
    }
}