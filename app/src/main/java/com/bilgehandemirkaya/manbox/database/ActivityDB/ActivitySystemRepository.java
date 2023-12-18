package com.bilgehandemirkaya.manbox.database.ActivityDB;

import androidx.lifecycle.LiveData



/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class OperatingSystemRepository(private val ActivitySystemDAO: ActivitySystemDAO) {
    val readAlldata: LiveData<List<ActivitySystem>> = ActivitySystemDAO.getAllOperatingSystems()

    fun insertOperatingSystem(operatingSystem: OperatingSystems){
        operatingSystemDAO.insertOperatingSystem(operatingSystem)
    }
    fun insertOperatingSystems(operatingSystems:ArrayList<OperatingSystems>){
        operatingSystemDAO.insertAllOperatingSystems(operatingSystems)
    }

    fun updateOperatingSystem(operatingSystem: OperatingSystems){
        operatingSystemDAO.updateOperatingSystem(operatingSystem)
    }

    fun deleteOperatingSystem(operatingSystem: OperatingSystems){
        operatingSystemDAO.deleteOperatingSystem(operatingSystem)
    }

    fun deleteAllOperatingSystems(){
        operatingSystemDAO.deleteAllOperatingSystems()
    }

    fun getAllOperatingSystems(): LiveData<List<OperatingSystems>> {
        return operatingSystemDAO.getAllOperatingSystems()
    }

    fun getOperationsSystemsById(id:Int):OperatingSystems{
        return operatingSystemDAO.getOperatingSystemById(id)
    }
    fun getOperatingSystemsBySearchKey(searchKey:String): Flow<List<OperatingSystems>> {
        return operatingSystemDAO.getOperatingSystemsBySearchKey(searchKey)
    }
}
