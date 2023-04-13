# Squatting attack in Android custom permissions
Experimental code to demonstrate squatting attack of Enhance Calling Definition Security for Android Curtom Permission

# App roles in custom permission scenario
|       App Role        |                   Description                    | Example App Name |
| :-------------------: | :----------------------------------------------: | :--------------: |
| Permission Source App |       The app that defaines a permission.        |      App P       |
| Resource Provider App | The app that declares to provide some resources. |      App R       |
| Resource Consumer App | The app that requests to consume some resources. |      App C       |
|       Evil App        |   The app that was developed by the attacker.    |      App E       |

# Signing key files
- keystore4source.jks: The keystore that uses to sign App P, App R and App C.
- keystore4source.der: The signing certificate of App P, App R and App C.
  sha256(keystore4source.der) = 657d6f7c6295d453f027a8cc4ce528f411d95276cca140f540c53f396df1ceff

- keystore4evil.jks: The keystore that uses to sign App E.
- keystore4evil.der: The signing certificate of App E.
  sha256(keystore4evil.der) =  41b0a8d53cd8d134c226f41c89bbbd275e11cd104147c839427e52c89c248f64

# Squatting attack reproduction process
- App P, App R and App C are signed with keystore4source.jks. App E is signed with keystore4evil.jks.
- Install App P, App R and App C in sequence.
- Run App R first and then run App C. You will find that App C can access the data provided by App R. Here is the expected behavior.
- Before proceeding, ensure that all running Apps have been closed. Uninstalling App P will simulate a scenario where the user has not installed it. If the user installs the evil app App E, and then runs App R followed by App E, the evil app can obtain data provided by App R, which is known as a 'squatting attack'.

# Evaluation result of the squatting attack

| Manufacturer | Device Model  | Operation System | Squatting Attack |
| :----------: | :-----------: | :--------------: | :--------------: |
|    Google    |    Pixel 3    |    Android 12    |        ✔         |
|   Sumsung    | Galaxy S20 5G |    Android 12    |        ✔         |
|    Huawei    |      P40      | HarmonyOS 2.0.0  |        ✔         |
|    Xiaomi    |     Mi 10     |    Android 12    |        ✔         |
|     vivo     |    iQOO 7     |    Android 13    |        ✔         |
|     OPPO     |  Find X5 Pro  |    Android 13    |        ✔         |
