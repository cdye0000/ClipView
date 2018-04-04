# ClipView

 已发布，可直接依赖
 
 
    allprojects {
		repositories {
	 		...
			maven { url 'https://jitpack.io' }
	  	}
	 }

	  dependencies {
	    	        compile 'com.github.cdye0000:ClipView:1.0'
    	}


     RoundImageView：
      app:radius 圆角半径


     ClipImageView
      app:radius_left_top      左上角圆角半径
      app:radius_right_top     右上角圆角半径
      app:radius_left_bottom   左下角圆角半径
      app:radius_right_bottom  右下角圆角半径
     

 
    <com.cdye.clipview.ClipImageView
        app:radius_left_top="10dp"
        app:radius_right_top="10dp"
        android:src="@drawable/pic_2"
        android:layout_marginTop="50dp"
        android:layout_width="200dp"
        android:layout_height="100dp" />
        
    <com.cdye.clipview.RoundImageView
        app:radius="10dp"
        android:src="@drawable/pic_1"
        android:layout_marginTop="50dp"
        android:layout_width="200dp"
        android:layout_height="100dp" />
