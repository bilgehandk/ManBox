package com.bilgehandemirkaya.manbox.database.MembershipDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MembershipViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Membership>>
    private val repository: MembershipRepository

    init {
        val membershipDao = MembershipDatabase.getDatabase(application).membershipDao()
        repository = MembershipRepository(membershipDao)
        readAllData = repository.readAllData
    }

    fun insertMembership(membership: Membership) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMembership(membership)
        }
    }

    fun insertMemberships(memberships: List<Membership>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMemberships(memberships)
        }
    }

    fun updateMembership(membership: Membership) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMembership(membership)
        }
    }

    fun deleteMembership(membership: Membership) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMembership(membership)
        }
    }

    fun deleteAllMemberships() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMemberships()
        }
    }

    fun getMembershipById(id: Int): Membership {
        return repository.getMembershipById(id)
    }

    fun getMembershipsBySearchKey(searchKey: String): LiveData<List<Membership>> {
        return repository.getMembershipsBySearchKey(searchKey)
    }
}
