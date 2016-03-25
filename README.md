# PW-Android
The Android app for the awsomagical PW

- Available on the Google Play Store: https://play.google.com/store/apps/details?id=io.pwapp.pw19.
- Always online at: http://pwapp.io.

## Credits

- Based on https://github.com/simontabor/pw and outputs the same passwords.

## What is it?

Create unique theft proof passwords for each service that you use. Never store them but easily create them when you need it.

Simply enter a service and then a password. PW will then hash it using SHA1 and now you have super strong and unique password for that site.

## How does it work?

PW takes the two inputs Service (the service is always converted to lowercase) and Password and runs it through a SHA1 hash with four pipes thrown in to make it a little interesting:

SHA1(“Service” + “||” + “Password” + “||”)

For example with the service “facebook” and the password “hackference” it would be:

SHA1(facebook||hackference||)

and output:

762b679fA17b10D6Cc2d2194542d2235738b3e33

## Further Links

- iOS App -> https://github.com/hughhopkins/PW-iOS
- OS X App -> https://github.com/hughhopkins/PW-OSX
- Website -> https://github.com/hughhopkins/PW-Site