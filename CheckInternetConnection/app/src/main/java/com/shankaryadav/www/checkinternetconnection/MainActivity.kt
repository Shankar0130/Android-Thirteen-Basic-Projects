package com.shankaryadav.www.checkinternetconnection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { checkNetworkConnection() }
    }

    private fun checkNetworkConnection() {
        var wifi = false
        var mydata = false

        val connectivityManager: ConnectivityManager =  getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected){
            wifi = networkInfo.type == ConnectivityManager.TYPE_WIFI
            mydata = networkInfo.type == ConnectivityManager.TYPE_MOBILE

            if (wifi){
                imageview.setImageResource(R.drawable.ic_wifi)
                textview.text = getString(R.string.wifi)
            }else if (mydata){
                imageview.setImageResource(R.drawable.ic_mobile_data)
                textview.text = getString(R.string.mobile_data)
            }else{
                imageview.setImageResource(R.drawable.ic_network)
                textview.text = getString(R.string.no_connection)
            }
        }else{
            Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_SHORT).show()
            imageview.setImageResource(R.drawable.ic_network)
            textview.text = getString(R.string.no_connection)
        }
    }
}
