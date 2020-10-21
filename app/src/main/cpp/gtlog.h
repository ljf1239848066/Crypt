/**
 * description 公共日志模块
 * author      Created by lxzh
 * date        2020/10/19
 */
#ifndef __GEETEST_THEMIS_LOG_H__
#define __GEETEST_THEMIS_LOG_H__

#include <jni.h>
#include <android/log.h>
#include <string.h>


#define TAG "Geetest_Themis"
#define LOG_LEVEL ANDROID_LOG_VERBOSE

#define LOGV(FORMAT, ...) while(1){if(LOG_LEVEL<=ANDROID_LOG_VERBOSE){__android_log_print(ANDROID_LOG_VERBOSE, TAG, FORMAT, ##__VA_ARGS__);}break;};
#define LOGD(FORMAT, ...) while(1){if(LOG_LEVEL<=ANDROID_LOG_DEBUG){__android_log_print(ANDROID_LOG_DEBUG, TAG, FORMAT, ##__VA_ARGS__);}break;};
#define LOGI(FORMAT, ...) while(1){if(LOG_LEVEL<=ANDROID_LOG_INFO){__android_log_print(ANDROID_LOG_INFO, TAG, FORMAT, ##__VA_ARGS__);}break;};
#define LOGW(FORMAT, ...) while(1){if(LOG_LEVEL<=ANDROID_LOG_WARN){__android_log_print(ANDROID_LOG_WARN, TAG, FORMAT, ##__VA_ARGS__);}break;};
#define LOGE(FORMAT, ...) while(1){if(LOG_LEVEL<=ANDROID_LOG_ERROR){__android_log_print(ANDROID_LOG_ERROR, TAG, FORMAT, ##__VA_ARGS__);}break;};

void log(int level, const char *tag, const char *format, ...);
void dlog(int level, const char *tag, const char *format, ...);
void plog(int level, const char *tag, const char *format, ...);

//static int log_level = ANDROID_LOG_VERBOSE;
//static char log_tag[100] = "Geetest_Themis";
//static bool debug = true;

void setEnable(bool enable);

void initLog(int level, const char* tag, int len, bool enableDebug);

void openPrint();

void logv(const char *format, ...);
void logd(const char *format, ...);
void logi(const char *format, ...);
void logw(const char *format, ...);
void loge(const char *format, ...);
//
//void logv(const char *format, ...);
//void logd(const char *format, ...);
//void logi(const char *format, ...);
//void logw(const char *format, ...);
//void logd(const char *format, ...);

void errorCatch(JNIEnv *env);

#endif //__GEETEST_THEMIS_LOG_H__
