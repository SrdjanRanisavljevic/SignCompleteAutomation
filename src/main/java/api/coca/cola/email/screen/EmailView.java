package api.coca.cola.email.screen;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface EmailView {

    public abstract void openEmail() throws IOException, ParseException;

    public abstract void clickActivateAppFromEmail();

    public abstract void openOldEmail() throws IOException, ParseException;


}
