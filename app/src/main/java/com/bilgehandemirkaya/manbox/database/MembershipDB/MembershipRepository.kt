package com.bilgehandemirkaya.manbox.database.MembershipDB

import androidx.lifecycle.LiveData

class MembershipRepository(private val membershipDao: MembershipDao) {

        val allMemberships: LiveData<List<Membership>> = membershipDao.getAllMemberships()

        suspend fun insert(membership: Membership) {
            membershipDao.insert(membership)
        }



        suspend fun deleteAll() {
            membershipDao.deleteAll()
        }

        suspend fun getLatestMembershipNumber(id: Int): Int {
            return membershipDao.getLatestMembershipNumber(id)
        }

        suspend fun getMembership(username: String): Membership {
            return membershipDao.getMembership(username)
        }

        suspend fun updateMembership(username: String, size: Int) {
            membershipDao.updateMembership(username, size)
        }


        suspend fun insertOrUpdateMembership(membership: Membership) {
            val membershipFromDB = membershipDao.getMembership(membership.username_mail)
            if (membershipFromDB == null) {
                membershipDao.insert(membership)
            } else {
                membershipDao.updateMembership(membership.username_mail, membership.sizeClass)
            }
        }


}
