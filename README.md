# AI-Powered Job Recommendation System

A Spring Boot backend prototype for an AI-powered platform that recommends jobs to users based on skills, experience, and interests.

## Stack
- Java 17 + Spring Boot
- MySQL (primary persistence)
- Elasticsearch (search integration-ready)
- Rule-based recommendation baseline (can be replaced by ML model)

## Core Features Implemented
1. **Resume Parsing API**
   - Extracts known skills from resume text.
   - Estimates years of experience from textual patterns.
2. **Skill Matching Algorithm**
   - Computes overlap score between user skills and job-required skills.
3. **Personalized Job Recommendations**
   - Produces ranked recommendations for a user.
   - Adds an interest boost when job title aligns with user interests.

## API Endpoints
### Parse Resume
`POST /api/resume/parse`

Example request:
```json
{
  "resumeText": "Java Spring Boot developer with 4 years experience in SQL and Elasticsearch"
}
```

### Get Recommendations
`GET /api/recommendations/{userId}`

## Run Locally
1. Start MySQL and create DB `job_recommendation`.
2. Optional: start Elasticsearch at `http://localhost:9200`.
3. Configure env vars if needed:
   - `MYSQL_URL`
   - `MYSQL_USER`
   - `MYSQL_PASSWORD`
   - `ELASTICSEARCH_URI`
4. Run:
   ```bash
   mvn spring-boot:run
   ```

## Next Enhancements
- Replace rule-based scoring with a trained ML ranking model.
- Add proper Elasticsearch document indexing/search for jobs.
- Integrate OCR/PDF parsing for real resume files.
- Add authentication and user onboarding workflow.
