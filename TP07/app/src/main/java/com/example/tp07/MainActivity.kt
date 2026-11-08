package com.example.tp07

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp07.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result -> onSignInResult(result) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSignOut.setOnClickListener { signOut() }

        if (FirebaseAuth.getInstance().currentUser != null) {
            showProfile()
        } else if (savedInstanceState == null) {
            startSignIn()
        }
    }

    private fun startSignIn() {
        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            showProfile()
        } else {
            val error = result.idpResponse?.error
            if (error != null) {
                Toast.makeText(this, "Erro ao entrar: ${error.message}", Toast.LENGTH_LONG).show()
            }
            finish()
        }
    }

    private fun showProfile() {
        val user = FirebaseAuth.getInstance().currentUser
        binding.txtEmail.text = user?.email.orEmpty()
    }

    private fun signOut() {
        AuthUI.getInstance().signOut(this).addOnCompleteListener {
            binding.txtEmail.text = ""
            startSignIn()
        }
    }
}