package com.example.khabeer.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabeer.data.network.NetworkStatus
import com.example.khabeer.domain.model.information.Payroll
import com.example.khabeer.domain.model.user.UserModel
import com.example.khabeer.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val application    : Application
    ) : ViewModel() {


    private var _getUser = MutableStateFlow<UserModel?>(null)
    var getUser = _getUser.asStateFlow()
    fun userLogin() = viewModelScope.launch{
        try {
            val network = NetworkStatus.isNetworkAvailable(application)
            if (network)
            {
                val response = mainRepository.login()
                if (response.isSuccessful)
                {
                    Log.d("testApp" , "Success to user login")
                   // Log.d("testApp" , response.body().toString())
                    response.body()?.let { _getUser.emit(it) }

                }
                else
                {
                    Log.d("testApp" , response.code().toString())
                    Log.d("testApp" , response.message().toString())
                    Log.d("testApp" , response.errorBody().toString())
                }
            }
            else {
                Log.d("testApp"  , "There is no internet connection")
            }
        }catch (e : Exception)
        {
            Log.d("testApp"  , e.message.toString())
        }
    }


    private var _getInformation =  MutableLiveData<Payroll>()
    var getInformation : LiveData<Payroll> = _getInformation

    fun getInformation(token : String) = viewModelScope.launch {
        try {
            val network = NetworkStatus.isNetworkAvailable(application)
            if (network)
            {
                val response = mainRepository.getInformation(token)
                if (response.isSuccessful)
                {
                    Log.d("testApp" , " Success to get information")
                   // Log.d("testApp" , response.body().toString())
                    response.body()?.Payroll?.let { _getInformation.postValue(it) }
                }
                else
                {
                    Log.d("testApp" , response.code().toString())
                    Log.d("testApp" , response.message().toString())
                    Log.d("testApp" , response.errorBody().toString())
                }
            }
            else
            {
                Log.d("testApp"  , "There is no internet connection")
            }

        }catch (e : Exception)
        {
            Log.d("testApp" , e.message.toString())
        }
    }

}