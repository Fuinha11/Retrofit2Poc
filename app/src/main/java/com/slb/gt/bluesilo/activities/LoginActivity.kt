package com.slb.gt.bluesilo.activities

import com.slb.gt.bluesilo.R
import com.slb.gt.bluesilo.services.api.ApiService
import com.slb.gt.bluesilo.services.api.AuthCheckCallback
import com.slb.gt.bluesilo.services.api.data.response.WikiResponse
import com.slb.gt.bluesilo.ui.EnterKeyListener
import com.slb.gt.bluesilo.utils.extensions.hideKeyboard
import com.slb.gt.bluesilo.utils.extensions.snackbar
import com.slb.gt.bluesilo.utils.interfaces.SuccessFailCallback
import kotlinx.android.synthetic.main.activity_login.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_login)
open class LoginActivity : BaseActivity() {

    @Bean
    lateinit var apiService: ApiService

    @AfterViews
    override fun initComponents(){
        emailTF.setOnKeyListener(EnterKeyListener {passwordTF.requestFocus()})
        passwordTF.setOnKeyListener(EnterKeyListener {loginWithEmail()})
    }

    @Click(R.id.loginBtn)
    open fun loginWithEmail(){
        hideKeyboard()
        //login and set apiService token
    }

    @Click(R.id.registerBtn)
    open fun register(){
        hideKeyboard()
        apiService.beginSearch("Elvis", object : AuthCheckCallback<WikiResponse.Result>(this) {
            override fun onFailure(data: Exception) {
                snackbar(data.localizedMessage)
            }

            override fun onSuccess(data: WikiResponse.Result) {
                snackbar("found elvis: ${data.query.searchinfo.totalhits}")
            }

        })
    }
}
