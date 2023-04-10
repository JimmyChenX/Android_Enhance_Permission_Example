# Android Enhance Permission Example
Experiment code for Enhance Calling Definition Security for Android Curtom Permission

# App Roles in Custom Permission Scenario
|       App Role        |                   Description                    | Example App Name |
| :-------------------: | :----------------------------------------------: | :--------------: |
| Permission Source App |       The app that defaines a permission.        |      App P       |
| Resource Provider App | The app that declares to provide some resources. |      App R       |
| Resource Consumer App | The app that requests to consume some resources. |      App C       |
|       Evil App        |    The app that was developed by the atacker.    |      App E       |

# Keystore file
- keystore4source.jks: The keystore that uses to sign App P, App R and App C.
- keystore4evil.jks: The keystore that uses to sign App E.
