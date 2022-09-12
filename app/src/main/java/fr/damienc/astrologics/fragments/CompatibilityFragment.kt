package fr.damienc.astrologics.fragments

import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock.sleep
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.damienc.astrologics.MainActivity
import fr.damienc.astrologics.R
import fr.damienc.astrologics.SignModel
import android.widget.*
import com.bumptech.glide.Glide


class CompatibilityFragment(private val context: MainActivity,
                            private val signList: List<SignModel>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_compatibility, container, false)

        var listSignName = listOf<String>()
        for (sign in signList) {
            listSignName += sign.name
        }

        val result = view.findViewById<TextView>(R.id.fragment_compatibility_tv_result)
        val firstSpinner = view.findViewById<Spinner>(R.id.fragment_compatibility_spinner_first_sign)
        val secondSpinner = view.findViewById<Spinner>(R.id.fragment_compatibility_spinner_second_sign)
        val firstSignImageView = view.findViewById<ImageView>(R.id.fragment_compatibility_iv_first_sign_image)
        val secondSignImageView = view.findViewById<ImageView>(R.id.fragment_compatibility_iv_second_sign_image)
        var firstSignPos : Int = 0
        var secondSignPos : Int = 0


        val adapter = ArrayAdapter(context,
            R.layout.spinner_item, listSignName)
            firstSpinner.adapter = adapter
            secondSpinner.adapter = adapter

        firstSpinner.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    firstSignPos = position
                    Glide.with(context).load(Uri.parse("android.resource://fr.damienc.astrologics/drawable/ic_${(listSignName[position]).lowercase()}")).into(firstSignImageView)
                    val score = compatibility(firstSignPos, secondSignPos)
                    result.text = "${getEmoji(score)} $score%"
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                    }
            }


        secondSpinner.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                secondSignPos = position
                Glide.with(context).load(Uri.parse("android.resource://fr.damienc.astrologics/drawable/ic_${(listSignName[position]).lowercase()}")).into(secondSignImageView)
                val score = compatibility(firstSignPos, secondSignPos)
                result.text = "${getEmoji(score)} $score%"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        return view
    }

    private fun getEmoji(score:Int) : String {
        var emoji_score = ""
        emoji_score = if (score >= 66){
            "❤️"
        } else if (score <= 33){
            "\uD83E\uDD76"
        } else{
            "\uD83D\uDC9B"
        }
        return emoji_score
    }

    private fun compatibility(firstPos: Int, secondPos: Int) : Int {
        val line1 = listOf<Int>(50,38,83,42,97,63,85,50,93,47,78,67)
        val line2 = listOf<Int>(38,65,33,97,73,90,65,88,30,98,58,85)
        val line3 = listOf<Int>(83,33,60,65,88,68,93,28,60,68,85,53)
        val line4 = listOf<Int>(42,97,65,75,35,90,43,94,53,83,27,98)
        val line5 = listOf<Int>(97,73,88,35,45,35,97,58,93,35,68,38)
        val line6 = listOf<Int>(63,90,68,90,35,65,68,88,48,95,30,88)
        val line7 = listOf<Int>(85,65,93,43,97,68,75,35,73,55,90,88)
        val line8 = listOf<Int>(50,88,28,94,58,88,35,80,28,95,73,97)
        val line9 = listOf<Int>(93,30,60,53,93,48,73,28,45,60,90,63)
        val line10 = listOf<Int>(47,98,68,83,35,95,55,95,60,75,68,88)
        val line11 = listOf<Int>(75,58,85,27,68,30,90,73,90,68,45,45)
        val line12 = listOf<Int>(67,85,53,98,38,88,88,97,63,88,45,60)

        val tab = listOf<List<Int>>(line1,line2,line3,line4,line5,line6,
                                    line7,line8,line9,line10,line11,line12)

        return tab[firstPos][secondPos]
    }

}
