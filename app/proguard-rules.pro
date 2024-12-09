# Mantén la clase Login y todos sus métodos
-keep class firebase.app.calculoflechaselecnor.Login { *; }

# Evita ofuscar nombres de métodos críticos en Android (como onCreate, onClick, etc.)
-keepclassmembers class * {
    public void onClick(android.view.View);
    public void onCreate(android.os.Bundle);
}

# Evita advertencias innecesarias
-dontwarn android.support.**
-dontwarn androidx.**

# Optimización estándar
-optimizations !code/simplification/arithmetic
