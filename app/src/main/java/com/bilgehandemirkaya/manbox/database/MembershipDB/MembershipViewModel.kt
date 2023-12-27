package com.bilgehandemirkaya.manbox.database.MembershipDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MembershipViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MembershipRepository
    val AllData : LiveData<List<Membership>>

    init {
        val membershipDao = MembershipDatabase.getDatabase(application).membershipDao()
        repository = MembershipRepository(membershipDao)
        AllData = repository.allMemberships
    }

    fun insert(membership: Membership) = viewModelScope.launch {
        repository.insert(membership)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getLatestMembershipNumber(id: Int) : Int? {
        return repository.getLatestMembershipNumber(id)
    }

    fun insertOrUpdateMembership(membership: Membership) {
        repository.insertOrUpdateMembership(membership)
    }
}
