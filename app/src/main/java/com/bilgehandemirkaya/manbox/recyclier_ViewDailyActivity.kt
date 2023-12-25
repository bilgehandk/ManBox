package com.bilgehandemirkaya.manbox

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bilgehandemirkaya.manbox.database.ActivityDB.ActivitySystem
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginViewModel
import com.bilgehandemirkaya.manbox.database.MembershipDB.Membership
import com.bilgehandemirkaya.manbox.database.MembershipDB.MembershipViewModel

class RecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewItemHolder>() {

    private var recyclerItemValues = emptyList<ActivitySystem>()

    fun setData(items: List<ActivitySystem>) {
        recyclerItemValues = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_recyclier_view_daily, parent, false)
        return RecyclerViewItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewItemHolder, position: Int) {
        val item = recyclerItemValues[position]

        // Assuming that your ActivitySystem class has methods like getTime(), getAvailability(), getTrainerName()
        val id = item.id_activity
        holder.timeClassActivityText.text = item.clockActivity
        holder.numberClassActivityText.text = item.dateActivity
        holder.trainerNameTextView.text = item.trainerName

        // Move click handling logic here
        holder.addMembershipBtn.setOnClickListener {
            val intent = Intent(context, TraningActivity::class.java)
            // Assuming you want to pass some information to the TraningActivity
            intent.putExtra("activity_id", item.id_activity)
            context.startActivity(intent)

            val loginViewModel = ViewModelProvider(context as TraningActivity).get(LoginViewModel::class.java)
            val membershipViewModel = ViewModelProvider(context as TraningActivity).get(MembershipViewModel::class.java)

            val getLastLogin = loginViewModel.getLastUser()
            val membershipModel = membershipViewModel.getMembershipByActivityId(id)
            val membershipNumber = membershipViewModel.getLatestMembershipNumber(membershipModel.id_class) + 1

            val membership = Membership(
                0,
                id, // Use the dateActivity from ActivitySystem
                getLastLogin.username_mail, // Use the clockActivity from ActivitySystem
                getLastLogin.name_surname,
                membershipNumber
            )
            membershipViewModel.insertMembership(membership)
            Toast.makeText(context, "Membership is added", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemValues.size
    }

    inner class RecyclerViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeClassActivityText: TextView = itemView.findViewById(R.id.timeClassActivityText)
        val numberClassActivityText: TextView = itemView.findViewById(R.id.numberClassActivityText)
        val trainerNameTextView: TextView = itemView.findViewById(R.id.TrainerNameTextView)
        val addMembershipBtn: Button = itemView.findViewById(R.id.addMembershipBtn)
    }
}
