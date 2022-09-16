package com.example.restapi.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {
    String str = """
            {
              "id": "Question1",
              "description": "Most Popular Cloud Platform Today",
              "options": [
                "AWS",
                "Azure",
                "Google Cloud",
                "Oracle Cloud"
              ],
              "correctAnswer": "AWS"
            }
            """;

    private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
    private static String GENERIC_QUESTION_URL = "/surveys/Survey1/questions";


    @Autowired
    private TestRestTemplate template;

    @Test
    void retrieveSpecificSurveyQuestion_basicScenario() throws JSONException {
        ResponseEntity<String> responseEntity = template.getForEntity(GENERIC_QUESTION_URL, String.class);
        String expectedResponse = """
                [
                  {
                    "id": "Question1",
                    "description": "Most Popular Cloud Platform Today",
                    "options": [
                      "AWS",
                      "Azure",
                      "Google Cloud",
                      "Oracle Cloud"
                    ],
                    "correctAnswer": "AWS"
                  }
                ]
                """;

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));

        JSONAssert.assertEquals(expectedResponse,responseEntity.getBody(),false);
    }

    @Test
    void addNewQuestion_basicScenario(){
        String body = """
                {
                    "id": "1724951759",
                    "description": "Your favorite language?",
                    "options": [
                      "Java",
                      "Python",
                      "Javascript",
                      "Carbon"
                    ],
                    "correctAnswer": "Java"
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");

        HttpEntity<String> httpEntity = new HttpEntity<>(body,headers);

        ResponseEntity<String> responseEntity = template.exchange(GENERIC_QUESTION_URL, HttpMethod.POST, httpEntity, String.class);

        System.out.println(responseEntity.getHeaders());
        System.out.println(responseEntity.getBody());

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertTrue(responseEntity.getHeaders().get("Location").get(0).contains("/surveys/Survey1/questions/"));

    }
}
