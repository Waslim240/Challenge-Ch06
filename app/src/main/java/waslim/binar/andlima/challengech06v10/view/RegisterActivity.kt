package waslim.binar.andlima.challengech06v10.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import waslim.binar.andlima.challengech06v10.R
import waslim.binar.andlima.challengech06v10.datastore.DataUserManager
import waslim.binar.andlima.challengech06v10.model.datauser.DataUserResponseItem
import waslim.binar.andlima.challengech06v10.model.datauser.PostRequest
import waslim.binar.andlima.challengech06v10.network.ApiClient
import waslim.binar.andlima.challengech06v10.viewmodel.ViewModelUser

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register()
        goToLogin()
    }



// ===================================== PROSES REGISTER ===========================================//
    private fun register(){
        btn_Register.setOnClickListener {
            proses()
        }
    }

    private fun proses(){
        val username = masukan_username_Register.text.toString()
        val email = masukan_email_Register.text.toString()
        val password = masukan_password_Register.text.toString()
        val konfPass = masukan_konfpassword_Register.text.toString()

        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)

        viewModel.getLiveRegister().observe(this, Observer {
            when {
                username.isEmpty() -> {
                    Toast.makeText(this, "Username Tidak Boleh Kosong !",
                        Toast.LENGTH_LONG).show()
                }
                email.isEmpty() -> {
                    Toast.makeText(this, "Email Tidak Boleh Kosong !",
                        Toast.LENGTH_LONG).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(this, "Password Tidak Boleh Kosong !",
                        Toast.LENGTH_LONG).show()
                }
                konfPass.isEmpty() -> {
                    Toast.makeText(this, "Konfirmasi Password Tidak Boleh Kosong !",
                        Toast.LENGTH_LONG).show()
                }
                password != konfPass -> {
                    Toast.makeText(this, "Password Dan Konfirmasi Password Harus Sama",
                        Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this, "Registrasi Sukses",
                        Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        })

        viewModel.makeApiRegister(username, email, password)
    }


// ===================================== GO TO LOGIN ==============================================//
    private fun goToLogin(){
        sudah_punya_akun.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


}