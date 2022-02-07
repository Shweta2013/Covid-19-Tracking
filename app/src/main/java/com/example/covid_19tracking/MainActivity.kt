package com.example.covid_19tracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19tracking.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.covidtracking.com/v1/"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var states: MutableList<States> = mutableListOf()
        val adapter = StateDataAdapter(this, states)
        binding.rvStates.adapter = adapter
        binding.rvStates.layoutManager = LinearLayoutManager(this)

        var retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val trackingService = retrofitBuilder.create(TrackingService::class.java)
        trackingService.searchState().enqueue(object : Callback<List<States>> {
            override fun onResponse(call: Call<List<States>>, response: Response<List<States>>) {
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if (body == null) {
                    Log.i(TAG, "Did not receive valid response body from Covid Tracking API...exiting")
                    return
                }
                states.addAll(body)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<States>>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }

        })

    }
}