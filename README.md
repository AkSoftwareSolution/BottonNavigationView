Step 1. Add the JitPack repository to your build file

dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}


 Step 2. Add the dependency


 	dependencies {
	        implementation 'com.github.AkSoftwareSolution:BottonNavigationView:1.0.0'
	}


 Add to xml file   


 <com.aksoftwaresolution.BottomNavigationView.BottomNavigationView
        android:id="@+id/bottomNavigation"
        android:layout_width="match_parent"
        android:layout_height="80dp"
        app:mcn_backgroundColor="#EFC486"
        app:mcn_circleColor="#fff"
        app:mcn_iconColor="#FF5722"
        android:layout_alignParentBottom="true"
        app:mcn_selectedIconColor="#FF5722"/>
