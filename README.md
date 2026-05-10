📄 AI Resume Analyzer (Backend)

An AI-powered backend application that analyzes resumes, extracts key insights, and evaluates ATS (Applicant Tracking System) compatibility using modern AI and Spring Boot technologies.

🚀 Features.......
📊 Analyze resumes and extract key skills
⭐ Generate resume quality score (1–10)
💡 Provide improvement suggestions
🎯 Perform ATS compatibility check
🔍 Identify matched & missing keywords from job descriptions
📄 Support for PDF/DOC/DOCX files

🛠️ Tech Stack..........
Backend: Java, Spring Boot
AI Integration: Spring AI, Ollama
File Processing: Apache Tika
API: REST APIs
Frontend (Optional): HTML, CSS, JavaScript

📂 Project Structure.........
ResumeAnalyzer/
│── controller/
│   └── ResumeController.java
│── config/
│   └── config.java
│── ResumeAnalyzerApplication.java
│── resources/

⚙️ API Endpoints..............
📌 1. Analyze Resume

POST /api/resume/analyze

Request:

Form-data → file (resume file)

Response:

{
  "analysis": {
    "key_skills": ["Java", "Spring Boot"],
    "overall_resume_quality": 8,
    "suggested_improvements": ["Add projects", "Improve formatting"]
  }
}
📌 2. ATS Compatibility Check

POST /api/resume/ats-check

Request:

Form-data →
file (resume)
jd (job description text)

Response:

{
  "atsScore": 75,
  "matchedKeywords": ["Java", "REST API"],
  "missingKeywords": ["Docker", "AWS"],
  "summary": "Good match but missing cloud skills"
}
⚡ How It Works....................

Upload resume file
Text is extracted using Apache Tika
AI model processes content using Spring AI + Ollama
Structured insights are returned as JSON

🧪 How to Run Locally............
# Clone repo
git clone https://github.com/your-username/ai-resume-analyzer.git

# Navigate to project
cd ai-resume-analyzer

# Run Spring Boot app
mvn spring-boot:run

Server runs at:..........................

👉 http://localhost:8080

📌 Future Improvements
🔐 User authentication (JWT)
☁️ Cloud deployment (AWS / Render)
📊 Dashboard for resume history
🤖 Chat-based resume suggestions
👨‍💻 Author

Abhiraj Kumar

📧 Email: mr10abhiraj@gmail.com
🔗 LinkedIn: https://www.linkedin.com/in/abhiraj17
⭐ Show Your Support

If you like this project, give it a ⭐ on GitHub!
