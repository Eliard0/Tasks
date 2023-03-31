package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import retrofit2.Retrofit

class TaskRepository(context: Context) {

    private val remote = RetrofitClient.getService(TaskService::class.java)

    fun create(task: TaskModel){
        val call = remote.create(task.priorityId, task.description, task.dueDate, task.complete)
        call.enqueue(object )
    }

}