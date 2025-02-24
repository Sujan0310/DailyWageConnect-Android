# 🚀 DailyWageConnect-Android  

DailyWageConnect is an Android application designed to connect daily wage workers with job opportunities. Employers can post jobs, and workers can browse and apply efficiently.  

## 📱 Features  
✅ **User Authentication** – Secure login and registration for both workers and employers.  
✅ **Role-Based Navigation** – Redirect users to different dashboards based on their role.  
✅ **Job Posting** – Employers can post job listings with necessary details.  
✅ **Job Listing & Browsing** – Workers can view and apply for available jobs.  
✅ **SQLite Database** – Stores user roles, job postings, and related data.  
✅ **Simple & Intuitive UI** – Designed for ease of use and accessibility.  

## 🛠 Tech Stack  
- **Language:** Java  
- **Database:** SQLite  
- **UI Framework:** Android XML Layouts  
- **IDE:** Android Studio  

## 🚀 Getting Started  

### 1️⃣ Clone the Repository  
```sh
git clone https://github.com/Sujan0310/DailyWageConnect-Android.git  
cd DailyWageConnect-Android  


### 2️⃣ Open in Android Studio  
- Open Android Studio  
- Click on **"Open an Existing Project"**  
- Select the cloned project  

### 3️⃣ Run the App  
- Connect an Android device or use an emulator  
- Click on the **Run (▶️) Button** in Android Studio  

## 🔧 Firebase Setup  
This project uses Firebase services. To enable them, follow these steps:  

1️⃣ Go to [Firebase Console](https://console.firebase.google.com/)  
2️⃣ Select or create a Firebase project  
3️⃣ Navigate to **Project Settings > General**  
4️⃣ Download the `google-services.json` file for your Android app  
5️⃣ Replace the existing **`google-service-dummy.json`** file in the `app/` directory with your actual `google-services.json` file  

⚠️ **Important:** Never share your `google-services.json` file publicly to avoid security risks.  

## 📌 Database Schema (SQLite)  

### **Users Table**  
| Column Name  | Data Type | Description              |  
|-------------|----------|--------------------------|  
| id          | INTEGER (Primary Key) | Unique user ID |  
| email       | TEXT (Unique) | User email |  
| role        | TEXT | User role (worker/employer) |  

### **Jobs Table**  
| Column Name       | Data Type | Description           |  
|------------------|----------|-----------------------|  
| job_id          | INTEGER (Primary Key) | Unique job ID |  
| title          | TEXT | Job title |  
| description    | TEXT | Job description |  
| location      | TEXT | Job location |  
| employer_email | TEXT | Employer's email |  

## 🤝 Contributing  
Contributions are welcome! If you’d like to contribute:  

1. **Fork** the repository  
2. Create a new branch:  
   ```sh
   git checkout -b feature-branch  
   ```  
3. Make your changes and commit:  
   ```sh
   git commit -m "Add new feature"  
   ```  
4. Push the changes:  
   ```sh
   git push origin feature-branch  
   ```  
5. Create a **Pull Request**  

## 🛡 License  
This project is licensed for **personal use only**.  

✅ **Allowed:** Personal use and learning.  
❌ **Prohibited:** Commercial use, replication for profit, or redistribution.  

See the [LICENSE](./LICENSE) file for details.  
