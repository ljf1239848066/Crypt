//
// Created by ljf12 on 2019/9/17.
//

#ifndef __GEETEST_THEMIS_H__
#define __GEETEST_THEMIS_H__

#include <jni.h>
#include <stdlib.h>

#define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))

//__BEGIN_DECLS

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved);
JNIEXPORT void JNICALL JNI_OnUnload(JavaVM* vm, void* reserved);

//__END_DECLS

#endif //__GEETEST_THEMIS_H__
