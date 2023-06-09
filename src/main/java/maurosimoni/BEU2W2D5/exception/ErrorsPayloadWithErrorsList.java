package maurosimoni.BEU2W2D5.exception;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ErrorsPayloadWithErrorsList extends ErrorsPayload {
    private List<String> errors;

    public ErrorsPayloadWithErrorsList(String message, Date timestamp, int internalCode, List<String> errors) {
        super(message, timestamp, internalCode);
        this.errors = errors;
    }

}
