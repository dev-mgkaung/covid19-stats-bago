package mk.myday.kotlinpj.model

import android.app.Dialog
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.util.Log
import android.widget.TextView
import gov.mm.covid19statsbago.R


/**
 * Created by Mg Kaung on 4/2/2020.
 */

class Utils(context: Context) {
    var context:Context=context
     fun checkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) {
            true
        } else {
            false
        }
    }

  fun checkVersionCode(context: Context,versionupdatechecker:String?):Boolean
  {
      try {

          val versionUpdated: String ?= versionupdatechecker
          var vcode:Int= versionUpdated!!.toInt()
          Log.i("version code is", versionUpdated)
          var packageInfo: PackageInfo? = null
          try {
              packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
          } catch (e: PackageManager.NameNotFoundException) {
              e.printStackTrace()
          }
          val version_code = packageInfo!!.versionCode
          val version_name = packageInfo.versionName
          Log.i("updated version code", "$version_code  $version_name")
          if (version_code !== vcode) {
              val packageName: String = context.getApplicationContext().getPackageName() //
             return true
          }
      } catch (e: Exception) {
          e.stackTrace
          return false
      }
      return  false
  }

}