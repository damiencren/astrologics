package fr.damienc.astrologics

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.*
import com.bumptech.glide.Glide
import java.util.*
import android.widget.DatePicker




class DatePickerDialog(private val context: MainActivity) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_date_selector)

        val datePicker = findViewById<DatePicker>(R.id.dialog_date_selector_dp)

        val bDay = Calendar.getInstance()
        val sharedPref : SharedPreferences = context.getSharedPreferences(
            context.getString(R.string.preference_file_key),Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        datePicker.init(
            bDay.get(Calendar.YEAR), bDay.get(Calendar.MONTH), bDay.get(Calendar.DAY_OF_MONTH)

        ) { _, year, month, day ->
            val month = month + 1
            val button: Button = findViewById(R.id.dialog_date_selector_btn_confirm)
            button.setOnClickListener {
                val sign = zodiacSign(day,month)
                val msg = "Sign changed to $sign"
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                editor.putString("Sign Name", sign)
                editor.commit()

                val signName = sharedPref.getString("Sign Name", null)

                val yourSignText = context.findViewById<TextView>(R.id.fragment_home_tv_sign_name)
                val yourSignImage = context.findViewById<ImageView>(R.id.fragment_home_iv_sign_image)

                yourSignText.text = signName
                Glide.with(context).load(Uri.parse("android.resource://fr.damienc.astrologics/drawable/ic_${signName?.lowercase()}")).into(yourSignImage)

                this.dismiss()
            }
        }
    }

    override fun onBackPressed() {
        Toast.makeText(context,"Enter your birthday",Toast.LENGTH_SHORT).show()
    }

    private fun zodiacSign(day : Int, month : Int): String {

        var astro_sign: String = ""

        if (month == 12) {

            astro_sign = if (day < 22)
                "Sagittarius"
            else
                "Capricorn"
        } else if (month == 1) {
            if (day < 20)
                astro_sign = "Capricorn"
            else
                astro_sign = "Aquarius"
        } else if (month == 2) {
            if (day < 19)
                astro_sign = "Aquarius"
            else
                astro_sign = "Pisces"
        } else if (month == 3) {
            if (day < 21)
                astro_sign = "Pisces"
            else
                astro_sign = "Aries"
        } else if (month == 4) {
            if (day < 20)
                astro_sign = "Aries"
            else
                astro_sign = "Taurus"
        } else if (month == 5) {
            if (day < 21)
                astro_sign = "Taurus"
            else
                astro_sign = "Gemini"
        } else if (month == 6) {
            if (day < 21)
                astro_sign = "Gemini"
            else
                astro_sign = "Cancer"
        } else if (month == 7) {
            if (day < 23)
                astro_sign = "Cancer"
            else
                astro_sign = "Leo"
        } else if (month == 8) {
            if (day < 23)
                astro_sign = "Leo"
            else
                astro_sign = "Virgo"
        } else if (month == 9) {
            if (day < 23)
                astro_sign = "Virgo"
            else
                astro_sign = "Libra"
        } else if (month == 10) {
            if (day < 23)
                astro_sign = "Libra"
            else
                astro_sign = "Scorpio"
        } else if (month == 11) {
            if (day < 22)
                astro_sign = "Scorpio"
            else
                astro_sign = "Sagittarius"
        }

        return astro_sign
    }

}

