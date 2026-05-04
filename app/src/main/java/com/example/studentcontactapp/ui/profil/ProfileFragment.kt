package com.example.studentcontactapp.ui.profil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.studentcontactapp.R
import com.example.studentcontactapp.ui.login.LoginActivity
import com.example.studentcontactapp.utils.PrefManager
import com.example.studentcontactapp.utils.SettingsManager

class ProfileFragment : Fragment() {

    private lateinit var prefManager: PrefManager
    private lateinit var settingsManager: SettingsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Inisialisasi Manager
        prefManager = PrefManager(requireContext())
        settingsManager = SettingsManager(requireContext())

        // Bind Views
        val tvWelcomeUser = view.findViewById<TextView>(R.id.tvWelcomeUser)
        val switchDarkMode = view.findViewById<Switch>(R.id.switchDarkMode)
        val switchFontSize = view.findViewById<Switch>(R.id.switchFontSize)
        val switchNotif = view.findViewById<Switch>(R.id.switchNotif)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        // Set Data & State dari Manager
        tvWelcomeUser.text = "Hello, ${prefManager.getUsername()}!"
        switchDarkMode.isChecked = settingsManager.isDarkMode
        switchFontSize.isChecked = settingsManager.fontSize == 18
        switchNotif.isChecked = settingsManager.isNotificationEnabled

        // Listener Settings
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            settingsManager.isDarkMode = isChecked
        }

        switchFontSize.setOnCheckedChangeListener { _, isChecked ->
            settingsManager.fontSize = if (isChecked) 18 else 14
        }

        switchNotif.setOnCheckedChangeListener { _, isChecked ->
            settingsManager.isNotificationEnabled = isChecked
        }

        // Listener Logout
        btnLogout.setOnClickListener {
            prefManager.logout()

            // Pindah ke LoginActivity dan hapus tumpukan Activity sebelumnya
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }
}