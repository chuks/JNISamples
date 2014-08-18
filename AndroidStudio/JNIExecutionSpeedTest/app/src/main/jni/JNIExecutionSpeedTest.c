#include <string.h>
#include <jni.h>
#include <time.h>
#include <android/log.h>

JNIEXPORT
jdouble
JNICALL
Java_com_kekwanu_jniexecutionspeedtest_MainActivity_elapsedTimeFromJNI( JNIEnv* env, jobject obj, jdouble billAmount ) {

    clock_t launch = clock();

    double tip   = billAmount * 0.17;
    double total = tip + billAmount;

    clock_t done = clock();
    double diff = (double)((done - launch)) / CLOCKS_PER_SEC;

    __android_log_print(ANDROID_LOG_VERBOSE, "JNIExecutionSpeedTest", "The value of billAmount is %lf", billAmount);
    __android_log_print(ANDROID_LOG_VERBOSE, "JNIExecutionSpeedTest", "The value of diff is %lf", diff);

    return diff;
}