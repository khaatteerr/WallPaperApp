package com.khater.retromvvm.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.khater.retromvvm.database.localData.data.LocalWall
import com.khater.retromvvm.repository.WallRepositery
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class WallViewModel : ViewModel() {


    val newNoteText = MutableLiveData<String>()
    val repositery = WallRepositery()

    private val _notes = MutableLiveData<List<LocalWall>>()
    val notes: LiveData<List<LocalWall>> = _notes

    init {
        loadData()
    }

    fun addNote() {
        newNoteText.value?.let {
            repositery.insertNewNote(
                LocalWall(0,it)
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            newNoteText.postValue("")
        }
    }

    private fun loadData() {

        repositery.getAllNotes().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onGetNote,
                this::onFail
            )

    }

    private fun onGetNote(listOfNotes: List<LocalWall>) {
        _notes.postValue(listOfNotes)
    }

    private fun onFail(e: Throwable) {}
}