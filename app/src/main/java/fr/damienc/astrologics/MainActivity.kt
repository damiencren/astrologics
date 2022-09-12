package fr.damienc.astrologics

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.damienc.astrologics.fragments.HomeFragment
import java.lang.NullPointerException
import android.content.SharedPreferences
import android.widget.TextView

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.damienc.astrologics.fragments.HoroscopeFragment
import android.content.Intent
import android.widget.ImageView
import fr.damienc.astrologics.fragments.CompatibilityFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signList: List<SignModel> = listOf(
            SignModel("Aries","Description DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription",
                "ic_aries","Mars","Libra","Fire"),

            SignModel("Taurus","sdf",
                "ic_taurus","Venus","Scorpio","Earth"),

            SignModel("Gemini","dfsfsd",
                "ic_gemini","Mercury","Sagittarius","Air"),

            SignModel("Cancer","sdfsdff",
                "ic_cancer","Moon","Capricorn","Water"),

            SignModel("Leo","sdfsdf",
                "ic_leo","Sun ☀️","Aquarius","Fire"),

            SignModel("Virgo","fsdfsd",
                "ic_virgo","Mercury","Pisces","Earth"),

            SignModel("Libra","fsdfsdf",
                "ic_libra","Venus","Aries","Air"),

            SignModel("Scorpio","sdfsdf",
                "ic_scorpio","Platon","Taurus","Water"),

            SignModel("Sagittarius","sdfsdf",
                "ic_sagittarius","Jupiter","Gemini","Fire"),

            SignModel("Capricorn","sdfsf",
                "ic_capricorn","Saturn","Cancer","Earth"),

            SignModel("Aquarius","sdfsf",
                "ic_aquarius","Uranus","Leo","Air"),

            SignModel("Pisces","sdfsf",
                "ic_pisces","Jupiter","Virgo","Water")
        )


        val sharedPref : SharedPreferences = this.getSharedPreferences(
            this.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val signName = sharedPref.getString("Sign Name", null)
        if (signName == null){
            val dialog = DatePickerDialog(this)
            dialog.setCancelable(false)
            dialog.show()
        }

        val settingsBtn = findViewById<ImageView>(R.id.activity_main_btn_settings)
        settingsBtn.setOnClickListener{
            val myIntent = Intent(this, SettingsActivity::class.java)
            startActivity(myIntent)
        }


        val pageTitle = findViewById<TextView>(R.id.activity_main_tv_title)
        val navigatioView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigatioView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this, signList))
                    pageTitle.text = getString(R.string.home_title)
                    true
                }
                R.id.horoscope_page -> {
                    loadFragment(HoroscopeFragment(this, signList))
                    pageTitle.text = getString(R.string.horoscope_title)
                    true
                }

                R.id.compatibility_page -> {
                    loadFragment(CompatibilityFragment(this, signList))
                    pageTitle.text = getString(R.string.compatibility_title)
                    true
                }

                else -> false
            }
        }

        loadFragment(HomeFragment(this, signList))
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_fl, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
