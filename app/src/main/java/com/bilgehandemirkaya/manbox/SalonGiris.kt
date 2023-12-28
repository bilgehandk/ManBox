package com.bilgehandemirkaya.manbox

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bilgehandemirkaya.manbox.databinding.ActivityAccountBinding
import com.bilgehandemirkaya.manbox.databinding.ActivitySalonGirisBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class SalonGiris : AppCompatActivity() {
    private lateinit var imgqr: ImageView
    private lateinit var btnqr: Button
    private lateinit var countdownText: TextView
    private lateinit var binding: ActivitySalonGirisBinding


    private var currentBitmap: Bitmap? = null
    private var remainingTime: Int = 15 // Set countdown to 15 seconds
    private val handler = Handler(Looper.getMainLooper())
    private val countdownRunnable = object : Runnable {
        override fun run() {
            remainingTime--

            // Update countdown text
            countdownText.text = "Time Left: ${remainingTime}s"

            if (remainingTime > 0) {
                // Continue countdown
                handler.postDelayed(this, 1000) // 1 second
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salon_giris)

        imgqr = findViewById(R.id.ImageQR)
        btnqr = findViewById(R.id.qrbutton)
        countdownText = findViewById(R.id.countdownText)

        binding = ActivitySalonGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button44.setOnClickListener{
            val intent = Intent(this, MenuScreen::class.java)
            startActivity(intent)
        }


        btnqr.setOnClickListener {
            val data = generateRandomString()
            generateAndSetQRCode(data)

            // Schedule hiding of the QR code after 15 seconds
            handler.postDelayed({
                hideQRCode()
            }, 15000) // 15 seconds in milliseconds

            // Start countdown
            startCountdown()
        }
    }

    private fun generateRandomString(): String {
        // Implement your logic to generate a random string.
        // For simplicity, using the current time as a simple example.
        return System.currentTimeMillis().toString()
    }

    private fun generateAndSetQRCode(data: String) {
        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            currentBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    currentBitmap?.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            imgqr.setImageBitmap(currentBitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    private fun hideQRCode() {
        // Set the ImageView's bitmap to null or any other method to hide the QR code
        imgqr.setImageBitmap(null)
        currentBitmap = null

        // Reset countdown text
        countdownText.text = "Time Left: 15s"

        // Remove callbacks to stop the countdown
        handler.removeCallbacks(countdownRunnable)
        remainingTime = 15
    }

    private fun startCountdown() {
        // Start the countdown
        handler.postDelayed(countdownRunnable, 0)
    }
}
