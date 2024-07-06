package com.gatirapp.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gatirapp.myapplication.data.MessageModel
import com.gatirapp.myapplication.htttpservice.config.ApiServices
import com.gatirapp.myapplication.htttpservice.config.createApiServiceInstance
import com.google.android.material.button.MaterialButton
import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var ApiServices :ApiServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        ApiServices = createApiServiceInstance()

        val emailtext = findViewById<EditText>(R.id.email_et)
        val messagetext = findViewById<EditText>(R.id.message_et)

        val btnsubmit = findViewById<MaterialButton>(R.id.btnSubmit)




        btnsubmit.setOnClickListener {
            ApiServices.sendMessage(
                JsonObject().apply {
                    addProperty("Email", emailtext.text.toString())
                    addProperty("text", messagetext.text.toString())
                }
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe()

            AlertDialog.Builder(this)
                .setTitle("وضعیت پیام")
                .setPositiveButton("باشه") { dialog, _ ->
                    dialog.dismiss()
                }
                .setMessage("پیام با موفقیت ارسال شد").show()
        }


    }
}