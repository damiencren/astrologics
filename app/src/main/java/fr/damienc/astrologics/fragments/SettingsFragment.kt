package fr.damienc.astrologics.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import fr.damienc.astrologics.R


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}
