package br.com.eupedro.desafio_webservice

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.eupedro.desafio_webservice.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservables()
    }

    private fun setupObservables() {
        with(binding) {
            btnLoginRegistrar.setOnClickListener{
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

            btnLoginLogin.setOnClickListener {
                val checkResult = tfLoginEmail.validateTextInputLayout(R.string.email)
                    .and(tfLoginSenha.validateTextInputLayout(R.string.senha))

                if(checkResult) {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    @SuppressLint("StringFormatInvalid")
    fun TextInputLayout.validateTextInputLayout(field: Int) : Boolean {
        val value = this.editText?.text.toString()
        if(value.isBlank()) {
            this.error = this.context.getString(R.string.required,
                this.context.getString(field))
            return false
        }
        this.isErrorEnabled = false
        return true
    }
}