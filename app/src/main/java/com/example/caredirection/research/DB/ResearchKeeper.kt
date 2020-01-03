package com.example.caredirection.research.DB

import android.content.Context
import androidx.core.content.edit

class ResearchKeeper (context: Context) {
    private val local = context.getSharedPreferences(KEEPER, Context.MODE_PRIVATE)

    var name: String?
        get() {
            return local.getString(RESEARCH_NAME, null)
        }
        set(value) {
            local.edit {
                putString(RESEARCH_NAME, value)
            }
        }

    var gender: Int?
        get() {
            val candi = local.getInt(RESEARCH_GENDER, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_GENDER, value?:-1)
            }
        }

    var year: String?
        get() {
              return local.getString(RESEARCH_YEAR, null)
        }
        set(value) {
            local.edit {
                putString(RESEARCH_YEAR, value)
            }
        }

    var disease: Set<String>?
        get() {
            return local.getStringSet(RESEARCH_DISEASE, null)
        }
        set(value) {
            local.edit {
                putStringSet(RESEARCH_DISEASE, value)
            }
        }


    var symptom: Set<String>?
        get() {
            return local.getStringSet(RESEARCH_SYMPTOM, null)
        }
        set(value) {
            local.edit {
                putStringSet(RESEARCH_SYMPTOM, value)
            }
        }

    var lifeCycle: Int?
        get() {
            val candi = local.getInt(RESEARCH_CHANGE, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_CHANGE, value?:0)
            }
        }

    var lifeCyclerPage : Int
        get(){
            val candi = local.getInt(RESEARCH_STEP, 0)
            return if (candi == -1) 0 else candi
        }
        set(value){
            local.edit{
                putInt(RESEARCH_STEP, value)
            }
        }

    var alcohol: Int?
        get() {
            val candi = local.getInt(RESEARCH_ALCOHOL, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_ALCOHOL, value?:-1)
            }
        }

    var cigarette: Int?
        get() {
            val candi = local.getInt(RESEARCH_CIGARETTE, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_CIGARETTE, value?:-1)
            }
        }

    var vegetable: Int?
        get() {
            val candi = local.getInt(RESEARCH_VEGETABLE, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_VEGETABLE, value?:-1)
            }
        }

    var exercise: Int?
        get() {
            val candi = local.getInt(RESEARCH_EXERCISE, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_EXERCISE, value?:-1)
            }
        }

    var activity: Int?
        get() {
            val candi = local.getInt(RESEARCH_ACTIVITY, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_ACTIVITY, value?:-1)
            }
        }

    var researchFinish: Int?
        get() {
            val candi = local.getInt(RESEARCH_FINISH, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_FINISH, value ?: 0)
            }
        }

    var careProductAdd: Int?
        get() {
            val candi = local.getInt(RESEARCH_CAREPRODUCTADD, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_CAREPRODUCTADD, value ?: 0)
            }
        }

    var careProductComplete: Int?
        get() {
            val candi = local.getInt(RESEARCH_CAREPRODUCTCOMPLETE, -1)
            return if (candi == -1) null else candi
        }
        set(value) {
            local.edit {
                putInt(RESEARCH_CAREPRODUCTCOMPLETE, value ?: 0)
            }
        }

    companion object {
        private const val KEEPER = "research-keeper"

        private const val RESEARCH_NAME = "1"
        private const val RESEARCH_GENDER = "2"
        private const val RESEARCH_YEAR = "3"
        private const val RESEARCH_DISEASE = "4"
        private const val RESEARCH_SYMPTOM = "5"
        private const val RESEARCH_ALCOHOL = "6"
        private const val RESEARCH_CIGARETTE = "7"
        private const val RESEARCH_VEGETABLE = "8"
        private const val RESEARCH_EXERCISE = "9"
        private const val RESEARCH_ACTIVITY = "10"

        private const val RESEARCH_CHANGE = "11"
        private const val RESEARCH_STEP = "12"

        private const val RESEARCH_FINISH = "13"
        private const val RESEARCH_CAREPRODUCTADD = "14"
        private const val RESEARCH_CAREPRODUCTCOMPLETE = "15"

        const val MALE = 0
        const val FEMALE = 1
    }
}