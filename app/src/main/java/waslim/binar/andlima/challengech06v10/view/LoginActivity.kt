package waslim.binar.andlima.challengech06v10.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech06v10.R
import waslim.binar.andlima.challengech06v10.datastore.DataUserManager
import waslim.binar.andlima.challengech06v10.model.datauser.DataUserResponseItem
import waslim.binar.andlima.challengech06v10.viewmodel.ViewModelUser

class LoginActivity : AppCompatActivity() {

    lateinit var dataUserManager: DataUserManager
    lateinit var viewModel : ViewModelUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dataUserManager = DataUserManager(this)

        goToRegister()
        login()

    }


// ===================================== PROSES LOGIN =============================================//
    private fun login(){
        btn_login.setOnClickListener {
            getApiViewModel()
        }
    }

    private fun getApiViewModel(){
        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveLogin().observe(this, Observer {
            if(it != null){
                proses(it)
            }
        })

        viewModel.makeApiLogin()
    }


    private fun proses(dataUser : List<DataUserResponseItem>){
        val email = masukan_email_login.text.toString()
        val password = masukan_password_login.text.toString()

        when {
            email.isEmpty() -> {
                Toast.makeText(this, "Email Harus Di Isi", Toast.LENGTH_SHORT).show()
            }
            password.isEmpty() -> {
                Toast.makeText(this, "Password Harus Di Isi", Toast.LENGTH_SHORT).show()
            }
            else -> {
                for (i in dataUser.indices){
                    when {
                        email == dataUser[i].email && password == dataUser[i].password -> {
                            GlobalScope.launch {
                                dataUserManager.checkData(true)
                                dataUserManager.saveData(
                                    dataUser[i].id,
                                    dataUser[i].username,
                                    dataUser[i].email,
                                    dataUser[i].password,
                                    dataUser[i].fullName,
                                    dataUser[i].dateOfBirth,
                                    dataUser[i].address,
                                    dataUser[i].image
                                )
                            }
                            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }

    }


// ======================================== GO TO REGISTER ========================================//
    private fun goToRegister(){
        belum_punya_akun.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

}