ষ

# BottonNavigationView (Custom Navigation View)

A simple and customizable Bottom Navigation View for Android.

## 🚀 Installation

### Step 1. Add the JitPack repository to your **settings.gradle**
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
 Step 2. Add the dependency in your app/build.gradle
dependencies {
    implementation 'com.github.AkSoftwareSolution:BottonNavigationView:1.0.0'
}
📌 Usage
Add the view to your XML layout:

<com.aksoftwaresolution.BottomNavigationView.BottomNavigationView
    android:id="@+id/bottomNavigation"
    android:layout_width="match_parent"
    android:layout_height="80dp"
    android:layout_alignParentBottom="true"
    app:mcn_backgroundColor="#EFC486"
    app:mcn_circleColor="#FFFFFF"
    app:mcn_iconColor="#FF5722"
    app:mcn_selectedIconColor="#FF5722" />
