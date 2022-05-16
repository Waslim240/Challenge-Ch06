package waslim.binar.andlima.challengech06v10.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech06v10.R
import waslim.binar.andlima.challengech06v10.datastore.DataUserManager
import waslim.binar.andlima.challengech06v10.viewmodel.ViewModelUser

class ProfileActivity : AppCompatActivity() {
    lateinit var dataUserManager: DataUserManager
    lateinit var viewModel : ViewModelUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dataUserManager = DataUserManager(this)

        getDataStore()
        updateDataUser()
        logout()
        backHome()
        selectImage()


    }


// ===================================== GET DATA STORE ===========================================//
    private fun getDataStore(){
        dataUserManager.images.asLiveData().observe(this){
            Glide.with(this).load(it).into(imageViewProfile)
        }

        dataUserManager.urlImage.asLiveData().observe(this){
            masukan_url_image.setText(it)
        }

        dataUserManager.username.asLiveData().observe(this){
            masukan_username_profile.setText(it)
        }

        dataUserManager.fullName.asLiveData().observe(this){
            nama_lengkap_profile.setText(it)
        }

        dataUserManager.dateOfbirth.asLiveData().observe(this){
            tanggal_lahir_profile.setText(it)
        }

        dataUserManager.address.asLiveData().observe(this){
            alamat.setText(it)
        }
    }


// ===================================== SELECT IMAGE =============================================//
    private fun selectImage(){
        imageViewProfile.setOnClickListener {
            pichImageGalery()
        }
    }

    private fun pichImageGalery(){
        val inten = Intent(Intent.ACTION_PICK)
        inten.type = "image/*"
        startActivityForResult(inten, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2000 && resultCode == RESULT_OK){
            imageViewProfile.setImageURI(data?.data)
            GlobalScope.launch {
                dataUserManager.saveImage(data?.data.toString())
            }

        }
    }


//===================================== PROSES UPDATE DATA ========================================//
    private fun updateDataUser(){
        btn_update.setOnClickListener {
            prosesUpdateDataUser()
        }
    }

    private fun prosesUpdateDataUser(){
        var id = ""
        dataUserManager.id.asLiveData().observe(this){
            id = it
        }
        val image = masukan_url_image.text.toString()
        val username = masukan_username_profile.text.toString()
        val fullName = nama_lengkap_profile.text.toString()
        val dateOfBirth = tanggal_lahir_profile.text.toString()
        val address = alamat.text.toString()

        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Update Data")
            .setMessage("Anda Yakin Ingin Update Data ?")

            .setPositiveButton("YA"){ dialogInterface: DialogInterface, i: Int ->
                viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)

                GlobalScope.launch {
                    dataUserManager.saveData(id, username, "", "", fullName, dateOfBirth, address, image)
                }
                Toast.makeText(this, "Update Berhasil", Toast.LENGTH_SHORT).show()
                viewModel.makeApiUpdate(id, address, dateOfBirth, fullName, image, username)
            }

            .setNegativeButton("TIDAK"){ dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(this, "Tidak Jadi Di Update", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }

            .setNeutralButton("NANTI"){ dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(this, "Jangan Lama Mikirnya", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }
            .show()
    }


// ========================================= LOGOUT ===============================================//
    private fun logout(){
        btn_logout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("KONFIRMASI LOGOUT")
                .setMessage("Anda Yakin Ingin Logout ?")

                .setPositiveButton("YA"){ dialogInterface: DialogInterface, i: Int ->
                    GlobalScope.launch {
                        dataUserManager.logout()
                        startActivity(Intent(this@ProfileActivity, SplashScreenActivity::class.java))
                        finish()
                    }

                }
                .setNegativeButton("TIDAK"){ dialogInterface: DialogInterface, i: Int ->
                    Toast.makeText(this, "Tidak Jadi Logout", Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }

                .setNeutralButton("NANTI"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                    Toast.makeText(this, "Jangan Lama Mikirnya", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }


// ========================================= HOME =================================================//
    private fun backHome(){
        backHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}

