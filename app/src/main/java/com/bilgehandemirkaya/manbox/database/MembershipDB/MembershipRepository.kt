package com.bilgehandemirkaya.manbox.database.MembershipDB

import androidx.lifecycle.LiveData

class MembershipRepository(private val membershipDao: MembershipDao) {
    val readAllData: LiveData<List<Membership>> = membershipDao.getAllMemberships()

    fun insertMembership(membership: Membership) {
        membershipDao.insertMembership(membership)
    }

    fun insertMemberships(memberships: List<Membership>) {
        membershipDao.insertAllMemberships(memberships)
    }

    fun updateMembership(membership: Membership) {
        membershipDao.updateMembership(membership)
    }

    fun deleteMembership(membership: Membership) {
        membershipDao.deleteMembership(membership)
    }

    fun deleteAllMemberships() {
        membershipDao.deleteAllMemberships()
    }

    fun getAllMemberships(): LiveData<List<Membership>> {
        return membershipDao.getAllMemberships()
    }

    fun getMembershipById(id: Int): Membership {
        return membershipDao.getMembershipById(id)
    }

    fun getMembershipsBySearchKey(searchKey: String): LiveData<List<Membership>> {
        return membershipDao.getMembershipsBySearchKey(searchKey)
    }
}
