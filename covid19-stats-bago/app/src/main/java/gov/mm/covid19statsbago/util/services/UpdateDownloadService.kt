package gov.mm.covid19statsbago.util.services

import android.app.IntentService
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Environment
import gov.mm.covid19statsbago.generals.EXTRA_APK_NAME
import gov.mm.covid19statsbago.generals.EXTRA_DOWNLOAD_LINK
import gov.mm.covid19statsbago.util.UnsafeOkHttpClient
import okhttp3.Request
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException

/**
 * @author kyawhtut
 * @date 05/04/2020
 */
class UpdateDownloadService : IntentService("UpdateDownloadService") {

    companion object {
        var callback: (String, Int) -> Unit = { _, _ -> }
    }

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            try {
                val file =
                    File(
                        getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                        intent.getStringExtra(EXTRA_APK_NAME) ?: ""
                    )
                if (file.exists()) {
                    file.delete()
                }
                downloadApk(
                    intent.getStringExtra(EXTRA_APK_NAME) ?: "",
                    intent.getStringExtra(EXTRA_DOWNLOAD_LINK) ?: ""
                )
            } catch (e: Exception) {
                e.printStackTrace()
                callback(e.localizedMessage ?: "", 0)
            }
        }
    }

    @Throws(Exception::class)
    private fun downloadApk(apkName: String, downloadURL: String) {
        try {
            val applicationInfo =
                packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        val request = Request.Builder().url(downloadURL).build()
        val client = UnsafeOkHttpClient.getUnsafeOkHttpClient()
            .addNetworkInterceptor {
                val originalResponse = it.proceed(it.request())
                originalResponse.newBuilder()
                    .body(ProgressResponseBody(originalResponse.body!!, object : ProgressInterface {
                        override fun update(bytesRead: Long, contentLength: Long, done: Boolean) {
                            callback("", ((100 * bytesRead) / contentLength).toInt())
                        }
                    })).build()
            }.build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw IOException(String.format("Unexpected code %s", response))
        val downloadFile = File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), apkName)

        val sink = downloadFile.sink().buffer()
        sink.writeAll(response.body!!.source())
        sink.close()
    }
}
