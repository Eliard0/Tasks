package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.TaskModel
import retrofit2.Call
import retrofit2.http.*

interface TaskService {

    @GET("Task")
    fun list(): Call<List<TaskModel>>

    @GET("Task/Next7Days")
    fun listNext(): Call<List<TaskModel>>

    @GET("Task/Overdue")
    fun listOverdue(): Call<List<TaskModel>>

    @GET("Task/{id}")
    fun load(@Path(value = "id", encoded = false) id: Int): Call<TaskModel>

    @POST("Task")
    @FormUrlEncoded
    fun create(
        @Field("PriorityId") priority: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean,
    ): Call<Boolean>

    @PUT("Task")
    fun update(
        @Field("Id") id: Int,
        @Field("Priority") priority: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean,
    ): Call<Boolean>

    @PUT("Task/Complete")
    @FormUrlEncoded
    fun complete(@Field("Id") id: Int): Call<Boolean>

    @PUT("Task/Undo")
    @FormUrlEncoded
    fun undo(@Field("Id") id: Int): Call<Boolean>

    @HTTP(method = "DELETE", path = "task", hasBody = true)
    @FormUrlEncoded
    fun delete(@Field("Id") id: Int): Call<Boolean>


}