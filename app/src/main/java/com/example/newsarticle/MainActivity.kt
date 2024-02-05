package com.example.newsarticle

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsarticle.databinding.ActivityMainBinding
import com.example.newsarticle.model.getArticleData
import com.example.newsarticle.model.getArticlesResponse
import com.example.newsarticle.view_model.ViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var notificationManager: NotificationManager? = null
    private val NOTIFICATION_REQUEST_CODE = 101

    val viewModel = ViewModel()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get Intent data which was set to notification
        notificationSetUp()


        getArticlesAPI()



    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notificationSetUp() {
        requestPermission(
            Manifest.permission.POST_NOTIFICATIONS,
            NOTIFICATION_REQUEST_CODE)
        notificationManager =
            this!!.getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
            "com.ebookfrenzy.notifydemo.news",
            "NotifyDemo News",
            "Example News Channel")

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getArticlesAPI() {
        viewModel!!.getArticlesList()
            .observe(this) { res: getArticlesResponse ->

                if (res.status.toString().equals("ok", ignoreCase = true)) {

                    if (res.articles!!.size > 0) {
                        var data = res.articles
                        val text1:ArrayList<String> = ArrayList()
                        val text2:ArrayList<String> = ArrayList()
                        val images:ArrayList<String> = ArrayList()
                       for(i in 0..9) {


                           text1.add(data[i].author.toString())
                           text2.add(data[i].title.toString())
                           images.add(data[i].urlToImage.toString())

                       }
                        binding.latestArticlesRecyclerView.visibility= View.VISIBLE
                        binding.latestArticlesRecyclerView.setHasFixedSize(true)
                        binding.latestArticlesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
                        binding.latestArticlesRecyclerView.adapter = LatestArticlesAdapter(this, data)

                        binding.carouselRecyclerview.setHasFixedSize(true)
                        binding.carouselRecyclerview.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                        binding.carouselRecyclerview.adapter =
                            CarouselAdapter(this, images, text1, text2)
                        binding.carouselRecyclerview.apply {
                            set3DItem(true)
                            setAlpha(true)
                            setInfinite(true)
                        }


                        showAllArticles(data)



                    } else {
                        binding.articlesRecyclerView.visibility = View.GONE

                    }


                }
            }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showAllArticles(data: List<getArticleData>) {
//        var listofArticles = ArrayList<getArticleData>()
//        listofArticles = data as ArrayList<getArticleData>
//        listofArticles.sortBy { it.publishedAt}
        binding.articlesRecyclerView.visibility= View.VISIBLE
        binding.articlesRecyclerView.setHasFixedSize(true)
        binding.articlesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.articlesRecyclerView.adapter = ArticlesAdapter(this, data.sortedBy { it.publishedAt })

        sendNotification()
    }



    private fun requestPermission(permissionType: String, requestCode: Int) {
        val permission = ContextCompat.checkSelfPermission(this!!,
            permissionType)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this!!,
                arrayOf(permissionType), requestCode
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            NOTIFICATION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0]
                    != PackageManager.PERMISSION_GRANTED
                ) {

                    Toast.makeText(
                        this,
                        "Notification permission required",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        channel.enableVibration(true)

        notificationManager?.createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification() {

        val notificationID = 101
        val channelID = "com.ebookfrenzy.notifydemo.news"

        try {

            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(this, notification)
            r.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notification = android.app.Notification.Builder(this,
            channelID)
            .setContentTitle("NewsFeed")
            .setContentText("Welcome to our NewsFeed! We're thrilled to have you with us. Feel free to explore and enjoy your stay!")
            .setSmallIcon(R.drawable.logo)
            .setChannelId(channelID)
            .setSound(soundUri)
            .setOngoing(false)
            .build()


        notificationManager?.notify(notificationID, notification)

    }

}