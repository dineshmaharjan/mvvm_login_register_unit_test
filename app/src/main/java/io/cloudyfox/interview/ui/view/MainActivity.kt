package io.cloudyfox.interview.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.cloudyfox.interview.R
import io.cloudyfox.interview.data.db.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(intent!=null){
            var user=intent.getSerializableExtra("user") as User
            usernameTextView.text="Welcome,\n"+user.fullName+"\n"+user.email
        }
    }
}