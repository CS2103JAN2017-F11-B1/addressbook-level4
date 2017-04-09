package seedu.jobs.logic.calendar;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import seedu.jobs.model.calendar.EventCalendar;
import seedu.jobs.model.task.Task;
import seedu.jobs.model.task.UniqueTaskList.IllegalTimeException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public abstract class BasicCommandCalendar {
    
    protected static com.google.api.services.calendar.Calendar service;

    public abstract void execute() throws IOException, IllegalTimeException;	
    
    public String retrieveID (EventCalendar target) throws IOException {
    	String id = "";
    	String pageToken = null;
    	do {
    		Events events = service.events().list("primary").setPageToken(pageToken).execute();
    		List<Event> items = events.getItems();
    		for (Event event : items) {
    			if (event.getSummary().equals(target.getSummary())) {
    				id = event.getId();
    			}
    		}
    		pageToken = events.getNextPageToken();
    	} while (pageToken != null);
    	return id;
    }
}