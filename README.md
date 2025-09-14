# BottonNavigationView  
_A simple and customizable Bottom Navigation View for Android_  

[![JitPack](https://jitpack.io/v/AkSoftwareSolution/BottonNavigationView.svg)](https://jitpack.io/#AkSoftwareSolution/BottonNavigationView)  
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)  

---

## ✨ Features
- 🎨 Fully customizable colors (background, circle, icons, selected icons)  
- 🔄 Smooth animation when switching tabs  
- 📱 Easy integration in any Android project  
- ⚡ Lightweight & dependency-free  

---

## 📦 Installation  

### Step 1. Add JitPack repository in your **settings.gradle**  
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
---
### Step 2. Add the dependency in your app/build.gradle
```gradle
dependencies {
    implementation 'com.github.AkSoftwareSolution:BottonNavigationView:1.2.1'
} 
```

## 🚀 Usage

1. Add View in XML

```xml
  <com.aksoftwaresolution.navigationview.BottomNavigationView
        android:id="@+id/bottomNavigation"
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:layout_alignParentBottom="true"
        app:mcn_backgroundColor="#EFC486"
        app:mcn_circleColor="#FFFFFF"
        app:mcn_iconColor="#FF5722"
        app:mcn_selectedIconColor="#FF5722"
        app:menu="@menu/bottom_nav_menu"
        />


```
### 2. Setup in Activity/Fragment
```Activity/Fragment
BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

bottomNav.setOnItemSelectedListener((index, item) -> {
    switch (item.getItemId()) {
        case R.id.home:
            Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
            break;
        case R.id.search:
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
            break;
        case R.id.profile:
            Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
            break;
    }
});
```
### 📜 License
```License
Copyright 2025 Ak Software Solution

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    
