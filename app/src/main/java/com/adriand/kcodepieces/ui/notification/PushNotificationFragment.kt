package com.adriand.kcodepieces.ui.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.adriand.kcodepieces.databinding.FragmentPushNotificationBinding

class PushNotificationFragment : Fragment() {

    companion object {
        const val CHANNEL_ID = "newChannel"
    }

    private var _binding: FragmentPushNotificationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPushNotificationBinding.inflate(layoutInflater, container, false)

        createChannel()
        binding.btnNotify.setOnClickListener { createBasicNotification() }

        return binding.root
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                "NewChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel: $CHANNEL_ID"
            }

            val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createBasicNotification(){

        val intent = Intent(requireContext(), PushNotificationFragment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        // For API 22 or less
        // val flag = 0
        // In case you need both, you need to check
        // val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0

        // For API 23 or greater
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setContentTitle("New Notification")
            .setContentText("Short Message")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Large text. Large text. Large text. Large text. Large text. Large text. Large text. Large text. Large text. Large text. Large text. Large text. Large text. Large text."))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())){
            checkPermission()
            notify(1, builder.build())
        }

    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
    }
}