package com.bilgehandemirkaya.manbox
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilgehandemirkaya.manbox.database.ActivityDB.ActivitySystemViewModel
import com.bilgehandemirkaya.manbox.database.MembershipDB.Membership
import com.bilgehandemirkaya.manbox.database.MembershipDB.MembershipViewModel
import com.bilgehandemirkaya.manbox.databinding.ActivityTraningBinding

class TraningActivity : AppCompatActivity(), DatePickerFragment.DatePickerListener {

    private lateinit var binding: ActivityTraningBinding
    private lateinit var activitySystemViewModel: ActivitySystemViewModel
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var membershipSystemViewModel: ActivitySystemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activitySystemViewModel = ViewModelProvider(this).get(ActivitySystemViewModel::class.java)
        activitySystemViewModel.performActivity()

        adapter = RecyclerViewAdapter(this)
        binding.recyclerCustomerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerCustomerView.adapter = adapter




        binding.dateEditBox.setOnClickListener {
            // Open DatePicker and handle the selected date
            showDatePicker()
        }

        getDataTime()


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
        if (selectedDate != null) {
            // Fetch data based on the selected date
            activitySystemViewModel.getActivityByDate(selectedDate).observe(this, Observer { activitySystem ->
                adapter.setData(activitySystem)
            })

        }
    }
}
