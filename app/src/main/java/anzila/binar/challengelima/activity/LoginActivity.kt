package anzila.binar.challengelima.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import anzila.binar.challengelima.R
import anzila.binar.challengelima.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var sharedPref : SharedPreferences
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnGoogle.setOnClickListener {
            Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
            signInGoogle()
        }

       binding.btnLogin.setOnClickListener {
            val uname = binding.etUnameLog.text.toString()
            val pass = binding.etPassLog.text.toString()

            if (uname.isNotEmpty() && pass.isNotEmpty()) {
                val rPrefDua = sharedPref.edit()
                rPrefDua.putString("userName", uname)
                rPrefDua.apply()
                firebaseAuth.signInWithEmailAndPassword(uname, pass).addOnCompleteListener {
                   if (it.isSuccessful) {
                       val intent = Intent(this, HomeActivity::class.java)
                       startActivity(intent)
                   } else {
                       Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                   }
               }
           } else {
               Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
           }
            //val dataUname = sharedPref.getString("username", "")
            //val dataPass = sharedPref.getString("password", "")
            //if(uname == dataUname && pass == dataPass) {
             //   startActivity(Intent(this, HomeActivity::class.java))
            //} else {
            //    Toast.makeText(this, "Login Tidak Berhasil", Toast.LENGTH_SHORT).show()
            //}
       }

        binding.txtNoReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            startActivity(
                Intent(
                    this, HomeActivity
                    ::class.java
                )
            )
            finish()
        }
    }
}