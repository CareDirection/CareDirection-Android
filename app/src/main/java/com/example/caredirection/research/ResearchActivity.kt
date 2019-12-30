package com.example.caredirection.research

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.caredirection.R
import com.example.caredirection.research.DB.ResearchKeeper
import com.example.caredirection.research.userInfo.ResearchDiseaseActivity
import com.example.caredirection.research.userInfo.ResearchGenderActivity
import com.example.caredirection.research.userInfo.ResearchNameActivity
import com.example.caredirection.research.userInfo.ResearchSymptomActivity

class ResearchActivity : AppCompatActivity() {

    private lateinit var keeper :ResearchKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_product)
        keeper = ResearchKeeper(this)

        val intents = mutableListOf<Intent>()
        keeper.run {
            intents.add(Intent(this@ResearchActivity, ResearchNameActivity::class.java))
            if (name == null) return@run

            intents.add(Intent(this@ResearchActivity, ResearchGenderActivity::class.java))
            if (gender == null || year == null) return@run

            intents.add(Intent(this@ResearchActivity, ResearchDiseaseActivity::class.java))
            if (disease == null) return@run

            intents.add(Intent(this@ResearchActivity, ResearchSymptomActivity::class.java))
            if (symptom == null) return@run
        }

        startActivities(intents.toTypedArray())
        finish()
    }
}