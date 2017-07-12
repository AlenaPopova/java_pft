package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.jayway.jsonpath.JsonPath.read;


/**
 * Created by popovaa on 11.07.2017.
 */
public class TestBase {


    public boolean isIssueOpen(int issueId) throws IOException {
        String status = getIssueStatus(5);
        if (status.equals("resolved") || status.equals("closed")) {
            return false;
        } else {
            System.out.println("Issue still open");
            return true;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }


    protected String getIssueStatus(int id) throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + id + ".json")).returnContent().asString();
        List<String> status = read(json, "$.issues[*].state_name");
       return status.get(0);

    }


    protected Executor getExecutor() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }
}
