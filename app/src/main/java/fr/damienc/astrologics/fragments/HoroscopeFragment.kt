package fr.damienc.astrologics.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import fr.damienc.astrologics.MainActivity
import fr.damienc.astrologics.R
import fr.damienc.astrologics.SignModel
import okhttp3.*
import java.io.IOException
import okhttp3.OkHttpClient
import org.json.JSONObject


class HoroscopeFragment(private val context: MainActivity,
                        private val signList: List<SignModel>
) : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_horoscope, container, false)

        val sharedPref : SharedPreferences = context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val signName = sharedPref.getString("Sign Name", null)
        val sign : SignModel? = signList.find { it.name == signName }

        val signDescription = view.findViewById<TextView>(R.id.fragment_horoscope_tv_sign_horoscope)
        val signLuckyNumber = view.findViewById<TextView>(R.id.fragment_horoscope_tv_sign_lucky_number)
        val signLuckyTime = view.findViewById<TextView>(R.id.fragment_horoscope_tv_sign_lucky_time)
        val signMood = view.findViewById<TextView>(R.id.fragment_horoscope_tv_sign_mood)
        val signPlanet = view.findViewById<TextView>(R.id.fragment_horoscope_tv_sign_planet)
        val signOpposedSign = view.findViewById<TextView>(R.id.fragment_horoscope_tv_sign_opposed_sign)
        val signElement = view.findViewById<TextView>(R.id.fragment_horoscope_tv_sign_element)

        val progress = ProgressDialog(context)
        progress.setMessage("Consulting the stars...")
        progress.setCancelable(false)
        progress.show()


        val url = "https://sameer-kumar-aztro-v1.p.rapidapi.com/?sign=${signName?.lowercase()}&day=today"

        val client = OkHttpClient()

        val formBody: RequestBody = FormBody.Builder().build()
        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .addHeader("X-RapidAPI-Key", "e1787399bemsh23eb32c1a751c5cp1d93e1jsn11b84a11e21c")
            .addHeader("X-RapidAPI-Host", "sameer-kumar-aztro-v1.p.rapidapi.com")
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                context.runOnUiThread { val toast = Toast.makeText(context,"No internet connection",Toast.LENGTH_LONG)
                    toast.show()}
                progress.dismiss()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }
                    val data = response.body!!.string()
                    val json = JSONObject(data)
                    context.runOnUiThread {
                        signDescription.text = json.getString("description")
                        signLuckyNumber.text = json.getString("lucky_number")
                        signLuckyTime.text = json.getString("lucky_time")
                        signMood.text = json.getString("mood")
                        signPlanet.text = sign?.planet
                        signOpposedSign.text = sign?.opposed_sign
                        signElement.text = sign?.element
                    }


                }

                progress.dismiss()
            }
        })

        return view
    }


}
