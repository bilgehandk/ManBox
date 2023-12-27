package com.bilgehandemirkaya.manbox.WorkManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginDatabase.Companion.getDatabase
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginRepository

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        try {
            val repository = LoginRepository(getDatabase(applicationContext).loginDao())

            val loginList = repository.readAllData.value

            if (!loginList.isNullOrEmpty()) {
                for (login in loginList) {
                    val userId = login.user_id
                    val userName = login.username_mail

                    val bmi = calculateBMI(login.height, login.weight)
                    val category = getBMICategory(bmi)

                    Log.d("MyLoginWorker", "User ID: $userId, Username: $userName, BMI: $bmi, Category: $category")
                }
            }

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    private fun calculateBMI(height: Int, weight: Int): Float {
        val heightInMeter = height / 100.0f
        return weight / (heightInMeter * heightInMeter)
    }

    private fun getBMICategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal Weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }
    }
}
