package com.bilgehandemirkaya.manbox

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bilgehandemirkaya.manbox.database.ActivityDB.ActivitySystem
import com.bilgehandemirkaya.manbox.database.LoginDB.Login
import com.bilgehandemirkaya.manbox.database.MembershipDB.Membership
import com.bilgehandemirkaya.manbox.database.MembershipDB.MembershipViewModel
import kotlinx.coroutines.launch

class recyclier_ViewDailyActivity(val activity: TraningActivity) : RecyclerView.Adapter<recyclier_ViewDailyActivity.RecyclerViewItemHolder>() {

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
            // Ensure that the context is a LifecycleOwner
            val intent = activity.intent
            intent.putExtra("id", id)
            //Call addMembershipDB() function
            activity.addMembershipDB()

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
