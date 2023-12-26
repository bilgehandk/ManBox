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

class TraningActivity : AppCompatActivity(), DatePickerFragment.DatePickerListener {

    private lateinit var binding: ActivityTraningBinding
    private lateinit var activitySystemViewModel: ActivitySystemViewModel
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var membershipViewModel: MembershipViewModel
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activitySystemViewModel = ViewModelProvider(this).get(ActivitySystemViewModel::class.java)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        membershipViewModel = ViewModelProvider(this).get(MembershipViewModel::class.java)

        // RecyclerViewAdapter'ı geç başlatma
        adapter = RecyclerViewAdapter(this, null, membershipViewModel)
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
