# Privacy Policy

**Last updated:** September 8, 2026

## 1. Introduction

This Privacy Policy explains how this Password Manager application (referred to here as "the Application") handles your data. The short version is simple: the Application does not collect, transmit, or share any of your information. Everything you store stays on your own computer, and nobody but you has access to it.

We wrote this policy in plain language on purpose. If any part of it feels unclear, feel free to open an issue on the project's GitHub repository at [Charlzk Password Manager](https://github.com/Charliezkie-cloud/charlzk-password-manager).

## 2. What Data the Application Collects

None. This is a fully offline, local-first application. It does not connect to the internet, does not phone home, does not use analytics, and does not send crash reports anywhere. There are no servers involved in how this software operates, because there is nothing to connect to in the first place.

Whatever passwords, notes, or account details you save are entered by you and stored by you, on your own device, and nowhere else.

## 3. How Your Data is Stored

The Application uses SQLite as its local database and SQLCipher to encrypt that database file. In practice, this means your vault is saved as a single encrypted file on your hard drive. Without your master password, that file is unreadable, even to someone who has direct access to the file itself.

The database file typically lives in a local application data folder on your operating system. No copies are made outside that folder, and no background process uploads it anywhere.

## 4. Your Master Password

Your master password is the key that unlocks your encrypted vault. It is never stored in plain text, and it is never saved anywhere by the Application in a way that could be reversed or recovered.

This leads to something important that I want to be upfront about: **if you forget your master password, there is no way to recover it, and there is no way to recover the data inside your vault.** This is not a limitation we plan to fix later. It is a direct consequence of how real encryption works. If a password manager could recover your master password for you, that would mean the password itself is not actually protecting your data, which would defeat the entire purpose of the tool.

Because of this, please treat your master password with real care. Write it down somewhere safe if you need to, use a passphrase you can remember, or use whatever backup method you trust, but understand that once it is lost, it is lost for good.

## 5. Third-Party Sharing

There is no third-party sharing to speak of, since there is no third party involved at all. The Application does not use external APIs, cloud services, advertising networks, or telemetry libraries of any kind. Nothing about your usage of this software is visible to us, to Anthropic, to Charles Henry M. Tinoy Jr., or to anyone else.

## 6. Security Measures

A few things are worth knowing about how your data is protected:

- Your vault is encrypted at rest using SQLCipher, so the file on disk is not readable without the correct master password.
- The application does not log your plaintext passwords to any log file.
- No network sockets are opened by the application during normal use, so there is no exposure to network-based attacks through the app itself.

That said, encryption protects your data from someone who gets hold of the file. It cannot protect you from things outside the application's control, such as malware already running on your computer, a compromised operating system, or someone who already knows your master password. Keeping your own device secure is still your responsibility.

## 7. Open Source Transparency

This project is open source and released under the MIT License. The full source code is publicly available at [Charlzk Password Manager](https://github.com/Charliezkie-cloud/charlzk-password-manager), so you, or anyone else, can inspect exactly what the Application does with your data. You do not have to take our word for any of the claims in this policy. You can read the code yourself, or ask someone you trust to review it for you.

## 8. Backups Are Your Responsibility

Since everything is stored locally and there is no cloud sync, there is also no automatic backup of your vault. If your computer's hard drive fails, or the vault file is accidentally deleted, that data cannot be recovered by us, because we never had a copy in the first place. We recommend backing up your encrypted vault file periodically to a location you trust, such as an external drive.

## 9. Changes to This Policy

If this policy is ever updated, the changes will be reflected in this document and noted in the project's version history on GitHub. Since the Application itself has no way to reach you directly, checking the repository is the only way to know if something has changed.

## 10. License

This project is distributed under the MIT License. You are free to use, modify, and distribute the software, provided that the original license and copyright notice are included. As with most open source software distributed under MIT, it is provided "as is," without warranty of any kind.

## 11. Contact

If you have questions about this policy or the project in general, the best place to reach out is through the GitHub repository at [Charlzk Password Manager](https://github.com/Charliezkie-cloud/charlzk-password-manager), either by opening an issue or checking the contact details listed there.