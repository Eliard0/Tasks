package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PriorityRepository
import com.devmasterteam.tasks.service.repository.TaskRepository

class TaskListViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository(application.applicationContext)
    private val priorityRepository = PriorityRepository(application.applicationContext)

    private val _task = MutableLiveData<List<TaskModel>>()
    val task: LiveData<List<TaskModel>> = _task

    private val _delete = MutableLiveData<ValidationModel>()
    val delete: LiveData<ValidationModel> = _delete

    private val _status = MutableLiveData<ValidationModel>()
    val status: LiveData<ValidationModel> = _status

    fun list() {
        taskRepository.list(object : APIListener<List<TaskModel>> {
            override fun onSuccess(result: List<TaskModel>) {
                result.forEach {
                    it.priorityDescription = priorityRepository.getDescription(it.priorityId)
                }
                _task.value = result
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }
        })
    }

    fun delete(id: Int) {
        taskRepository.delete(id, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                list()
            }

            override fun onFailure(message: String) {
                _delete.value = ValidationModel(message)
            }
        })
    }

    fun status(id: Int, complete: Boolean) {
        val listener = object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                list()
            }

            override fun onFailure(message: String) {
                _delete.value = ValidationModel(message)
            }
        }

        if (complete) {
            taskRepository.complete(id, listener)
        } else {
            taskRepository.undo(id, listener)
        }
    }

}