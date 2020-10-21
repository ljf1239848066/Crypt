package com.lxzh123.crypt;

public class Strings {
    private static final String[] S_S;

    private static final int START = 0;
    private static final int n0 = START;
    private static final int n1 = n0 + 1;
    private static final int n2 = n1 + 1;
    private static final int n3 = n2 + 1;
    private static final int n4 = n3 + 1;
    private static final int n5 = n4 + 1;
    private static final int n6 = n5 + 1;
    private static final int n7 = n6 + 1;
    private static final int n8 = n7 + 1;
    private static final int n9 = n8 + 1;
    private static final int a = n9 + 1;
    private static final int b = a + 1;
    private static final int c = b + 1;
    private static final int d = c + 1;
    private static final int e = d + 1;
    private static final int f = e + 1;
    private static final int g = f + 1;
    private static final int h = g + 1;
    private static final int i = h + 1;
    private static final int j = i + 1;
    private static final int k = j + 1;
    private static final int l = k + 1;
    private static final int m = l + 1;
    private static final int n = m + 1;
    private static final int o = n + 1;
    private static final int p = o + 1;
    private static final int q = p + 1;
    private static final int r = q + 1;
    private static final int s = r + 1;
    private static final int t = s + 1;
    private static final int u = t + 1;
    private static final int v = u + 1;
    private static final int w = v + 1;
    private static final int x = w + 1;
    private static final int y = x + 1;
    private static final int z = y + 1;
    private static final int A = z + 1;
    private static final int B = A + 1;
    private static final int C = B + 1;
    private static final int D = C + 1;
    private static final int E = D + 1;
    private static final int F = E + 1;
    private static final int G = F + 1;
    private static final int H = G + 1;
    private static final int I = H + 1;
    private static final int J = I + 1;
    private static final int K = J + 1;
    private static final int L = K + 1;
    private static final int M = L + 1;
    private static final int N = M + 1;
    private static final int O = N + 1;
    private static final int P = O + 1;
    private static final int Q = P + 1;
    private static final int R = Q + 1;
    private static final int S = R + 1;
    private static final int T = S + 1;
    private static final int U = T + 1;
    private static final int V = U + 1;
    private static final int W = V + 1;
    private static final int X = W + 1;
    private static final int Y = X + 1;
    private static final int Z = Y + 1;
    private static final int TAG = Z + 1;
    private static final int VERSION = TAG + 1;

    private static final int Sensitive = VERSION + 1;

    private static final int ActivityThread = Sensitive + 1;
    private static final int currentActivityThread = ActivityThread + 1;
    private static final int getApplication = currentActivityThread + 1;
    private static final int proc = getApplication + 1;
    private static final int cmdline = proc + 1;
    private static final int word = cmdline + 1;
    private static final int category = word + 1;
    private static final int risk_level = category + 1;
    private static final int parseCat = risk_level + 1;
    private static final int compileReg = parseCat + 1;
    private static final int parseWord = compileReg + 1;
    private static final int parseReg = parseWord + 1;
    private static final int prepareTrieTree = parseReg + 1;

    private static final int preProcessing = prepareTrieTree + 1;
    private static final int postProcessing = preProcessing + 1;
    private static final int splitResult = postProcessing + 1;
    private static final int cleanText = splitResult + 1;
    private static final int normalizeText = cleanText + 1;
    private static final int stripText = normalizeText + 1;

    private static final int searchTask = stripText + 1;
    private static final int search = searchTask + 1;
    private static final int searchAll = search + 1;
    private static final int match = searchAll + 1;

    private static final int password = match + 1;

    private static final int END = password + 1;

    static {
        S_S = new String[END];
        S_S[n0] = "0";
        S_S[n1] = "1";
        S_S[n2] = "2";
        S_S[n3] = "3";
        S_S[n4] = "4";
        S_S[n5] = "5";
        S_S[n6] = "6";
        S_S[n7] = "7";
        S_S[n8] = "8";
        S_S[n9] = "9";
        S_S[a] = "a";
        S_S[b] = "b";
        S_S[c] = "c";
        S_S[d] = "d";
        S_S[e] = "e";
        S_S[f] = "f";
        S_S[g] = "g";
        S_S[h] = "h";
        S_S[i] = "i";
        S_S[j] = "j";
        S_S[k] = "k";
        S_S[l] = "l";
        S_S[m] = "m";
        S_S[n] = "n";
        S_S[o] = "o";
        S_S[p] = "p";
        S_S[q] = "q";
        S_S[r] = "r";
        S_S[s] = "s";
        S_S[t] = "t";
        S_S[u] = "u";
        S_S[v] = "v";
        S_S[w] = "w";
        S_S[x] = "x";
        S_S[y] = "y";
        S_S[z] = "z";
        S_S[A] = "A";
        S_S[B] = "B";
        S_S[C] = "C";
        S_S[D] = "D";
        S_S[E] = "E";
        S_S[F] = "F";
        S_S[G] = "G";
        S_S[H] = "H";
        S_S[I] = "I";
        S_S[J] = "J";
        S_S[K] = "K";
        S_S[L] = "L";
        S_S[M] = "M";
        S_S[N] = "N";
        S_S[O] = "O";
        S_S[P] = "P";
        S_S[Q] = "Q";
        S_S[R] = "R";
        S_S[S] = "S";
        S_S[T] = "T";
        S_S[U] = "U";
        S_S[V] = "V";
        S_S[W] = "W";
        S_S[X] = "X";
        S_S[Y] = "Y";
        S_S[Z] = "Z";
        S_S[TAG] = "Geetest_Themis";
        S_S[VERSION] = "1.0.0";
        S_S[Sensitive] = "Sensitive";
        S_S[ActivityThread] = "android.app.ActivityThread";
        S_S[currentActivityThread] = "currentActivityThread";
        S_S[getApplication] = "getApplication";
        S_S[proc] = "/proc/";
        S_S[cmdline] = "/cmdline";
        S_S[word] = "word";
        S_S[category] = "category";
        S_S[risk_level] = "risk_level";
        S_S[parseCat] = "parseCat";
        S_S[compileReg] = "compileReg";
        S_S[parseWord] = "parseWord";
        S_S[parseReg] = "parseReg";
        S_S[prepareTrieTree] = "prepareTrieTree";

        S_S[preProcessing] = "preProcessing";
        S_S[postProcessing] = "postProcessing";
        S_S[splitResult] = "splitResult";
        S_S[cleanText] = "cleanText";
        S_S[normalizeText] = "normalizeText";
        S_S[stripText] = "stripText";

        S_S[searchTask] = "searchTask";
        S_S[search] = "search";
        S_S[searchAll] = "searchAll";
        S_S[match] = "match";
        S_S[password] = S_S[g] + S_S[e] + S_S[e] + S_S[t] + S_S[e] + S_S[s] + S_S[t] + S_S[n1] + S_S[n2] + S_S[n3] + S_S[n4] + S_S[n5] + S_S[n6];//geetest123456
    }

    public static String TAG() {
        return S_S[TAG];
    }

    public static String Version() {
        return S_S[VERSION];
    }
    public static String SensitiveWords() {
        return S_S[Sensitive] + S_S[W] + S_S[o] + S_S[r] + S_S[d] + S_S[s] +
                "." + S_S[c] + S_S[s] + S_S[v];
    }
    public static String SensitiveRegs() {
        return S_S[Sensitive] + S_S[R] + S_S[e] + S_S[g] + S_S[s] +
                "." + S_S[t] + S_S[x] + S_S[t];
    }
    public static String CatType() {
        return S_S[C] + S_S[a] + S_S[t] + S_S[T] + S_S[y] + S_S[p] + S_S[e] +
                "." + S_S[d] + S_S[a] + S_S[t] + S_S[a];
    }
    public static String password() {
        return S_S[password];
    }
    public static String ActivityThread() {
        return S_S[ActivityThread];
    }
    public static String currentActivityThread() {
        return S_S[currentActivityThread];
    }
    public static String getApplication() {
        return S_S[getApplication];
    }
    public static String proc() {
        return S_S[proc];
    }
    public static String cmdline() {
        return S_S[cmdline];
    }
    public static String word() {
        return S_S[word];
    }
    public static String category() {
        return S_S[category];
    }
    public static String risk_level() {
        return S_S[risk_level];
    }
    public static String parseCat() {
        return S_S[parseCat];
    }
    public static String compileReg() {
        return S_S[compileReg];
    }
    public static String parseWord() {
        return S_S[parseWord];
    }
    public static String parseReg() {
        return S_S[parseReg];
    }
    public static String prepareTrieTree() {
        return S_S[prepareTrieTree];
    }
    public static String preProcessing() {
        return S_S[preProcessing];
    }
    public static String postProcessing() {
        return S_S[postProcessing];
    }
    public static String splitResult() {
        return S_S[splitResult];
    }
    public static String cleanText() {
        return S_S[cleanText];
    }
    public static String normalizeText() {
        return S_S[normalizeText];
    }
    public static String stripText() {
        return S_S[stripText];
    }
    public static String searchTask() {
        return S_S[searchTask];
    }
    public static String search() {
        return S_S[search];
    }
    public static String searchAll() {
        return S_S[searchAll];
    }
    public static String match() {
        return S_S[match];
    }

    public static String getName() {
        return Strings.class.getName();
    }
}
