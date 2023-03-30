package com.devmasterteam.tasks.service.repository

import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import retrofit2.Retrofit

class TaskRepository {

    private val remote = RetrofitClient.getService(TaskService::class.java)

}