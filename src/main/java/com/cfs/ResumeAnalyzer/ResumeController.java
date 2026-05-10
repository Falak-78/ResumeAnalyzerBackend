package com.cfs.ResumeAnalyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.james.mime4j.dom.Multipart;
import org.apache.tika.Tika;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "*")
public class ResumeController {

    private final ChatClient chatClient;

    private final Tika tika = new Tika();

    public ResumeController(ChatClient chatClient)
    {
        this.chatClient=chatClient;
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyser(@RequestParam("file") MultipartFile file) throws Exception
    {
        //extract text
        String content = tika.parseToString(file.getInputStream());

        String prompt = """
                You are an expert resume reviewer and ATS evaluator.
                
                Analyze this resume deeply and provide professional feedback.
                
                Resume:
                %s
                
                Return ONLY valid JSON.
                
                Do not use markdown.
                Do not write explanations outside JSON.
                
                Expected JSON format:
                
                {
                  "overall_resume_quality_rating": 0,
                  "key_skills": [],
                  "suggested_improvements": []
                }
                
                Rules:
                - Rating must be between 1-10
                - Skills should contain technical skills only
                - Improvements should be personalized
                - Avoid generic suggestions
                """.formatted(content);



        String aiResponse = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

      // return Map.of("analysis",aiResponse);
        aiResponse = aiResponse
                .replace("```json", "")
                .replace("```", "")
                .trim();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(aiResponse, Map.class);
    }

    @PostMapping("/ats-check")
    public Map<String,Object> analyzeATS(@RequestParam("file") MultipartFile file,
                                         @RequestParam("jd") String jobDescription) throws Exception
    {
        String resumeText = tika.parseToString(file.getInputStream());

        String prompt =
           """
           You are an expert ATS analyzer.

           Return ONLY valid JSON.

           Do not use markdown.
           Do not add explanations.
           Do not write ```json.

           Expected format:

           {
             "atsScore": 0,
             "matchedKeywords": [],
             "missingKeywords": [],
             "summary": ""
           }

           Resume:
           %s

           Job Description:
           %s
           """.formatted(resumeText,jobDescription);

        String aiResponse=chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        return Map.of("atsReport",aiResponse);
    }
}
