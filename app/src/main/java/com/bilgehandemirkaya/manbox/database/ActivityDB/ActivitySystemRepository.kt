package com.bilgehandemirkaya.manbox.database.ActivityDB

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class ActivitySystemRepository(private val activitySystemDAO: ActivitySystemDAO) {
    val readAlldata: LiveData<List<ActivitySystem>> = activitySystemDAO.getAllActivitySystems()

    fun insertActivitySystem(activitySystem: ActivitySystem){
        activitySystemDAO.insertActivitySystem(activitySystem)
    }


    fun updateActivitySystem(activitySystem: ActivitySystem){
        activitySystemDAO.updateActivitySystem(activitySystem)
    }

    fun deleteActivitySystem(activitySystem: ActivitySystem){
        activitySystemDAO.deleteActivitySystem(activitySystem)
    }

    fun deleteAllActivitySystems(){
        activitySystemDAO.deleteAllActivitySystems()
    }

    fun getAllActivitySystems(): LiveData<List<ActivitySystem>> {
        return activitySystemDAO.getAllActivitySystems()
    }



}
