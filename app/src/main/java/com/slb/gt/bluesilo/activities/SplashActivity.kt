package com.slb.gt.bluesilo.activities

import android.os.Bundle
import com.slb.gt.bluesilo.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity


@EActivity(R.layout.activity_splash)
open class SplashActivity : BaseActivity() {
    override fun initComponents() {
        LoginActivity_.intent(this).start() //redirect to Login activity
    }
}
