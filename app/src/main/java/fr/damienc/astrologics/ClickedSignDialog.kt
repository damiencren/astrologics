package fr.damienc.astrologics

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ClickedSignDialog(private val context : MainActivity,
                        private val sign : SignModel)
    : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_clicked_sign)


        val signName = findViewById<TextView>(R.id.dialog_clicked_sign_tv_sign_name)
        val signDescription = findViewById<TextView>(R.id.dialog_clicked_sign_tv_sign_description)
        val signPlanet = findViewById<TextView>(R.id.dialog_clicked_sign_tv_sign_planet)
        val signOpposedSign = findViewById<TextView>(R.id.dialog_clicked_sign_tv_sign_opposed_sign)
        val signElement = findViewById<TextView>(R.id.dialog_clicked_sign_tv_sign_element)

        val signImageImageView = findViewById<ImageView>(R.id.dialog_clicked_sign_iv_sign_image)
        val closeButton = findViewById<ImageView>(R.id.dialog_clicked_sign_iv_close)

        closeButton.setOnClickListener{
            dismiss()
        }

        signName.text = sign.name
        signDescription.text = sign.description
        signPlanet.text = sign.planet
        signOpposedSign.text = sign.opposed_sign
        signElement.text = sign.element
        Glide.with(context).load(Uri.parse("android.resource://fr.damienc.astrologics/drawable/${sign.icon}")).into(signImageImageView)
    }
}