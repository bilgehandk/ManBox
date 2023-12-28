package com.bilgehandemirkaya.manbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilgehandemirkaya.manbox.database.ActivityDB.ActivitySystemViewModel
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginViewModel
import com.bilgehandemirkaya.manbox.database.MembershipDB.MembershipViewModel
import com.bilgehandemirkaya.manbox.databinding.ActivityTraningBinding
import androidx.lifecycle.lifecycleScope
import com.bilgehandemirkaya.manbox.database.MembershipDB.Membership
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TraningActivity : AppCompatActivity(), DatePickerFragment.DatePickerListener {

    private lateinit var binding: ActivityTraningBinding
    private lateinit var activitySystemViewModel: ActivitySystemViewModel
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var membershipViewModel: MembershipViewModel
    private lateinit var adapter: recyclier_ViewDailyActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activitySystemViewModel = ViewModelProvider(this).get(ActivitySystemViewModel::class.java)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        membershipViewModel = ViewModelProvider(this).get(MembershipViewModel::class.java)

        // RecyclerViewAdapter'ı geç başlatma
        adapter = recyclier_ViewDailyActivity(this)
        binding.recyclerCustomerView.adapter = adapter
        binding.recyclerCustomerView.layoutManager = LinearLayoutManager(this)

        activitySystemViewModel.performActivity()

        loginViewModel.lastUser.observe(this, Observer { lastUser ->
            if (lastUser != null) {


                // getDataTime'i buraya taşı
                getDataTime()
            }
        })



        binding.dateEditBox.setOnClickListener {
            // Open DatePicker and handle the selected date
            showDatePicker()
        }
    }

    fun addMembershipDB() {
        val intent = intent
        val id = intent.getIntExtra("id", 0)

        // Use lifecycleScope.launch to perform the database operation in a coroutine
        lifecycleScope.launch {
            val userEx = loginViewModel.lastUser.value


            // Use withContext(Dispatchers.IO) to switch to the background thread
            val membershipSizeNumber = withContext(Dispatchers.IO) {
                membershipViewModel.getLatestMembershipNumber(id)
            }

            val newMembershipSizeNumber = membershipSizeNumber?.plus(1) ?: 1



            val membership = Membership(
                id,
                userEx?.username_mail ?: "",
                userEx?.name_surname ?: "",
                newMembershipSizeNumber
            )

            // Use withContext(Dispatchers.IO) to switch to the background thread
            withContext(Dispatchers.IO) {
                membershipViewModel.insertOrUpdateMembership(membership)
            }
        }
    }



    private fun showDatePicker() {
        val datePickerFragment = DatePickerFragment()
        datePickerFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDateSelected(date: String) {
        // Use the selected date to filter data
        getDataTime(date)
        binding.dateEditBox.setText(date)
    }

    private fun getDataTime(selectedDate: String? = null) {
        // Check if the adapter is initialized before using it
        if (::adapter.isInitialized && selectedDate != null) {
            // Fetch data based on the selected date
            activitySystemViewModel.getActivityByDate(selectedDate).observe(this, Observer { activitySystem ->
                // Update adapter data
                adapter.setData(activitySystem)
                adapter.notifyDataSetChanged()
            })
        }
    }
}
