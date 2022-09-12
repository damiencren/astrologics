package fr.damienc.astrologics.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.damienc.astrologics.*


class SignAdapter (
    val context: MainActivity,
    private val signList: List<SignModel>
    ) : RecyclerView.Adapter<SignAdapter.ViewHolder>(){


    //boite pour ranger les composants
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val signImage = view.findViewById<ImageView>(R.id.dialog_clicked_sign_iv_sign_image)
        val signName = view.findViewById<TextView>(R.id.item_vertical_sign_tv_sign_name)
        val signDescription = view.findViewById<TextView>(R.id.item_vertical_sign_tv_sign_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical_sign, parent, false)
        return ViewHolder(view)
    }

    //mettre a jour
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSign = signList[position]
        Glide.with(context).load(Uri.parse("android.resource://fr.damienc.astrologics/drawable/" + currentSign.icon)).into(holder.signImage)
        holder.signName.text = currentSign.name
        holder.signDescription.text = currentSign.description

        holder.itemView.setOnClickListener{
            ClickedSignDialog(context,currentSign).show()
        }
    }

    override fun getItemCount(): Int = signList.size

}
