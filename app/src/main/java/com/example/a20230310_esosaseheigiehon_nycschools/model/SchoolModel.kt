package com.example.a20230310_esosaseheigiehon_nycschools.model

import android.net.Uri
import java.util.*

class SchoolModel (
    val school: HighSchool?= null,
     val satScores: SATScore?= null

){

    val dbn: String? = school?.dbn
    val schoolName: String = "${school?.schoolName} (${school?.dbn})"
    val detailsSummary: String get(){
        val gradesRange = school?.finalgrades?.split("-")
        return if (gradesRange?.size == 2){
            "${gradesRange[0]} to ${gradesRange[1]}"
        } else {
            school?.finalgrades ?: ""
        }
    }
    val addressString: String get() = "${school?.primaryAddressLine1}, ${school?.city}, ${school?.stateCode}, ${school?.zip}"
    val overviewParagraph = school?.overviewParagraph
    val website = school?.website

    val webSiteUrl: Uri
    get() = if (website?.lowercase(Locale.ROOT)?.startsWith("http")== true){
        Uri.parse(website)
    } else{
        Uri.parse("http://$website")
    }
    val email = school?.schoolEmail
    val emailUrl : Uri? get() = email?.let {Uri.parse("mailto:$it") }
    val phone: String get() = school?.phoneNumber ?: " "
    val phoneURL: Uri get() = phone.let { Uri.parse("tel:$it")}

}