# Squatting attack in Android custom permissions
Experimental code to demonstrate squatting attack of Enhance Calling Definition Security for Android Curtom Permission

# App roles in custom permission scenario
|       App Role        |                   Description                    | Example App Name |
| :-------------------: | :----------------------------------------------: | :--------------: |
| Permission Source App |       The app that defaines a permission.        |      App P       |
| Resource Provider App | The app that declares to provide some resources. |      App R       |
| Resource Consumer App | The app that requests to consume some resources. |      App C       |
|       Evil App        |   The app that was developed by the attacker.    |      App E       |

# Keystore file
- keystore4source.jks: The keystore that uses to sign App P, App R and App C.
- keystore4evil.jks: The keystore that uses to sign App E.

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
