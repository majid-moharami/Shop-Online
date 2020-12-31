package com.example.digikala.utillity.workmanager

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.*
import com.example.digikala.R
import com.example.digikala.data.model.poduct.Product
import com.example.digikala.data.repository.ProductRepository
import com.example.digikala.utillity.ImageUtil
import com.example.digikala.utillity.ListType
import com.squareup.picasso.Picasso
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class NewProductNotifyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        fetchFromServer(applicationContext)
        return Result.success()
    }

    private fun fetchFromServer(context: Context){
        var productRepository = ProductRepository.getInstance()
        productRepository.fetchProducts(ListType.POPULAR_PRODUCT, 1)
        var productList = productRepository.popularProductLiveData.value
        if (productList==null || productList.isEmpty()){
            return
        }
        showNotification(context, productList[Random.nextInt(0, 9)])
    }
    private fun showNotification(context: Context, product: Product){
        var bundle = Bundle()
        bundle.putInt("bundle_key_product_id", Integer.parseInt(product.id))
        var pendingIntent : PendingIntent = NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.nav_productDetailFragment)
                .setArguments(bundle)
                .createPendingIntent()

        var notification : Notification? = null
        try {
            notification = NotificationCompat.Builder(context, "digikala_channel_id")
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setContentTitle("محصول جدید")
                    .setContentText(product.name)
                    .setLargeIcon(Picasso.get().load(ImageUtil.getFirstImageUrlOfProduct(product)).get())
                    .setStyle(NotificationCompat.BigPictureStyle()
                            .bigPicture(Picasso.get().load(ImageUtil.getFirstImageUrlOfProduct(product)).get())
                            .bigLargeIcon(null))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
        }catch (e: IOException) {
            e.printStackTrace();
        }

        var notificationManagerCompat = NotificationManagerCompat.from(context)
        if (notification != null) {
            notificationManagerCompat.notify(0, notification)
        }

    }

    companion object{
        private val WORK_TAG_POLL = "productPollWorker"
        fun scheduleNotification(context: Context, repeatingTime: Long , startSchedule : Boolean){

//            if (!startSchedule) {
//                return
//            }

            val constraints : Constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.UNMETERED)
                    .build()

            val periodicWorkRequest: PeriodicWorkRequest =
                    PeriodicWorkRequest
                            .Builder(NewProductNotifyWorker::class.java, repeatingTime, TimeUnit.MINUTES)
                            .setConstraints(constraints)
                            .build()

            WorkManager.getInstance(context)
                    .enqueueUniquePeriodicWork(
                            WORK_TAG_POLL,
                            ExistingPeriodicWorkPolicy.KEEP,
                            periodicWorkRequest)

        }
    }


}