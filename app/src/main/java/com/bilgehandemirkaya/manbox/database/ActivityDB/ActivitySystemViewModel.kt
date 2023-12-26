package com.bilgehandemirkaya.manbox.database.ActivityDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bilgehandemirkaya.manbox.database.LoginDB.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between repository and the UI
 */
class ActivitySystemViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<ActivitySystem>>
    private val repository: ActivitySystemRepository
    init {
        val activitySystemDAO= ActivitySystemRoomDatabase.getDatabase(application).ActivitySystemDAO()
        repository = ActivitySystemRepository(activitySystemDAO)
        readAllData = repository.readAlldata
    }
    fun addActivitySystem(activitySystem: ActivitySystem){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertActivitySystem(activitySystem)
        }
    }
    fun addActivitySystems(customers: List<ActivitySystem>){
        viewModelScope.launch(Dispatchers.IO) { // that code will be run in background thread, coroutine scope
            customers.forEach{
                repository.insertActivitySystem(it)
            }
        }
    }
    fun deleteActivitySystem(ActivitySystem: ActivitySystem){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteActivitySystem(ActivitySystem)
        }
    }
    fun deleteAllActivitySystems(){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteAllActivitySystems()
        }
    }
    fun updateOperatingSystems(activitySystem: ActivitySystem){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.updateActivitySystem(activitySystem)
        }
    }

    fun getActivityByDate(date: String): LiveData<List<ActivitySystem>> {
        return repository.getActivityByDate(date)
    }

    fun performActivity() {
        for (hour in 8..20) {
                val newActivity = ActivitySystem(
                    0,
                    "Class $hour: 00",
                    "29/12/2023",
                    "$hour: 00",
                    "Bilgehan Demirkaya",
                )
                addActivitySystem(newActivity)

        }

    }

}

