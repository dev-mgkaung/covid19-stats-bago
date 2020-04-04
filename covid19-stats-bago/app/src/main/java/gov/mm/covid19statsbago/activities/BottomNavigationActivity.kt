package gov.mm.covid19statsbago.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import gov.mm.covid19statsbago.R
import mk.myday.kotlinpj.model.Utils

class BottomNavigationActivity : AppCompatActivity() {

    /*
    * 0 is HomeFragment
    * 1 is ReturnPeopleFragment
    * 2 is TreatmentFragment
    */
    var currentFragment: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_returnlist,
                R.id.navigation_treatment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (!Utils(context = this).checkConnection(applicationContext)) {
            showDialog()
        }
    }
    fun showDialog() {
        val dialog = Dialog(this@BottomNavigationActivity)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.no_internet_message_dialog)
        dialog.setCancelable(false)
        val text_title = dialog.findViewById(R.id.pno_message) as TextView
        val button_retry: Button = dialog.findViewById(R.id.pno_ok) as Button
        dialog.show()
        text_title.text = "အင်တာနက်ကွန်နက်ရှင်ချိတ်ရန် လိုအပ်နေပါသည်"
        button_retry.setOnClickListener {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss()
            }
            if (!Utils(context = this).checkConnection(applicationContext)) {
            } else {
                dialog.dismiss()
            }
        }

    }
}
