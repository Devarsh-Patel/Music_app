package com.example.androiditunes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiditunes.model.ItunesDetails
import com.example.androiditunes.model.ItunesItem
import com.example.androiditunes.model.remote.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ItunesViewModel"
class ItunesViewModel : ViewModel() {

    private var _resultCount: Int = 0

    private val _itunesClassicResult = MutableLiveData<List<ItunesItem>>()

    private val _itunesPopResult = MutableLiveData<List<ItunesItem>>()

    private val _itunesRockResult = MutableLiveData<List<ItunesItem>>()

    private val _errorMessages = MutableLiveData("")


    val itunesClassicResult: LiveData<List<ItunesItem>>
        get() = _itunesClassicResult

    val itunesPopResult: LiveData<List<ItunesItem>>
        get() = _itunesPopResult

    val itunesRockResult: LiveData<List<ItunesItem>>
        get() = _itunesRockResult

    val errorMessages: LiveData<String>
        get() = _errorMessages

    var resultCount: Int
        get() = _resultCount
        set(value) { _resultCount = value}

    fun getClassicItunes() {
        API.itunesApi.queryClassicItunes()
            .enqueue(object : Callback<ItunesDetails> {
                override fun onResponse(
                    call: Call<ItunesDetails>,
                    response: Response<ItunesDetails>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _resultCount = it.resultCount
                            _itunesClassicResult.value = it.results
                        } ?: kotlin.run {
                            _errorMessages.value = response.message()
                        }
                    } else {
                        _errorMessages.value = response.message()
                    }


                }

                override fun onFailure(call: Call<ItunesDetails>, t: Throwable) {
                    t.printStackTrace()
                    _errorMessages.value = t.message ?: "Unknown error"
                }
            })
    }

    fun getPopItunes() {
        API.itunesApi.queryPopItunes()
            .enqueue(object : Callback<ItunesDetails> {
                override fun onResponse(
                    call: Call<ItunesDetails>,
                    response: Response<ItunesDetails>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _resultCount = it.resultCount
                            _itunesPopResult.value = it.results
                        } ?: kotlin.run {
                            _errorMessages.value = response.message()
                        }
                    } else {
                        _errorMessages.value = response.message()

                    }
                }

                override fun onFailure(call: Call<ItunesDetails>, t: Throwable) {
                    t.printStackTrace()
                    _errorMessages.value = t.message ?: "Unknown error"
                }

            })

    }

    fun getRockItunes() {
        Log.d(TAG, "getRockItunes: anything1")
        API.itunesApi.queryRockItunes()
            .enqueue(object : Callback<ItunesDetails>{
                override fun onResponse(
                    call: Call<ItunesDetails>,
                    response: Response<ItunesDetails>
                ) {
                    Log.d(TAG, "onResponse: anything2")

                    if (response.isSuccessful){
                        response.body()?.let {
                            _resultCount = it.resultCount
                            _itunesRockResult.value = it.results
                            Log.d(TAG, "onResponse: $it & $resultCount ")

                        } ?: kotlin.run {
                            _errorMessages.value = response.message()
                        }
                    } else {
                        _errorMessages.value = response.message()
                        Log.d(TAG, "onResponseError: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ItunesDetails>, t: Throwable) {
                    _errorMessages.value = t.message ?: "Unknown error"
                }

            })
    }


}