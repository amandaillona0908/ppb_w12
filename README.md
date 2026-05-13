# 🔐 Flo Login

Aplikasi **Login & Register** dengan MVVM Architecture dan Room Database.

## 👨‍🎓 Identitas

| Field | Detail |
|-------|--------|
| **Nama** | Amanda Illona Farrel |
| **NRP** | 5025221056 |
| **Mata Kuliah** | Pemrograman Perangkat Bergerak (C)|

## ✨ Fitur

- 🔑 **Login** → Input username & password dengan validasi
- 📝 **Register** → Daftar akun baru dengan validasi username unik
- 👋 **Welcome Screen** → Halaman selamat datang setelah login berhasil
- 💾 **Room Database** → Penyimpanan user lokal secara persistent
- 🎨 **Modern UI** → Jetpack Compose dengan Material Design 3
- 👁️ **Show/Hide Password** → Toggle visibility password
- ⏳ **Loading State** → Visual feedback saat proses login/register

## 🛠️ Tech Stack

- **Kotlin** → Bahasa pemrograman
- **Jetpack Compose** → UI toolkit modern
- **Room Database** → SQLite abstraction
- **MVVM Architecture** → State management
- **Kotlin Coroutines** → Async programming

## 🚀 Cara Run

1. Buka project di Android Studio
2. Sync Gradle (File → Sync Project with Gradle Files)
3. Klik Run ▶️

## 🧪 Test Login

**Default Account:**
```
Username: admin
Password: 12345
```

**Buat Akun Baru:**
- Klik "Daftar di sini" di Login Screen
- Input username (min 3 karakter), password (min 5 karakter)
- Klik DAFTAR
- Login dengan akun baru kamu

## 💾 Database

User disimpan di SQLite table `users` dengan kolom:
- `id` (auto-increment primary key)
- `username` (unique)
- `password` (plaintext - sesuai instruksi tugas)

Lihat database dengan **App Inspection** → **Database Inspector** → tab `users`.

## 📁 Struktur File

```
data/
  ├── local/
  │   ├── dao/UserDao.kt
  │   ├── database/AppDatabase.kt
  │   └── entity/User.kt
  └── repository/UserRepository.kt

ui/screen/
  ├── LoginScreen.kt
  ├── RegisterScreen.kt
  └── WelcomeScreen.kt

viewmodel/
  ├── LoginViewModel.kt
  └── LoginViewModelFactory.kt

MainActivity.kt
```

## 🎓 Pembelajaran
 
- MVVM Architecture & state management
- Room Database & SQLite
- Jetpack Compose UI
- Form validation & user feedback
- Kotlin Coroutines & async operations
- Repository pattern & dependency injection


<p align="center">
  Dibuat untuk memenuhi tugas <strong>Pemrograman Perangkat Bergerak</strong>
</p>
