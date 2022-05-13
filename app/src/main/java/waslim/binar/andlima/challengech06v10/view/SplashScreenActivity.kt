package waslim.binar.andlima.challengech06v10.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.asLiveData
import waslim.binar.andlima.challengech06v10.R
import waslim.binar.andlima.challengech06v10.datastore.DataUserManager

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    lateinit var dataUserManager: DataUserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        dataUserManager = DataUserManager(this)
        check()

    }


//========================= CHECK APAKAH PERNAH LOGIN =============================================//
    private fun check(){
        Handler(Looper.getMainLooper()).postDelayed({
            dataUserManager.boolean.asLiveData().observe(this){
                if (it == true){
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }, 2000)
    }

}