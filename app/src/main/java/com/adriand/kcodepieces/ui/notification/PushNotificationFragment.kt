package com.adriand.kcodepieces.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adriand.kcodepieces.databinding.FragmentPushNotificationBinding

class PushNotificationFragment : Fragment() {

    private var _binding: FragmentPushNotificationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPushNotificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}