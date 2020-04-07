package gov.mm.covid19statsbago.activities;

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.github.florent37.runtimepermission.kotlin.askPermission
import gov.mm.covid19statsbago.BuildConfig
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.generals.EXTRA_APK_NAME
import gov.mm.covid19statsbago.generals.EXTRA_DOWNLOAD_LINK
import gov.mm.covid19statsbago.generals.UpdateStatus
import gov.mm.covid19statsbago.jsonparsings.UpdateCheck
import gov.mm.covid19statsbago.util.services.UpdateDownloadService
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.io.File
import kotlin.random.Random

/**
 * Created by mk on 4/1/2020.
 */

class SplashScreen : AppCompatActivity() {

    private val upToDown: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.up_to_down
        )
    }
    private val downToUp: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.down_to_up
        )
    }
    private val rotate: RotateAnimation by lazy {
        RotateAnimation(
            0f,
            720f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 2000
            interpolator = LinearInterpolator()
        }
    }

    private val infoList by lazy {
        arrayOf(
            getString(R.string.splash_label_one),
            getString(R.string.splash_label_two),
            getString(R.string.splash_label_three),
            getString(R.string.splash_label_four)
        )
    }

    private lateinit var updateStatus: UpdateStatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashImage.animation = downToUp
        splashImage.startAnimation(downToUp)

        splashText.apply {
            animation = upToDown
            text = infoList[Random.nextInt(3) + 1]
        }

        UpdateDownloadService.callback = { error, progress ->
            runOnUiThread {
                if (error.isNotEmpty()) {
                    checkUpdate()
                } else {
                    download_progress.progress = progress
                    if (progress == 100) {
                        installApp()
                    }
                }
            }
        }
    }

    private fun askPermission() {
        askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
            loading.visibility = View.GONE
            download_progress.visibility = View.VISIBLE
            tv_download_message.visibility = View.VISIBLE
            val downloadService = Intent(this, UpdateDownloadService::class.java)
            downloadService.apply {
                putExtra(EXTRA_APK_NAME, updateStatus.apkName)
                putExtra(EXTRA_DOWNLOAD_LINK, updateStatus.downloadURL)
            }
            startService(downloadService)
        }.onDeclined {
            finish()
            return@onDeclined
        }
    }

    private fun installApp() {
        val fileApkToInstall =
            File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), updateStatus.apkName)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val apkUri = FileProvider.getUriForFile(
                this,
                BuildConfig.APPLICATION_ID + ".update_provider",
                fileApkToInstall
            )
            val intent = Intent(Intent.ACTION_INSTALL_PACKAGE)
            intent.data = apkUri
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            startActivity(intent)
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(
                Uri.fromFile(fileApkToInstall),
                "application/vnd.android.package-archive"
            )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun getFileUri(filePath: String): Uri {
        return if (Build.VERSION.SDK_INT >= 24) {
            FileProvider.getUriForFile(this, "$packageName.update_provider", File(filePath))
        } else Uri.fromFile(File(filePath))
    }

    private fun checkUpdate() {
        UpdateCheck().checkUpdate(
            success = {
                updateStatus = it
                if (it.version != BuildConfig.VERSION_NAME) {
                    AlertDialog.Builder(this).apply {
                        setMessage("Version အသစ်ထွက်နေပါသည်။​ ကျေးဇူပြု၍ ဒေါင်းလုတ်ဆွဲပေးပါ။")
                        setPositiveButton("Ok") { dialog, _ ->
                            dialog.dismiss()
                            askPermission()
                        }
                        setCancelable(false)
                    }.show()
                } else {
                    startActivity(Intent(this, BottomNavigationActivity::class.java))
                    finish()
                }
            },
            error = {
                AlertDialog.Builder(this).apply {
                    setMessage("Internet error. Please try again.")
                    setPositiveButton("Ok") { dialog, _ ->
                        checkUpdate()
                        dialog.dismiss()
                    }
                    setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                }.show()
            }
        )
    }

    override fun onResume() {
        checkUpdate()
        super.onResume()
    }
}
