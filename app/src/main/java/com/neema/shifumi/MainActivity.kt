package com.neema.shifumi

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.neema.shifumi.gameclass.Computer
import com.neema.shifumi.gameclass.Human
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "------------------ $TAG OnCreate ------------------ ")
        setContentView(R.layout.activity_main)

        val intent = Intent(this@MainActivity, GameActivity::class.java)

        btn_vs_computer.setOnClickListener {
            Log.d(TAG, "button ${Human::class.java.simpleName} vs ${Computer::class.java.simpleName} ...")
            intent.putExtra("VS_COMPUTER", true)
            startActivity(intent)
        }

        btn_only_computer.setOnClickListener {
            Log.d(TAG, "button ${Computer::class.java.simpleName} vs ${Computer::class.java.simpleName} ...")
            intent.putExtra("VS_COMPUTER", false)
            startActivity(intent)
        }
    }

    private fun showExitDialog() {

        // before inflating the custom alert dialog layout, we will get the current activity viewGroup
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_ask, viewGroup, false)

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(dialogView)

        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // Supprime le background
        val title = dialogView.findViewById<TextView>(R.id.tv_title_dialog)
        val tvVS = dialogView.findViewById<TextView>(R.id.tv_type_party)
        title.text = getString(R.string.exit_game)
        tvVS.text = getString(R.string.exit_game_ask)
        val yep = dialogView.findViewById<Button>(R.id.btn_yep)
        val nope = dialogView.findViewById<Button>(R.id.btn_nope)
        yep.setOnClickListener {
            alertDialog.dismiss()
            finish()
            exitProcess(0)//android.os.Process.killProcess(android.os.Process.myPid())
        }
        nope.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()

    }

    override fun onBackPressed() {
        showExitDialog()
    }
}
