package br.com.eupedro.desafio_webservice.view


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import br.com.eupedro.desafio_webservice.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val SPLASH_TIME_OUT:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("dhmarvekd3-ic", MODE_PRIVATE)
        initComponents()
        setupObservables()
    }

    private fun initComponents(){
        binding.edRegisterName.setText(sharedPreferences.getString("name", ""))
        binding.edRegisterEmail.setText(sharedPreferences.getString("email", ""))
        binding.edRegisterPwd.setText(sharedPreferences.getString("pwd", ""))
    }

    private fun setupObservables(){
        binding.btRegisterSave.setOnClickListener{
            var valid = true
            val name = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val pwd = binding.edRegisterPwd.text.toString()

            if (name == "") {
                valid = false
                binding.edRegisterName.error = "Nome inválido!"
            }

            if (email == "") {
                valid = false
                binding.edRegisterEmail.error = "E-mail inválido!"
            }

            if (pwd == ""){
                valid = false
                binding.edRegisterPwd.error = "Password inválido!"
            }

            if (valid) {
                sharedPreferences.edit {
                    putString("name",binding.edRegisterName.text.toString())
                    putString("email",binding.edRegisterEmail.text.toString())
                    putString("pwd",binding.edRegisterPwd.text.toString())
                    putBoolean("remember",true)
                }
                Toast.makeText(this, "Cadastro realizado com Sucesso!", Toast.LENGTH_LONG).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }, SPLASH_TIME_OUT)
            }
        }

    }
}