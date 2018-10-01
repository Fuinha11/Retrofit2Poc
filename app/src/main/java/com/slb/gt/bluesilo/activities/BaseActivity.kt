package com.slb.gt.bluesilo.activities

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import org.androidannotations.annotations.*


@EActivity
abstract class BaseActivity : AppCompatActivity() {

    private lateinit var loadingDialog : ProgressDialog

    @AfterInject
    fun validateAuth() {
//        if (this !is LoginActivity) {
//            if (!authService.isLoggedIn())
//                logout()
//        }
    }

    fun logout() {
//        authService.firebase.signOut()
        val i = Intent(this, LoginActivity_::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    @UiThread
    open fun showLoading(message: String = "Loading...") {
        if (!::loadingDialog.isInitialized) {
            loadingDialog = ProgressDialog(this)
            loadingDialog.setIndeterminate(true)
            loadingDialog.setMessage(message)
            loadingDialog.setTitle("")
        }
        loadingDialog.show()
        loadingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    @UiThread
    open fun hideLoading() {
        if (::loadingDialog.isInitialized)
            loadingDialog.hide()
    }

    @AfterViews
    abstract fun initComponents()
}