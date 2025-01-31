// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.google.firebase.crashlytics) apply false

}

dependencies {
    // ... autres dépendances ...
    implementation(libs.material.v1100) //ou version plus recente
    // ... autres dépendances ...
}

fun implementation(v1100: Any) {


}


