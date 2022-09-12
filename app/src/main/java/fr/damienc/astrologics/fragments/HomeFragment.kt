package fr.damienc.astrologics.fragments

import android.content.Context;
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.damienc.astrologics.ClickedSignDialog
import fr.damienc.astrologics.MainActivity
import fr.damienc.astrologics.SignItemDecoration
import fr.damienc.astrologics.R
import fr.damienc.astrologics.SignModel
import fr.damienc.astrologics.adapter.SignAdapter

class HomeFragment (private val context : MainActivity,
                    private val signList: List<SignModel>) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val sharedPref : SharedPreferences = context.getSharedPreferences(
            context.getString(R.string.preference_file_key),Context.MODE_PRIVATE)

        val savedSignName = sharedPref.getString("Sign Name", null)
        val sign = signList.find { it.name == savedSignName }

        val signName = view.findViewById<TextView>(R.id.fragment_home_tv_sign_name)
        val signImage = view.findViewById<ImageView>(R.id.fragment_home_iv_sign_image)
        val signImageBg = view.findViewById<View>(R.id.fragment_home_cv_sign_image_bg)

        signName.text = sign?.name
        Glide.with(context).load(Uri.parse("android.resource://fr.damienc.astrologics/drawable/${sign?.icon}")).into(signImage)

        signImageBg.setOnClickListener{
            if (savedSignName != null && sign != null) {
                    ClickedSignDialog(context, sign).show()
            }
        }

        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.fragment_home_rv)
        verticalRecyclerView.adapter = SignAdapter(context, signList)
        verticalRecyclerView.addItemDecoration(SignItemDecoration())

        return view
    }

}

