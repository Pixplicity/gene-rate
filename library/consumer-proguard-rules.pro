-dontobfuscate
# See https://speakerdeck.com/chalup/proguard
-optimizations !code/allocation/variable

-keep public class * {
    public protected *;
}