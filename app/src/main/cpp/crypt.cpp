#include "crypt.h"
#include "gtlog.h"

#include "crypt/aes.h"

#include <string.h>

#define JNIREG_CLASS "com/lxzh123/crypt/Native"// 指定要注册的类

int gSdkInt = 0;

void oblk(char m[], unsigned char v[], unsigned long n) {
    unsigned long i;

    LOGD("\n%s", m);

    for (i = 0; i < n; ++i)
    LOGD("%02x", v[i]);
}

void print(char m[], unsigned char *v, unsigned long n) {
    char *out = (char *) malloc(n * 2 + 1);
    memset(out, 0, n * 2 + 1);
    int j = 0;
    for (int i = 0; i < n; ++i) {
        j += sprintf(out + j, "%02x", v[i]);
    }
    LOGD("%s %s len:%d", m, out, j);
    free(out);
}

void print1(char m[], unsigned char *v, unsigned long n) {
    char *out = (char *) malloc(n + 1);
    memset(out, 0, n + 1);
    memcpy(out, v, n);
    LOGD("%s %s len:%d", m, out, n);
    free(out);
}

void char2byte(const char src[], unsigned char **dst) {
    int n = strlen(src);
    *dst = (unsigned char *)malloc(n+1);
    memset(*dst, 0, n+1);
    for (int i = 0, j = 0; i < n; ++i) {
        j += sprintf((char *)((*dst) + j), "%02x", src[i]);
    }
}

void byte2char(unsigned char *src, int n, char **dst) {
    int nn = n / 2;
    *dst = (char *) malloc(nn + 1);
    memset(*dst, 0, nn + 1);
    for (int i = 0, j = 0; i < n; i += 2, j++) {
        int a = src[i]-'0';
        a <<= 4;
        a |= src[i + 1]-'0';
        *((*dst) + j) = a;
    }
}


JNIEXPORT void JNICALL
init(JNIEnv *env, jclass clazz, jstring jvalue, jstring jpassword) {
//    LOGI("[+] init start");
    const char *value = env->GetStringUTFChars(jvalue, NULL);
    const char *password = env->GetStringUTFChars(jpassword, NULL);
    LOGD("[+] init value:%s password:%s", value, password);

    int vlen = strlen(value);
    int klen = strlen(password);
    LOGD("[+] init vlen:%d klen:%d", vlen, klen);

    unsigned char * value1 = NULL;
    char2byte(value, &value1);
    print1("value1  ", value1, vlen*2);

    char * value2 = NULL;
    byte2char(value1, vlen*2, &value2);
    LOGD("value2   %s", value2);


    mbedtls_aes_context ectx;
    mbedtls_aes_context dctx;
    unsigned char iv1[16];
    unsigned char iv2[16];
    unsigned char key1[64];
    unsigned char key2[64];
    unsigned char buf[64];
    unsigned int keybits = 128;
    int mode, ret;

    memset(key1, 0, 64);
    memset(key2, 0, 64);
    memset(iv1, 0, 16);
    memset(iv2, 0, 16);
    memset(buf, 0, 16);

    LOGD("[+] init key1:%p key2:%p", key1, key2);

    for (int i = 0, j = 0; i < vlen; i++) {
        j += sprintf(reinterpret_cast<char *>(buf + j), "%02x", value[i]);
    }
    for (int i = 0, j = 0; i < klen; i++) {
        j += sprintf((char *)(key1 + j), "%02x", password[i]);
    }
    for (int i = 0, j = 0; i < klen; i++) {
        j += sprintf((char *)(key2 + j), "%02x", password[i]);
    }


    print1("input  ", buf, 64);
    print1("key1   ", key1, 32);
    print1("key2   ", key2, 32);


    mbedtls_aes_init(&ectx);
    // 0:MBEDTLS_AES_DECRYPT
    // 1:MBEDTLS_AES_ENCRYPT
    mode = MBEDTLS_AES_ENCRYPT;
    ret = mbedtls_aes_setkey_enc(&ectx, key1, keybits);
    ret = mbedtls_aes_crypt_cbc(&ectx, mode, 16, iv1, buf, buf);
    print1("encrypt", buf, 16);
    print("encrypt", buf, 16);

    mbedtls_aes_init(&dctx);
    mode = MBEDTLS_AES_DECRYPT;
    ret = mbedtls_aes_setkey_dec(&dctx, key2, keybits);
    ret = mbedtls_aes_crypt_cbc(&dctx, mode, 16, iv2, buf, buf);
    print1("decrypt", buf, 16);
    print("decrypt", buf, 16);

    char *deout = NULL;
    byte2char(buf, strlen((char*)buf), &deout);
    LOGD("decrypt %s", deout);

    if (vlen > 0) {
        return;
    }

}

static JNINativeMethod gMethods[] = {
        {"init", "(Ljava/lang/String;Ljava/lang/String;)V", (void *) init}
};

int jniRegisterNativeMethods(JNIEnv *env, const char *className, const JNINativeMethod *gMethods,
                             int numMethods) {
//    LOGI("[+] jniRegisterNativeMethods");
    jclass clazz;
    clazz = env->FindClass(className);
    if (clazz == NULL) {
        return -1;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        LOGE("[-] RegisterNatives failed");
        return -1;
    }
    return 0;
}

void initJni(JNIEnv *env) {
//    LOGI("[+] init");
    jclass VersionClass = env->FindClass("android/os/Build$VERSION");
    jfieldID SDK_INT = env->GetStaticFieldID(VersionClass, "SDK_INT", "I");

    gSdkInt = env->GetStaticIntField(VersionClass, SDK_INT);
//    LOGI("[+] init sdk_int:%d", gSdkInt);

    jniRegisterNativeMethods(env, JNIREG_CLASS, gMethods, NELEM(gMethods));
    env->DeleteLocalRef(VersionClass);
}

JNIEXPORT int JNICALL
JNI_OnLoad(JavaVM
           *vm,
           void *reserved
) {
//    LOGI("[+] JNI_OnLoad");
    JNIEnv *env;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return
                JNI_ERR;
    }
    initJni(env);
    return
            JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL
JNI_OnUnload(JavaVM
             *vm,
             void *reserved
) {
//    LOGI("[+] JNI_OnUnload");
}