LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := hello-jni
LOCAL_SRC_FILES := \
	/Users/onwuneme/projects/Android/UW/JNIExample/AndroidStudio/hello-jni/app/src/main/jni/Android.mk \
	/Users/onwuneme/projects/Android/UW/JNIExample/AndroidStudio/hello-jni/app/src/main/jni/Application.mk \
	/Users/onwuneme/projects/Android/UW/JNIExample/AndroidStudio/hello-jni/app/src/main/jni/hello-jni.c \

LOCAL_C_INCLUDES += /Users/onwuneme/projects/Android/UW/JNIExample/AndroidStudio/hello-jni/app/src/main/jni
LOCAL_C_INCLUDES += /Users/onwuneme/projects/Android/UW/JNIExample/AndroidStudio/hello-jni/app/src/debug/jni

include $(BUILD_SHARED_LIBRARY)
