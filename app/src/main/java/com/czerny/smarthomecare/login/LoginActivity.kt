package com.czerny.smarthomecare.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.NavGraphDirections
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.data.User
import com.czerny.smarthomecare.databinding.FragmentLoginBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//class LoginActivity : Fragment() {
class LoginActivity : AppCompatActivity() {
    val viewModel by viewModels<LoginViewModel> { getVmFactory() }

    private lateinit var binding: FragmentLoginBinding

    /**    4. 在登录 Activity 的 onCreate 方法中，获取 FirebaseAuth 对象的共享实例：*/
    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient


    /**   5.  初始化您的 Activity 时，请检查用户当前是否已登录：*/
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        Log.d("knowGoogleSignIn", "currentUser = $currentUser")
        updateUI(currentUser)
        Log.d("knowGoogleSignIn", "updatedCurrentUser = $currentUser")
        Log.d("knowGoogleSignIn", "updateUI(currentUser) = ${updateUI(currentUser)})")



        currentUser?.let {
            moveMainActivity(it) /**-------*/
        }

    }

//    fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_login)


        /**---------1 . 进行 Firebase 身份验证-------------*/
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // [END config_signin]
        /**---------1 . 进行 Firebase 身份验证-------------*/


        /**   4.  在登录 Activity 的 onCreate 方法中，获取 FirebaseAuth 对象的共享实例：*/
        auth = Firebase.auth
//        auth = FirebaseAuth.getInstance()


        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)

        signInButton.setOnClickListener {
            Log.d("knowGoogleSignIn", "auth = $auth")
            Log.d("check_googleSign", "signInButton in Activity is clicked.")
            viewModel.firebaseAuthWithGoogle("a29733547@gmail.com")
            signIn()
        }

        viewModel.firebaseUser.observe(this, Observer {
            Log.d("check_googleSign", "firebaseUser = $it")
            it?.let {
                moveMainActivity(it)
            }
        })


//        binding.button2.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
//        }

//        return binding.root
    }


    private fun moveMainActivity(user: FirebaseUser) {
        Log.d("checkUser", "fun moveMainActivity is used.")

        val intent = Intent(this, MainActivity::class.java)

        val currentUser = User(
            id = user.uid,
//            image = user.photoUrl.toString(),
            name = user.displayName.toString(),
            email = user.email.toString()
        )
        Log.d("checkUser", "currentUser = $currentUser")

        UserManager.user = currentUser
        Log.d("checkUser", "UserManager.user = ${UserManager.user}")

        viewModel.postUser(currentUser)

        /**-------*/
        startActivity(intent)
//        startActivity(Intent(context, MainActivity::class.java))
        overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
        finish()
    }

    /**---------3 . 集成 Google 登录服务后-------------*/
    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("knowGoogleSignIn", "requestCode = $requestCode")

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
//                firebaseAuthWithGoogle(account.idToken!!)
                viewModel.firebaseAuthWithGoogle(account.idToken!!)
                updateUI(viewModel.firebaseUser.value)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
// [END onactivityresult]
    /**---------3 . 集成 Google 登录服务后-------------*/

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }
// [END auth_with_google]

    /**---------2 . 集成 Google 登录服务后-------------*/
// [START signin]
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        Log.d("knowGoogleSignIn", "signInIntent = $signInIntent")
        Log.d("knowGoogleSignIn", "RC_SIGN_IN = $RC_SIGN_IN")
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    /**---------2 . 集成 Google 登录服务后-------------*/

// [END signin]

    private fun updateUI(user: FirebaseUser?) {}

    companion object {
        const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}