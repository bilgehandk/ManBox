package com.bilgehandemirkaya.manbox

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bilgehandemirkaya.manbox.database.ActivityDB.ActivitySystem
import com.bilgehandemirkaya.manbox.database.LoginDB.Login
import com.bilgehandemirkaya.manbox.database.MembershipDB.Membership
import com.bilgehandemirkaya.manbox.database.MembershipDB.MembershipViewModel

class RecyclerViewAdapter(private val activity: Activity, private val lastUser: Login?, private val membershipViewModel: MembershipViewModel) :
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

        val id = item.id_activity
        holder.timeClassActivityText.text = item.clockActivity
        holder.numberClassActivityText.text = item.dateActivity
        holder.trainerNameTextView.text = item.trainerName

        holder.addMembershipBtn.setOnClickListener {
            val membershipNumber = membershipViewModel.getLatestMembershipNumber(id).toString().toInt() + 1

            val membership = lastUser?.let { it1 ->
                Membership(
                    id,
                    it1.username_mail,
                    it1.name_surname,
                    membershipNumber
                )
            }

            if (membership != null) {
                membershipViewModel.insertOrUpdateMembership(membership)
            }
            Toast.makeText(activity, "Membership is added", Toast.LENGTH_SHORT).show()
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
