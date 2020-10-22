#include "crypt.h"
#include "gtlog.h"

#include "crypt/aes.h"

#include "crypt/Rijndael.h"

#include <string.h>
#include <cstring>

#define JNIREG_CLASS "com/lxzh123/crypt/Native"// 指定要注册的类

//unsigned char ascii[]={
//        0x00,0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,0x0a,0x0b,0x0c,0x0d,0x0e,0x0f,
//        0x10,0x11,0x12,0x13,0x14,0x15,0x16,0x17,0x18,0x19,0x1a,0x1b,0x1c,0x1d,0x1e,0x1f,
//};
unsigned char ascii[] ={
     0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
     0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
     0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
     0x00,0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,0x00,0x00,0x00,0x00,0x00,0x00,
     0x0a,0x0b,0x0c,0x0f,0x0e,0x0f,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
     0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
     0x0a,0x0b,0x0c,0x0f,0x0e,0x0f,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
     0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00
};

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

void char2byte(unsigned char *src, int n, unsigned char **dst) {
    *dst = (unsigned char *) malloc(n + 1);
    memset(*dst, 0, n + 1);
    for (int i = 0, j = 0; i < n; ++i) {
        j += sprintf((char *) ((*dst) + j), "%02x", src[i]);
    }
}

void byte2char(unsigned char *src, int n, char **dst) {
    int nn = n / 2;
    *dst = (char *) malloc(nn + 1);
    memset(*dst, 0, nn + 1);
    for (int i = 0, j = 0; i < n; i += 2, j++) {
        *((*dst) + j) = (ascii[src[i]]<<4)|ascii[src[i + 1]];
    }
}

int pkcs5padding(unsigned char *src, int len, int bsize, unsigned char **dst) {
    int padding = bsize - len % bsize;
    int nn = len + padding;
    *dst = (unsigned char *) malloc(nn + 1);
    memset(*dst, padding, nn);
    *((*dst) + nn) = 0;
    memcpy(*dst, src, len);
    return nn;
}

int pkcs5unpadding(unsigned char *src, int len, int bsize, unsigned char **dst) {
    int padding = *(src + len - 1);
    int nn = len - padding;
    *dst = (unsigned char *) malloc(nn + 1);
    memcpy(*dst, src, len);
    *((*dst) + nn) = 0;
    return nn;
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

    std::string sval =value;
    std::string skey = password;
    std::string ba = encryt(sval, skey);
    std::string o = decryt(ba, skey);
    LOGD("[+] init ba:%s o:%s", ba.c_str(), o.c_str());


    unsigned char * value1 = NULL;
    char2byte((unsigned char *)value, vlen, &value1);
    print1("value1  ", value1, vlen*2);

    char * value2 = NULL;
    byte2char(value1, vlen*2, &value2);
    LOGD("value2   %s", value2);

    unsigned char *pvalue = NULL;
    int pvlen = pkcs5padding((unsigned char *)value, vlen, 16, &pvalue);
    LOGD("pvlen    %d", pvlen);
//    print1("pvalue  ", pvalue, pvlen);

    mbedtls_aes_context ectx;
    mbedtls_aes_context dctx;
    unsigned char iv1[16];
    unsigned char iv2[16];
    unsigned char key1[17];
    unsigned char key2[17];
    unsigned char buf[64];
    unsigned int keybits = 128;
    int mode, ret;

    memset(key1, 0, 17);
    memset(key2, 0, 17);
    memset(iv1, 0, 16);
    memset(iv2, 0, 16);
    memset(buf, 0, 32);

    LOGD("[+] init key1:%p key2:%p", key1, key2);

//    for (int i = 0, j = 0; i < pvlen; i++) {
//        j += sprintf(reinterpret_cast<char *>(buf + j), "%02x", pvalue[i]);
//    }
//    for (int i = 0, j = 0; i < klen; i++) {
//        j += sprintf((char *)(key1 + j), "%02x", password[i]);
//    }
//    for (int i = 0, j = 0; i < klen; i++) {
//        j += sprintf((char *)(key2 + j), "%02x", password[i]);
//    }

//    memcpy(buf, value, vlen);
//    memcpy(key1, password, vlen);

    sprintf((char *)buf, "%s", pvalue);
    sprintf((char *)key1, "%s", password);
    sprintf((char *)key2, "%s", password);


    print("input  ", buf, pvlen);
    print("key1   ", key1, 16);
    print("key2   ", key2, 16);


    mbedtls_aes_init(&ectx);
    // 0:MBEDTLS_AES_DECRYPT
    // 1:MBEDTLS_AES_ENCRYPT
    mode = MBEDTLS_AES_ENCRYPT;
    ret = mbedtls_aes_setkey_enc(&ectx, key1, keybits);
    ret = mbedtls_aes_crypt_cbc(&ectx, mode, 16, iv1, buf, buf);
    print1("encrypt", buf, 32);
    print("encrypt", buf, 16);

    mbedtls_aes_init(&dctx);
    mode = MBEDTLS_AES_DECRYPT;
    ret = mbedtls_aes_setkey_dec(&dctx, key2, keybits);
    ret = mbedtls_aes_crypt_cbc(&dctx, mode, 16, iv2, buf, buf);
//    print1("decrypt", buf, 32);
    print("decrypt", buf, 16);

    unsigned char *dvalue = NULL;
    int dvlen = pkcs5unpadding((unsigned char *)buf, 16, 16, &dvalue);
    LOGD("dvlen    %d", dvlen);
    LOGD("dvalue   %s", dvalue);

//    char *deout = NULL;
//    byte2char(buf, strlen((char*)buf), &deout);
//    LOGD("decrypt %s", deout);

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