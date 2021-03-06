package com.hellohasan.sqlite.ui.student_list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hellohasan.sqlite.core.DataFetchCallback
import com.hellohasan.sqlite.data.model.student.Student
import com.hellohasan.sqlite.data.model.student.StudentModel

class StudentListViewModel(private val model: StudentModel): ViewModel() {

    val studentListLiveData = MutableLiveData<MutableList<Student>>()
    val studentListFailedLiveData = MutableLiveData<String>()
    val studentDeletionSuccessLiveData = MutableLiveData<Int>()
    val studentDeletionFailedLiveData = MutableLiveData<String>()

    fun getStudentList() {

        model.getStudentList(object : DataFetchCallback<MutableList<Student>> {

            override fun onSuccess(data: MutableList<Student>) {
                studentListLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                studentListFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }

    fun deleteStudentById(id: Long) {

        model.deleteStudent(id, object : DataFetchCallback<Int> {
            override fun onSuccess(data: Int) {
                studentDeletionSuccessLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                studentDeletionFailedLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}