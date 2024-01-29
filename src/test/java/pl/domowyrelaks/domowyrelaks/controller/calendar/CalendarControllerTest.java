package pl.domowyrelaks.domowyrelaks.controller.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarControllerTest {

    @Mock
    private CalendarController calendarController;

    @Test
    public void TestGetCredentialsShouldReturnException() throws IOException {

        // When & Then
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            calendarController.getCredentials(null);
        });

        assertEquals("HTTP_TRANSPORT can't be null", exception.getMessage());
    }

    @Test
    public void TestGetCredentialsShouldNotReturnNull() throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        Credential credentials = calendarController.getCredentials(httpTransport);
        assertNotNull(credentials);
    }
}
