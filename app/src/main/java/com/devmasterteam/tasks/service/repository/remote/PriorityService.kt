package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.model.TaskModel
import retrofit2.Call
import retrofit2.http.*

interface PriorityService {

    @GET("Task")
    fun list(): Call<List<PriorityModel>>

    @GET("Next7Days")
    fun listNext(): Call<List<PriorityModel>>

    @GET("OverDue")
    fun listOverdue(): Call<List<TaskModel>>

    @GET("Task/{id}")
    fun load(@Path(value = "id", encoded = true) id: Int): Call<TaskModel>

    @POST("Task")
    @FormUrlEncoded
    fun create(
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean,
    ): Call<Boolean>

    @PUT("Task")
    @FormUrlEncoded
    fun update(
        @Field("Id") id: Int,
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean,
    ): Call<Boolean>
}