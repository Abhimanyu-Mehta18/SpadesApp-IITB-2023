# SpadesApp-IITB-2023
Spades security android application

# 📱 SPADES Android App – Smart Parcel Detection

This repository contains the **Android application** of **SPADES (Smart Parcel And Delivery Entry System)** – a project developed to combat **parcel theft** using a combination of **AI-based object detection**, **face recognition**, and **smart alerts**.

> 🛠️ This app is part of a larger smart home automation system developed for the **Atomquest Innovation Challenge** by Team Mirage.

---

## 🚀 Overview

The **SPADES Android app** serves as the **user interface** for receiving real-time notifications, responding to parcel-related events, and taking quick anti-theft actions. It connects to a Firebase backend, which in turn is updated by an object detection model (YOLOv7) running on Raspberry Pi.

---

## 📦 Key Features

### 🔔 Real-Time Notifications
- Receives alerts when a **parcel is detected** at the doorstep.
- Push notification triggered via **Firebase Realtime Database**.

### 🚨 Abnormality Response Options
If the system detects that the parcel is **missing**, the user is prompted with 3 immediate actions:

1. **Ring Buzzer**  
   Notifies nearby people using a hardware buzzer (controlled via Arduino).

2. **Call Emergency Contact**  
   Call a pre-set contact (like a guard or neighbor).

3. **It Was Me**  
   Confirms the user has collected the parcel to stop further alerts.

### 🔐 Security Integration (Face Recognition Ready)
While not handled within the Android app directly, this app can be extended to work with the face recognition module in the full system.

---

## 🧩 Architecture (App's Role)

```plaintext
[ YOLOv7 on Raspberry Pi ]
           ↓
[ Firebase Database ]
           ↓
[ Android App ]
     - Notification display and abnormality response
